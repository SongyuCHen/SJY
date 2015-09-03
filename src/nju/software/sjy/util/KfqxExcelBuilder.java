package nju.software.sjy.util;

import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nju.software.sjy.mapper.MKfqx;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.springframework.web.servlet.view.document.AbstractExcelView;

public class KfqxExcelBuilder extends AbstractExcelView {
 
    @SuppressWarnings("unchecked")
	@Override
    protected void buildExcelDocument(Map<String, Object> model,
            HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        List<MKfqx> kfqxList = (List<MKfqx>) model.get("kfqxList");
         
        // 创建Sheet
        HSSFSheet sheet = workbook.createSheet("扣分情形");
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
         
        header.createCell(2).setCellValue("扣分类别");
        header.getCell(2).setCellStyle(style);
         
        header.createCell(3).setCellValue("扣分项目名称");
        header.getCell(3).setCellStyle(style);
         
        header.createCell(4).setCellValue("获得时间");
        header.getCell(4).setCellStyle(style);
        
        header.createCell(5).setCellValue("分数");
        header.getCell(5).setCellStyle(style);
        
        header.createCell(6).setCellValue("扣分次数");
        header.getCell(6).setCellStyle(style);
        
        header.createCell(7).setCellValue("备注");
        header.getCell(7).setCellStyle(style);
         
        // 创建数据项
        int rowCount = 1;
         
        for (MKfqx kfqx : kfqxList) {
            HSSFRow aRow = sheet.createRow(rowCount++);
            aRow.createCell(0).setCellValue(kfqx.getRyxm());
            aRow.createCell(1).setCellValue(kfqx.getBmmc());
            aRow.createCell(2).setCellValue(kfqx.getKfxm().getKflb().getMc());
            aRow.createCell(3).setCellValue(kfqx.getKfxm().getMc());
            aRow.createCell(4).setCellValue(kfqx.getHdsj());
            aRow.createCell(5).setCellValue(kfqx.getKfxm().getFs());
            aRow.createCell(6).setCellValue(kfqx.getKfcs());
            aRow.createCell(7).setCellValue(kfqx.getComment());
        }
        
        response.setContentType("application/vnd.ms-excel");     
        //设置excel文件名
        String curDateStr = DateUtil.getFormatStr(new Date());
        String filename = "扣分情形-"+curDateStr+".xls";
		response.setHeader("Content-Disposition", "attachment; filename=" 
				+ EncodeUtil.encodeFilename(filename, request));
        OutputStream ouputStream = response.getOutputStream();     
        workbook.write(ouputStream);
        ouputStream.flush();     
        ouputStream.close();
    }
 
}
