package com.yuliamz.GUI;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
            XSSFSheet fSheet = fWorkbook.createSheet("Cuadrados Medios");
            for (int i = 0; i < numbers.size(); i++) {
                fSheet.createRow(i).createCell(0).setCellValue(numbers.get(i));
            }
            try {
                fWorkbook.write(new FileOutputStream(file));
                fWorkbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
