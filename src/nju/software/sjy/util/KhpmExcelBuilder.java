package nju.software.sjy.util;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.software.sjy.mapper.MPfkh;
import nju.software.sjy.mapper.MPfkhgz;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class KhpmExcelBuilder extends AbstractExcelView
{

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			HSSFWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception
	{
		List<MPfkh> mPfkhList = (List<MPfkh>)model.get("mPfkhList");
		List<MPfkhgz> mPfkhgzList = (List<MPfkhgz>)model.get("mPfkhgzList");
		
		
		// 创建Sheet
        HSSFSheet sheet = workbook.createSheet("考核排名");
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
        
        header.createCell(0).setCellValue("排名");
        header.getCell(0).setCellStyle(style);
        
        header.createCell(1).setCellValue("姓名");
        header.getCell(1).setCellStyle(style);
         
        header.createCell(2).setCellValue("部门");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("时间");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("评分");
        header.getCell(4).setCellStyle(style);
         
        // 创建数据项
        int rowCount = 1;
        
        if(mPfkhList != null && !mPfkhList.isEmpty())
        {
        	for(int i=0;i<mPfkhList.size();i++)
        	{
        		MPfkh mpfkh = mPfkhList.get(i);
        		
        		HSSFRow aRow = sheet.createRow(rowCount++);
        		aRow.createCell(0).setCellValue(i + 1);
            	aRow.createCell(1).setCellValue(mpfkh.getRyxm());
                aRow.createCell(2).setCellValue(mpfkh.getBmmc());
                aRow.createCell(3).setCellValue(mpfkh.getNf() + "年" + mpfkh.getJd() + "季度");
                aRow.createCell(4).setCellValue(mpfkh.getJddf());
        	}
        }
        else if(mPfkhgzList != null && !mPfkhgzList.isEmpty())
        {
        	for(int i=0;i<mPfkhgzList.size();i++)
        	{
        		MPfkhgz mpfkhgz = mPfkhgzList.get(i);
        		
        		HSSFRow aRow = sheet.createRow(rowCount++);
        		aRow.createCell(0).setCellValue(i + 1);
            	aRow.createCell(1).setCellValue(mpfkhgz.getRyxm());
                aRow.createCell(2).setCellValue(mpfkhgz.getBmmc());
                aRow.createCell(3).setCellValue(mpfkhgz.getNf() + "年" + mpfkhgz.getJd() + "季度");
                aRow.createCell(4).setCellValue(mpfkhgz.getGzdf());
        	}
        }
        
        response.setContentType("application/vnd.ms-excel");     
        //设置excel文件名
        String curDateStr = DateUtil.getFormatStr(new Date());
        String filename = "考核排名-"+curDateStr+".xls";
		response.setHeader("Content-Disposition", "attachment; filename=" 
				+ EncodeUtil.encodeFilename(filename, request));
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);
        ouputStream.flush();     
        ouputStream.close();
	}

}
