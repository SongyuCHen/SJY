package nju.software.sjy.bean;

/**
 * 状态信息类，继承于StatusMsg
 * 附加字段zt用于标识工作实绩、加分情形的状态
 * 
 * @author zceolrj
 *
 */
public class ZtStatusMsg extends StatusMsg
{
	private String zt;
	
	public ZtStatusMsg()
	{
		super();
	}
	
	public ZtStatusMsg(int status, String msg, String zt)
	{
		super(status, msg);
		this.zt = zt;
	}

	public String getZt()
	{
		return zt;
	}

	public void setZt(String zt)
	{
		this.zt = zt;
	}
	
	
}
