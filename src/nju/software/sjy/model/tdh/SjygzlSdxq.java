package nju.software.sjy.model.tdh;

// Generated 2015-5-19 0:24:08 by Hibernate Tools 3.4.0.CR1

/**
 * SjygzlSdxq generated by hbm2java
 */
public class SjygzlSdxq implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	private SjygzlSdxqId id;
	private String ah;
	private String cbbm;
	private String cbbmmc;
	private String sjy;
	private String sjymc;
	private String sdrq;
	private String sddsr;
	private String sddz;
	private String ysdrq;
	public SjygzlSdxq()
	{
	}

	public SjygzlSdxq(SjygzlSdxqId id)
	{
		this.id = id;
	}

	public SjygzlSdxq(SjygzlSdxqId id, String ah, String cbbm, String cbbmmc,
			String sjy, String sjymc, String sdrq, String sddsr, String sddz, String ysdrq)
	{
		super();
		this.id = id;
		this.ah = ah;
		this.cbbm = cbbm;
		this.cbbmmc = cbbmmc;
		this.sjy = sjy;
		this.sjymc = sjymc;
		this.sdrq = sdrq;
		this.sddsr = sddsr;
		this.sddz = sddz;
		this.ysdrq = ysdrq;
	}

	public SjygzlSdxqId getId()
	{
		return this.id;
	}

	public void setId(SjygzlSdxqId id)
	{
		this.id = id;
	}

	public String getAh()
	{
		return this.ah;
	}

	public void setAh(String ah)
	{
		this.ah = ah;
	}

	public String getCbbm()
	{
		return this.cbbm;
	}

	public void setCbbm(String cbbm)
	{
		this.cbbm = cbbm;
	}

	public String getCbbmmc()
	{
		return this.cbbmmc;
	}

	public void setCbbmmc(String cbbmmc)
	{
		this.cbbmmc = cbbmmc;
	}

	public String getSjy()
	{
		return this.sjy;
	}

	public void setSjy(String sjy)
	{
		this.sjy = sjy;
	}

	public String getSjymc()
	{
		return this.sjymc;
	}

	public void setSjymc(String sjymc)
	{
		this.sjymc = sjymc;
	}

	public String getSddz()
	{
		return sddz;
	}

	public void setSddz(String sddz)
	{
		this.sddz = sddz;
	}

	public String getSdrq()
	{
		return sdrq;
	}

	public void setSdrq(String sdrq)
	{
		this.sdrq = sdrq;
	}

	public String getSddsr()
	{
		return sddsr;
	}

	public void setSddsr(String sddsr)
	{
		this.sddsr = sddsr;
	}

	public String getYsdrq() {
		return ysdrq;
	}

	public void setYsdrq(String ysdrq) {
		this.ysdrq = ysdrq;
	}

}
