package nju.software.sjy.mapper;

import java.io.Serializable;

public class MKhpm implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int bh;
	private String khzbmc;
	private String ryxm;
	private String bmmc;
	private String bmlxmc;
	private int nf;
	private int jd;
	private Double jddf;
	
	public MKhpm()
	{
		
	}
	
	public MKhpm(String ryxm, String bmmc, int nf, int jd)
	{
		this.ryxm = ryxm;
		this.bmmc = bmmc;
		this.nf = nf;
		this.jd = jd;
	}

	public int getBh()
	{
		return bh;
	}

	public void setBh(int bh)
	{
		this.bh = bh;
	}

	public String getKhzbmc()
	{
		return khzbmc;
	}

	public void setKhzbmc(String khzbmc)
	{
		this.khzbmc = khzbmc;
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

	public String getBmlxmc()
	{
		return bmlxmc;
	}

	public void setBmlxmc(String bmlxmc)
	{
		this.bmlxmc = bmlxmc;
	}

	public int getNf()
	{
		return nf;
	}

	public void setNf(int nf)
	{
		this.nf = nf;
	}

	public int getJd()
	{
		return jd;
	}

	public void setJd(int jd)
	{
		this.jd = jd;
	}

	public Double getJddf()
	{
		return jddf;
	}

	public void setJddf(Double jddf)
	{
		this.jddf = jddf;
	}

	
	
}
