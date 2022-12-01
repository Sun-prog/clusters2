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
    public static void createFile(String pathOutputFile) {

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

        //27 медиана Вероятность цитирования после прочтения
        cell = row.createCell(27, CellType.STRING);
        cell.setCellValue("Медиана вероятности цитирования после прочтения");

        //28 максимум Вероятность цитирования после прочтения
        cell = row.createCell(28, CellType.STRING);
        cell.setCellValue("Максимум вероятности цитирования после прочтения");

        //29 массив Вероятность цитирования после прочтения по годам
        cell = row.createCell(29, CellType.STRING);
        cell.setCellValue("массив значений вероятности цитирования после прочтения");

        //30 медиана Двухлетний импакт-фактор по ядру РИНЦ
        cell = row.createCell(30, CellType.STRING);
        cell.setCellValue("Медиана Двухлетний импакт-фактор по ядру РИНЦ");

        //31 максимум Двухлетний импакт-фактор по ядру РИНЦ
        cell = row.createCell(31, CellType.STRING);
        cell.setCellValue("Максимум Двухлетний импакт-фактор по ядру РИНЦ");

        //32 массив Двухлетний импакт-фактор по ядру РИНЦ
        cell = row.createCell(32, CellType.STRING);
        cell.setCellValue("массив значений Двухлетний импакт-фактор по ядру РИНЦ");


        //33 медиана Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования
        cell = row.createCell(33, CellType.STRING);
        cell.setCellValue("Медиана Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования");

        //34 максимум Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования
        cell = row.createCell(34, CellType.STRING);
        cell.setCellValue("Максимум Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования");

        //35 массив Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования
        cell = row.createCell(35, CellType.STRING);
        cell.setCellValue("массив значений Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования");


        //36 медиана Двухлетний импакт-фактор РИНЦ
        cell = row.createCell(36, CellType.STRING);
        cell.setCellValue("Медиана Двухлетний импакт-фактор РИНЦ");

        //37 максимум Двухлетний импакт-фактор РИНЦ
        cell = row.createCell(37, CellType.STRING);
        cell.setCellValue("Максимум Двухлетний импакт-фактор РИНЦ");

        //38 массив Двухлетний импакт-фактор РИНЦ
        cell = row.createCell(38, CellType.STRING);
        cell.setCellValue("массив значений Двухлетний импакт-фактор РИНЦ");


        //39 медиана Двухлетний импакт-фактор РИНЦ без самоцитирования
        cell = row.createCell(39, CellType.STRING);
        cell.setCellValue("Медиана Двухлетний импакт-фактор РИНЦ без самоцитирования");

        //40 максимум Двухлетний импакт-фактор РИНЦ без самоцитирования
        cell = row.createCell(40, CellType.STRING);
        cell.setCellValue("Максимум Двухлетний импакт-фактор РИНЦ без самоцитирования");

        //41 массив Двухлетний импакт-фактор РИНЦ без самоцитирования
        cell = row.createCell(41, CellType.STRING);
        cell.setCellValue("массив значений Двухлетний импакт-фактор РИНЦ без самоцитирования");


        //42 медиана Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников
        cell = row.createCell(42, CellType.STRING);
        cell.setCellValue("Медиана Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников");

        //43 максимум Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников
        cell = row.createCell(43, CellType.STRING);
        cell.setCellValue("Максимум Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников");

        //44 массив Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников
        cell = row.createCell(44, CellType.STRING);
        cell.setCellValue("массив значений Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников");


        //45 медиана Двухлетний коэффициент авторского самоцитирования
        cell = row.createCell(45, CellType.STRING);
        cell.setCellValue("Медиана Двухлетний коэффициент авторского самоцитирования");

        //46 максимум Двухлетний коэффициент авторского самоцитирования
        cell = row.createCell(46, CellType.STRING);
        cell.setCellValue("Максимум Двухлетний коэффициент авторского самоцитирования");

        //47 массив Двухлетний коэффициент авторского самоцитирования
        cell = row.createCell(47, CellType.STRING);
        cell.setCellValue("массив значений Двухлетний коэффициент авторского самоцитирования");


        //48 медиана Двухлетний коэффициент самоцитирования
        cell = row.createCell(48, CellType.STRING);
        cell.setCellValue("Медиана Двухлетний коэффициент самоцитирования");

        //49 максимум Двухлетний коэффициент самоцитирования
        cell = row.createCell(49, CellType.STRING);
        cell.setCellValue("Максимум Двухлетний коэффициент самоцитирования");

        //50 массив Двухлетний коэффициент самоцитирования
        cell = row.createCell(50, CellType.STRING);
        cell.setCellValue("массив значений Двухлетний коэффициент самоцитирования");


        //51 медиана Десятилетний индекс Хирша
        cell = row.createCell(51, CellType.STRING);
        cell.setCellValue("Медиана Десятилетний индекс Хирша");

        //52 максимум Десятилетний индекс Хирша
        cell = row.createCell(52, CellType.STRING);
        cell.setCellValue("Максимум Десятилетний индекс Хирша");

        //53 массив Десятилетний индекс Хирша
        cell = row.createCell(53, CellType.STRING);
        cell.setCellValue("массив значений Десятилетний индекс Хирша");


        //54 медиана Индекс Джини
        cell = row.createCell(54, CellType.STRING);
        cell.setCellValue("Медиана Индекс Джини");

        //55 максимум Индекс Джини
        cell = row.createCell(55, CellType.STRING);
        cell.setCellValue("Максимум Индекс Джини");

        //56 массив Индекс Джини
        cell = row.createCell(56, CellType.STRING);
        cell.setCellValue("массив значений Индекс Джини");


        //57 медиана Индекс Херфиндаля по организациям авторов
        cell = row.createCell(57, CellType.STRING);
        cell.setCellValue("Медиана Индекс Херфиндаля по организациям авторов");

        //58 максимум Индекс Херфиндаля по организациям авторов
        cell = row.createCell(58, CellType.STRING);
        cell.setCellValue("Максимум Индекс Херфиндаля по организациям авторов");

        //59 массив Индекс Херфиндаля по организациям авторов
        cell = row.createCell(59, CellType.STRING);
        cell.setCellValue("массив значений Индекс Херфиндаля по организациям авторов");


        //60 медиана Место журнала в рейтинге SCIENCE INDEX
        cell = row.createCell(60, CellType.STRING);
        cell.setCellValue("Медиана Место журнала в рейтинге SCIENCE INDEX");

        //61 максимум Место журнала в рейтинге SCIENCE INDEX
        cell = row.createCell(61, CellType.STRING);
        cell.setCellValue("Максимум Место журнала в рейтинге SCIENCE INDEX");

        //62 массив Место журнала в рейтинге SCIENCE INDEX
        cell = row.createCell(62, CellType.STRING);
        cell.setCellValue("массив значений Место журнала в рейтинге SCIENCE INDEX");


        //63 медиана Общее число цитирований журнала в текущем году
        cell = row.createCell(63, CellType.STRING);
        cell.setCellValue("Медиана Общее число цитирований журнала в текущем году");

        //64 максимум Общее число цитирований журнала в текущем году
        cell = row.createCell(64, CellType.STRING);
        cell.setCellValue("Максимум Общее число цитирований журнала в текущем году");

        //65 массив Общее число цитирований журнала в текущем году
        cell = row.createCell(65, CellType.STRING);
        cell.setCellValue("массив значений Общее число цитирований журнала в текущем году");


        //66 медиана Общее число цитирований журнала в текущем году в том числе: самоцитирований"
        cell = row.createCell(66, CellType.STRING);
        cell.setCellValue("Медиана Общее число цитирований журнала в текущем году, в том числе: самоцитирований");

        //67 максимум Общее число цитирований журнала в текущем году в том числе: самоцитирований"
        cell = row.createCell(67, CellType.STRING);
        cell.setCellValue("Максимум Общее число цитирований журнала в текущем году, в том числе: самоцитирований");

        //68 массив Общее число цитирований журнала в текущем году в том числе: самоцитирований"
        cell = row.createCell(68, CellType.STRING);
        cell.setCellValue("массив значений Общее число цитирований журнала в текущем году, в том числе: самоцитирований");


        //69 находится ли журнал в таблице RSCI рейтинг
        cell = row.createCell(69, CellType.STRING);
        cell.setCellValue("Присутствие в таблице RSCI рейтинг");

        //70 Группа OECD
        cell = row.createCell(70, CellType.STRING);
        cell.setCellValue("Группа OECD");

        //71 Нормированный рейтинг RSCI
        cell = row.createCell(71, CellType.STRING);
        cell.setCellValue("Нормированный рейтинг RSCI");

        //72 квартиль RSCI
        cell = row.createCell(72, CellType.STRING);
        cell.setCellValue("Квартиль RSCI");

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

                //26 находится ли в таблице id rus
                cell = row.createCell(26, CellType.BOOLEAN);
                cell.setCellValue(journal.isRus);

                //27 медиана Вероятность цитирования после прочтения
                cell = row.createCell(27, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianProbabilityQuotingReading());

                //28 максимум Вероятность цитирования после прочтения
                cell = row.createCell(28, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxProbabilityQuotingReading());

                //29 массив Вероятность цитирования после прочтения по годам
                cell = row.createCell(29, CellType.STRING);
                cell.setCellValue(journal.getProbabilityQuotingReading());


                //30 медиана Двухлетний импакт-фактор по ядру РИНЦ
                cell = row.createCell(30, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTwoYearImpactFactorCore());

                //31 максимум Двухлетний импакт-фактор по ядру РИНЦ
                cell = row.createCell(31, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTwoYearImpactFactorCore());

                //32 массив Двухлетний импакт-фактор по ядру РИНЦ
                cell = row.createCell(32, CellType.STRING);
                cell.setCellValue(journal.getTwoYearImpactFactorCore());


                //33 медиана Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования
                cell = row.createCell(33, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTwoYearImpactFactorCoreWithoutCitations());

                //34 максимум Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования
                cell = row.createCell(34, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTwoYearImpactFactorCoreWithoutCitations());

                //35 массив Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования
                cell = row.createCell(35, CellType.STRING);
                cell.setCellValue(journal.getTwoYearImpactFactorCoreWithoutCitations());


                //36 медиана Двухлетний импакт-фактор РИНЦ
                cell = row.createCell(36, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTwoYearImpactFactor());

                //37 максимум Двухлетний импакт-фактор РИНЦ
                cell = row.createCell(37, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTwoYearImpactFactor());

                //38 массив Двухлетний импакт-фактор РИНЦ
                cell = row.createCell(38, CellType.STRING);
                cell.setCellValue(journal.getTwoYearImpactFactor());


                //39 медиана Двухлетний импакт-фактор РИНЦ без самоцитирования
                cell = row.createCell(39, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTwoYearImpactFactorWithCitations());

                //40 максимум Двухлетний импакт-фактор РИНЦ без самоцитирования
                cell = row.createCell(40, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTwoYearImpactFactorWithoutCitations());

                //41 массив Двухлетний импакт-фактор РИНЦ без самоцитирования
                cell = row.createCell(41, CellType.STRING);
                cell.setCellValue(journal.getTwoYearImpactFactorWithoutCitations());


                //42 медиана Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников
                cell = row.createCell(42, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTwoYearImpactFactorWithCitations());

                //43 максимум Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников
                cell = row.createCell(43, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTwoYearImpactFactorWithCitations());

                //44 массив Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников
                cell = row.createCell(44, CellType.STRING);
                cell.setCellValue(journal.getTwoYearImpactFactorWithCitations());


                //45 медиана Двухлетний коэффициент авторского самоцитирования
                cell = row.createCell(45, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTwoYearСoefficientAuthorSelfCitation());

                //46 максимум Двухлетний коэффициент авторского самоцитирования
                cell = row.createCell(46, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTwoYearСoefficientAuthorSelfCitation());

                //47 массив Двухлетний коэффициент авторского самоцитирования
                cell = row.createCell(47, CellType.STRING);
                cell.setCellValue(journal.getTwoYearСoefficientAuthorSelfCitation());


                //48 медиана Двухлетний коэффициент самоцитирования
                cell = row.createCell(48, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTwoYearСoefficientAuthorCitation());

                //49 максимум Двухлетний коэффициент самоцитирования
                cell = row.createCell(49, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTwoYearСoefficientAuthorCitation());

                //50 массив Двухлетний коэффициент самоцитирования
                cell = row.createCell(50, CellType.STRING);
                cell.setCellValue(journal.getTwoYearСoefficientAuthorCitation());


                //51 медиана Десятилетний индекс Хирша
                cell = row.createCell(51, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTenYearHirschIndex());

                //52 максимум Десятилетний индекс Хирша
                cell = row.createCell(52, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTenYearHirschIndex());

                //53 массив Десятилетний индекс Хирша
                cell = row.createCell(53, CellType.STRING);
                cell.setCellValue(journal.getTenYearHirschIndex());


                //54 медиана Индекс Джини
                cell = row.createCell(54, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianIndexGini());

                //55 максимум Индекс Джини
                cell = row.createCell(55, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxIndexGini());

                //56 массив Индекс Джини
                cell = row.createCell(56, CellType.STRING);
                cell.setCellValue(journal.getIndexGini());


                //57 медиана Индекс Херфиндаля по организациям авторов
                cell = row.createCell(57, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianIndexHerfindahlAuthorOrganizations());

                //58 максимум Индекс Херфиндаля по организациям авторов
                cell = row.createCell(58, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxIndexHerfindahlAuthorOrganizations());

                //59 массив Индекс Херфиндаля по организациям авторов
                cell = row.createCell(59, CellType.STRING);
                cell.setCellValue(journal.getIndexHerfindahlAuthorOrganizations());


                //60 медиана Место журнала в рейтинге SCIENCE INDEX
                cell = row.createCell(60, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianPlaceScienceIndexRanking());

                //61 максимум Место журнала в рейтинге SCIENCE INDEX
                cell = row.createCell(61, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxPlaceScienceIndexRanking());

                //62 массив Место журнала в рейтинге SCIENCE INDEX
                cell = row.createCell(62, CellType.STRING);
                cell.setCellValue(journal.getPlaceScienceIndexRanking());


                //63 медиана Общее число цитирований журнала в текущем году
                cell = row.createCell(63, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTotalNumberCitationsCurrentYear());

                //64 максимум Общее число цитирований журнала в текущем году
                cell = row.createCell(64, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTotalNumberCitationsCurrentYear());

                //65 массив Общее число цитирований журнала в текущем году
                cell = row.createCell(65, CellType.STRING);
                cell.setCellValue(journal.getTotalNumberCitationsCurrentYear());


                //66 медиана Общее число цитирований журнала в текущем году в том числе: самоцитирований"
                cell = row.createCell(66, CellType.NUMERIC);
                cell.setCellValue(journal.getMedianTotalNumberCitationsCurrentYearSelf());
                //67 максимум Общее число цитирований журнала в текущем году в том числе: самоцитирований"
                cell = row.createCell(67, CellType.NUMERIC);
                cell.setCellValue(journal.getMaxTotalNumberCitationsCurrentYearSelf());

                //68 массив Общее число цитирований журнала в текущем году в том числе: самоцитирований"
                cell = row.createCell(68, CellType.STRING);
                cell.setCellValue(journal.getTotalNumberCitationsCurrentYearSelf());

                //69 находится ли журнал в таблице RSCI рейтинг
                cell = row.createCell(69, CellType.BOOLEAN);
                cell.setCellValue(journal.isRatingRSCI);

                //70 Группа OECD
                cell = row.createCell(70, CellType.STRING);
                cell.setCellValue(journal.getOesdRSCI());

                //71 Нормированный рейтинг RSCI
                cell = row.createCell(71, CellType.NUMERIC);
                cell.setCellValue(journal.getRatingRSCI());

                //72 квартиль RSCI
                cell = row.createCell(72, CellType.NUMERIC);
                cell.setCellValue(journal.getQuartileRSCI());


            }


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

    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
}
