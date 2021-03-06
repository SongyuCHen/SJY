package nju.software.sjy.model.xy;

// Generated 2016-1-9 0:52:06 by Hibernate Tools 3.4.0.CR1

/**
 * SjygzlAjxxId generated by hbm2java
 */
public class LocalAjxxId implements java.io.Serializable {

	private String fydm;
	private String ahdm;

	public LocalAjxxId() {
	}

	public LocalAjxxId(String fydm, String ahdm) {
		this.fydm = fydm;
		this.ahdm = ahdm;
	}

	public String getFydm() {
		return this.fydm;
	}

	public void setFydm(String fydm) {
		this.fydm = fydm;
	}

	public String getAhdm() {
		return this.ahdm;
	}

	public void setAhdm(String ahdm) {
		this.ahdm = ahdm;
	}

	public boolean equals(Object other) {
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof LocalAjxxId))
			return false;
		LocalAjxxId castOther = (LocalAjxxId) other;

		return ((this.getFydm() == castOther.getFydm()) || (this.getFydm() != null
				&& castOther.getFydm() != null && this.getFydm().equals(
				castOther.getFydm())))
				&& ((this.getAhdm() == castOther.getAhdm()) || (this.getAhdm() != null
						&& castOther.getAhdm() != null && this.getAhdm()
						.equals(castOther.getAhdm())));
	}

	public int hashCode() {
		int result = 17;

		result = 37 * result
				+ (getFydm() == null ? 0 : this.getFydm().hashCode());
		result = 37 * result
				+ (getAhdm() == null ? 0 : this.getAhdm().hashCode());
		return result;
	}

}
