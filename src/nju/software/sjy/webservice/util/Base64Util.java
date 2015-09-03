package nju.software.sjy.webservice.util;

import org.springframework.security.crypto.codec.Base64;

public class Base64Util
{
	public static String decode(String str)
	{
		if(str == null)
		{
			return null;
		}
		
		byte[] arr = Base64.decode(str.getBytes());
		String result = new String(arr);
		
		return result;
	}
	
	public static String encode(String str)
	{
		if(str == null)
		{
			return null;
		}
		
		byte[] arr = Base64.encode(str.getBytes());
		
		String result = new String(arr);
		
		return result;
	}
}
