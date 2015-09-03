package nju.software.sjy.dao.tdh;

import java.util.List;

import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.model.tdh.SjygzlKtxq;

public interface SjygzlKtxqDao 
{
	List<SjygzlKtxq> getKtxqByFyAndYhdm(String fjm,String yhdm,String kssj, String jssj );
	
	MGzs getKtsByFy(String fjm, String yhdm, String kssj, String jssj );
	
	MGzs getKtljsjByFy(String fjm, String yhdm, String kssj, String jssj );
	

}
