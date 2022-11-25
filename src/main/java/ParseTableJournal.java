import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseTableJournal {
    public static ArrayList<Integer> parse(String name, ArrayList<Integer> listCheck) throws InterruptedException {
        InputStream in = null;
        XSSFWorkbook wb = null;
        int read =0;

        List<Journal> list = JournalDAO.listJournal();

        int y = 1;
        try {

            FileInputStream file = new FileInputStream(new File(name));

            wb = new XSSFWorkbook(file);

            Sheet datatypeSheet = wb.getSheetAt(0);
            Iterator<Row> it = datatypeSheet.iterator();

            while (it.hasNext()) {
                try {
                    Row row = it.next();
                    if (row.getRowNum() == 0) {continue;}
                    System.out.println("читается строчка из Uni "+y);
                    int number=0;
                        if (row.getCell(0).getCellTypeEnum() == CellType.STRING) {
                            number = Integer.parseInt(row.getCell(0).getStringCellValue());
                        } else if (row.getCell(0).getCellTypeEnum() == CellType.NUMERIC) {
                            number = Integer.parseInt(String.valueOf(row.getCell(0).getNumericCellValue()));
                        }
                    for (Journal journal : list) {
                        if (journal.getNumber() == number) {
                            if (row.getCell(1).getCellTypeEnum() == CellType.STRING) {
                                journal.setUniTitle(true);
                                journal.setNameJournalUni(row.getCell(1).getStringCellValue());
                                read++;
                            }
                        }
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    //System.out.println("ошибка в кластере " + y);}
                }
                y++;
            }

            file.close();
            listCheck.add(read);



        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listCheck;
        //findSourceInJournals();


    }

}
