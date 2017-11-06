package choym.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.linecorp.bot.client.LineMessagingClient;
import com.linecorp.bot.model.PushMessage;
import com.linecorp.bot.model.event.Event;
import com.linecorp.bot.model.event.FollowEvent;
import com.linecorp.bot.model.event.JoinEvent;
import com.linecorp.bot.model.event.LeaveEvent;
import com.linecorp.bot.model.event.MessageEvent;
import com.linecorp.bot.model.event.PostbackEvent;
import com.linecorp.bot.model.event.UnfollowEvent;
import com.linecorp.bot.model.event.message.TextMessageContent;
import com.linecorp.bot.model.message.TextMessage;
import com.linecorp.bot.spring.boot.annotation.EventMapping;
import com.linecorp.bot.spring.boot.annotation.LineMessageHandler;

import choym.model.LineUser;
import choym.model.instagram.IGMedia;
import choym.service.LineService;
import choym.util.IGUtils;

@LineMessageHandler
public class LineController {
	Logger log = Logger.getLogger(LineController.class.getName());

	@Value("${line.help.text}")
	private String lineHelpText;

	@Autowired
	private LineService service;

	@Autowired
	private LineMessagingClient lineMessagingClient;

	@EventMapping
	public TextMessage handleTextMessageEvent(MessageEvent<TextMessageContent> event) {
		String cmd = event.getMessage().getText().trim();

		try {
			String[] strArr = cmd.split(" ");

			if (strArr == null)
				return null;

			if ("bot".equalsIgnoreCase(strArr[0])) {
				// if user is in group chats (senderID != userID), take senderID, else take userID
				String id = event.getSource().getSenderId().equals(event.getSource().getUserId()) ? event.getSource().getUserId() : event.getSource().getSenderId();

				// list all followed IG user
				switch(strArr[1].toLowerCase()) {
				case "list":
					List<Map<String, Object>> list = this.service.getFollow(id);
					StringBuilder sb = new StringBuilder("Follow IG Users: " + list.size());
					for (int i = 0; i < list.size(); i++) {
						sb.append("\n " + (i + 1) + ". https://www.instagram.com/" + list.get(i).get("ig_id"));
					}
					return new TextMessage(sb.toString());
				
				case "follow":
					// check if ig user exist
					if (!IGUtils.exist(strArr[2])) {
						return new TextMessage("[" + strArr[2] + "] dosen't exist!");
					}

					// check if ig is private
					IGMedia media = IGUtils.getMedia(strArr[2]);
					if (!media.isMoreAvailable()) {
						return new TextMessage("[" + strArr[2] + "] is not public!");
					}

					// check if user has already followed ig user
					if (this.service.hasFollowed(id, strArr[2])) {
						return new TextMessage("You have already followed [" + strArr[2] + "]!");
					}

					String mediaCode = media.getItems().get(0).getCode();
					int code = this.service.addFollow(id, strArr[2], mediaCode);
					return new TextMessage("Follow [" + strArr[2] + (code == 1 ? "] success!" : "] fail!"));
				
				case "unfollow":
					if (!this.service.hasFollowed(id, strArr[2])) {
						return new TextMessage("You don't follow [" + strArr[2] + "]!");
					}

					int code1 = this.service.deleteFollow(id, strArr[2]);
					return new TextMessage("Unfollow [" + strArr[2] + (code1 == 1 ? "] success!" : "] fail!"));
				
				case "help":
					return new TextMessage(this.lineHelpText);
				
				case "on":
					this.service.enableBOT(id, true);
					return new TextMessage("BOT ON");
				
				case "off":
					this.service.enableBOT(id, false);
					return new TextMessage("BOT OFF");
				
				case "status":
					LineUser user = this.service.getUser(id);
					if (user == null) {
						return null;
					}

					return new TextMessage("BOT: " + (user.isActive() ? "ON" : "OFF"));
				default:
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();

			return new TextMessage("Command [" + cmd + "] error! Pls try again.");
		}

		return null;
	}

	/**
	 * delete user data while user unfollow BOT
	 * 
	 * @param event
	 */
	@EventMapping
	public void handleUnfollowEvent(UnfollowEvent event) {
		String userID = event.getSource().getUserId();
		this.deleteUser(userID);
	}

	@EventMapping
	public void handleFollowEvent(FollowEvent event) throws InterruptedException, ExecutionException {
		String userID = event.getSource().getUserId();
		this.addUser(userID);
	}

	@EventMapping
	public void handleJoinEvent(JoinEvent event) {
		String groupID = event.getSource().getSenderId();
		this.addUser(groupID);
		this.lineMessagingClient.pushMessage(new PushMessage(groupID, new TextMessage(this.lineHelpText)));
	}

	@EventMapping
	public void handleLeaveEvent(LeaveEvent event) {
		String groupID = event.getSource().getSenderId();
		this.deleteUser(groupID);
	}
	
	private void deleteUser(String id) {
		log.info("DELETE USER: " + id);
		this.service.deleteUser(id);
		log.info("DELETE USER: " + id + " COMPLETE");
	}
	
	private void addUser(String id) {
		if (!this.service.userExist(id)) {
			log.info("ADD USER: " + id);
			this.service.addUser(id);
			log.info("ADD USER: " + id + " COMPLETE");
		}
	}

	@EventMapping
    public void handlePostbackEvent(PostbackEvent event) {
		System.out.println("data: " + event.getPostbackContent().getData());
		System.out.println("param: " + event.getPostbackContent().getParams());
    }

	@EventMapping
    public void handleOtherEvent(Event event) {
        
    }
}
