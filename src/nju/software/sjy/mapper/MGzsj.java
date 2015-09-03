package nju.software.sjy.mapper;

import java.io.Serializable;
import java.util.List;

public class MGzsj implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int bh;
	private String xm;
	private String bmmc;
	private String rq;
	private String zt;
	private List<Integer> szList;
	
	public MGzsj()
	{
		
	}
	
	public MGzsj(int bh, String xm, String bmmc, String rq)
	{
		this.bh = bh;
		this.xm = xm;
		this.bmmc = bmmc;
		this.rq = rq;
	}
	
	public MGzsj(int bh, String xm, String bmmc)
	{
		this.bh = bh;
		this.xm = xm;
		this.bmmc = bmmc;
	}

	public int getBh()
	{
		return bh;
	}

	public void setBh(int bh)
	{
		this.bh = bh;
	}

	public String getXm()
	{
		return xm;
	}

	public void setXm(String xm)
	{
		this.xm = xm;
	}

	public String getBmmc()
	{
		return bmmc;
	}

	public void setBmmc(String bmmc)
	{
		this.bmmc = bmmc;
	}

	public String getRq()
	{
		return rq;
	}

	public void setRq(String rq)
	{
		this.rq = rq;
	}

	public List<Integer> getSzList()
	{
		return szList;
	}

	public void setSzList(List<Integer> szList)
	{
		this.szList = szList;
	}

	public String getZt()
	{
		return zt;
	}

	public void setZt(String zt)
	{
		this.zt = zt;
	}
	
}
