package nju.software.sjy.model.xy;

// Generated 2016-1-9 0:52:06 by Hibernate Tools 3.4.0.CR1

/**
 * SjygzlSdxq generated by hbm2java
 */
public class LocalSdxq implements java.io.Serializable {

	private LocalSdxqId id;
	private String ah;
	private String cbbm;
	private String cbbmmc;
	private String sjy;
	private String sjymc;
	private String xzsjy;
	private String xzsjymc;
	private String sddz;
	private String ysdrq;
	public LocalSdxq() {
	}

	public LocalSdxq(LocalSdxqId id) {
		this.id = id;
	}

	public LocalSdxq(LocalSdxqId id, String ah, String cbbm, String cbbmmc,
			String sjy, String sjymc, String xzsjy, String xzsjymc, String sddz) {
		this.id = id;
		this.ah = ah;
		this.cbbm = cbbm;
		this.cbbmmc = cbbmmc;
		this.sjy = sjy;
		this.sjymc = sjymc;
		this.xzsjy = xzsjy;
		this.xzsjymc = xzsjymc;
		this.sddz = sddz;
	}

	public LocalSdxqId getId() {
		return this.id;
	}

	public void setId(LocalSdxqId id) {
		this.id = id;
	}

	public String getAh() {
		return this.ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}

	public String getCbbm() {
		return this.cbbm;
	}

	public void setCbbm(String cbbm) {
		this.cbbm = cbbm;
	}

	public String getCbbmmc() {
		return this.cbbmmc;
	}

	public void setCbbmmc(String cbbmmc) {
		this.cbbmmc = cbbmmc;
	}

	public String getSjy() {
		return this.sjy;
	}

	public void setSjy(String sjy) {
		this.sjy = sjy;
	}

	public String getSjymc() {
		return this.sjymc;
	}

	public void setSjymc(String sjymc) {
		this.sjymc = sjymc;
	}

	public String getXzsjy() {
		return this.xzsjy;
	}

	public void setXzsjy(String xzsjy) {
		this.xzsjy = xzsjy;
	}

	public String getXzsjymc() {
		return this.xzsjymc;
	}

	public void setXzsjymc(String xzsjymc) {
		this.xzsjymc = xzsjymc;
	}

	public String getSddz() {
		return this.sddz;
	}

	public void setSddz(String sddz) {
		this.sddz = sddz;
	}

	public String getYsdrq() {
		return ysdrq;
	}

	public void setYsdrq(String ysdrq) {
		this.ysdrq = ysdrq;
	}

}
