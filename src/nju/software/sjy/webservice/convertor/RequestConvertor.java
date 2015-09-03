package nju.software.sjy.webservice.convertor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import nju.software.sjy.webservice.bean.request.SjyGzlReq;
import nju.software.sjy.webservice.util.Base64Util;

public class RequestConvertor
{
	public static String convertToXML(Object obj)
	{
		Class<?> cls = obj.getClass();
		
		Field[] fields = cls.getDeclaredFields();
		StringBuffer xml = new StringBuffer();
		xml.append("<Request>");
		try
		{
			if(fields != null)
			{
				for(Field field : fields)
				{
					String fieldName = field.getName();
					String firstLetter = fieldName.substring(0, 1).toUpperCase();
					String getMethodName = "get" + firstLetter + fieldName.substring(1);
					
					Method getMethod = cls.getMethod(getMethodName, new Class[]{});
					Object value = getMethod.invoke(obj, new Object[]{});
					String colValue = Base64Util.encode(value.toString());
					String col = "<" + fieldName + ">" + colValue + "</" + fieldName + ">";
					xml.append(col);
				}
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		xml.append("</Request>");
		
		String result = Base64Util.encode(xml.toString());
		
		return result;
	}
	
	public static void main(String[] args)
	{
		SjyGzlReq req = new SjyGzlReq("A30", "321088", "20150201", "20150530");
		
		String str = convertToXML(req);
		
		System.out.println(str);
	}
}
