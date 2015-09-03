package nju.software.sjy.mapper;

import java.io.Serializable;
import java.util.Comparator;

public class MGypz implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int bh;
	private int pzbh;
	private String lx;
	private String mc;
	private int sz;
	
	public MGypz()
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
	public int getPzbh()
	{
		return pzbh;
	}
	public void setPzbh(int pzbh)
	{
		this.pzbh = pzbh;
	}
	public String getLx()
	{
		return lx;
	}
	public void setLx(String lx)
	{
		this.lx = lx;
	}
	public String getMc()
	{
		return mc;
	}
	public void setMc(String mc)
	{
		this.mc = mc;
	}
	public int getSz()
	{
		return sz;
	}
	public void setSz(int sz)
	{
		this.sz = sz;
	}
	
	/*按照数值倒序比较*/
	public static Comparator<MGypz> MGypzComparator = new Comparator<MGypz>() {
		public int compare(MGypz s1, MGypz s2) {
			   int sz1 = s1.getSz();
			   int sz2 = s2.getSz();
			   return sz2-sz1;
		}
	};
	
	
}
