package nju.software.sjy.webservice.bean.response;

public class ZzjgResp
{
	/**
	 * 法院代码
	 */
	private String FY;
	
	/**
	 * 部门代码
	 */
	private String BMDM;
	
	/**
	 * 机构标识
	 */
	private String JGBS;
	
	/**
	 * 名称
	 */
	private String MC;
	
	
	
	public String getFY() {
		return FY;
	}



	public void setFY(String fY) {
		FY = fY;
	}



	public String getBMDM() {
		return BMDM;
	}



	public void setBMDM(String bMDM) {
		BMDM = bMDM;
	}



	public String getJGBS() {
		return JGBS;
	}



	public void setJGBS(String jGBS) {
		JGBS = jGBS;
	}



	public String getMC() {
		return MC;
	}



	public void setMC(String mC) {
		MC = mC;
	}



	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(FY).append(",");
		sb.append(BMDM).append(",");
		sb.append(JGBS).append(",");
	
		sb.append(MC);
		
		return sb.toString();
	}
}
