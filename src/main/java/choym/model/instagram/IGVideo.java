package choym.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IGVideo bean used in IGVideos class
 * 
 * @author choYM
 * @since 2017-10-26
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGVideo {
	private int width;
	private int height;
	private String url;
	private String id;

	@JsonProperty("width")
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	@JsonProperty("height")
	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	@JsonProperty("url")
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@JsonProperty("id")
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
