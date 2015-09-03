package nju.software.sjy.mapper;

import java.io.Serializable;

public class MJfqx implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int bh;
	private String ryxm;
	private String bmmc;
	private int nf;
	private int yf;
	private String jllx;
	private String hdsj;
	private String jblx;
	private Integer jfz;
	private int jfcs;
	private String zt;
	private String comment;
	private int rybh;
	private int bmbh;
	private MJfkfxm jfxm;
	
	public MJfqx()
	{
		
	}
	
	public MJfqx(String bmmc, int nf, int yf)
	{
		this.bmmc = bmmc;
		this.nf = nf;
		this.yf = yf;
	}
	
	public MJfqx(int bh, String ryxm, String bmmc, String jllx, String hdsj, String jblx, Integer jfz)
	{
		this.bh = bh;
		this.ryxm = ryxm;
		this.bmmc = bmmc;
		this.jllx = jllx;
		this.hdsj = hdsj;
		this.jblx = jblx;
		this.jfz = jfz;
	}
	
	public MJfqx(int bh, String ryxm, String bmmc, String jllx, String hdsj, String jblx, Integer jfz, String zt)
	{
		this.bh = bh;
		this.ryxm = ryxm;
		this.bmmc = bmmc;
		this.jllx = jllx;
		this.hdsj = hdsj;
		this.jblx = jblx;
		this.jfz = jfz;
		this.zt = zt;
	}

	public int getBh()
	{
		return bh;
	}

	public void setBh(int bh)
	{
		this.bh = bh;
	}

	public String getRyxm()
	{
		return ryxm;
	}

	public void setRyxm(String ryxm)
	{
		this.ryxm = ryxm;
	}

	public String getBmmc()
	{
		return bmmc;
	}

	public void setBmmc(String bmmc)
	{
		this.bmmc = bmmc;
	}

	public int getNf()
	{
		return nf;
	}

	public void setNf(int nf)
	{
		this.nf = nf;
	}

	public int getYf()
	{
		return yf;
	}

	public void setYf(int yf)
	{
		this.yf = yf;
	}

	public String getJllx()
	{
		return jllx;
	}

	public void setJllx(String jllx)
	{
		this.jllx = jllx;
	}

	public String getHdsj()
	{
		return hdsj;
	}

	public void setHdsj(String hdsj)
	{
		this.hdsj = hdsj;
	}

	public String getJblx()
	{
		return jblx;
	}

	public void setJblx(String jblx)
	{
		this.jblx = jblx;
	}

	public int getJfcs()
	{
		return jfcs;
	}

	public void setJfcs(int jfcs)
	{
		this.jfcs = jfcs;
	}

	public Integer getJfz()
	{
		return jfz;
	}

	public void setJfz(Integer jfz)
	{
		this.jfz = jfz;
	}

	public String getZt()
	{
		return zt;
	}

	public void setZt(String zt)
	{
		this.zt = zt;
	}

	public MJfkfxm getJfxm()
	{
		return jfxm;
	}

	public void setJfxm(MJfkfxm jfxm)
	{
		this.jfxm = jfxm;
	}

	public int getRybh()
	{
		return rybh;
	}

	public void setRybh(int rybh)
	{
		this.rybh = rybh;
	}

	public int getBmbh()
	{
		return bmbh;
	}

	public void setBmbh(int bmbh)
	{
		this.bmbh = bmbh;
	}

	public String getComment()
	{
		return comment;
	}

	public void setComment(String comment)
	{
		this.comment = comment;
	}
	
	
}
