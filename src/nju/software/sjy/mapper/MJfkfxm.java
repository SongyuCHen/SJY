package nju.software.sjy.mapper;

import java.io.Serializable;


public class MJfkfxm implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int bh;
	
	/* 扣分项目类别 */
	private MGypz lb;
	
	/* 加分/扣分项目名称 */
	private String mc;
	
	/* 分数 */
	private double fs;
	
	/* 加分还是扣分 */
	private int jfkf;
	
	public MJfkfxm()
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

	public MGypz getLb()
	{
		return lb;
	}

	public void setLb(MGypz lb)
	{
		this.lb = lb;
	}

	public String getMc()
	{
		return mc;
	}

	public void setMc(String mc)
	{
		this.mc = mc;
	}

	public double getFs()
	{
		return fs;
	}

	public void setFs(double fs)
	{
		this.fs = fs;
	}

	public int getJfkf()
	{
		return jfkf;
	}

	public void setJfkf(int jfkf)
	{
		this.jfkf = jfkf;
	}
	
	
}
