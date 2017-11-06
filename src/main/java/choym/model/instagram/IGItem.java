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
	private String code;
	private IGUser user;
	private IGImages images;
	private String createdTime;
	private IGCaption caption;
	private boolean userHasLiked;
	// private IGLikes likes;
	// private Comments comments;
	private boolean canViewComments;
	private boolean canDeleteComments;
	private String type;
	private String link;
	// private Location location;
	private IGVideos videos;
	private List<IGCarouselMedia> carouselMedia;

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@JsonProperty("can_view_comments")
	public boolean canViewComments() {
		return canViewComments;
	}

	public void setCanViewComments(boolean canViewComments) {
		this.canViewComments = canViewComments;
	}

	@JsonProperty("can_delete_comments")
	public boolean canDeleteComments() {
		return canDeleteComments;
	}

	public void setCanDeleteComments(boolean canDeleteComments) {
		this.canDeleteComments = canDeleteComments;
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

}
