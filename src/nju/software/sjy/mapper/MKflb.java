package nju.software.sjy.mapper;

import java.io.Serializable;

public class MKflb implements Serializable 
{
	private static final long serialVersionUID = 1L;
	private int bh;
	private int pzbh;
	private String mc;
	public int getBh() {
		return bh;
	}
	public void setBh(int bh) {
		this.bh = bh;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public int getPzbh() {
		return pzbh;
	}
	public void setPzbh(int pzbh) {
		this.pzbh = pzbh;
	}
}
