package nju.software.sjy.dao.tdh;

import java.util.List;

import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.model.tdh.SjygzlSdxq;

public interface SjygzlSdxqDao 
{
	List<SjygzlSdxq> getSdxqByFyAndYhdm(String fjm,String yhdm,String kssj, String jssj );
	
	MGzs getSdsByFy(String fjm, String yhdm, String kssj, String jssj);

}
