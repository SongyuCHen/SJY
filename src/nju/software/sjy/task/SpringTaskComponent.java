package nju.software.sjy.task;

import java.util.Date;
import java.util.List;

import nju.software.sjy.model.xy.TGzsj;
import nju.software.sjy.model.xy.TLog;
import nju.software.sjy.service.GzsjService;
import nju.software.sjy.service.LogService;
import nju.software.sjy.service.tdh.SjygzlXqService;
import nju.software.sjy.webservice.service.SjyWsService;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * spring task 定时任务
 * 
 *     cronExpression的配置说明
 *     字段   允许值   允许的特殊字符
 *     秒         0-59    , - * /
 * 	        分         0-59    , - * /
 *     小时    0-23    , - * /
 *     日期    1-31    , - * ? / L W C
 *     月份    1-12 或者 JAN-DEC    , - * /
 *     星期    1-7 或者 SUN-SAT    , - * ? / L C #
 *     年（可选）    留空, 1970-2099    , - * / 
 *     - 区间  
 *     * 通配符  
 *     ? 你不想设置那个字段
 */
@Component
public class SpringTaskComponent
{
	private static Logger log = Logger.getLogger(SpringTaskComponent.class);
	
	@Autowired
	private GzsjService gzsjService;
	
	@Autowired
	private SjyWsService sjyWsService;
	
	@Autowired
	private SjygzlXqService sjygzlxqService;
	
	@Autowired
	private LogService logService;
	
	/**
	 * 每天晚上12点触发
	 */
	@Scheduled(cron="0 0 0 * * ?")
	public void getGzsjJob()
	{
		log.info("start to fetch gzsj by webservice from ...");
		
		/* 待实现 */

		
		Date date = new Date();
		int type = 1;
//		List<TGzsj> gzsjList = sjyWsService.getSjyGzl(date, type);
		sjygzlxqService.getSjyGzlFromView(date, 0);
		TLog tLog = new TLog();
		int maxbh = logService.getMaxBh();
		maxbh++;
		tLog.setBh(maxbh);
		tLog.setSj(date);
		tLog.setNr("系统自动提取数据");
		logService.saveLog(tLog);
		log.info("finish fetching gzsj");
	}
}
