package nju.software.sjy.mapper;


public class MBm implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;
	
	private int bmbh;
	private String bmid;
	private String bmmc;
	private String bmlx;
	
	public MBm()
	{
		
	}

	public int getBmbh()
	{
		return bmbh;
	}

	public void setBmbh(int bmbh)
	{
		this.bmbh = bmbh;
	}

	public String getBmid()
	{
		return bmid;
	}

	public void setBmid(String bmid)
	{
		this.bmid = bmid;
	}

	public String getBmmc()
	{
		return bmmc;
	}

	public void setBmmc(String bmmc)
	{
		this.bmmc = bmmc;
	}

	public String getBmlx()
	{
		return bmlx;
	}

	public void setBmlx(String bmlx)
	{
		this.bmlx = bmlx;
	}
	
	
}
