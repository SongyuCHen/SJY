package nju.software.sjy.mapper;

import java.io.Serializable;

public class MKfqx implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private MKfxm kfxm;
	private int bh;
	private String ryxm;
	private int rybh;
	private int bmbh;
	private int kfcs;
	private String comment;
	
	public int getRybh() {
		return rybh;
	}


	public void setRybh(int rybh) {
		this.rybh = rybh;
	}


	public int getBmbh() {
		return bmbh;
	}


	public void setBmbh(int bmbh) {
		this.bmbh = bmbh;
	}


	


	private String bmmc;
	private int nf;
	private int yf;
	private String hdsj;
	
	public int getBh() {
		return bh;
	}


	public void setBh(int bh) {
		this.bh = bh;
	}


	public String getRyxm() {
		return ryxm;
	}


	public void setRyxm(String ryxm) {
		this.ryxm = ryxm;
	}


	public String getBmmc() {
		return bmmc;
	}


	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}


	public int getNf() {
		return nf;
	}


	public void setNf(int nf) {
		this.nf = nf;
	}


	public int getYf() {
		return yf;
	}


	public void setYf(int yf) {
		this.yf = yf;
	}


	public String getHdsj() {
		return hdsj;
	}


	public void setHdsj(String hdsj) {
		this.hdsj = hdsj;
	}
	
	public MKfqx()
	{
		
	}


	public MKfxm getKfxm() {
		return kfxm;
	}


	public void setKfxm(MKfxm kfxm) {
		this.kfxm = kfxm;
	}


	public String getComment()
	{
		return comment;
	}


	public void setComment(String comment)
	{
		this.comment = comment;
	}


	public int getKfcs()
	{
		return kfcs;
	}


	public void setKfcs(int kfcs)
	{
		this.kfcs = kfcs;
	}
	
	
}
