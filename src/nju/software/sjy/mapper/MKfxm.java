package nju.software.sjy.mapper;

import java.io.Serializable;

public class MKfxm  implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private int bh;
	private MKflb kflb;
	private String mc;
	private double fs;
	public MKflb getKflb() {
		return kflb;
	}
	public void setKflb(MKflb kflb) {
		this.kflb = kflb;
	}
	public String getMc() {
		return mc;
	}
	public void setMc(String mc) {
		this.mc = mc;
	}
	public double getFs() {
		return fs;
	}
	public void setFs(double fs) {
		this.fs = fs;
	}
	public int getBh() {
		return bh;
	}
	public void setBh(int bh) {
		this.bh = bh;
	}
	
}
