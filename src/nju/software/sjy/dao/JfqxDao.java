package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TUser;

public interface JfqxDao 
{
	List<TJfqx> getJfqxByBmNY(TJfqx jfqx);
	
	Integer getMaxBh();
	
	void save(TJfqx jfqx);
	
	void update(TJfqx jfqx);
	
	TJfqx getJfqxByBh(int bh);

	List<TJfqx> getAllJfqx();

	void delete(TJfqx jfqx);

	List<TJfqx> getJfqxByRybhDateRangeKfxm(int rybh, String startDatetime, String endDatetime, TJfkfxm kfxm);
	
	void updateAllJfqx(List<TJfqx> tlist);
	
	List<TJfqx> getJfqxByBhArr(Integer[] bhArr);
	
	List<TJfqx> getJfqxByUserDateRangeJfxmZt(TUser user, String startTime, String endTime, TJfkfxm jfxm, TGypz zt);
	
	Integer getCountOfJfqxByUserNfJdJfxmZt(TUser user, int nf, int startYf, int endYf, TJfkfxm jfxm, TGypz zt);
	
	List<TJfqx> getJfqxByDateRangeExcludeQtbmlx(String startDate, String endDate, TGypz qtbmlx);
	
	List<TJfqx> getJfqxByBmDateRange(TBm bm, String startDate, String endDate);
	
	List<TJfqx> getJfqxByUserDateRange(TUser user, String startDate, String endDate);
	
	int getCountOfJfqxByUserDateRangeJfxmZt(TUser user, String startDate, String endDate, TJfkfxm jfxm, TGypz zt);
}
