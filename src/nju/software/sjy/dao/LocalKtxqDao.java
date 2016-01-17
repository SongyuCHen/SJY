package nju.software.sjy.dao;

import java.util.List;

import nju.software.sjy.model.xy.LocalKtxq;

public interface LocalKtxqDao {

	LocalKtxq getLocalKtxqByFydm(String fjm,String ahdm,String ktrq,String kssj);
	
	List<LocalKtxq> getLocalKtxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
	
	void updateLocalKtxq(LocalKtxq localKtxq);
	
	void saveLocalKtxq(LocalKtxq localKtxq); 
}
