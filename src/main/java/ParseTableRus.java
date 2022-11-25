import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseTableRus {
    public static ArrayList<Integer> parse(String name, ArrayList<Integer> listCheck) throws InterruptedException {
        List<Journal> list = JournalDAO.listJournal();
        int number=0;
        int find=0;
        try {
            File file = new File(name);
            //создаем объект FileReader для объекта File
            FileReader fr = new FileReader(file);
            //создаем BufferedReader с существующего FileReader для построчного считывания
            BufferedReader reader = new BufferedReader(fr);
            // считаем сначала первую строку
            //BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
            String line;
            while ((line = reader.readLine()) != null) {
                // Do something with line
                number = Integer.parseInt(line);
                for (Journal journal : list) {
                    if (journal.getNumber() == number) {
                        journal.isRus = true;
                        find++;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        listCheck.add(find);
        return listCheck;

    }

}
