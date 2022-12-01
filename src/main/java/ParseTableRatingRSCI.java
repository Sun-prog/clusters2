import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CellValue;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class ParseTableRatingRSCI {
    public static void parse(String name) throws InterruptedException {
        InputStream in = null;
        XSSFWorkbook wb = null;
        int read = MessageDAO.getNumberReadRSCI();
        boolean find=false;

        List<Journal> list = JournalDAO.listJournal();
        List<String> notReadRSCI = MessageDAO.listMessage();

        int y = 1;
        try {

            FileInputStream file = new FileInputStream(new File(name));

            wb = new XSSFWorkbook(file);

            Sheet datatypeSheet = wb.getSheetAt(0);
            Iterator<Row> it = datatypeSheet.iterator();

            while (it.hasNext()) {
                try {
                    Row row = it.next();
                    find=false;
                    if (row.getRowNum() == 0) {continue;}
                    System.out.println("читается строчка из RatingRince "+y);
                    String tempName ="";
                        if (row.getCell(1).getCellTypeEnum() == CellType.STRING) {
                            tempName = row.getCell(1).getStringCellValue();
                        }
                    for (Journal journal : list) {
                        String journalName = journal.getNameJournalUni().replaceAll("\\s+","");
                        if(tempName.replaceAll("\\s+","").equalsIgnoreCase(journalName)){
                            journal.setRatingRSCI(true);
                            System.out.println("Statement  is true");
                            find=true;
                            MessageDAO.setNumberReadRSCI(read++);

                            if (HSSFDateUtil.isCellDateFormatted(row.getCell(3))) {
                                DateFormat df = new SimpleDateFormat("dd.MM.yy");
                                Date date = row.getCell(3).getDateCellValue();
                                String cellValue = df.format(date);
                                System.out.println (cellValue);
                                journal.setOesdRSCI(cellValue);
                            }

                            try {
                                if (row.getCell(5).getCellTypeEnum() == CellType.STRING) {
                                    journal.setQuartileRSCI(Integer.parseInt(row.getCell(5).getStringCellValue()));
                                    System.out.println("квартиль String"+Integer.parseInt(row.getCell(5).getStringCellValue()));
                                } else if (row.getCell(5).getCellTypeEnum() == CellType.NUMERIC) {
                                    System.out.println("квартиль int"+Double.parseDouble(String.valueOf(row.getCell(5).getNumericCellValue())));
                                    journal.setQuartileRSCI(Double.parseDouble(String.valueOf(row.getCell(5).getNumericCellValue())));
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                //journal.setQuartileRSCI(0);
                                notReadRSCI.add(tempName + "ошибка в квартиль");
                            }
                            try {
                                if (row.getCell(4).getCellTypeEnum() == CellType.STRING) {
                                    journal.setRatingRSCI(Double.parseDouble(row.getCell(4).getStringCellValue().replace(',', '.')));
                                } else if (row.getCell(4).getCellTypeEnum() == CellType.NUMERIC) {
                                    System.out.println("rating"+Double.parseDouble(String.valueOf(row.getCell(4).getNumericCellValue())));
                                    journal.setRatingRSCI (Double.parseDouble(String.valueOf(row.getCell(4).getNumericCellValue())));
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                                notReadRSCI.add(tempName+"ошибка в rating");

                            } catch (NullPointerException e){
                                e.printStackTrace();
                                notReadRSCI.add(tempName+"ошибка в rating");
                            }
                        }

                    }
                    if (find==false)notReadRSCI.add(tempName);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
                y++;
            }

            file.close();
            //listCheck.add(read);



        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //findSourceInJournals();


    }

}
