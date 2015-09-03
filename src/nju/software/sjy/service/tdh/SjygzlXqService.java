package nju.software.sjy.service.tdh;

import java.util.Date;
import java.util.List;

import nju.software.sjy.model.da.ViewDajgSsfzxx;
import nju.software.sjy.model.tdh.SjygzlAjxx;
import nju.software.sjy.model.tdh.SjygzlBlxq;
import nju.software.sjy.model.tdh.SjygzlKtxq;
import nju.software.sjy.model.tdh.SjygzlSdxq;
import nju.software.sjy.model.xy.TGzsj;

public interface SjygzlXqService
{
	List<SjygzlBlxq> getBlxqByFyAndYhdm2(String fjm, String yhdm, String kssj, String jssj);
	
	List<SjygzlBlxq> getBlxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);

	List<SjygzlKtxq> getKtxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);

	List<SjygzlSdxq> getSdxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
		
	List<SjygzlAjxx> getAjxxByFydmAndGdjsrq(String fydm, String yhdm, String kssj, String jssj);
	
	ViewDajgSsfzxx getDaysByAhdm(String ahdm);
	
	List<TGzsj> getSjyGzlFromView(Date date, int type);
}