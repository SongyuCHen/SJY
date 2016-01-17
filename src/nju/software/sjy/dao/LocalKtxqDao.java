package nju.software.sjy.dao;

import nju.software.sjy.model.xy.LocalKtxq;

public interface LocalKtxqDao {

	LocalKtxq getLocalKtxqByFydm(String fjm,String ahdm,String ktrq,String kssj);
	
	void updateLocalKtxq(LocalKtxq localKtxq);
	
	void saveLocalKtxq(LocalKtxq localKtxq); 
}
