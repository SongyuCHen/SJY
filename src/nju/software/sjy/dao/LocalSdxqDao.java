package nju.software.sjy.dao;

import nju.software.sjy.model.xy.LocalSdxq;

public interface LocalSdxqDao {
	LocalSdxq getSdxqById(String fjm,String ahdm,String sdrq,String sdddsr);
	void save(LocalSdxq localSdxq);
	void update(LocalSdxq localSdxq);

}
