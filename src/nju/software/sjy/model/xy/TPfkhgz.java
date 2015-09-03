package nju.software.sjy.model.xy;

public class TPfkhgz
{
	private int bh;
	private TPfkh pfkh;
	private TGypz gz;
	private double gzdf;
	
	public TPfkhgz()
	{
		
	}
	
	public TPfkhgz(int bh, TPfkh pfkh, TGypz gz, double gzdf)
	{
		this.bh = bh;
		this.pfkh = pfkh;
		this.gz = gz;
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

	public TPfkh getPfkh()
	{
		return pfkh;
	}

	public void setPfkh(TPfkh pfkh)
	{
		this.pfkh = pfkh;
	}

	public TGypz getGz()
	{
		return gz;
	}

	public void setGz(TGypz gz)
	{
		this.gz = gz;
	}

	public double getGzdf()
	{
		return gzdf;
	}

	public void setGzdf(double gzdf)
	{
		this.gzdf = gzdf;
	}
	
	
}
