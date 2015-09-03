package nju.software.sjy.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nju.software.sjy.dao.LogDao;
import nju.software.sjy.model.xy.TLog;
import nju.software.sjy.service.LogService;

@Service
public class LogServiceImpl implements LogService {
	@Autowired
	private LogDao logDao;

	@Override
	public List<TLog> getAllLog() {
		// TODO Auto-generated method stub
		return logDao.getAllLog();
	}

	@Override
	public Integer getMaxBh() {
		// TODO Auto-generated method stub
		return logDao.getMaxBh();
	}

	@Override
	public void saveLog(TLog log) {
		// TODO Auto-generated method stub
		logDao.saveLog(log);
	}

	@Override
	public List<TLog> getLogBySj(Date sj) {
		// TODO Auto-generated method stub
		return logDao.getLogBySj(sj);
	}

}
