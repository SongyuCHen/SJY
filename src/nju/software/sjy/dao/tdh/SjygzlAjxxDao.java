package nju.software.sjy.dao.tdh;

import java.util.List;

import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.model.tdh.SjygzlAjxx;

public interface SjygzlAjxxDao 
{
	List<SjygzlAjxx> getAjxxByFydmAndGdjsrq(String fydm,String yhdm, String kssj, String jssj);
	List<SjygzlAjxx> getAjxxByFydmAndYhdm(String fydm,String yhdm);
	List<SjygzlAjxx> getWGDAjxxByFydmAndYhdm(String fydm,String yhdm, String kssj);
	MGzs getAjsByFydmAndYhdm(String fydm,String yhdm, String kssj, String jssj);

}
