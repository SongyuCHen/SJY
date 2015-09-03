package nju.software.sjy.webservice.bean.request;

public class ZzjgReq
{
	/**
	 * 法院分级码
	 */
	private String FY;
	
	/**
	 * 部门代码（机构标识）
	 */
	private String JGBS;
	
	
	public ZzjgReq()
	{
		
	}
	public ZzjgReq(String FY)
	{
		this.FY = FY;
	}
	
	public ZzjgReq(String FY, String JGBS)
	{
		this.FY = FY;
		this.JGBS = JGBS;

	}

	public String getFY() {
		return FY;
	}

	public void setFY(String fY) {
		FY = fY;
	}

	public String getJGBS() {
		return JGBS;
	}

	public void setJGBS(String jGBS) {
		JGBS = jGBS;
	}

	
	
}
