package nju.software.sjy.mapper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.model.xy.TOperation;

public class MRole implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int rolebh;

	private String rolename;

	private Integer levels;

	private String operations;

	private List<TOperation> operationList;

	private String simpleReses;

	private String detailReses;

	public MRole() 
	{
		operationList = new ArrayList<TOperation>();
	}

	public int getRolebh()
	{
		return rolebh;
	}

	public void setRolebh(int rolebh)
	{
		this.rolebh = rolebh;
	}

	public String getRolename()
	{
		return rolename;
	}

	public void setRolename(String rolename)
	{
		this.rolename = rolename;
	}

	public Integer getLevels()
	{
		return levels;
	}

	public void setLevels(Integer levels)
	{
		this.levels = levels;
	}

	public String getOperations()
	{
		return operations;
	}

	public void setOperations(String operations)
	{
		this.operations = operations;
	}

	public List<TOperation> getOperationList()
	{
		return operationList;
	}

	public void setOperationList(List<TOperation> operationList)
	{
		this.operationList = operationList;
	}

	public String getSimpleReses()
	{
		return simpleReses;
	}

	public void setSimpleReses(String simpleReses)
	{
		this.simpleReses = simpleReses;
	}

	public String getDetailReses()
	{
		return detailReses;
	}

	public void setDetailReses(String detailReses)
	{
		this.detailReses = detailReses;
	}

	

}
