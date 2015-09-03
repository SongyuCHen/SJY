package nju.software.sjy.webservice.xml;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class RespConfig
{
	private static Logger log = Logger.getLogger(RespConfig.class);
	
	//private static final String XML_PATH = "\\respConfig.xml";
	private static final String XML_PATH = "nju/software/sjy/webservice/xml/respConfig.xml";
	
	public static Map<String, String> classMap = new HashMap<String, String>();
	
	static
	{
		loadRespConfig();
	}
	
	@SuppressWarnings("unchecked")
	public static void loadRespConfig()
	{
		//String currentPath = System.getProperty("user.dir");
		//String filePath = currentPath + XML_PATH;
		//log.info("文件路径" + filePath);
		//File file = new File(filePath);
		InputStream in = RespConfig.class.getClassLoader().getResourceAsStream(XML_PATH);
		SAXReader saxReader = new SAXReader();
        try 
        {
			//Document document = saxReader.read(file);
        	Document document = saxReader.read(in);
			Element root = document.getRootElement();
			List<Element> classes = root.elements();
			if(classes != null)
			{
				for(Element elem : classes)
				{
					String id = elem.attributeValue("id");
					String classPath = elem.attributeValue("class");
					classMap.put(id, classPath);
				}
			}
		}
		catch (DocumentException e) 
        {
			e.printStackTrace();
		}
        
        for(Entry<String, String> entry : classMap.entrySet())
    	{
    		log.info(entry.getKey() + "-" + entry.getValue());
    	}
	}
	
	public static String getClassPath(String id)
	{
		return classMap.get(id);
	}
	
//	public static void main(String[] args)
//	{
//		for(Entry<String, String> entry : classMap.entrySet())
//		{
//			System.out.println(entry.getKey() + "-" + entry.getValue());
//		}
//	}
}
