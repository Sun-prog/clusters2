

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ArrayList<Integer> listCheck = Parser.parse("C:/Users/doc1.xlsx");

        listCheck = ParseTableJournal.parse("C:/Users/doc2.xlsx", listCheck);

        listCheck = ParseTableRince.parse("C:/Users/doc3.xlsx", listCheck);

        listCheck = ParseTableRus.parse("C:/Users/doc3.txt", listCheck);

        NewFileReport.createFile(listCheck);

        NewFileJournal.createFile();

    }
}