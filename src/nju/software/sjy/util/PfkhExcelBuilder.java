package nju.software.sjy.util;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.software.sjy.mapper.MPfkh;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class PfkhExcelBuilder extends AbstractExcelView
{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		List<MPfkh> mlist = (List<MPfkh>) model.get("mlist");
		
		// 创建Sheet
        HSSFSheet sheet = workbook.createSheet("评分考核");
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
        
        header.createCell(0).setCellValue("姓名");
        header.getCell(0).setCellStyle(style);
         
        header.createCell(1).setCellValue("部门");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(4).setCellValue("时间");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("得分");
        header.getCell(5).setCellStyle(style);
        
        // 创建数据项
        int rowCount = 1;
        
        if(mlist != null)
        {
        	for(MPfkh pfkh : mlist)
        	{
        		HSSFRow aRow = sheet.createRow(rowCount++);
                aRow.createCell(0).setCellValue(pfkh.getRyxm());
                aRow.createCell(1).setCellValue(pfkh.getBmmc());
                aRow.createCell(2).setCellValue(pfkh.getNf() + "年" + pfkh.getJd() + "季度");
                aRow.createCell(3).setCellValue(pfkh.getJddf());
        	}
        }
        
        response.setContentType("application/vnd.ms-excel");     
        //设置excel文件名
        String curDateStr = DateUtil.getFormatStr(new Date());
        String filename = "评分考核-"+curDateStr+".xls";
		response.setHeader("Content-Disposition", "attachment; filename=" 
				+ EncodeUtil.encodeFilename(filename, request));
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);
        ouputStream.flush();     
        ouputStream.close();
	}
	
}
