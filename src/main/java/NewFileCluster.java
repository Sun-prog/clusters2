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

public class NewFileCluster {
    public static void createFile() {
       // Document doc;
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Articles");
        //HSSFWorkbook workbook = new HSSFWorkbook();
        //HSSFSheet sheet = workbook.createSheet("Articles sheet");
        List<Cluster> list = ClusterDAO.listCluster();

        int numberCell = 10;
        int rownum = 0;
        Cell cell;
        Row row;
        //
        //HSSFCellStyle style = createStyleForTitle(workbook);

        row = sheet.createRow(rownum);

        // Excel Country
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Cluster Number");
        //cell.setCellStyle(style);
        //Excel Year
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("List Articles");
        //cell.setCellStyle(style);
        //Excel Proposals
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("List Journal");
        //cell.setCellStyle(style);
        //Excel Proposals
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("elastic");
        //cell.setCellStyle(style);
        //Excel Proposals
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("count");
        //cell.setCellStyle(style);
        //Excel Proposals
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("years diff");
        //cell.setCellStyle(style);

        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("years max");
        //cell.setCellStyle(style);

        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("retracted");
        //cell.setCellStyle(style);

        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("SRSTI code");
        //cell.setCellStyle(style);

        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("SRSTI topic");
       // cell.setCellStyle(style);

        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("OECD code");
        //cell.setCellStyle(style);

        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("OECD topic");
        //cell.setCellStyle(style);

        cell = row.createCell(12, CellType.STRING);
        cell.setCellValue("array article");
        //cell.setCellStyle(style);

        cell = row.createCell(13, CellType.STRING);
        cell.setCellValue("array journal");
        //cell.setCellStyle(style);



        try {


// заполняем новую таблицу excel
            for (Cluster cluster : list) {
                //for (int i=2012;i<2022;i++) {
                rownum++;
                row = sheet.createRow(rownum);

                // названия стран
                cell = row.createCell(0, CellType.NUMERIC);
                cell.setCellValue(cluster.getNumber());
                // год
                cell = row.createCell(1, CellType.STRING);
                cell.setCellValue(cluster.getArrayArticle());

                cell = row.createCell(2, CellType.STRING);
                cell.setCellValue(cluster.getArrayJournal());


            }


            File file = new File("C:/Users/clasters.xlsx");
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
