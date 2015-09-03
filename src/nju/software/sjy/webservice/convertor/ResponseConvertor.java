package nju.software.sjy.webservice.convertor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import nju.software.sjy.webservice.util.Base64Util;
import nju.software.sjy.webservice.xml.RespConfig;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class ResponseConvertor
{
	private static Logger log = Logger.getLogger(ResponseConvertor.class);
	
	@SuppressWarnings("unchecked")
	public static List<Object> convertToObj(String xmlStr)
	{
		List<Object> list = null;
		String xml = Base64Util.decode(xmlStr);
		
		xml = xml.replaceAll("</BMDM><YHDM>", "</BMMC><YHDM>");
		
		try 
		{
			Document doc=(Document)DocumentHelper.parseText(xml);
			Element response = doc.getRootElement();
			Element result = response.element("Result");
			Element code = result.element("Code");
			String codeStr = code.getText();
			codeStr = Base64Util.decode(codeStr);
			
			if(codeStr.equals("0"))
			{
				list = new ArrayList<Object>();
				
				Element data = response.element("Data");
				String countStr = data.attributeValue("Count");
				countStr = Base64Util.decode(countStr);
				int count = Integer.parseInt(countStr);
				
				List<Element> dataList = data.elements();
				if(dataList != null)
				{
					if(count != dataList.size())
					{
						log.info("count is not equal to datalist.size()");
					}
					else
					{
						for(Element bean : dataList)
						{
							Object obj = convertToObj(bean);
							
							list.add(obj);
						}
					}
				}
			}
			else
			{
				Element msg = result.element("Msg");
				String message = msg.getText();
				log.info("fetch by webservice, get error:" + message);
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public static Object convertToObj(Element elem)
	{
		String beanName = elem.getName();
		beanName = beanName.toUpperCase();
		
		Class<?> cls = null;
		Object obj = null;
		try 
		{
			//String path = "nju.software.sjy.webservice.bean.response.SjyGzlResp";
			String path = RespConfig.getClassPath(beanName);
			cls = Class.forName(path);
			obj = cls.newInstance();
			
			Field[] fields = cls.getDeclaredFields();
			
			List<Element> childList = elem.elements();
			if(childList != null)
			{
				for(Element child : childList)
				{
					String attrName = child.getName();
					String attrValue = child.getText();
					
					Field field = getFieldByNameFromArray(attrName, fields);
					if(field != null)
					{
						String firstLetter = attrName.substring(0, 1).toUpperCase();
						String setMethodName = "set" + firstLetter + attrName.substring(1);
						
						Method setMethod = cls.getMethod(setMethodName, new Class[]{field.getType()});
						
						attrValue = Base64Util.decode(attrValue);
						
						Object value = ValueConvertor.convert(attrValue, field.getType());
						setMethod.invoke(obj, new Object[]{value});
					}
				}
			}
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		return obj;
	}
	
	/**
	 * 判断一个属性名称是否在一个属性数组中
	 * 
	 * @param fieldName		属性名称
	 * @param fieldArr		属性数组
	 * @return
	 */
	private static Field getFieldByNameFromArray(String fieldName, Field[] fieldArr) 
	{
		for (Field field : fieldArr) 
		{
			if (fieldName.equals(field.getName())) 
			{
				return field;
			}
		}
		return null;
	}
	
	public static void main(String[] args)
	{
		String code = "0";
		String msg = "";
		
		int count = 1;
		
		String FYDM = "fy30";
		String BMDM = "bm10";
		String BMMC = "执行庭";
		
		String YHDM = "yh50";
		String YHXM = "张三";
		String AJS = "8";
		
		String KTS = "20";
		String DAYS = "15";
		String KTSJ = "20150301";
		
		String BLZS = "100";
		
		String xml = "<Response>" + 
					     "<Result>" + 
					     	 "<Code>" + code + "</Code>" + 
					         "<Msg>" + msg + "</Msg>" +
				         "</Result>" +
					     "<Data Count='" + count + "'>" +
				         	 "<SjyGzlResp>" +
				         	 	"<FYDM>" + FYDM + "</FYDM>" +
				         	 	"<BMDM>" + BMDM + "</BMDM>" + 
				         	 	"<BMMC>" + BMMC + "</BMMC>" +
				         	 	"<YHDM>" + YHDM + "</YHDM>" + 
				         	 	"<YHXM>" + YHXM + "</YHXM>" +
				         	 	"<AJS>" + AJS + "</AJS>" + 
				         	 	"<KTS>" + KTS + "</KTS>" +
				         	 	"<DAYS>" + DAYS + "</DAYS>" + 
				         	 	"<KTSJ>" + KTSJ + "</KTSJ>" +
				         	 	"<BLZS>" + BLZS + "</BLZS>" + 
				         	 "</SjyGzlResp>" +
					     "</Data>" + 
					 "</Response>";
		
		List<Object> list = convertToObj(xml);
		
		if(list != null)
		{
			for(Object obj : list)
			{
				System.out.println(obj.toString());
			}
		}
	}
}
