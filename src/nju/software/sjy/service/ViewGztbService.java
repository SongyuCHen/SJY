package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TJfqx;
import nju.software.sjy.model.xy.TKfqx;

public interface ViewGztbService
{
	
	List<TGzsj> getGzsjByBmmcDateRange(String bmmc, String startDate, String endDate);
	
	List<TJfqx> getJfqxByBmbhDateRange(int bmbh, String startDate, String endDate);
	
	List<TKfqx> getKfqxByGmbhDateRange(int bmbh, String startDate, String endDate);
}
