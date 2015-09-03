package nju.software.sjy.util;

import java.util.Collection;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class JsonUtil
{
	public static String getJsonStr(Object obj)
	{
		String jsonStr = JSONObject.fromObject(obj).toString();
		return jsonStr;
	}
	
	public static String getJsonStr(Collection<?> c)
	{
		String jsonStr = JSONArray.fromObject(c).toString();
		return jsonStr;
	}
}
