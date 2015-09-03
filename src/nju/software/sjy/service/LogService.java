package nju.software.sjy.service;

import java.util.Date;
import java.util.List;

import nju.software.sjy.model.xy.TLog;

public interface LogService {
	
	List<TLog> getAllLog();
	Integer getMaxBh();
	
	void saveLog(TLog log);
	
	List<TLog> getLogBySj(Date sj);

}
