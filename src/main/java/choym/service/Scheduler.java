package choym.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.action.URIAction;
import com.linecorp.bot.model.message.ImageMessage;
import com.linecorp.bot.model.message.Message;
import com.linecorp.bot.model.message.TemplateMessage;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.model.message.VideoMessage;
import com.linecorp.bot.model.message.template.ImageCarouselColumn;
import com.linecorp.bot.model.message.template.ImageCarouselTemplate;

import choym.model.Follow;
import choym.model.instagram.IGCarouselMedia;
import choym.model.instagram.IGItem;
import choym.model.instagram.IGMedia;
import choym.util.IGUtils;

/**
 * A scheduler that push IG user's new media to followers
 * 
 * @author choYM
 * @since 2017-10-27
 *
 */
@Component
@Configurable
@EnableScheduling
public class Scheduler {
	private static final Logger logger = LoggerFactory.getLogger(Scheduler.class);

	@Value("${instagram.accesstoken}")
	private String instagramAccessToken;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private LineMessagingClient lineMessagingClient;

	@Scheduled(cron = "0 */5 *  * * * ")
	@Transactional
	public void doWork() throws ClientProtocolException, IOException {
		logger.info("Start scheduler... " + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));

		// query data from ig_follow_list table
		List<Follow> list = this.jdbcTemplate.query(
				"SELECT b.serial_id, b.line_id, b.ig_id, b.ig_username, b.media_code FROM line_user a INNER JOIN follow b ON a.id = b.line_id WHERE a.active = true",
				new BeanPropertyRowMapper<Follow>(Follow.class));

		for (Follow follow : list) {
			String ig_id = follow.getIgId();

			// get ig user's media
			IGMedia media = IGUtils.getMedia(ig_id, this.instagramAccessToken);
			if (media.getItems() != null && !media.getItems().isEmpty()) {
				IGItem item = media.getItems().get(0);

				String mediaCode = item.getMediaCode();
				String username = item.getUser().getUserName();
				if (mediaCode.equals(follow.getMediaCode()) && username.equals(follow.getIgUsername())) {
					continue;
				}

				// save the latest media code & username to database
				this.jdbcTemplate.update("UPDATE follow SET media_code = ?, ig_username = ? WHERE serial_id = ?", new Object[] { mediaCode, username, follow.getSerialId() });

				// push message
				List<Message> messages = new ArrayList<Message>();
				messages.add(new TextMessage("[" + username + "] posts a new " + item.getType()));
				switch (item.getType()) {
				case "video":
					String previewImageUrl = item.getImages().getStandardResolution().getUrl();
					String originalContentUrl = item.getVideos().getStandardResolution().getUrl();
					messages.add(new VideoMessage(originalContentUrl, previewImageUrl));
					break;
				case "image":
					String url = item.getImages().getStandardResolution().getUrl();
					messages.add(new ImageMessage(url, url));
					break;
				case "carousel":
					messages.add(new TextMessage("https://www.instagram.com/p/" + mediaCode));

					// handle image carousel
					List<ImageCarouselColumn> columns = new ArrayList<ImageCarouselColumn>();
					for (IGCarouselMedia carouselMedia : item.getCarouselMedia()) {
						boolean isImage = "image".equals(carouselMedia.getType());
						String uri = isImage ? carouselMedia.getImages().getStandardResolution().getUrl()
								: carouselMedia.getVideos().getStandardResolution().getUrl();
						columns.add(new ImageCarouselColumn(uri,
								new URIAction("view " + (isImage ? "image" : "video"), uri)));
					}
					messages.add(new TemplateMessage("new carousel", new ImageCarouselTemplate(columns)));
					break;
				default:
					break;
				}
				this.lineMessagingClient.pushMessage(new PushMessage(follow.getLineId(), messages));
			}
		}

		logger.info("End scheduler... " + new SimpleDateFormat("yyyy.MM.dd HH:mm:ss").format(new Date()));
	}
}
