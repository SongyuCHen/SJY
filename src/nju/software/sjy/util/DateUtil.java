package nju.software.sjy.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import nju.software.sjy.common.Constants;

/**
 * 日期工具类
 * 
 * @author zceolrj
 *
 */
public class DateUtil
{
	/**
	 * 标准的"年-月-日"
	 */
	private static final String DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 只包含"年-月"
	 */
	private static final String YEAR_MONTH_FORMAT = "yyyy-MM";
	
	private static SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
	
	private static SimpleDateFormat yearMonthSdf = new SimpleDateFormat(YEAR_MONTH_FORMAT);
	
	private static final int[] QUARTERS = {1, 2, 3, 4};
	
	private static final String DATE_FORMAT_WS = "yyyyMMdd";
	
	private static SimpleDateFormat SDF_WS = new SimpleDateFormat(DATE_FORMAT_WS);
	
	/**
	 * 获取当前的年份
	 */
	public static int getCurrentYear()
	{
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.YEAR);
	}
	
	/**
	 * 获取当前的月份
	 */
	public static int getCurrentMonth()
	{
		Calendar c = Calendar.getInstance();
		return c.get(Calendar.MONTH) + 1;
	}
	
	/**
	 * 获取当前季度
	 */
	public static int getCurrentQuarter()
	{
		int currentMonth = getCurrentMonth();
		int index = (currentMonth - 1)/3;
		return QUARTERS[index];
	}
	
	public static int[] getPastYears()
	{
		return getPastYears(Constants.YEAR_NUM);
	}
	
	public static int[] getPastYears(int num)
	{
		int years[] = new int[num];
		int start = getCurrentYear();
		for(int i=0;i<num;i++)
		{
			years[i] = start - i;
		}
		
		return years;
	}
	
	public static int[] getQuarters()
	{
		return QUARTERS;
	}
	
	public static int[] getMonthsByQuarter(int quarter)
	{
		int[] months = new int[3];
		if(quarter == 1)
		{
			months[0] = 1;
			months[1] = 2;
			months[2] = 3;
		}
		else if(quarter == 2)
		{
			months[0] = 4;
			months[1] = 5;
			months[2] = 6;
		}
		else if(quarter == 3)
		{
			months[0] = 7;
			months[1] = 8;
			months[2] = 9;
		}
		else if(quarter == 4)
		{
			months[0] = 10;
			months[1] = 11;
			months[2] = 12;
		}
		
		return months;
	}
	
	public static String getFormatStr(Date date)
	{
		return sdf.format(date);
	}
	
	public static String getSimpleFormatStr(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return SDF_WS.format(c.getTime());
	}
	
	/**
	 * 根据季度获取该季度对应的起始月份
	 * 
	 * @param quarter	季度
	 * @return			起始月份
	 */
	public static int[] getRangeOfQuarter(int quarter)
	{
		int[] months = new int[2];
		if(quarter == 1)
		{
			months[0] = 1;
			months[1] = 3;
		}
		else if(quarter == 2)
		{
			months[0] = 4;
			months[1] = 6;
		}
		else if(quarter == 3)
		{
			months[0] = 7;
			months[1] = 9;
		}
		else if(quarter == 4)
		{
			months[0] = 10;
			months[1] = 12;
		}
		else
		{
			months[0] = -1;
			months[1] = -1;
		}
		
		return months;
	}
	
	/**
	 * 根据年份和季度获取开始和结束的日期
	 * 
	 * @param nf
	 * @param jd
	 * @return
	 */
	public static String[] getDateRange(int nf, int jd)
	{
		String[] dateRange = new String[2];
		
		if(jd==1)
		{
			dateRange[0] = nf + "-01-01";
			dateRange[1] = nf + "-03-31";
		}
		else if(jd==2)
		{
			dateRange[0] = nf + "-04-01";
			dateRange[1] = nf + "-06-30";
		}
		else if(jd==3)
		{
			dateRange[0] = nf + "-07-01";
			dateRange[1] = nf + "-09-30";
		}
		else if(jd==4)
		{
			dateRange[0] = nf + "-10-01";
			dateRange[1] = nf + "-12-31";
		}
		else
		{
			dateRange[0] = null;
			dateRange[1] = null;
		}
		
		return dateRange;
	}
	
	public static String[] getYearMonthRange(int year, int quarter)
	{
		String[] dateRange = new String[2];
		
		if(quarter==1)
		{
			dateRange[0] = year + "-01";
			dateRange[1] = year + "-03";
		}
		else if(quarter==2)
		{
			dateRange[0] = year + "-04";
			dateRange[1] = year + "-06";
		}
		else if(quarter==3)
		{
			dateRange[0] = year + "-07";
			dateRange[1] = year + "-09";
		}
		else if(quarter==4)
		{
			dateRange[0] = year + "-10";
			dateRange[1] = year + "-12";
		}
		else
		{
			dateRange[0] = null;
			dateRange[1] = null;
		}
		
		return dateRange;
	}
	
	/**
	 * 构造成yyyy-MM
	 * 
	 * @param year
	 * @param month
	 * @return
	 */
	public static String yyyy_MM(int year, int month)
	{
		StringBuffer sb = new StringBuffer();
		sb.append(year);
		sb.append("-");
		if(month<10)
		{
			sb.append("0");
		}
		sb.append(month);
		
		return sb.toString();
	}
	
	/**
	 * 判断是否是合法的日期
	 * 
	 * @param nf
	 * @param jd
	 * @return
	 */
	public static boolean validDate(int nf, int jd)
	{
		int year = getCurrentYear();
		int quarter = getCurrentQuarter();
		
		if(nf > year)
		{
			return false;
		}
		else if(nf == year && jd > quarter)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public static String[] getStartEndDateStr(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH) + 1;	
		
		return getStartEndDateStr(year, month);
	}
	
	public static String[] getStartEndDateStr(int year, int month)
	{
		if(year < 0 || month < 1 || month > 12)
		{
			return null;
		}
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month - 1);
		
		int dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		c.set(Calendar.DATE, 1);
		String startStr = SDF_WS.format(c.getTime());
		
		c.set(Calendar.DATE, dayOfMonth);
		String endStr = SDF_WS.format(c.getTime());
		
		return new String[]{startStr, endStr};
	}
	
	public static Date getDateByStr(String str)
	{
		try 
		{
			return SDF_WS.parse(str);
		}
		catch (ParseException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	
	public static int getYear(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		
		return year;
	}
	
	public static int getMonth(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int month = c.get(Calendar.MONTH) + 1;
		
		return month;
	}
	
	public static Date getDate(int year, int month)
	{
		Calendar c = Calendar.getInstance();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month-1);
		
		return c.getTime();
	}
	
	public static String getCurrentYearMonth()
	{
		Date date = new Date();
		String str = yearMonthSdf.format(date);
		return str;
	}
	
	public static String getYearMonth(Date date)
	{
		String str = yearMonthSdf.format(date);
		
		return str;
	}
	
	public static String getLastMonthDateStr(Date date)
	{
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		
		if(month == 0)
		{
			year--;
			month = 11;
		}
		else
		{
			month--;
		}
		
		date = getDate(year, month + 1);
		
		String str = getYearMonth(date);
		
		return str;
	}
	
	public static String getStartDateOfCurMonth()
	{
		Date date = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = 1;
		c.clear();
		c.set(Calendar.YEAR, year);
		c.set(Calendar.MONTH, month);
		c.set(Calendar.DAY_OF_MONTH, day);
		
		Date start = c.getTime();
		String str = sdf.format(start);
		
		return str;
	}
	
	public static String getCurDate()
	{
		Date date = new Date();
		
		return sdf.format(date);
	}
	
	public static String getFirstDayOfDate(String dateStr)
	{
		Date date = null;
		try
		{
			date = yearMonthSdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		c.set(Calendar.DAY_OF_MONTH, 1);
		
		date = c.getTime();
		
		return SDF_WS.format(date);
	}
	
	public static String getLastDayOfDate(String dateStr)
	{
		Date date = null;
		try
		{
			date = yearMonthSdf.parse(dateStr);
		}
		catch (ParseException e)
		{
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		int dayOfMonth = c.getActualMaximum(Calendar.DAY_OF_MONTH);
		c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
		
		date = c.getTime();
		
		return SDF_WS.format(date);
	}

	public static Date getNextDay(Date date){
	     Calendar   calendar   =   new   GregorianCalendar(); 
	     calendar.setTime(date); 
	     calendar.add(calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
	     date=calendar.getTime();   //这个时间就是日期往后推一天的结果 
	     return date;
	}

	public static void main(String[] args) throws ParseException
	{
//		int year = 2015;
//		int month = 4;
//		String[] strs = getStartEndDateStr(year, month);
		
//		System.out.println(strs[0] + "-----" + strs[1]);
		
		Date date = new Date();

		System.out.println(getNextDay(date));
		
//		String s = getStartDateOfCurMonth();
//		System.out.println(s);
//		
//		String lastStr = getLastMonthDateStr(date);
//		System.out.println(lastStr);
//		
//		String s1 = "2015-04";
//		String s2 = "2015-05";
//		
//		System.out.println(getFirstDayOfDate(s1));
//		System.out.println(getLastDayOfDate(s2));
	}
}
