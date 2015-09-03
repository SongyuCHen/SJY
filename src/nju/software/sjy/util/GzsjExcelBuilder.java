package nju.software.sjy.util;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.software.sjy.mapper.MGzsj;
import nju.software.sjy.model.xy.TGypz;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class GzsjExcelBuilder extends AbstractExcelView
{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		List<MGzsj> gzsjList = (List<MGzsj>)model.get("gzsjList");
		List<TGypz> pzList = (List<TGypz>)model.get("pzList");
		
		// 创建Sheet
        HSSFSheet sheet = workbook.createSheet("工作实绩");
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
        
        header.createCell(2).setCellValue("日期");
        header.getCell(2).setCellStyle(style);
        
        for(int i=0;i<pzList.size();i++)
        {
        	TGypz gypz = pzList.get(i);
        	int index = 3 + i;
        	String mc = gypz.getMc();
        	header.createCell(index).setCellValue(mc);
        	header.getCell(index).setCellStyle(style);
        }
        
        int last = 3 + pzList.size();
        header.createCell(last).setCellValue("状态");
        header.getCell(last).setCellStyle(style);
        
        // 创建数据项
        int rowCount = 1;
        for(MGzsj gzsj : gzsjList)
        {
        	HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(gzsj.getXm());
            aRow.createCell(1).setCellValue(gzsj.getBmmc());
            aRow.createCell(2).setCellValue(gzsj.getRq());
            
            List<Integer> szList = gzsj.getSzList();
            for(int i=0;i<szList.size();i++)
            {
            	int sz = szList.get(i);
            	int index = 3 + i;
            	aRow.createCell(index).setCellValue(sz);
            }
            
            int lastIndex = 3 + szList.size();
            aRow.createCell(lastIndex).setCellValue(gzsj.getZt());
        }
        
        response.setContentType("application/vnd.ms-excel");     
        //设置excel文件名
        String curDateStr = DateUtil.getFormatStr(new Date());
        String filename = "工作实绩-"+curDateStr+".xls";
		response.setHeader("Content-Disposition", "attachment; filename=" 
				+ EncodeUtil.encodeFilename(filename, request));
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);
        ouputStream.flush();     
        ouputStream.close();
	}
	
}
