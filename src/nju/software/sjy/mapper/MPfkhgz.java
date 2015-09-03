package nju.software.sjy.mapper;

import java.util.ArrayList;
import java.util.List;

public class MPfkhgz
{
	private int bh;
	private String ryxm;
	private String bmmc;
	private int nf;
	private int jd;
	private String gzmc;
	private Double gzdf;
	private List<MGypz> gzxxList = new ArrayList<MGypz>();
	
	public MPfkhgz()
	{
		
	}
	
	public MPfkhgz(String gzmc, double gzdf)
	{
		this.gzmc = gzmc;
		this.gzdf = gzdf;
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

	public int getJd()
	{
		return jd;
	}

	public void setJd(int jd)
	{
		this.jd = jd;
	}

	public String getGzmc()
	{
		return gzmc;
	}

	public void setGzmc(String gzmc)
	{
		this.gzmc = gzmc;
	}

	public Double getGzdf()
	{
		return gzdf;
	}

	public void setGzdf(Double gzdf)
	{
		this.gzdf = gzdf;
	}

	public List<MGypz> getGzxxList()
	{
		return gzxxList;
	}

	public void setGzxxList(List<MGypz> gzxxList)
	{
		this.gzxxList = gzxxList;
	}
	
	
}
