package nju.software.sjy.util;

public class StringUtil 
{
	public static boolean isNullOrNone(String str)
    {
    	return null == str || "".equals(str);
    }
}
