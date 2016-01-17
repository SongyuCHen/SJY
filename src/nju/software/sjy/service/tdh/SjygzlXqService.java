package nju.software.sjy.service.tdh;

import java.util.Date;
import java.util.List;

import nju.software.sjy.model.da.ViewDajgSsfzxx;
import nju.software.sjy.model.tdh.SjygzlAjxx;
import nju.software.sjy.model.tdh.SjygzlBlxq;
import nju.software.sjy.model.tdh.SjygzlKtxq;
import nju.software.sjy.model.tdh.SjygzlSdxq;
import nju.software.sjy.model.xy.LocalBlxq;
import nju.software.sjy.model.xy.LocalBlxqId;
import nju.software.sjy.model.xy.LocalKtxq;
import nju.software.sjy.model.xy.LocalKtxqId;
import nju.software.sjy.model.xy.LocalSdxq;
import nju.software.sjy.model.xy.LocalSdxqId;
import nju.software.sjy.model.xy.LocalZdjz;
import nju.software.sjy.model.xy.LocalZdjzId;
import nju.software.sjy.model.xy.TGzsj;

public interface SjygzlXqService
{
	List<SjygzlBlxq> getBlxqByFyAndYhdm2(String fjm, String yhdm, String kssj, String jssj);
	
	List<SjygzlBlxq> getBlxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);

	List<SjygzlKtxq> getKtxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);

	List<SjygzlSdxq> getSdxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
		
	List<SjygzlAjxx> getAjxxByFydmAndGdjsrq(String fydm, String yhdm, String kssj, String jssj);
	
	/**
	 * 以下几个方法均是从本地获取详情，并可以进行修改
	 * @param fjm
	 * @param yhdm
	 * @param kssj
	 * @param jssj
	 * @return
	 */
	List<LocalBlxq> getLocalBlxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
	
	List<LocalKtxq> getLocalKtxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
	
	List<LocalSdxq> getLocalSdxqByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
	
	List<LocalZdjz> getLocalZdjzByFyAndYhdm(String fjm, String yhdm, String kssj, String jssj);
	
	LocalBlxq getLocalBlxqById(LocalBlxqId id);
	
	LocalKtxq getLocalKtxqById(LocalKtxqId id);
	
	LocalSdxq getLocalSdxqById(LocalSdxqId id);
	
	LocalZdjz getLocalZdjzById(LocalZdjzId id);
	
	void updateLocalBlxq(LocalBlxq localBlxq);
	
	void updateLocalKtxq(LocalKtxq localKtxq);
	
	void updateLocalSdxq(LocalSdxq localSdxq);
	
	void updateLocalZdjz(LocalZdjz localZdjz);
	
	ViewDajgSsfzxx getDaysByAhdm(String ahdm);
	
	List<TGzsj> getSjyGzlFromView(Date date, int type);
}