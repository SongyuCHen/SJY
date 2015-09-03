package nju.software.sjy.util;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.software.sjy.mapper.MSjygzl;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class SjygzlExcelBuilder extends AbstractExcelView 
{
	@SuppressWarnings("unchecked")
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
		String attrname = (String)model.get("attrname");
		List<String> attrList = (List<String>) model.get("attrList");
		List<MSjygzl> sjygzlList = (List<MSjygzl>)model.get("sjygzlList");
		
		// 创建Sheet
        HSSFSheet sheet = workbook.createSheet(attrname);
        sheet.setDefaultColumnWidth(20);
        
        // 创建样式
        CellStyle style = workbook.createCellStyle();
        style.setVerticalAlignment(CellStyle.VERTICAL_CENTER);// 垂直  
        style.setAlignment(CellStyle.ALIGN_CENTER);// 水平  
        Font font = workbook.createFont();
        font.setFontName("Arial");
        style.setFillForegroundColor(HSSFColor.BLUE.index);
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        font.setColor(HSSFColor.WHITE.index);
        style.setFont(font);
        
        // 创建表头
        HSSFRow header = sheet.createRow(0);
        if(attrList != null && !attrList.isEmpty())
        {
        	for(int i=0;i<attrList.size();i++)
        	{
        		header.createCell(i).setCellValue(attrList.get(i));
        		header.getCell(0).setCellStyle(style);
        	}
        }
        
        // 创建数据项
        int rowCount = 1;
         
        for (MSjygzl sjygzl : sjygzlList) 
        {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(sjygzl.getAttr1());
            aRow.createCell(1).setCellValue(sjygzl.getAttr2());
            aRow.createCell(2).setCellValue(sjygzl.getAttr3());
            aRow.createCell(3).setCellValue(sjygzl.getAttr4());
        }
        
        response.setContentType("application/vnd.ms-excel");     
        //设置excel文件名
        String curDateStr = DateUtil.getFormatStr(new Date());
        String filename = attrname + curDateStr + ".xls";
		response.setHeader("Content-Disposition", "attachment; filename=" 
				+ EncodeUtil.encodeFilename(filename, request));
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);
        ouputStream.flush();     
        ouputStream.close();
    }
}
