package nju.software.sjy.mapper;

import java.util.Date;

public class MChangeLog implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5279514621649394309L;
	private int bh;
	
	private int gzsjxxbh;
	
	private int sz1;
	
	private int sz2;
	
	private String xgr;
	
	private String xgrq;
	
	private String xgyy;

	public MChangeLog() {
		
	}

	public int getBh() {
		return bh;
	}

	public void setBh(int bh) {
		this.bh = bh;
	}

	public int getGzsjxxbh() {
		return gzsjxxbh;
	}

	public void setGzsjxxbh(int gzsjxxbh) {
		this.gzsjxxbh = gzsjxxbh;
	}

	public int getSz1() {
		return sz1;
	}

	public void setSz1(int sz1) {
		this.sz1 = sz1;
	}

	public int getSz2() {
		return sz2;
	}

	public void setSz2(int sz2) {
		this.sz2 = sz2;
	}

	public String getXgr() {
		return xgr;
	}

	public void setXgr(String xgr) {
		this.xgr = xgr;
	}

	public String getXgrq() {
		return xgrq;
	}

	public void setXgrq(String xgrq) {
		this.xgrq = xgrq;
	}

	public String getXgyy() {
		return xgyy;
	}

	public void setXgyy(String xgyy) {
		this.xgyy = xgyy;
	}

	
}
