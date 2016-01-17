package nju.software.sjy.model.xy;

import java.util.Date;

// Generated 2016-1-9 0:52:06 by Hibernate Tools 3.4.0.CR1

/**
 * SjygzlBlxq generated by hbm2java
 */
public class LocalBlxq implements java.io.Serializable {

	private LocalBlxqId id;
	private String ah;
	private String blmc;
	private Date zzrq;
	private String sjy;
	private String sjymc;
	private String xzsjy;
	private String xzsjymc;
	private Integer wjzs;

	public LocalBlxq() {
	}

	public LocalBlxq(LocalBlxqId id) {
		this.id = id;
	}

	public LocalBlxq(LocalBlxqId id, String ah, String blmc, Date zzrq,
			String sjy, String sjymc, String xzsjy, String xzsjymc, Integer wjzs) {
		super();
		this.id = id;
		this.ah = ah;
		this.blmc = blmc;
		this.zzrq = zzrq;
		this.sjy = sjy;
		this.sjymc = sjymc;
		this.xzsjy = xzsjy;
		this.xzsjymc = xzsjymc;
		this.wjzs = wjzs;
	}


	public LocalBlxqId getId() {
		return this.id;
	}

	public void setId(LocalBlxqId id) {
		this.id = id;
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

	public Integer getWjzs() {
		return this.wjzs;
	}

	public void setWjzs(Integer wjzs) {
		this.wjzs = wjzs;
	}

	public String getBlmc() {
		return blmc;
	}

	public void setBlmc(String blmc) {
		this.blmc = blmc;
	}

	public Date getZzrq() {
		return zzrq;
	}

	public void setZzrq(Date zzrq) {
		this.zzrq = zzrq;
	}

	public String getAh() {
		return ah;
	}

	public void setAh(String ah) {
		this.ah = ah;
	}
}
