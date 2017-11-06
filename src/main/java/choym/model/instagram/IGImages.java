package choym.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IGImages bean used in IGMedia class
 * 
 * @author choYM
 * @since 2017-10-26
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGImages {
	private IGImage thumbnail;
	private IGImage lowResolution;
	private IGImage standardResolution;

	@JsonProperty("thumbnail")
	public IGImage getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(IGImage thumbnail) {
		this.thumbnail = thumbnail;
	}

	@JsonProperty("low_resolution")
	public IGImage getLowResolution() {
		return lowResolution;
	}

	public void setLowResolution(IGImage lowResolution) {
		this.lowResolution = lowResolution;
	}

	@JsonProperty("standard_resolution")
	public IGImage getStandardResolution() {
		return standardResolution;
	}

	public void setStandardResolution(IGImage standardResolution) {
		this.standardResolution = standardResolution;
	}

}
