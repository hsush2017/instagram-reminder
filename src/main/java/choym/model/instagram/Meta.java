package choym.model.instagram;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Meta bean used in IGMedia class
 * 
 * @author choYM
 * @since 2017-11-08
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Meta {
	private String code;
	private String errorType;
	private String errorMessage;

	@JsonProperty("code")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@JsonProperty("error_type")
	public String getErrorType() {
		return errorType;
	}

	public void setErrorType(String errorType) {
		this.errorType = errorType;
	}

	@JsonProperty("error_message")
	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	
}
