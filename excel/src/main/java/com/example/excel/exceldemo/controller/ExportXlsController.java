package com.example.excel.exceldemo.controller;

import com.example.excel.exceldemo.common.utils.poi.ExcelUtil;
import com.example.excel.exceldemo.domain.SysLogininfor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ExportXlsController {
    @GetMapping("/export")
    public void export() throws Exception {
        XSSFWorkbook wk=new XSSFWorkbook();
        XSSFSheet sheet = wk.createSheet("测试");
        XSSFRow row = sheet.createRow(0);
        XSSFCell cell1 = row.createCell(0);
        cell1.setCellValue("中国1");
        XSSFCell cell2 = row.createCell(1);
        cell2.setCellValue("中国2");
        FileOutputStream fos = new FileOutputStream("c:\\demo.xlsx");
        wk.write(fos);
        fos.flush();
        fos.close();
    }

    @GetMapping("/export2")
    public void export2() throws Exception {
        List<SysLogininfor> list = new ArrayList<>();
        SysLogininfor s1=new SysLogininfor();
        s1.setInfoId(100L);
        s1.setLoginName("张三");
        SysLogininfor s2=new SysLogininfor();
        s2.setInfoId(200L);
        s2.setLoginName("李四");
        list.add(s1);
        list.add(s2);
        ExcelUtil<SysLogininfor> util = new ExcelUtil<SysLogininfor>(SysLogininfor.class);
        util.exportExcel(list, "登陆日志");
    }

    @GetMapping("/import")
    public void imp() throws Exception {
        FileInputStream fis = new FileInputStream("c:\\demo.xlsx");
        XSSFWorkbook wb=new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        XSSFRow row = sheet.getRow(0);
        XSSFCell cell1 = row.getCell(0);
        XSSFCell cell2 = row.getCell(1);
        System.out.println(cell1.toString());
        System.out.println(cell2.toString());
        wb.close();
        fis.close();
    }
}
