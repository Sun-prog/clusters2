import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ParseTableRince {
    public static ArrayList<Integer> parse(String name, ArrayList<Integer> listCheck) throws InterruptedException {
        InputStream in = null;
        XSSFWorkbook wb = null;
        int read =0;
        int nullNumberIssuesPerYear=0;

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
                    System.out.println("читается строчка из UniRince "+y);
                    int number=0;
                        if (row.getCell(0).getCellTypeEnum() == CellType.STRING) {
                            number = Integer.parseInt(row.getCell(0).getStringCellValue());
                        } else if (row.getCell(0).getCellTypeEnum() == CellType.NUMERIC) {
                            number = Integer.parseInt(String.valueOf(row.getCell(0).getNumericCellValue()));
                        }
                    for (Journal journal : list) {
                        if (journal.getNumber() == number) {
                            try {
                                if (row.getCell(1).getCellTypeEnum() == CellType.STRING) {
                                    journal.setUniRince(true);
                                    journal.setNameJournalRince(row.getCell(1).getStringCellValue());
                                    read++;
                                }
                                if (row.getCell(2).getCellTypeEnum() == CellType.STRING) {
                                    journal.setPlace(row.getCell(2).getStringCellValue());
                                }
                                if (row.getCell(3).getCellTypeEnum() == CellType.STRING) {
                                    journal.setCity(row.getCell(3).getStringCellValue());
                                }
                                if (row.getCell(4).getCellTypeEnum() == CellType.STRING) {
                                   journal.setNumberIssues(Integer.parseInt(row.getCell(4).getStringCellValue()));
                                } else if (row.getCell(4).getCellTypeEnum() == CellType.NUMERIC) {
                                    journal.setNumberIssues(Integer.parseInt(String.valueOf(row.getCell(4).getNumericCellValue())));
                                }
                                if (row.getCell(5).getCellTypeEnum() == CellType.STRING) {
                                    journal.setUniQuantityArticles(Integer.parseInt(row.getCell(5).getStringCellValue()));
                                } else if (row.getCell(5).getCellTypeEnum() == CellType.NUMERIC) {
                                    journal.setUniQuantityArticles(Integer.parseInt(String.valueOf(row.getCell(5).getNumericCellValue())));
                                }
                                if (row.getCell(6).getCellTypeEnum() == CellType.STRING) {
                                    journal.setUniQuantityArticlesFullText(Integer.parseInt(row.getCell(6).getStringCellValue()));
                                } else if (row.getCell(6).getCellTypeEnum() == CellType.NUMERIC) {
                                    journal.setUniQuantityArticlesFullText(Integer.parseInt(String.valueOf(row.getCell(6).getNumericCellValue())));
                                }
                                if (row.getCell(7).getCellTypeEnum() == CellType.STRING) {
                                    journal.setNumberCitations(Integer.parseInt(row.getCell(7).getStringCellValue()));
                                } else if (row.getCell(7).getCellTypeEnum() == CellType.NUMERIC) {
                                    journal.setNumberCitations(Integer.parseInt(String.valueOf(row.getCell(7).getNumericCellValue())));
                                }
                                if (row.getCell(8).getCellTypeEnum() == CellType.STRING) {
                                    journal.setNumberArticlesPerIssue(Integer.parseInt(row.getCell(8).getStringCellValue()));
                                } else if (row.getCell(8).getCellTypeEnum() == CellType.NUMERIC) {
                                    journal.setNumberArticlesPerIssue(Integer.parseInt(String.valueOf(row.getCell(8).getNumericCellValue())));
                                }
                                try {
                                    if (row.getCell(9).getCellTypeEnum() == CellType.STRING) {
                                        journal.setNumberIssuesPerYear(Integer.parseInt(row.getCell(9).getStringCellValue()));
                                    } else if (row.getCell(9).getCellTypeEnum() == CellType.NUMERIC) {
                                        journal.setNumberIssuesPerYear(Integer.parseInt(String.valueOf(row.getCell(9).getNumericCellValue())));
                                    }
                                } catch (NumberFormatException e) {
                                    e.printStackTrace();
                                    journal.setNumberIssuesPerYear(0);
                                    nullNumberIssuesPerYear++;
                                }
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
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
            listCheck.add(nullNumberIssuesPerYear);



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
