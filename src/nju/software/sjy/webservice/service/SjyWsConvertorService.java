package nju.software.sjy.webservice.service;

import java.util.List;

import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.webservice.bean.response.SjyGzlResp;

public interface SjyWsConvertorService 
{
	List<TGzsj> convertToTGzsjList(List<Object> objList, String yearMonth);
	
	TGzsj convertToTGzsj(SjyGzlResp resp, String yearMonth);
}
