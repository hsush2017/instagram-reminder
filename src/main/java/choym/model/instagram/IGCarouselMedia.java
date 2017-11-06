package choym.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IGCarouselMedia bean used in IGMedia class
 * 
 * @author choYM
 * @since 2017-10-26
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGCarouselMedia {
	private String type;
//	private IGUsersInPhoto usersInPhoto;
	private IGVideos videos;
	private IGImages images;

	@JsonProperty("type")
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@JsonProperty("videos")
	public IGVideos getVideos() {
		return videos;
	}

	public void setVideos(IGVideos videos) {
		this.videos = videos;
	}

	@JsonProperty("images")
	public IGImages getImages() {
		return images;
	}

	

	public void setImages(IGImages images) {
		this.images = images;
	}
}
