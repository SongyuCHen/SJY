package nju.software.sjy.mapper;

import java.io.Serializable;

public class MGzs implements Serializable{
	private static final long serialVersionUID = 1L;
	private String fjm;
	private String yhdm;
	private int gzs;
	
	public MGzs(){
		
	}
	public MGzs(String fjm, String yhdm, int gzs) {

		this.fjm = fjm;
		this.yhdm = yhdm;
		this.gzs = gzs;
	}
	public String getFjm() {
		return fjm;
	}
	public void setFjm(String fjm) {
		this.fjm = fjm;
	}
	public String getYhdm() {
		return yhdm;
	}
	public void setYhdm(String yhdm) {
		this.yhdm = yhdm;
	}
	public int getGzs() {
		return gzs;
	}
	public void setGzs(int gzs) {
		this.gzs = gzs;
	}
	
	

}
