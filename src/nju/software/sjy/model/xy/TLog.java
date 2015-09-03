package nju.software.sjy.model.xy;

import java.io.Serializable;
import java.util.Date;

public class TLog implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5446544436168986798L;
	
	private int bh;
	private Date sj;
	private String nr;
	
	public TLog(){
		
	}
	public TLog(int bh, Date sj, String nr) {
		this.bh = bh;
		this.sj = sj;
		this.nr = nr;
	}
	public int getBh() {
		return bh;
	}
	public void setBh(int bh) {
		this.bh = bh;
	}
	public Date getSj() {
		return sj;
	}
	public void setSj(Date sj) {
		this.sj = sj;
	}
	public String getNr() {
		return nr;
	}
	public void setNr(String nr) {
		this.nr = nr;
	}
	
	
	

}
