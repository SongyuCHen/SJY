package nju.software.sjy.model.xy;

// Generated Mar 27, 2015 2:10:41 PM by Hibernate Tools 3.4.0.CR1

/**
 * TRoleres generated by hbm2java
 */
public class TRoleres implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	
	private int rrbh;
	private TRole role;
	private TResource res;

	public TRoleres()
	{
	}

	public TRoleres(int rrbh, TRole role, TResource res)
	{
		this.rrbh = rrbh;
		this.role = role;
		this.res = res;
	}

	public int getRrbh()
	{
		return this.rrbh;
	}

	public void setRrbh(int rrbh)
	{
		this.rrbh = rrbh;
	}

	public TRole getRole()
	{
		return role;
	}

	public void setRole(TRole role)
	{
		this.role = role;
	}

	public TResource getRes()
	{
		return res;
	}

	public void setRes(TResource res)
	{
		this.res = res;
	}

	
}
