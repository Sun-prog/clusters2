import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
//import java.nio.charset.StandardCharsets;
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

        //14 нашелся ли журнал в таблице UniTitles Names
        cell = row.createCell(14, CellType.STRING);
        cell.setCellValue("Find in UniTitles Names");

        //15 наименование журнала из таблицы Uni title names
        cell = row.createCell(15, CellType.STRING);
        cell.setCellValue("Uni Title Name");

        //16 нашелся ли журнал в таблице Rince Titles Names
        cell = row.createCell(16, CellType.STRING);
        cell.setCellValue("Find in Rince Titles Names");

        //17 наименование журнала из таблицы Rince title names
        cell = row.createCell(17, CellType.STRING);
        cell.setCellValue("Rince Title Name");

        //18 какой университет печатает
        cell = row.createCell(18, CellType.STRING);
        cell.setCellValue("place");

        //19 город издания журнала
        cell = row.createCell(19, CellType.STRING);
        cell.setCellValue("City");

        //20 общее число выпусков журнала
        cell = row.createCell(20, CellType.STRING);
        cell.setCellValue("Общее число выпусков журнала");

        //21 Общее число статей из журнала
        cell = row.createCell(21, CellType.STRING);
        cell.setCellValue("Общее число статей из журнала");

        //22 общее число статей с полными текстами
        cell = row.createCell(22, CellType.STRING);
        cell.setCellValue("Общее число статей с полными текстами");

        //23 суммарное число цитирований
        cell = row.createCell(23, CellType.STRING);
        cell.setCellValue("Суммарное число цитирований журнала в РИНЦ");

        //24 Среднее число статей в выпуске
        cell = row.createCell(24, CellType.STRING);
        cell.setCellValue("Среднее число статей в выпуске");

        //25 Число выпусков в год
        cell = row.createCell(25, CellType.STRING);
        cell.setCellValue("Число выпусков в год");

        //26 Число выпусков в год
        cell = row.createCell(26, CellType.STRING);
        cell.setCellValue("Нахождение в id-journals-rus");


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

                //14 нашелся ли журнал в таблице UniTitles Names
                cell = row.createCell(14, CellType.BOOLEAN);
                cell.setCellValue(journal.isUniTitle);

                //15 наименование журнала из таблицы Uni title names
                cell = row.createCell(15, CellType.STRING);
                cell.setCellValue(journal.getNameJournalUni());

                //16 нашелся ли журнал в таблице Rince Titles Names
                cell = row.createCell(16, CellType.BOOLEAN);
                cell.setCellValue(journal.isUniRince);

                //17 наименование журнала из таблицы Rince title names
                cell = row.createCell(17, CellType.STRING);
                cell.setCellValue(journal.getNameJournalRince());

                //18 какой университет печатает
                cell = row.createCell(18, CellType.STRING);
                cell.setCellValue(journal.getPlace());

                //19 город издания журнала
                cell = row.createCell(19, CellType.STRING);
                cell.setCellValue(journal.getCity());

                //20 общее число выпусков журнала
                cell = row.createCell(20, CellType.NUMERIC);
                cell.setCellValue(journal.getNumberIssues());

                //21 Общее число статей из журнала
                cell = row.createCell(21, CellType.NUMERIC);
                cell.setCellValue(journal.getUniQuantityArticles());

                //22 общее число статей с полными текстами
                cell = row.createCell(22, CellType.NUMERIC);
                cell.setCellValue(journal.getUniQuantityArticlesFullText());

                //23 суммарное число цитирований
                cell = row.createCell(23, CellType.NUMERIC);
                cell.setCellValue(journal.getNumberCitations());

                //24 Среднее число статей в выпуске
                cell = row.createCell(24, CellType.NUMERIC);
                cell.setCellValue(journal.getNumberArticlesPerIssue());

                //25 Число выпусков в год
                cell = row.createCell(25, CellType.STRING);
                cell.setCellValue(journal.getNumberIssuesPerYear());

                //26 Число выпусков в год
                cell = row.createCell(26, CellType.BOOLEAN);
                cell.setCellValue(journal.isRus);

            }


            File file = new File("C:/Users/grupp/Documents/data/article/journal.xlsx");
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
