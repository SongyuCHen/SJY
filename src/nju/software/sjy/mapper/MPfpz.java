package nju.software.sjy.mapper;

import java.io.Serializable;

public class MPfpz implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int bh;
	private String mc;
	private String lx;
	private int fs;
	
	public MPfpz()
	{
		
	}
	
	public MPfpz(String mc, int fs)
	{
		this.mc = mc;
		this.fs = fs;
	}
	
	public MPfpz(int bh, String mc, int fs)
	{
		this.bh = bh;
		this.mc = mc;
		this.fs = fs;
	}

	public int getBh()
	{
		return bh;
	}

	public void setBh(int bh)
	{
		this.bh = bh;
	}

	public String getMc()
	{
		return mc;
	}

	public void setMc(String mc)
	{
		this.mc = mc;
	}

	public String getLx()
	{
		return lx;
	}

	public void setLx(String lx)
	{
		this.lx = lx;
	}

	public int getFs()
	{
		return fs;
	}

	public void setFs(int fs)
	{
		this.fs = fs;
	}

	
}
