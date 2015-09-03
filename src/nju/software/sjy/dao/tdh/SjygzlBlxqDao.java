package nju.software.sjy.dao.tdh;

import java.util.Date;
import java.util.List;
import nju.software.sjy.mapper.MGzs;
import nju.software.sjy.model.tdh.SjygzlBlxq;

public interface SjygzlBlxqDao 
{
	List<SjygzlBlxq> getBlxqByFyAndYhdm(String fjm, String yhdm, Date kssj, Date jssj);
	
	MGzs getBlsByFy(String fjm, String yhdm, Date kssj, Date jssj);
	

}
