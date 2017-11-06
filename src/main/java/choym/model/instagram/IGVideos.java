package choym.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IGVideos bean used in IGMedia class
 * 
 * @author choYM
 * @since 2017-10-26
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGVideos {
	private IGVideo standardResolution;
	private IGVideo lowBandwidth;
	private IGVideo lowResolution;

	@JsonProperty("standard_resolution")
	public IGVideo getStandardResolution() {
		return standardResolution;
	}

	public void setStandardResolution(IGVideo standardResolution) {
		this.standardResolution = standardResolution;
	}

	@JsonProperty("low_bandwidth")
	public IGVideo getLowBandwidth() {
		return lowBandwidth;
	}

	public void setLowBandwidth(IGVideo lowBandwidth) {
		this.lowBandwidth = lowBandwidth;
	}

	@JsonProperty("low_resolution")
	public IGVideo getLowResolution() {
		return lowResolution;
	}

	public void setLowResolution(IGVideo lowResolution) {
		this.lowResolution = lowResolution;
	}

}
