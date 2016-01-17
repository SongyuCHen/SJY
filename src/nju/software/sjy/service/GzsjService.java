package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TUser;

public interface GzsjService 
{
	List<TGzsj> getAllGzsj();
	
	TGzsj getGzsjByBh(int bh);
	
	void saveGzsj(TGzsj gzsj);
	
	void updateGzsj(TGzsj gzsj);
	
	void deleteGzsj(TGzsj gzsj);
	
	Integer getMaxGzsjBh();
	
	void updateAllGzsj(List<TGzsj> tlist);
	
	List<TGzsj> getGzsjByBhArr(Integer[] bhArr);
	
	TGzsj  getGzsjByUserNfYf(TUser user, int nf, int yf);
	
	List<TGzsj> getGzsjByUserNfJdZt(TUser user, int nf, int jd, TGypz finalZt);
	
	List<TGzsj> getGzsjByUserBmDateRange(TUser user, TBm bm, String startDate, String endDate);
	
	List<TGzsj> getGzsjByDateRangeExcludeQtbmlx(String startDate, String endDate, TGypz qtbmlx);
	
	List<TGzsj> getGzsjByBmDateRange(TBm bm, String startDate, String endDate);
	
	TGzsj getGzsjByUserDate(TUser user, String date);
}
