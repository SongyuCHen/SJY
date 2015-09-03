package nju.software.sjy.mapper;

public class MUser implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;
	private int rybh;
	private String xm;
	private String bmmc;
	private String username;
	private String password;
	private String yhsf;
	private String rolename;
	
	public MUser()
	{
		
	}

	public int getRybh()
	{
		return rybh;
	}

	public void setRybh(int rybh)
	{
		this.rybh = rybh;
	}

	public String getXm()
	{
		return xm;
	}

	public void setXm(String xm)
	{
		this.xm = xm;
	}

	public String getBmmc()
	{
		return bmmc;
	}

	public void setBmmc(String bmmc)
	{
		this.bmmc = bmmc;
	}

	public String getUsername()
	{
		return username;
	}

	public void setUsername(String username)
	{
		this.username = username;
	}

	public String getPassword()
	{
		return password;
	}

	public void setPassword(String password)
	{
		this.password = password;
	}

	public String getYhsf()
	{
		return yhsf;
	}

	public void setYhsf(String yhsf)
	{
		this.yhsf = yhsf;
	}

	public String getRolename() 
	{
		return rolename;
	}

	public void setRolename(String rolename) 
	{
		this.rolename = rolename;
	}
	
	
}
