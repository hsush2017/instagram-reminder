package choym.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Pagination bean used in IGMedia class
 * 
 * @author choYM
 * @since 2017-11-05
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Pagination {
	private String nextMaxId;
	private String nextUrl;

	@JsonProperty("next_max_id")
	public String getNextMaxId() {
		return nextMaxId;
	}

	public void setNextMaxId(String nextMaxId) {
		this.nextMaxId = nextMaxId;
	}

	@JsonProperty("next_url")
	public String getNextUrl() {
		return nextUrl;
	}

	public void setNextUrl(String nextUrl) {
		this.nextUrl = nextUrl;
	}

}
