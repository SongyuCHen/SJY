package nju.software.sjy.webservice.service;

import java.util.Date;
import java.util.List;

import nju.software.sjy.model.xy.TBm;
import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TUser;

public interface SjyWsService
{
	List<TGzsj> getSjyGzl(Date date, int type);
	List<TUser> getAllRy();
	List<TBm> getAllZzjg();
}
