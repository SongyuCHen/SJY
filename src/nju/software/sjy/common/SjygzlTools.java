package nju.software.sjy.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SjygzlTools
{
	public static final String ZDJZ = "装订卷宗";
	
	public final static String TSJL = "庭审记录";
	
	public final static String TSLJSJ = "庭审累计时间";
	
	public final static String SDS = "送达数";
	
	public final static String BLS = "笔录数";
	
	public final static String AH = "案号";
	
	public final static String AJH = "正卷页数";
	
	public final static String YS = "副卷页数";
	
	public final static String GDRQ = "归档日期";
	
	public final static String SJY = "书记员";
	
	public final static String DBSJY = "代办书记员";
	
	public final static String BLWJM = "笔录文件名";
	
	public final static String BLZS = "笔录字数";
	
	public final static String BLTJSJ = "笔录提交时间";
	
	public final static String KTDD = "开庭地点";
	
	public final static String KTRQ = "开庭日期";
	
	public final static String KSSJ_JSSJ = "开始时间-结束时间";
	
	public final static String SDDSR = "送达当事人";
	
	public final static String SDDZ = "送达地址";
	
	public final static String SDRQ = "送达日期";
	
	public static List<String> ZDJZ_ATTR_LIST = new ArrayList<String>();
	
	public static List<String> BLXQ_ATTR_LIST = new ArrayList<String>();
	
	public static List<String> KTXQ_ATTR_LIST = new ArrayList<String>();
	
	public static List<String> SDXQ_ATTR_LIST = new ArrayList<String>();
	
	public static Map<String, List<String>> SJYGZL_MAP = new HashMap<String, List<String>>();
	
	static
	{
		ZDJZ_ATTR_LIST.add(AH);
		ZDJZ_ATTR_LIST.add(AJH);
		ZDJZ_ATTR_LIST.add(YS);
		ZDJZ_ATTR_LIST.add(GDRQ);
		ZDJZ_ATTR_LIST.add(SJY);
		ZDJZ_ATTR_LIST.add(DBSJY);
		
		BLXQ_ATTR_LIST.add(AH);
		BLXQ_ATTR_LIST.add(BLWJM);
		BLXQ_ATTR_LIST.add(BLZS);
		BLXQ_ATTR_LIST.add(BLTJSJ);
		BLXQ_ATTR_LIST.add(SJY);
		BLXQ_ATTR_LIST.add(DBSJY);
		
		KTXQ_ATTR_LIST.add(AH);
		KTXQ_ATTR_LIST.add(KTDD);
		KTXQ_ATTR_LIST.add(KTRQ);
		KTXQ_ATTR_LIST.add(KSSJ_JSSJ);
		KTXQ_ATTR_LIST.add(SJY);
		KTXQ_ATTR_LIST.add(DBSJY);
		
		SDXQ_ATTR_LIST.add(AH);
		SDXQ_ATTR_LIST.add(SDDSR);
		SDXQ_ATTR_LIST.add(SDDZ);
		SDXQ_ATTR_LIST.add(SDRQ);
		SDXQ_ATTR_LIST.add(SJY);
		SDXQ_ATTR_LIST.add(DBSJY);
		
		SJYGZL_MAP.put(ZDJZ, ZDJZ_ATTR_LIST);
		SJYGZL_MAP.put(BLZS, BLXQ_ATTR_LIST);
		SJYGZL_MAP.put(TSJL, KTXQ_ATTR_LIST);
		SJYGZL_MAP.put(TSLJSJ, KTXQ_ATTR_LIST);
		SJYGZL_MAP.put(SDS, SDXQ_ATTR_LIST);
	}
	
	public static List<String> getAttrList(String attrname)
	{
		return SJYGZL_MAP.get(attrname);
	}
}
