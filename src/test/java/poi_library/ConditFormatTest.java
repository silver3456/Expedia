package poi_library;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.FileOutputStream;
import org.apache.poi.ss.usermodel.ConditionalFormattingRule;
import org.apache.poi.ss.util.CellRangeAddress;

public class ConditFormatTest {
    public static void main(String[] args) {

        Workbook workbook = new HSSFWorkbook();
        Sheet sheet = workbook.createSheet("Mathtest");

        Row row0 = sheet.createRow(0);
        row0.createCell(0).setCellValue(38);
        row0.createCell(1).setCellValue("+");
        row0.createCell(2).setCellValue(12);
        row0.createCell(3).setCellValue("=");

        //Create conditional formats
        SheetConditionalFormatting conditionalFormatting = sheet.getSheetConditionalFormatting();

        //Create rules
        ConditionalFormattingRule rule = conditionalFormatting.createConditionalFormattingRule(ComparisonOperator.EQUAL, "50");

        //Change background color
        PatternFormatting background = rule.createPatternFormatting();
        background.setFillBackgroundColor(IndexedColors.RED.getIndex());


        CellRangeAddress[] range = {CellRangeAddress.valueOf("E1:E1")};
        conditionalFormatting.addConditionalFormatting(range, rule);


        try {
            FileOutputStream output = new FileOutputStream("Mathtest.xls");
            workbook.write(output);
            output.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
