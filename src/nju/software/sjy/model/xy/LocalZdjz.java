package nju.software.sjy.model.xy;

public class LocalZdjz implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3448478129368685015L;
	
	private LocalZdjzId id;
	private String ah;
	private String sjy;
	private String sjymc;
	private String xzsjy;
	private String xzsjymc;
	private Integer zjys;
	private Integer fjys;
	public LocalZdjz(){
		
	}
	
	public LocalZdjz(LocalZdjzId id, String ah, String sjy, String sjymc,
			String xzsjy, String xzsjymc, Integer zjys, Integer fjys) {
		super();
		this.id = id;
		this.ah = ah;
		this.sjy = sjy;
		this.sjymc = sjymc;
		this.xzsjy = xzsjy;
		this.xzsjymc = xzsjymc;
		this.zjys = zjys;
		this.fjys = fjys;
	}

	public LocalZdjzId getId() {
		return id;
	}
	public void setId(LocalZdjzId id) {
		this.id = id;
	}
	public String getAh() {
		return ah;
	}
	public void setAh(String ah) {
		this.ah = ah;
	}
	public String getSjy() {
		return sjy;
	}
	public void setSjy(String sjy) {
		this.sjy = sjy;
	}
	public String getSjymc() {
		return sjymc;
	}
	public void setSjymc(String sjymc) {
		this.sjymc = sjymc;
	}
	public String getXzsjy() {
		return xzsjy;
	}
	public void setXzsjy(String xzsjy) {
		this.xzsjy = xzsjy;
	}
	public String getXzsjymc() {
		return xzsjymc;
	}
	public void setXzsjymc(String xzsjymc) {
		this.xzsjymc = xzsjymc;
	}

	public Integer getZjys() {
		return zjys;
	}

	public void setZjys(Integer zjys) {
		this.zjys = zjys;
	}

	public Integer getFjys() {
		return fjys;
	}

	public void setFjys(Integer fjys) {
		this.fjys = fjys;
	}
	
}
