package nju.software.sjy.service;

import java.util.List;

import nju.software.sjy.model.xy.TGypz;
import nju.software.sjy.model.xy.TUser;

public interface ScoreService
{
	/**
	 * 装订卷宗页数全院最高的得30分
	 */
	final static int ZDJZ_MAX_SCORE = 40;
	
	/**
	 * 庭审记录字数全院最高的得45分
	 */
	final static int TSJL_MAX_SCORE = 45;
	
	/**
	 * 立案数在本庭最高的得45分
	 */
	final static int LAS_MAX_SCORE = 45;
	
	/**
	 * 录入字数在本庭最高的得30分
	 */
	final static int LRZS_MAX_SCORE = 30;
	
	/**
	 * 工作质量的基础分
	 */
	final static int GZZL_BASE_SCORE = 0;
	
	/**
	 * 作风守纪的基础分
	 */
	final static int ZFSJ_BASE_SCORE = 0;
	
	/**
	 * 调研成果的最大加分值
	 */
	final static int DYCG_MAX_SCORE = 5;
	
	final static double RATE = 0.6;
	
	/**
	 * 计算规则得分
	 * 
	 * @param gz		规则
	 * @param gzxxList	规则细项
	 * @param user		用户
	 * @param nf		年份
	 * @param jd		季度
	 * @return			得分
	 */
	double getScore(TGypz gz, List<TGypz> gzxxList, TUser user, int nf, int jd);
}
