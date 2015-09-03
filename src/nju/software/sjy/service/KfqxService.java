package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.mapper.MKflb;
import nju.software.sjy.mapper.MKfqx;
import nju.software.sjy.mapper.MKfxm;
import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TJfkfxm;
import nju.software.sjy.model.xy.TKfqx;
import nju.software.sjy.model.xy.TUser;

public interface KfqxService 
{
	Integer getMaxBh();
	
	void save(TKfqx kfqx);
	
	void update(TKfqx kfqx);

	List<MKfqx> getAllKfqx();

	List<MKflb> getAllKflb();

	List<MKfxm> getAllKfxm();

	void addKflb(TGypz jflb);

	int getMaxKflbPzbh();

	int getMaxKflbBh();

	void deleteKflb(TGypz gypzByBh);

	void editKflb(TGypz kflb);

	int getMaxKfxmBh();

	void addKfxm(TJfkfxm kfxm);

	TJfkfxm getKfxmByBh(int parseInt);

	void editKfxm(TJfkfxm kfxm);

	void deleteKfxm(TJfkfxm kfxmByBh);

	int getMaxKfqxBh();

	void addKfqx(TKfqx kfqx);

	TKfqx getKfqxByBh(int bh);

	void deleteKfqx(TKfqx kfqxByBh);

	void editKfqx(TKfqx kfqx);

	List<MKflb> getAllJflb();

	int getMaxJflbPzbh();

	List<MKfxm> getAllJfxm();

	double getFsByLbMc(TGypz gypz, String mc);
	
	List<TJfkfxm> getKfxmByGypz(TGypz gypz);

	List<TKfqx> getKfqxByUserDateRangeKfxm(TUser user, String startTime, String endTime, TJfkfxm kfxm);
	
	Integer getCountByUserDateRangeKfxm(TUser user, String startTime, String endTime, TJfkfxm kfxm);
	
	List<TKfqx> getKfqxByUserDateRange(TUser user, String startDate, String endDate);
	
	List<TKfqx> getKfqxByDateRangeExcludeQtbmlx(String startDate, String endDate, TGypz qtbmlx);
	
	List<TKfqx> getKfqxByBmDateRange(TBm bm, String startDate, String endDate);
}
