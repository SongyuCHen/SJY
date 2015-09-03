package nju.software.sjy.model.xy;

import java.io.Serializable;

public class TRoleoperation implements Serializable
{

	private static final long serialVersionUID = 1L;
	
	private int robh;
	
	private TRole role;
	
	private TOperation operation;
	
	public TRoleoperation()
	{
		
	}
	
	public TRoleoperation(int robh, TRole role, TOperation operation)
	{
		this.robh = robh;
		this.role = role;
		this.operation = operation;
	}

	public int getRobh()
	{
		return robh;
	}

	public void setRobh(int robh)
	{
		this.robh = robh;
	}

	public TRole getRole()
	{
		return role;
	}

	public void setRole(TRole role)
	{
		this.role = role;
	}

	public TOperation getOperation()
	{
		return operation;
	}

	public void setOperation(TOperation operation)
	{
		this.operation = operation;
	}
	
	
}
