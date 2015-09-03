package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TGzsjxx;
import nju.software.sjy.model.xy.TUser;

public interface GzsjxxDao
{
	
	void update(TGzsjxx gztb);
	
	void save(TGzsjxx gztb);
	
	Integer getMaxBh();
	
	void delete(TGzsjxx gztb);
	
	TGzsjxx getGzsjxxByGzsjGzxx(TGzsj gzsj, TGypz gzxx);
	
	List<TGzsjxx> getGzsjxxByGzsjGzlx(TGzsj gzsj, String gzlx);
	
	Integer getMaxSzByNfJdGzxx(int nf, int fromMonth, int toMonth, TGypz gzxx);
	
	Integer getMinSzByNfJdGzxx(int nf, int fromMonth, int toMonth, TGypz gzxx);
	
	Integer getSzByRybhNfJdGzxx(TUser user, int nf, int fromMonth, int toMonth, TGypz gzxx);
	
	Integer getSzByGzsjGzxx(TGzsj gzsj, TGypz gzxx);
	
	Integer getMaxSumByGzxxNfJdZt(TGypz gzxx, int nf, int startMonth, int endMonth, TGypz finalZt);
	
	Integer getMinSumByGzxxNfJdZt(TGypz gzxx, int nf, int startMonth, int endMonth, TGypz finalZt);
	
	List<Integer> getSzByGzsjlistGzxx(List<TGzsj> tlist, TGypz gzxx);
	
	List<TGzsjxx> getGzsjxxByGzsj(TGzsj gzsj);
	
	Integer getMaxSumByGzxxDateRangeZt(TGypz gzxx, String startDate, String endDate, TGypz finalZt);
	
	Integer getMinSumByGzxxDateRangeZt(TGypz gzxx, String startDate, String endDate, TGypz finalZt);
}
