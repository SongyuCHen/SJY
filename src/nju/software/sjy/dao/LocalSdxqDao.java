package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.LocalSdxq;

public interface LocalSdxqDao {
	LocalSdxq getSdxqById(String fjm,String ahdm,String sdrq,String sdddsr);
	void save(LocalSdxq localSdxq);
	void update(LocalSdxq localSdxq);
	List<LocalSdxq> getLocalSdxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
}
