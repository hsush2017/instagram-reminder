package choym.model;

import java.math.BigDecimal;

public class Follow {
	private BigDecimal serialId;
	private String lineId;
	private String igId;
	private String mediaCode;
	private String igUsername;

	public BigDecimal getSerialId() {
		return serialId;
	}

	public void setSerialId(BigDecimal serialId) {
		this.serialId = serialId;
	}

	public String getMediaCode() {
		return mediaCode;
	}

	public void setMediaCode(String mediaCode) {
		this.mediaCode = mediaCode;
	}

	public Follow() {
		super();
	}

	public String getLineId() {
		return lineId;
	}

	public void setLineId(String lineId) {
		this.lineId = lineId;
	}

	public String getIgId() {
		return igId;
	}

	public void setIgId(String igId) {
		this.igId = igId;
	}

	public String getIgUsername() {
		return igUsername;
	}

	public void setIgUsername(String igUsername) {
		this.igUsername = igUsername;
	}
}
