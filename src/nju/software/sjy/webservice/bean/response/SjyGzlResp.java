package nju.software.sjy.webservice.bean.response;

public class SjyGzlResp
{
	/**
	 * 法院代码
	 */
	private String FYDM;
	
	/**
	 * 部门代码
	 */
	private String BMDM;
	
	/**
	 * 部门名称
	 */
	private String BMMC;
	
	/**
	 * 书记员代码
	 */
	private String YHDM;
	
	/**
	 * 书记员名称
	 */
	private String YHXM;
	
	/**
	 * 案件数
	 */
	private Integer AJS;
	
	/**
	 * 开庭数
	 */
	private Integer KTS;
	
	/**
	 * 电子档案页数
	 */
	private Integer DAYS;
	
	/**
	 * 送达数
	 */
	private Integer SDS;
	
	/**
	 * 开庭时间
	 */
	private Integer KTSJ;
	
	/**
	 * 笔录字数
	 */
	private Integer BLZS;
	
	public SjyGzlResp()
	{
		
	}

	public String getFYDM()
	{
		return FYDM;
	}

	public void setFYDM(String fYDM)
	{
		FYDM = fYDM;
	}

	public String getBMDM()
	{
		return BMDM;
	}

	public void setBMDM(String bMDM)
	{
		BMDM = bMDM;
	}

	public String getBMMC()
	{
		return BMMC;
	}

	public void setBMMC(String bMMC)
	{
		BMMC = bMMC;
	}

	public String getYHDM()
	{
		return YHDM;
	}

	public void setYHDM(String yHDM)
	{
		YHDM = yHDM;
	}

	public String getYHXM()
	{
		return YHXM;
	}

	public void setYHXM(String yHXM)
	{
		YHXM = yHXM;
	}

	public Integer getAJS()
	{
		return AJS;
	}

	public void setAJS(Integer aJS)
	{
		AJS = aJS;
	}

	public Integer getKTS()
	{
		return KTS;
	}

	public void setKTS(Integer kTS)
	{
		KTS = kTS;
	}

	public Integer getDAYS()
	{
		return DAYS;
	}

	public void setDAYS(Integer dAYS)
	{
		DAYS = dAYS;
	}

	public Integer getSDS()
	{
		return SDS;
	}

	public void setSDS(Integer sDS)
	{
		SDS = sDS;
	}

	

	public Integer getKTSJ() {
		return KTSJ;
	}

	public void setKTSJ(Integer kTSJ) {
		KTSJ = kTSJ;
	}

	public Integer getBLZS()
	{
		return BLZS;
	}

	public void setBLZS(Integer bLZS)
	{
		BLZS = bLZS;
	}
	
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(FYDM).append(",");
		sb.append(BMDM).append(",");
		sb.append(BMMC).append(",");
		
		sb.append(YHDM).append(",");
		sb.append(YHXM).append(",");
		sb.append(AJS).append(",");
		
		sb.append(KTS).append(",");
		sb.append(DAYS).append(",");
		sb.append(KTSJ).append(",");
		
		sb.append(BLZS);
		
		return sb.toString();
	}
}
