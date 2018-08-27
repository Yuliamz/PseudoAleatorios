package com.yuliamz.GUI;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

class FileUtils {
    public static void saveAsPlainText(List<String> numbers, File file) {
        if (file != null) {
            StringBuilder builder = new StringBuilder();
            numbers.forEach(e -> builder.append(e).append("\n"));
            builder.delete(builder.length() - 1, builder.length());
            try {
                org.apache.commons.io.FileUtils.writeStringToFile(file, builder.toString(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveAsExcel(List<String> numbers, File file) {
        if (file != null) {

            XSSFWorkbook fWorkbook = new XSSFWorkbook();
            //HSSFWorkbook workbook = new HSSFWorkbook();
            //HSSFSheet sheet = workbook.createSheet("Cuadrados Medios");
            XSSFSheet fSheet = fWorkbook.createSheet("Cuadrados Medios");
            for (int i = 0; i < numbers.size(); i++) {
                fSheet.createRow(i).createCell(0).setCellValue(numbers.get(i));
              //  sheet.createRow(i).createCell(0).setCellValue(numbers.get(i));
            }
            try {
               fWorkbook.write(new FileOutputStream(file));
               fWorkbook.close();
                //workbook.write(file);
                //workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
