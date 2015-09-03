package nju.software.sjy.model.xy;

import java.io.Serializable;

public class TZtoperation implements Serializable
{
	private static final long serialVersionUID = 1L;

	private int zobh;
	
	private TGypz zt;
	
	private TOperation operation;
	
	private TGypz nextZt;
	
	private Integer status;
	
	public TZtoperation()
	{
		
	}

	public int getZobh()
	{
		return zobh;
	}

	public void setZobh(int zobh)
	{
		this.zobh = zobh;
	}

	public TGypz getZt()
	{
		return zt;
	}

	public void setZt(TGypz zt)
	{
		this.zt = zt;
	}

	public TOperation getOperation()
	{
		return operation;
	}

	public void setOperation(TOperation operation)
	{
		this.operation = operation;
	}

	public TGypz getNextZt()
	{
		return nextZt;
	}

	public void setNextZt(TGypz nextZt)
	{
		this.nextZt = nextZt;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}


	
	
}
