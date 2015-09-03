package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TKfqx;
import nju.software.sjy.model.xy.TUser;

public interface KfqxDao 
{

	Integer getMaxBh();

	void save(TKfqx kfqx);

	void update(TKfqx kfqx);

	List<TKfqx> getAllKfqx();

	List<TGypz> getAllKflb();

	List<TJfkfxm> getAllKfxm();

	void save(TGypz kflb);

	int getMaxKflbPzbh(String lbStr);

	int getMaxKflbBh();

	void deleteKflb(TGypz gypzByBh);

	void editKflb(TGypz kflb);

	int getMaxKfxmBh();

	void addKfxm(TJfkfxm kfxm);

	TJfkfxm getKfxmByBh(int bh);

	void editKfxm(TJfkfxm kfxm);

	void deleteKfxm(TJfkfxm kfxm);

	int getMaxKfqxBh();

	void addKfqx(TKfqx kfqx);

	TKfqx getKfqxByBh(int bh);

	void deleteKfqx(TKfqx kfqx);

	void editKfqx(TKfqx kfqx);
	
	List<TKfqx> getKfqxByTUser(TUser user);

	List<TGypz> getAllJflb();
	
	List<TJfkfxm> getAllJfxm();

	List<TKfqx> getKfqxByDateRange(String startDatetime, String endDatetime);
	
	double getFsByLbMc(TGypz gypz, String mc);
	
	List<TJfkfxm> getKfxmByGypz(TGypz gypz);
	
	List<TKfqx> getKfqxByNfYfAndBmbh(int nf, int yf, int bmbh);
	
	List<TKfqx> getKfqxByRybhDateRangeKfxm(int rybh, String startDatetime, String endDatetime, TJfkfxm kfxm);

	Integer getCountByRybhDateRangeKfxm(int rybh, String startTime, String endTime, TJfkfxm kfxm);
	
	List<TKfqx> getKfqxByUserDateRangeKfxm(TUser user, String startTime, String endTime, TJfkfxm kfxm);
	
	Integer getCountByUserDateRangeKfxm(TUser user, String startTime, String endTime, TJfkfxm kfxm);
	
	List<TKfqx> getKfqxByUserDateRange(TUser user, String startDate, String endDate);
	
	List<TKfqx> getKfqxByDateRangeExcludeQtbmlx(String startDate, String endDate, TGypz qtbmlx);
	
	List<TKfqx> getKfqxByBmDateRange(TBm bm, String startDate, String endDate);
}
