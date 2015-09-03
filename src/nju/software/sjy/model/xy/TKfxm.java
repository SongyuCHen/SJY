package nju.software.sjy.model.xy;


public class TKfxm implements java.io.Serializable{
	private static final long serialVersionUID = 1L;
	private int bh;
	/*扣分项目类别*/
	private TGypz lb;
	/*扣分项目名称*/
	private String mc;
	/*分数*/
	private double fs;
	/*加分还是扣分*/
	private int jfkf;
	
	//private Set<TKfqx> kfqxs = new HashSet<TKfqx>();
	
	public int getBh() {
		return bh;
	}

	public void setBh(int bh) {
		this.bh = bh;
	}

	public TGypz getLb() {
		return lb;
	}

	public void setLb(TGypz lb) {
		this.lb = lb;
	}

	public String getMc() {
		return mc;
	}

	public void setMc(String mc) {
		this.mc = mc;
	}

	public double getFs() {
		return fs;
	}

	public void setFs(double fs) {
		this.fs = fs;
	}

	public TKfxm(){}
	
	public TKfxm(int bh, TGypz lb, String mc, double fs){
		this.bh = bh;
		this.lb = lb;
		this.mc = mc;
		this.fs = fs;
	}

//	public Set<TKfqx> getKfqxs() {
//		return kfqxs;
//	}
//
//	public void setKfqxs(Set<TKfqx> kfqxs) {
//		this.kfqxs = kfqxs;
//	}

	public int getJfkf() {
		return jfkf;
	}

	public void setJfkf(int jfkf) {
		this.jfkf = jfkf;
	}
}
