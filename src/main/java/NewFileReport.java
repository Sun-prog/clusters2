import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//import org.jsoup.nodes.Document;

public class NewFileReport {
    public static void createFile(ArrayList<Integer> listCheck) {
        // Document doc;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("report");

        List<Cluster> list = ClusterDAO.listCluster();

        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);
        //1 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Сумма статей во всех прочитанных журналах");

        //Сумма статей во всех прочитанных журналах
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(0));

        rownum++;
        row = sheet.createRow(rownum);
        //2 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Сумма count во всех кластерах");


        //Сумма статей во всех прочитанных журналах
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(1));


        rownum++;
        row = sheet.createRow(rownum);

        //3 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Количество прочитанных журналов");


        //Сумма статей во всех прочитанных журналах
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(2));

        rownum++;
        row = sheet.createRow(rownum);
        //4 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Количество прочитанных статей");


        //Сумма статей во всех прочитанных журналах
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(3));

        rownum++;
        row = sheet.createRow(rownum);

        //5 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Количество прочитанных кластеров");


        //Сумма статей во всех прочитанных журналах
        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(4));

        rownum++;
        row = sheet.createRow(rownum);

        // 6 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number break clusters");

        //заголовки
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("List Article");

        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("List Journal");

        try {
            // заполняем новую таблицу excel
            for (Cluster cluster : list) {
                if (!cluster.isCorrect) {
                    rownum++;
                    row = sheet.createRow(rownum);


                    cell = row.createCell(0, CellType.NUMERIC);
                    cell.setCellValue(cluster.getNumber());

                    cell = row.createCell(1, CellType.STRING);
                    cell.setCellValue(cluster.getArrayArticle());

                    cell = row.createCell(2, CellType.STRING);
                    cell.setCellValue(cluster.getArrayJournal());
                }
            }


            File file = new File("C:/Users/grupp/report.xlsx");
            file.getParentFile().mkdirs();

            FileOutputStream outFile = new FileOutputStream(file);
            workbook.write(outFile);
            System.out.println("Created file: " + file.getAbsolutePath());
            workbook.close();
            outFile.close();

        } catch (IOException e) {
            System.out.println("неа");
            e.printStackTrace();
        }

    }

}
