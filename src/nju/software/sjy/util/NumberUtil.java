package nju.software.sjy.util;


public class NumberUtil
{
	/**
	 * 将百分数转换为double
	 * 
	 * @param percent
	 * @return
	 */
	public static double percentToDouble(String percent)
	{
		if(percent==null)
		{
			return 0.0;
		}
		
		percent = percent.trim();
		
		if("".equals(percent) || "%".equals(percent))
		{
			return 0.0;
		}
		
		percent = percent.substring(0, percent.length() - 1);
		double num = Double.parseDouble(percent);
		
		return (double)num/100;
	}
	
	/**
	 * 将double保留2位小数
	 * 
	 * @param d
	 * @return
	 */
	public static double formatDouble(double d)
	{
		return (double)Math.round(d * 100) / 100;
	}
	
	public static void main(String[] args)
	{
		double d = 3.141592653;
		System.out.println(formatDouble(d));
	}
}
