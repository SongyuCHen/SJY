package nju.software.sjy.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MResource implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int resbh;
	
	private String resname;
	
	private String url;
	
	private String type;
	
	private List<MResource> childrenList;
	
	public MResource()
	{
		childrenList = new ArrayList<MResource>();
	}

	public int getResbh()
	{
		return resbh;
	}

	public void setResbh(int resbh)
	{
		this.resbh = resbh;
	}

	public String getResname()
	{
		return resname;
	}

	public void setResname(String resname)
	{
		this.resname = resname;
	}

	public String getUrl()
	{
		return url;
	}

	public void setUrl(String url)
	{
		this.url = url;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public List<MResource> getChildrenList()
	{
		return childrenList;
	}

	public void setChildrenList(List<MResource> childrenList)
	{
		this.childrenList = childrenList;
	}
	
	
}
