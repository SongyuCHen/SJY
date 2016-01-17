package nju.software.sjy.mapper;

import java.io.Serializable;

public class MSjy implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1277315044911480094L;
	
	private String xm;
	private String id;
	public MSjy(){
		
	}
	public MSjy(String xm, String id) {
		super();
		this.xm = xm;
		this.id = id;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	

}
