package nju.software.sjy.model.xy;

// Generated Mar 27, 2015 2:10:41 PM by Hibernate Tools 3.4.0.CR1

/**
 * TResource generated by hbm2java
 */
public class TResource implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int resbh;
	private String resname;
	private String url;
	private String type;

	public TResource()
	{
	}

	public TResource(int resbh, String resname, String url)
	{
		this.resbh = resbh;
		this.resname = resname;
		this.url = url;
	}

	public TResource(int resbh, String resname, String url, String type)
	{
		this.resbh = resbh;
		this.resname = resname;
		this.url = url;
		this.type = type;
	}

	public int getResbh()
	{
		return this.resbh;
	}

	public void setResbh(int resbh)
	{
		this.resbh = resbh;
	}

	public String getResname()
	{
		return this.resname;
	}

	public void setResname(String resname)
	{
		this.resname = resname;
	}

	public String getUrl()
	{
		return this.url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getType()
	{
		return this.type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

}
