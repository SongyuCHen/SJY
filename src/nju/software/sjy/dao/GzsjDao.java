package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TUser;

public interface GzsjDao 
{
	List<TGzsj> getAllGzsj();
	
	TGzsj getGzsjByBh(int bh);
	
	void saveGzsj(TGzsj gzsj);
	
	void updateGzsj(TGzsj gzsj);
	
	void deleteGzsj(TGzsj gzsj);
	
	Integer getMaxGzsjBh();
	
	void updateAllGzsj(List<TGzsj> tlist);
	
	List<TGzsj> getGzsjByBhArr(Integer[] bhArr);
	
	TGzsj getGzsjByUserNfYf(TUser user, int nf, int yf);
	
	List<TGzsj> getGzsjByUyUserNfJdZt(TUser user, int year, int startMonth, int endMonth, TGypz zt);
	
	List<TGzsj> getGzsjByUserBmDateRange(TUser user, TBm bm, String startDate, String endDate);
	
	List<TGzsj> getGzsjByDateRangeExcludeQtbmlx(String startDate, String endDate, TGypz qtbmlx);
	
	List<TGzsj> getGzsjByBmDateRange(TBm bm, String startDate, String endDate);
	
	TGzsj getGzsjByUserDate(TUser user, String date);
	
	List<TGzsj> getGzsjByUserDateRangeZt(TUser user, String startDate, String endDate, TGypz finalZt);
	
}
