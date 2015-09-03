package nju.software.sjy.webservice.bean.request;

public class SjyGzlReq
{
	/**
	 * 法院分级码
	 */
	private String FJM;
	
	/**
	 * 用户代码
	 */
	private String YHDM;
	
	/**
	 * 开始时间
	 */
	private String KSSJ;
	
	/**
	 * 结束时间
	 */
	private String JSSJ;
	
	public SjyGzlReq()
	{
		
	}
	
	public SjyGzlReq(String FJM, String YHDM, String KSSJ, String JSSJ)
	{
		this.FJM = FJM;
		this.YHDM = YHDM;
		this.KSSJ = KSSJ;
		this.JSSJ = JSSJ;
	}

	public String getFJM()
	{
		return FJM;
	}

	public void setFJM(String fJM)
	{
		FJM = fJM;
	}

	public String getYHDM()
	{
		return YHDM;
	}

	public void setYHDM(String yHDM)
	{
		YHDM = yHDM;
	}

	public String getKSSJ()
	{
		return KSSJ;
	}

	public void setKSSJ(String kSSJ)
	{
		KSSJ = kSSJ;
	}

	public String getJSSJ()
	{
		return JSSJ;
	}

	public void setJSSJ(String jSSJ)
	{
		JSSJ = jSSJ;
	}
	
}
