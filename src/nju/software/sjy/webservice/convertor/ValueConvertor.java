package nju.software.sjy.webservice.convertor;

public class ValueConvertor
{
	public static Object convert(String strValue, Class<?> type)
	{
		if(type == Integer.class)
		{
			return Integer.parseInt(strValue);
		}
		else if(type == Double.class)
		{
			return Double.parseDouble(strValue);
		}
		else if(type == Long.class)
		{
			return Long.parseLong(strValue);
		}
		else if(type == Float.class)
		{
			return Float.parseFloat(strValue);
		}
		
		return strValue;
	}
}
