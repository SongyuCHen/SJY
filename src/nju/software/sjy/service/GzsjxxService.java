package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;

public interface GzsjxxService
{	
	Integer getMaxBh();
	
	void update(TGzsjxx gztb);
	
	void save(TGzsjxx gztb);
	
	void delete(TGzsjxx gztb);
	
	TGzsjxx getGzsjxxByGzsjGzxx(TGzsj gzsj, TGypz gzxx);
	
	List<TGzsjxx> getGzsjxxByGzsjGzlx(TGzsj gzsj, String gzlx);
	
	Integer getMaxSumByGzxxNfJdZt(TGypz gzxx, int nf, int jd, TGypz finalZt);
	
	Integer getMinSumByGzxxNfJdZt(TGypz gzxx, int nf, int jd, TGypz finalZt);
	
	List<Integer> getSzByGzsjlistGzxx(List<TGzsj> tlist, TGypz gzxx);
	
	List<TGzsjxx> getGzsjxxByGzsj(TGzsj gzsj);
}
