

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException, InterruptedException {

        ArrayList<Integer> listCheck = Parser.parse("C:/Users/Test.xlsx");

        NewFileReport.createFile(listCheck);

        NewFileJournal.createFile();

    }
}