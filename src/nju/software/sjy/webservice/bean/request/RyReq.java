package nju.software.sjy.webservice.bean.request;

public class RyReq
{
	/**
	 * 法院分级码
	 */
	private String FJM;
	
	/**
	 * 部门代码、机构标识
	 */
	private String SZJG;
	
	/**
	 * 人员标识
	 */
	private String RYBS;
	
	/**
	 * 姓名
	 */
	private String XM;
	
	public RyReq()
	{
		
	}
	public RyReq(String FJM)
	{
		this.FJM = FJM;
	}

	public RyReq(String FJM, String SZJG, String RYBS, String XM) {
		this.FJM = FJM;
		this.SZJG = SZJG;
		this.RYBS = RYBS;
		this.XM = XM;
	}

	public String getFJM() {
		return FJM;
	}

	public void setFJM(String fJM) {
		FJM = fJM;
	}

	public String getSZJG() {
		return SZJG;
	}

	public void setSZJG(String sZJG) {
		SZJG = sZJG;
	}

	public String getRYBS() {
		return RYBS;
	}

	public void setRYBS(String rYBS) {
		RYBS = rYBS;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}
	
	
	
}
