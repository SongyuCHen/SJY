package nju.software.sjy.service;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TUser;

public interface StatisticsService
{
	int getCount(TGypz gzxx, TUser user, int nf, int jd);
	
	int getCountOfGZSJ(TGypz gzxx, TUser user, int nf, int jd);
	
	int getCountOfGZZL(TGypz gzxx, TUser user, int nf, int jd);
	
	int getCountOfZFSJ(TGypz gzxx, TUser user, int nf, int jd);
	
	int getCountOfJFQX(TGypz gzxx, TUser user, int nf, int jd);
	
	int getMaxCountOfApprovedGZSJ(TGypz gzxx, int nf, int jd);
	
	int getMinCountOfApprovedGZSJ(TGypz gzxx, int nf, int jd);
	
}
