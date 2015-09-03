package nju.software.sjy.webservice.bean.response;

public class RyResp
{
	/**
	 * 法院代码
	 */
	private String FYDM;
	

	/**
	 * 书记员代码
	 */
	private String YHDM;
	
	/**
	 * 部门代码
	 */
	private String YHBM;
	
	/**
	 * 书记员名称
	 */
	private String XM;
	
	/**
	 * 用户密码
	 */
	private Integer MD5;
	
	/**
	 * 职务
	 */
	private Integer ZW;
	
	
	
	public RyResp()
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

		
	public String getYHDM() {
		return YHDM;
	}

	public void setYHDM(String yHDM) {
		YHDM = yHDM;
	}

	public String getYHBM() {
		return YHBM;
	}

	public void setYHBM(String yHBM) {
		YHBM = yHBM;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}

	public Integer getMD5() {
		return MD5;
	}

	public void setMD5(Integer mD5) {
		MD5 = mD5;
	}

	public Integer getZW() {
		return ZW;
	}

	public void setZW(Integer zW) {
		ZW = zW;
	}

	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(FYDM).append(",");
		sb.append(YHDM).append(",");
		sb.append(YHBM).append(",");
		
		sb.append(XM).append(",");
		sb.append(MD5).append(",");
		sb.append(ZW);
		
		return sb.toString();
	}
}
