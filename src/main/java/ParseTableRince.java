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
        int nullTwoYearСoefficientAuthorSelfCitation=0;
        int nullTwoYearСoefficientAuthorCitation=0;
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
                                //добавляем массив значений "Вероятность цитирования после прочтения, %" по годам начиная с 2011(ячейка 10) до 2020 ячейка 19
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=10,yearMap=2011;cellNumber<20;cellNumber++,yearMap++) {
                                    System.out.println("добавляется инфо о журнале "+journal.getNumber());

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
                                    //System.out.println("читается строчка из UniRince "+y);
                                }
                                //добавляем массив значений "Двухлетний импакт-фактор по ядру РИНЦ, " по годам начиная с 2011 до 2020
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=40,yearMap=2011;cellNumber<50;cellNumber++,yearMap++) {
                                    System.out.println("добавляется импакт о журнале "+journal.getNumber());

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
                                    //System.out.println("читается строчка из UniRince "+y);
                                }
                                //добавляем массив значений "Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования " по годам начиная с 2011 до 2020
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=50,yearMap=2011;cellNumber<60;cellNumber++,yearMap++) {
                                    System.out.println("добавляется Двухлетний импакт-фактор по ядру РИНЦ без самоцитирования  о журнале "+journal.getNumber());

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
                                    //System.out.println("читается строчка из UniRince "+y);
                                }

                                //добавляем массив значений "Двухлетний импакт-фактор " по годам начиная с 2011 до 2020 ячейка
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=60,yearMap=2011;cellNumber<70;cellNumber++,yearMap++) {
                                    System.out.println("добавляется Двухлетний импакт-фактор о журнале "+journal.getNumber());

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
                                    //System.out.println("читается строчка из UniRince "+y);
                                }

                                //добавляем массив значений "Двухлетний импакт-фактор без самоцитирования" по годам начиная с 2011 до 2020 ячейка60
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=70,yearMap=2011;cellNumber<80;cellNumber++,yearMap++) {
                                    System.out.println("добавляется Двухлетний импакт-фактор без самоцитирования о журнале "+journal.getNumber());

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
                                    //System.out.println("читается строчка из UniRince "+y);
                                }
                                //добавляем массив значений "Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=80,yearMap=2011;cellNumber<90;cellNumber++,yearMap++) {
                                    System.out.println("добавляется Двухлетний импакт-фактор РИНЦ с учетом цитирования из всех источников журнале "+journal.getNumber());

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
                                    //System.out.println("читается строчка из UniRince "+y);
                                }

                                //добавляем массив значений "Двухлетний коэффициент авторского самоцитирования" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=90,yearMap=2011;cellNumber<100;cellNumber++,yearMap++) {
                                    System.out.println("Двухлетний коэффициент авторского самоцитирования в журнале "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYearСoefficientAuthorSelfCitation(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYearСoefficientAuthorSelfCitation(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYearСoefficientAuthorSelfCitation(yearMap, -1.0);

                                        nullTwoYearСoefficientAuthorSelfCitation++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYearСoefficientAuthorSelfCitation(yearMap, -1.0);
                                    }
                                    //System.out.println("читается строчка из UniRince "+y);
                                }

                                //добавляем массив значений "Двухлетний коэффициент самоцитирования" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=100,yearMap=2011;cellNumber<110;cellNumber++,yearMap++) {
                                    System.out.println("Двухлетний коэффициент самоцитирования в журнале "+journal.getNumber());

                                    try {
                                        if (row.getCell(cellNumber).getCellTypeEnum() == CellType.STRING) {
                                            journal.addTwoYearСoefficientAuthorCitation(yearMap, (Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.'))));
                                            System.out.println(yearMap+ Double.parseDouble(row.getCell(cellNumber).getStringCellValue().replace(',', '.')));
                                        } else if (row.getCell(cellNumber).getCellTypeEnum() == CellType.NUMERIC) {
                                            journal.addTwoYearСoefficientAuthorCitation(yearMap, (Double.parseDouble(String.valueOf(row.getCell(cellNumber).getNumericCellValue()))));
                                            System.out.println(yearMap+ row.getCell(cellNumber).getNumericCellValue());
                                        }
                                    } catch (NumberFormatException e) {
                                        e.printStackTrace();
                                        journal.addTwoYearСoefficientAuthorCitation(yearMap, -1.0);

                                        nullTwoYearСoefficientAuthorCitation++;
                                    } catch (NullPointerException e){
                                        e.printStackTrace();
                                        journal.addTwoYearСoefficientAuthorCitation(yearMap, -1.0);
                                    }
                                    //System.out.println("читается строчка из UniRince "+y);
                                }

                                //добавляем массив значений "Десятилетний индекс Хирша" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=110,yearMap=2011;cellNumber<120;cellNumber++,yearMap++) {
                                    System.out.println("Десятилетний индекс Хирша "+journal.getNumber());

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
                                    //System.out.println("читается строчка из UniRince "+y);
                                }

                                //добавляем массив значений "Индекс Джини" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=120,yearMap=2011;cellNumber<130;cellNumber++,yearMap++) {
                                    System.out.println("Индекс Джини "+journal.getNumber());

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
                                    //System.out.println("читается строчка из UniRince "+y);
                                }

                                //добавляем массив значений " Индекс Херфиндаля по организациям авторов" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=130,yearMap=2011;cellNumber<140;cellNumber++,yearMap++) {
                                    System.out.println(" Индекс Херфиндаля по организациям авторов "+journal.getNumber());

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
                                //добавляем массив значений " Место журнала в рейтинге SCIENCE INDEX" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=140,yearMap=2011;cellNumber<150;cellNumber++,yearMap++) {
                                    System.out.println(" Место журнала в рейтинге SCIENCE INDEX "+journal.getNumber());

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

                                //добавляем массив значений " Общее число цитирований журнала в текущем году" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=150,yearMap=2011;cellNumber<160;cellNumber++,yearMap++) {
                                    System.out.println("Общее число цитирований журнала в текущем году "+journal.getNumber());

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

                                //добавляем массив значений " Общее число цитирований журнала в текущем году, в том числе: самоцитирований" по годам начиная с 2011(ячейка 20) до 2020 ячейка29
                                //там, где прочерки, ставим -1. В массив для расчета медианы отрицательные значения не добавляются
                                for (int cellNumber=160,yearMap=2011;cellNumber<170;cellNumber++,yearMap++) {
                                    System.out.println("Общее число цитирований журнала в текущем году, в том числе: самоцитирований "+journal.getNumber());

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
                    //System.out.println("ошибка в кластере " + y);}
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
            listCheck.add(nullTwoYearСoefficientAuthorSelfCitation);
            listCheck.add( nullTwoYearСoefficientAuthorCitation);
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
