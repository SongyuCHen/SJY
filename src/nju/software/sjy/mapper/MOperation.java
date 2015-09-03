package nju.software.sjy.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MOperation implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int bh;
	private String name;
	private String range;
	private String alias;
	private Integer status;
	private List<String> rangeList;
	
	public MOperation()
	{
		rangeList = new ArrayList<String>();
	}

	public int getBh()
	{
		return bh;
	}

	public void setBh(int bh)
	{
		this.bh = bh;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getRange()
	{
		return range;
	}

	public void setRange(String range)
	{
		this.range = range;
	}

	public String getAlias()
	{
		return alias;
	}

	public void setAlias(String alias)
	{
		this.alias = alias;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public List<String> getRangeList()
	{
		return rangeList;
	}

	public void setRangeList(List<String> rangeList)
	{
		this.rangeList = rangeList;
	}
	
	
}
