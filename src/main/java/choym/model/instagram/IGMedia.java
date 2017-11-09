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
	private Pagination pagination;
	private List<IGItem> items;
	private Meta meta;

	@JsonProperty("pagination")
	public Pagination getPagination() {
		return pagination;
	}

	public void setPagination(Pagination pagination) {
		this.pagination = pagination;
	}

	@JsonProperty("data")
	public List<IGItem> getItems() {
		return items;
	}

	public void setItems(List<IGItem> items) {
		this.items = items;
	}

	@JsonProperty("meta")
	public Meta getMeta() {
		return meta;
	}

	public void setMeta(Meta meta) {
		this.meta = meta;
	}

}
