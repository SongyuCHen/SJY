package nju.software.sjy.model.xy;

import java.io.Serializable;
import java.util.Date;

public class TGzsjChangelog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int bh;
	private TGzsjxx gzsjxx;
	private int sz1;
	private int sz2;
	private String xgr;
	private Date xgrq;
	private String xgyy;
	public TGzsjChangelog(){
		
	}
	public TGzsjChangelog(int bh, TGzsjxx gzsjxx, int sz1, int sz2, String xgr,
			Date xgrq, String xgyy) {
		this.bh = bh;
		this.gzsjxx = gzsjxx;
		this.sz1 = sz1;
		this.sz2 = sz2;
		this.xgr = xgr;
		this.xgrq = xgrq;
		this.xgyy = xgyy;
	}
	public int getBh() {
		return bh;
	}
	public void setBh(int bh) {
		this.bh = bh;
	}
	public TGzsjxx getGzsjxx() {
		return gzsjxx;
	}
	public void setGzsjxx(TGzsjxx gzsjxx) {
		this.gzsjxx = gzsjxx;
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
	public Date getXgrq() {
		return xgrq;
	}
	public void setXgrq(Date xgrq) {
		this.xgrq = xgrq;
	}
	public String getXgyy() {
		return xgyy;
	}
	public void setXgyy(String xgyy) {
		this.xgyy = xgyy;
	}
	

}
