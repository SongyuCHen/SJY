package nju.software.sjy.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MPfkh implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int bh;
	private String ryxm;
	private String bmmc;
	private int nf;
	private int jd;
	private Double jddf;
	private String pfkhgzJsonStr;
	private List<MPfkhgz> pfkhgzList = new ArrayList<MPfkhgz>();
	
	public MPfkh()
	{
		
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

	public Double getJddf()
	{
		return jddf;
	}

	public void setJddf(Double jddf)
	{
		this.jddf = jddf;
	}

	public String getPfkhgzJsonStr()
	{
		return pfkhgzJsonStr;
	}

	public void setPfkhgzJsonStr(String pfkhgzJsonStr)
	{
		this.pfkhgzJsonStr = pfkhgzJsonStr;
	}

	public List<MPfkhgz> getPfkhgzList()
	{
		return pfkhgzList;
	}

	public void setPfkhgzList(List<MPfkhgz> pfkhgzList)
	{
		this.pfkhgzList = pfkhgzList;
	}
	
	
}
