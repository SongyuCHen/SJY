package nju.software.sjy.model.tdh;

import java.util.Date;

public class EajJz implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1027576076100831817L;
	
	private EajJzId id;
	private String mc;
	private String wjgs;
	

	private Integer wjzs;
	private Date zzsj;
	public EajJz(){
		
	}
	
	public EajJz(EajJzId id){
		this.id = id;
	}
	public EajJz(EajJzId id, String mc, Integer wjzs, Date zzsj) {
		this.id = id;
		this.mc = mc;
		this.wjzs = wjzs;
		this.zzsj = zzsj;
	}

	public EajJzId getId() {
		return id;
	}

	public void setId(EajJzId id) {
		this.id = id;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public Integer getWjzs() {
		return wjzs;
	}

	public void setWjzs(Integer wjzs) {
		this.wjzs = wjzs;
	}

	public Date getZzsj() {
		return zzsj;
	}

	public void setZzsj(Date zzsj) {
		this.zzsj = zzsj;
	}
	public String getWjgs() {
		return wjgs;
	}

	public void setWjgs(String wjgs) {
		this.wjgs = wjgs;
	}
	

}
