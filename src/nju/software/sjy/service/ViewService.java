package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TOperation;
import nju.software.sjy.model.xy.TRole;
import nju.software.sjy.model.xy.TUser;

public interface ViewService
{
	List<TBm> getAllBmList();
	
	List<TBm> getAllWrappedBmList();
	
	List<TBm> getBmList();
	
	List<TBm> getWrappedBmList();
	
	List<String> getBmlxmcList();
	
	List<String> getWrappedBmlxmcList();
	
	List<TUser> getUserList(List<TBm> bmList);
	
	List<TUser> getUserList(String bmmc, List<TBm> bmList);
	
	List<TRole> getAllRoleList();
	
	List<TRole> getAllWrappedRoleList();
	
	String getBmmc(String bmmc);
	
	int getBmbh(int bmbh);
	
	int[] getYears();
	
	int[] getMonths();
	
	int[] getQuarters();
	
	int getYear(int year);
	
	int getMonth(int month);
	
	int getQuarter(int quarter);
	
	List<TOperation> getOperationList();
	
	List<TGypz> getKhzbList();
	
	List<TGypz> getWrappedKhzbList();
	
	String getKhzbmc(String khzbmc);
	
	String getBmlxmc(String bmlxmc);
	
	String getRolename(String rolename);
	
	String getSimpleBmmc(String bmmc);
	
	String getStartYearMonth(String startDate);
	
	String getEndYearMonth(String endDate);
	
	String getStartDate(String startDate);
	
	String getEndDate(String endDate);
}
