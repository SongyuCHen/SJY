package nju.software.sjy.mapper;

import java.io.Serializable;

public class MKtjl implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String kssj;
	private String jssj;
	
	public MKtjl(){
		
	}
	public MKtjl(String kssj, String jssj) {
		super();
		this.kssj = kssj;
		this.jssj = jssj;
	}
	public String getKssj() {
		return kssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	

}
