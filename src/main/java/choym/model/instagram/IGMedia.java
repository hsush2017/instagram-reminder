package choym.model.instagram;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * IG page's json object
 * 
 * @author choYM
 * @since 2017-10-26
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IGMedia {
	private List<IGItem> items;
	private boolean moreAvailable;
	private String status;

	@JsonProperty("items")
	public List<IGItem> getItems() {
		return items;
	}

	public void setItems(List<IGItem> items) {
		this.items = items;
	}

	@JsonProperty("more_available")
	public boolean isMoreAvailable() {
		return moreAvailable;
	}

	public void setMoreAvailable(boolean moreAvailable) {
		this.moreAvailable = moreAvailable;
	}

	@JsonProperty("status")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
