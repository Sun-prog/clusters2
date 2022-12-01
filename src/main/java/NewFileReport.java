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
    public static void createFile(ArrayList<Integer> listCheck,String pathOutputFile) {
        // Document doc;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("report");

        List<Cluster> list = ClusterDAO.listCluster();
        List<String> notReadRSCI = MessageDAO.listMessage();
        int read = MessageDAO.getNumberReadRSCI();

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
        cell.setCellValue("Number read in file Uni title names");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(5));

        rownum++;
        row = sheet.createRow(rownum);

        // 7 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Quantity read null (NumberIssuesPerYear=0) in Rince table");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(7));

        rownum++;
        row = sheet.createRow(rownum);

        // 8 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number read in file Rince names");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(6));

        rownum++;
        row = sheet.createRow(rownum);

        // 9 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number read in file id journals rus");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(22));

        rownum++;
        row = sheet.createRow(rownum);

        // 10 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullProbabilityQuotingReading");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(8));

        rownum++;
        row = sheet.createRow(rownum);
        // 11 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTwoYearImpactFactorCore");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(9));

        rownum++;
        row = sheet.createRow(rownum);

        // 12 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTwoYearImpactFactorCoreWithoutCitations");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(10));

        rownum++;
        row = sheet.createRow(rownum);

        // 13 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTwoYearImpactFactor");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(11));

        rownum++;
        row = sheet.createRow(rownum);

        // 14 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTwoYearImpactFactorWithoutCitations");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(12));

        rownum++;
        row = sheet.createRow(rownum);

        // 15 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTwoYearImpactFactorWithCitations");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(13));

        rownum++;
        row = sheet.createRow(rownum);

        // 16 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTwoYearСoefficientAuthorSelfCitation");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(14));

        rownum++;
        row = sheet.createRow(rownum);

        // 17 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTwoYearСoefficientAuthorCitation");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(15));

        rownum++;
        row = sheet.createRow(rownum);

        int nullIndexGini=0;
        int nullIndexHerfindahlAuthorOrganizations=0;
        int nullPlaceScienceIndexRanking=0;
        int nullTotalNumberCitationsCurrentYear=0;
        int nullTotalNumberCitationsCurrentYearSelf=0;

        // 18 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTenYearHirschIndex");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(16));

        rownum++;
        row = sheet.createRow(rownum);

        // 19 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullIndexGini");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(17));

        rownum++;
        row = sheet.createRow(rownum);


        // 20 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullIndexHerfindahlAuthorOrganizations");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(18));

        rownum++;
        row = sheet.createRow(rownum);

        // 21 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullPlaceScienceIndexRanking");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(19));

        rownum++;
        row = sheet.createRow(rownum);

        // 22 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTotalNumberCitationsCurrentYear");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(20));

        rownum++;
        row = sheet.createRow(rownum);

        // 23 строка
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Number nullTotalNumberCitationsCurrentYearSelf");

        cell = row.createCell(1, CellType.NUMERIC);
        cell.setCellValue(listCheck.get(21));

        rownum++;
        row = sheet.createRow(rownum);

        // 24 строка
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
            //2 блок
            rownum++;
            row = sheet.createRow(rownum);
            cell = row.createCell(1, CellType.STRING);
            cell.setCellValue("не найдены журналы из таблицы rating rsci");

                // заполняем новую таблицу excel
                for (String message : notReadRSCI) {
                        rownum++;
                        row = sheet.createRow(rownum);

                        cell = row.createCell(1, CellType.STRING);
                        cell.setCellValue(message);

                }

                //3 блок
            rownum++;
            row = sheet.createRow(rownum);

            // 1 строка 3 блока
            cell = row.createCell(0, CellType.STRING);
            cell.setCellValue("Number find journals rating rsci");

            cell = row.createCell(1, CellType.NUMERIC);
            cell.setCellValue(read);




            File file = new File(pathOutputFile);
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
