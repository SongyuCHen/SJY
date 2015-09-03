package nju.software.sjy.model.tdh;

// Generated 2015-5-19 0:24:08 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * SjygzlBlxqId generated by hbm2java
 */
public class SjygzlBlxqId implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	private String fydm;
	private String ahdm;
	private String blmc;
	private Date zzrq;

	public SjygzlBlxqId()
	{
	}

	public SjygzlBlxqId(String fydm, String ahdm, String blmc, Date zzrq)
	{
		this.fydm = fydm;
		this.ahdm = ahdm;
		this.blmc = blmc;
		this.zzrq = zzrq;
	}

	public String getFydm()
	{
		return this.fydm;
	}

	public void setFydm(String fydm)
	{
		this.fydm = fydm;
	}

	public String getAhdm()
	{
		return this.ahdm;
	}

	public void setAhdm(String ahdm)
	{
		this.ahdm = ahdm;
	}

	public String getBlmc()
	{
		return this.blmc;
	}

	public void setBlmc(String blmc)
	{
		this.blmc = blmc;
	}

	public Date getZzrq()
	{
		return this.zzrq;
	}

	public void setZzrq(Date zzrq)
	{
		this.zzrq = zzrq;
	}

	public boolean equals(Object other)
	{
		if ((this == other))
			return true;
		if ((other == null))
			return false;
		if (!(other instanceof SjygzlBlxqId))
			return false;
		SjygzlBlxqId castOther = (SjygzlBlxqId) other;

		return ((this.getFydm() == castOther.getFydm()) || (this.getFydm() != null
				&& castOther.getFydm() != null && this.getFydm().equals(
				castOther.getFydm())))
				&& ((this.getAhdm() == castOther.getAhdm()) || (this.getAhdm() != null
						&& castOther.getAhdm() != null && this.getAhdm()
						.equals(castOther.getAhdm())))
				&& ((this.getBlmc() == castOther.getBlmc()) || (this.getBlmc() != null
						&& castOther.getBlmc() != null && this.getBlmc()
						.equals(castOther.getBlmc())))
				&& ((this.getZzrq() == castOther.getZzrq()) || (this.getZzrq() != null
						&& castOther.getZzrq() != null && this.getZzrq()
						.equals(castOther.getZzrq())));
	}

	public int hashCode()
	{
		int result = 17;

		result = 37 * result
				+ (getFydm() == null ? 0 : this.getFydm().hashCode());
		result = 37 * result
				+ (getAhdm() == null ? 0 : this.getAhdm().hashCode());
		result = 37 * result
				+ (getBlmc() == null ? 0 : this.getBlmc().hashCode());
		result = 37 * result
				+ (getZzrq() == null ? 0 : this.getZzrq().hashCode());
		return result;
	}

}
