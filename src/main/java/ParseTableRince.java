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
        int nullProbabilityQuotingReading=0;
        int nullTwoYearImpactFactorCore=0;
        int nullTwoYearImpactFactorCoreWithoutCitations=0;
        int nullTwoYearImpactFactor=0;
        int nullTwoYearImpactFactorWithoutCitations=0;
        int nullTwoYearImpactFactorWithCitations=0;
        int nullTwoYear–°oefficientAuthorSelfCitation=0;
        int nullTwoYear–°oefficientAuthorCitation=0;
        int nullTenYearHirschIndex=0;
        int nullIndexGini=0;
        int nullIndexHerfindahlAuthorOrganizations=0;
        int nullPlaceScienceIndexRanking=0;
        int nullTotalNumberCitationsCurrentYear=0;
        int nullTotalNumberCitationsCurrentYearSelf=0;

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
                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–í–Ķ—Ä–ĺ—Ź—ā–Ĺ–ĺ—Ā—ā—Ć —Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź –Ņ–ĺ—Ā–Ľ–Ķ –Ņ—Ä–ĺ—á—ā–Ķ–Ĺ–ł—Ź, %" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 10) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į 19
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=10,yearMap=2011;cellNumber<20;cellNumber++,yearMap++) {
                                    System.out.println("–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ—ā—Ā—Ź –ł–Ĺ—Ą–ĺ –ĺ –∂—É—Ä–Ĺ–į–Ľ–Ķ "+journal.getNumber());

                                    //2011
                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addProbabilityQuotingReading(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addProbabilityQuotingReading(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addProbabilityQuotingReading(yearMap, -1.0);

                                        nullProbabilityQuotingReading++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addProbabilityQuotingReading(yearMap, -1.0);
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }
                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä –Ņ–ĺ —Ź–ī—Ä—É –†–ė–Ě–¶, " –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011 –ī–ĺ 2020
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=40,yearMap=2011;cellNumber<50;cellNumber++,yearMap++) {
                                    System.out.println("–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ—ā—Ā—Ź –ł–ľ–Ņ–į–ļ—ā –ĺ –∂—É—Ä–Ĺ–į–Ľ–Ķ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYearImpactFactorCore(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYearImpactFactorCore(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactorCore(yearMap, -1.0);

                                        nullTwoYearImpactFactorCore++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactorCore(yearMap, -1.0);
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }
                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä –Ņ–ĺ —Ź–ī—Ä—É –†–ė–Ě–¶ –Ī–Ķ–∑ —Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź " –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011 –ī–ĺ 2020
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=50,yearMap=2011;cellNumber<60;cellNumber++,yearMap++) {
                                    System.out.println("–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ—ā—Ā—Ź –Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä –Ņ–ĺ —Ź–ī—Ä—É –†–ė–Ě–¶ –Ī–Ķ–∑ —Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź  –ĺ –∂—É—Ä–Ĺ–į–Ľ–Ķ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYearImpactFactorCoreWithoutCitations(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYearImpactFactorCoreWithoutCitations(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactorCoreWithoutCitations(yearMap, -1.0);

                                        nullTwoYearImpactFactorCoreWithoutCitations++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactorCore(yearMap, -1.0);
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä " –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011 –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=60,yearMap=2011;cellNumber<70;cellNumber++,yearMap++) {
                                    System.out.println("–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ—ā—Ā—Ź –Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä –ĺ –∂—É—Ä–Ĺ–į–Ľ–Ķ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYearImpactFactor(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYearImpactFactor(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactor(yearMap, -1.0);

                                        nullTwoYearImpactFactor++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactor(yearMap, -1.0);
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä –Ī–Ķ–∑ —Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011 –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į60
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=70,yearMap=2011;cellNumber<80;cellNumber++,yearMap++) {
                                    System.out.println("–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ—ā—Ā—Ź –Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä –Ī–Ķ–∑ —Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź –ĺ –∂—É—Ä–Ĺ–į–Ľ–Ķ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYearImpactFactorWithoutCitations(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYearImpactFactorWithoutCitations(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactorWithoutCitations(yearMap, -1.0);

                                        nullTwoYearImpactFactorWithoutCitations++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactorWithoutCitations(yearMap, -1.0);
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }
                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä –†–ė–Ě–¶ —Ā —É—á–Ķ—ā–ĺ–ľ —Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź –ł–∑ –≤—Ā–Ķ—Ö –ł—Ā—ā–ĺ—á–Ĺ–ł–ļ–ĺ–≤" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=80,yearMap=2011;cellNumber<90;cellNumber++,yearMap++) {
                                    System.out.println("–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ—ā—Ā—Ź –Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–ľ–Ņ–į–ļ—ā-—Ą–į–ļ—ā–ĺ—Ä –†–ė–Ě–¶ —Ā —É—á–Ķ—ā–ĺ–ľ —Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź –ł–∑ –≤—Ā–Ķ—Ö –ł—Ā—ā–ĺ—á–Ĺ–ł–ļ–ĺ–≤ –∂—É—Ä–Ĺ–į–Ľ–Ķ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYearImpactFactorWithCitations(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYearImpactFactorWithCitations(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactorWithCitations(yearMap, -1.0);

                                        nullTwoYearImpactFactorWithCitations++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYearImpactFactorWithCitations(yearMap, -1.0);
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ļ–ĺ—ć—Ą—Ą–ł—Ü–ł–Ķ–Ĺ—ā –į–≤—ā–ĺ—Ä—Ā–ļ–ĺ–≥–ĺ —Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=90,yearMap=2011;cellNumber<100;cellNumber++,yearMap++) {
                                    System.out.println("–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ļ–ĺ—ć—Ą—Ą–ł—Ü–ł–Ķ–Ĺ—ā –į–≤—ā–ĺ—Ä—Ā–ļ–ĺ–≥–ĺ —Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź –≤ –∂—É—Ä–Ĺ–į–Ľ–Ķ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYear–°oefficientAuthorSelfCitation(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYear–°oefficientAuthorSelfCitation(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYear–°oefficientAuthorSelfCitation(yearMap, -1.0);

                                        nullTwoYear–°oefficientAuthorSelfCitation++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYear–°oefficientAuthorSelfCitation(yearMap, -1.0);
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ļ–ĺ—ć—Ą—Ą–ł—Ü–ł–Ķ–Ĺ—ā —Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=100,yearMap=2011;cellNumber<110;cellNumber++,yearMap++) {
                                    System.out.println("–Ē–≤—É—Ö–Ľ–Ķ—ā–Ĺ–ł–Ļ –ļ–ĺ—ć—Ą—Ą–ł—Ü–ł–Ķ–Ĺ—ā —Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł—Ź –≤ –∂—É—Ä–Ĺ–į–Ľ–Ķ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYear–°oefficientAuthorCitation(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYear–°oefficientAuthorCitation(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYear–°oefficientAuthorCitation(yearMap, -1.0);

                                        nullTwoYear–°oefficientAuthorCitation++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYear–°oefficientAuthorCitation(yearMap, -1.0);
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–Ē–Ķ—Ā—Ź—ā–ł–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–Ĺ–ī–Ķ–ļ—Ā –•–ł—Ä—ą–į" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=110,yearMap=2011;cellNumber<120;cellNumber++,yearMap++) {
                                    System.out.println("–Ē–Ķ—Ā—Ź—ā–ł–Ľ–Ķ—ā–Ĺ–ł–Ļ –ł–Ĺ–ī–Ķ–ļ—Ā –•–ł—Ä—ą–į "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTenYearHirschIndex(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTenYearHirschIndex(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTenYearHirschIndex(yearMap, -1.0);

                                        nullTenYearHirschIndex++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTenYearHirschIndex(yearMap, -1.0);
                                        nullTenYearHirschIndex++;
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ "–ė–Ĺ–ī–Ķ–ļ—Ā –Ē–∂–ł–Ĺ–ł" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=120,yearMap=2011;cellNumber<130;cellNumber++,yearMap++) {
                                    System.out.println("–ė–Ĺ–ī–Ķ–ļ—Ā –Ē–∂–ł–Ĺ–ł "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addIndexGini(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addIndexGini(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addIndexGini(yearMap, -1.0);
                                        nullIndexGini++;

                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addIndexGini(yearMap, -1.0);
                                        nullIndexGini++;
                                    }
                                    //System.out.println("—á–ł—ā–į–Ķ—ā—Ā—Ź —Ā—ā—Ä–ĺ—á–ļ–į –ł–∑ UniRince "+y);
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ " –ė–Ĺ–ī–Ķ–ļ—Ā –•–Ķ—Ä—Ą–ł–Ĺ–ī–į–Ľ—Ź –Ņ–ĺ –ĺ—Ä–≥–į–Ĺ–ł–∑–į—Ü–ł—Ź–ľ –į–≤—ā–ĺ—Ä–ĺ–≤" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=130,yearMap=2011;cellNumber<140;cellNumber++,yearMap++) {
                                    System.out.println(" –ė–Ĺ–ī–Ķ–ļ—Ā –•–Ķ—Ä—Ą–ł–Ĺ–ī–į–Ľ—Ź –Ņ–ĺ –ĺ—Ä–≥–į–Ĺ–ł–∑–į—Ü–ł—Ź–ľ –į–≤—ā–ĺ—Ä–ĺ–≤ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addIndexHerfindahlAuthorOrganizations(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addIndexHerfindahlAuthorOrganizations(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addIndexHerfindahlAuthorOrganizations(yearMap, -1.0);
                                        nullIndexHerfindahlAuthorOrganizations++;

                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addIndexHerfindahlAuthorOrganizations(yearMap, -1.0);
                                        nullIndexHerfindahlAuthorOrganizations++;
                                    }
                                }
                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ " –ú–Ķ—Ā—ā–ĺ –∂—É—Ä–Ĺ–į–Ľ–į –≤ —Ä–Ķ–Ļ—ā–ł–Ĺ–≥–Ķ SCIENCE INDEX" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=140,yearMap=2011;cellNumber<150;cellNumber++,yearMap++) {
                                    System.out.println(" –ú–Ķ—Ā—ā–ĺ –∂—É—Ä–Ĺ–į–Ľ–į –≤ —Ä–Ķ–Ļ—ā–ł–Ĺ–≥–Ķ SCIENCE INDEX "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addPlaceScienceIndexRanking(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addPlaceScienceIndexRanking(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addPlaceScienceIndexRanking(yearMap, -1.0);
                                        nullPlaceScienceIndexRanking++;

                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addPlaceScienceIndexRanking(yearMap, -1.0);
                                        nullPlaceScienceIndexRanking++;
                                    }
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ " –ě–Ī—Č–Ķ–Ķ —á–ł—Ā–Ľ–ĺ —Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł–Ļ –∂—É—Ä–Ĺ–į–Ľ–į –≤ —ā–Ķ–ļ—É—Č–Ķ–ľ –≥–ĺ–ī—É" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=150,yearMap=2011;cellNumber<160;cellNumber++,yearMap++) {
                                    System.out.println("–ě–Ī—Č–Ķ–Ķ —á–ł—Ā–Ľ–ĺ —Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł–Ļ –∂—É—Ä–Ĺ–į–Ľ–į –≤ —ā–Ķ–ļ—É—Č–Ķ–ľ –≥–ĺ–ī—É "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTotalNumberCitationsCurrentYear(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTotalNumberCitationsCurrentYear(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTotalNumberCitationsCurrentYear(yearMap, -1.0);
                                        nullTotalNumberCitationsCurrentYear++;

                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTotalNumberCitationsCurrentYear(yearMap, -1.0);
                                        nullTotalNumberCitationsCurrentYear++;
                                    }
                                }

                                //–ī–ĺ–Ī–į–≤–Ľ—Ź–Ķ–ľ –ľ–į—Ā—Ā–ł–≤ –∑–Ĺ–į—á–Ķ–Ĺ–ł–Ļ " –ě–Ī—Č–Ķ–Ķ —á–ł—Ā–Ľ–ĺ —Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł–Ļ –∂—É—Ä–Ĺ–į–Ľ–į –≤ —ā–Ķ–ļ—É—Č–Ķ–ľ –≥–ĺ–ī—É, –≤ —ā–ĺ–ľ —á–ł—Ā–Ľ–Ķ:¬†—Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł–Ļ" –Ņ–ĺ –≥–ĺ–ī–į–ľ –Ĺ–į—á–ł–Ĺ–į—Ź —Ā 2011(—Ź—á–Ķ–Ļ–ļ–į 20) –ī–ĺ 2020 —Ź—á–Ķ–Ļ–ļ–į29
                                //—ā–į–ľ, –≥–ī–Ķ –Ņ—Ä–ĺ—á–Ķ—Ä–ļ–ł, —Ā—ā–į–≤–ł–ľ -1. –í –ľ–į—Ā—Ā–ł–≤ –ī–Ľ—Ź —Ä–į—Ā—á–Ķ—ā–į –ľ–Ķ–ī–ł–į–Ĺ—č –ĺ—ā—Ä–ł—Ü–į—ā–Ķ–Ľ—Ć–Ĺ—č–Ķ –∑–Ĺ–į—á–Ķ–Ĺ–ł—Ź –Ĺ–Ķ –ī–ĺ–Ī–į–≤–Ľ—Ź—é—ā—Ā—Ź
                                for (int cellNumber=160,yearMap=2011;cellNumber<170;cellNumber++,yearMap++) {
                                    System.out.println("–ě–Ī—Č–Ķ–Ķ —á–ł—Ā–Ľ–ĺ —Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł–Ļ –∂—É—Ä–Ĺ–į–Ľ–į –≤ —ā–Ķ–ļ—É—Č–Ķ–ľ –≥–ĺ–ī—É, –≤ —ā–ĺ–ľ —á–ł—Ā–Ľ–Ķ:¬†—Ā–į–ľ–ĺ—Ü–ł—ā–ł—Ä–ĺ–≤–į–Ĺ–ł–Ļ "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTotalNumberCitationsCurrentYearSelf(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTotalNumberCitationsCurrentYearSelf(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTotalNumberCitationsCurrentYearSelf(yearMap, -1.0);
                                        nullTotalNumberCitationsCurrentYearSelf++;

                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTotalNumberCitationsCurrentYearSelf(yearMap, -1.0);
                                        nullTotalNumberCitationsCurrentYearSelf++;
                                    }
                                }


                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }


                        }
                    }

                } catch (NullPointerException e) {
                    e.printStackTrace();
                    //System.out.println("–ĺ—ą–ł–Ī–ļ–į –≤ –ļ–Ľ–į—Ā—ā–Ķ—Ä–Ķ " + y);}
                }
                y++;
            }

            file.close();
            listCheck.add(read);
            listCheck.add(nullNumberIssuesPerYear);
            listCheck.add(nullProbabilityQuotingReading);
            listCheck.add(nullTwoYearImpactFactorCore);
            listCheck.add(nullTwoYearImpactFactorCoreWithoutCitations);
            listCheck.add(nullTwoYearImpactFactor);
            listCheck.add(nullTwoYearImpactFactorWithoutCitations);
            listCheck.add(nullTwoYearImpactFactorWithCitations);
            listCheck.add(nullTwoYear–°oefficientAuthorSelfCitation);
            listCheck.add( nullTwoYear–°oefficientAuthorCitation);
            listCheck.add(nullTenYearHirschIndex);
            listCheck.add(nullIndexGini);
            listCheck.add(nullIndexHerfindahlAuthorOrganizations);
            listCheck.add(nullPlaceScienceIndexRanking);
            listCheck.add(nullTotalNumberCitationsCurrentYear);
            listCheck.add(nullTotalNumberCitationsCurrentYearSelf);



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
