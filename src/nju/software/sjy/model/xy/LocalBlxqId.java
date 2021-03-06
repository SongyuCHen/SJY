package nju.software.sjy.model.xy;

// Generated 2016-1-9 0:52:06 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * SjygzlBlxqId generated by hbm2java
 */
public class LocalBlxqId implements java.io.Serializable {

	private String fydm;
	private String ahdm;
	private String xh;

	public LocalBlxqId() {
	}


	public LocalBlxqId(String fydm, String ahdm, String xh) {
		super();
		this.fydm = fydm;
		this.ahdm = ahdm;
		this.xh = xh;
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

	public String getXh() {
		return xh;
	}


	public void setXh(String xh) {
		this.xh = xh;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ahdm == null) ? 0 : ahdm.hashCode());
		result = prime * result + ((fydm == null) ? 0 : fydm.hashCode());
		result = prime * result + ((xh == null) ? 0 : xh.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LocalBlxqId other = (LocalBlxqId) obj;
		if (ahdm == null) {
			if (other.ahdm != null)
				return false;
		} else if (!ahdm.equals(other.ahdm))
			return false;
		if (fydm == null) {
			if (other.fydm != null)
				return false;
		} else if (!fydm.equals(other.fydm))
			return false;
		if (xh == null) {
			if (other.xh != null)
				return false;
		} else if (!xh.equals(other.xh))
			return false;
		return true;
	}


}
