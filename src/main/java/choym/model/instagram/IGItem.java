package choym.model.instagram;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IGItem bean used in IGMedia class
 * 
 * @author choYM
 * @since 2017-10-26
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGItem {
	private String id;
	// private String code;
	private IGUser user;
	private IGImages images;
	private String createdTime;
	private IGCaption caption;
	private boolean userHasLiked;
	// private IGLikes likes;
	private List<String> tags;
	private String filter;
	// private Comments comments;
	private String type;
	private String link;
	// private Location location;
	// private String attribution;
	// private List<IGUser> usersInPhoto;
	private IGVideos videos;
	private List<IGCarouselMedia> carouselMedia;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("user")
	public IGUser getUser() {
		return user;
	}

	public void setUser(IGUser user) {
		this.user = user;
	}

	@JsonProperty("images")
	public IGImages getImages() {
		return images;
	}

	public void setImages(IGImages images) {
		this.images = images;
	}

	@JsonProperty("created_time")
	public String getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(String createdTime) {
		this.createdTime = createdTime;
	}

	@JsonProperty("caption")
	public IGCaption getCaption() {
		return caption;
	}

	public void setCaption(IGCaption caption) {
		this.caption = caption;
	}

	@JsonProperty("user_has_liked")
	public boolean userHasLiked() {
		return userHasLiked;
	}

	public void setUserHasLiked(boolean userHasLiked) {
		this.userHasLiked = userHasLiked;
	}

	@JsonProperty("tags")
	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	@JsonProperty("filter")
	public String getFilter() {
		return filter;
	}

	public void setFilter(String filter) {
		this.filter = filter;
	}

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("link")
	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	@JsonProperty("videos")
	public IGVideos getVideos() {
		return videos;
	}

	public void setVideos(IGVideos videos) {
		this.videos = videos;
	}

	@JsonProperty("carousel_media")
	public List<IGCarouselMedia> getCarouselMedia() {
		return carouselMedia;
	}

	public void setCarouselMedia(List<IGCarouselMedia> carouselMedia) {
		this.carouselMedia = carouselMedia;
	}
	
	public String getMediaCode() {
		String[] s = this.link.split("/");
		return s[s.length-1];
	}

}
