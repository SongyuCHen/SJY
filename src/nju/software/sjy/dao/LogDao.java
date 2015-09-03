package nju.software.sjy.dao;

import java.util.Date;
import java.util.List;

import nju.software.sjy.model.xy.TLog;



public interface LogDao {
	
	List<TLog> getAllLog();
	Integer getMaxBh();
	
	void saveLog(TLog log);
	
	List<TLog> getLogBySj(Date sj);

}
