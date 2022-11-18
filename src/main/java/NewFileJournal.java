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
import java.util.List;

//import org.jsoup.nodes.Document;

public class NewFileJournal {
    public static void createFile() {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Journals");

        List<Journal> list = JournalDAO.listJournal();

        int rownum = 0;
        Cell cell;
        Row row;

        row = sheet.createRow(rownum);

        // 0 номер журнала
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Journal Number");

        //1 количество статей журнала в кластерах
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Quantity articles in table clusters");

        //2 перечисление статей журнала в кластерах
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("List articles in table clusters");

        //3 количество статей-первоисточников
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Quantity source articles");

        //4 перечисление статей-первоисточников
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("List source articles");

        //5 количество других статей журнала в кластерах
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Quantity other articles");

        //6 перечисление других статей журнала в кластерах
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("List other articles");

        //7 количество кластеров, в которых журнал упомянут
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Quantity clusters");

        //8 перечисление наименований кластеров, в которых упомянут журнал
        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("List clusters");

        //9 лист SRSTI кодов статей для журналов
        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("SRSTI code");

        //10 лист SRSTI названий для статей журналов
        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("SRSTI topic");

        // 11 лист OECD кодов статей для журналов
        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("OECD code");

        //12 лист OECD названий для статей журналов
        cell = row.createCell(12, CellType.STRING);
        cell.setCellValue("OECD topic");

        //13 количество SRSTI кодов в журнале
        cell = row.createCell(13, CellType.STRING);
        cell.setCellValue("Quantity SRSTI code");

        try {


// заполняем новую таблицу excel
            for (Journal journal : list) {
                //for (int i=2012;i<2022;i++) {
                rownum++;
                row = sheet.createRow(rownum);

                // название журнала
                cell = row.createCell(0, CellType.STRING);
                cell.setCellValue(journal.getNumber());

                // 1 количество статей журнала в кластерах
                cell = row.createCell(1, CellType.NUMERIC);
                cell.setCellValue(journal.getNumberRepeat());

                //2 перечисление статей журнала в кластерах
                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(journal.listArticleToString());

                //3 количество статей-первоисточников
                cell = row.createCell(3, CellType.NUMERIC);
                cell.setCellValue(journal.listArticleSource.size());

                //4 перечисление статей-первоисточников
                cell = row.createCell(4, CellType.STRING);
                cell.setCellValue(journal.listArticleSourceToString());

                //5 количество других статей журнала в кластерах
                cell = row.createCell(5, CellType.NUMERIC);
                cell.setCellValue(journal.listArticleCopy.size());

                //6 перечисление других статей журнала в кластерах
                cell = row.createCell(6, CellType.STRING);
                cell.setCellValue(journal.listArticleCopyToString());

                //7 количество кластеров, в которых журнал упомянут
                cell = row.createCell(7, CellType.NUMERIC);
                cell.setCellValue(journal.getListJournalCluster().size());

                //8 перечисление наименований кластеров, в которых упомянут журнал
                cell = row.createCell(8, CellType.STRING);
                cell.setCellValue(journal.listClusterToString());

                //9 лист SRSTI кодов статей для журналов
                cell = row.createCell(9, CellType.STRING);
                cell.setCellValue(journal.listSrstiCodeToString());

                //10 лист SRSTI названий для статей журналов
                cell = row.createCell(10, CellType.STRING);
                cell.setCellValue(journal.listSrstiTopicToString());

                // 11 лист OECD кодов статей для журналов
                cell = row.createCell(11, CellType.STRING);
                cell.setCellValue(journal.listOecdCodeToString());

                //12 лист OECD названий для статей журналов
                cell = row.createCell(12, CellType.STRING);
                cell.setCellValue(journal.listOecdTopicToString());

                //13 количество SRSTI кодов в журнале
                cell = row.createCell(13, CellType.NUMERIC);
                cell.setCellValue(journal.getListSrstiTopic().size());

            }


            File file = new File("C:/Users/journal.xlsx");
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

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
