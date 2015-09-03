package nju.software.sjy.bean;

/**
 * 状态信息基类
 * 用于封装返回的状态和信息
 * 
 * @author zceolrj
 *
 */
public class StatusMsg
{
	private int status;
	
	private String msg;
	
	public StatusMsg()
	{
		
	}
	
	public StatusMsg(int status, String msg)
	{
		this.status = status;
		this.msg = msg;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getMsg()
	{
		return msg;
	}

	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	
	
}
