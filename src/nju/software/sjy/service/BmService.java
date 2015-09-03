package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;

public interface BmService
{
	
	List<TBm> getAllBm();
	
	List<String> getUserXmOfBm(String bmmc);

	TBm getBmByBmbh(int bmbh);
	
	TBm getBmByBmmc(String bmmc);
	
	List<TBm> getBmByBmlx(TGypz bmlx);
	
	Integer getMaxBmbh();
	
	void saveBm(TBm bm);
	
	void updateBm(TBm bm);
	
	void deleteBm(TBm bm);
	
	List<TBm> getBmExcludeBmlx(TGypz bmlx);
	
	TBm getBmByBmid(String bmid);
}
