//import com.google.cloud.bigquery.*;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Parser {
    public static boolean existArticles(int number) {
        List<Article> list = ArticleDAO.listArticle();
        for (Article article : list) {
            if (article.getNumber()==number) return true;
        }
        return false;
    }

    public static boolean existSrstiCode(String newCode, Journal journal) {
        List<String> list = journal.getListSrstiCode();
        for (String existCode : list) {
            if (existCode.equals(newCode)) return true;
        }
        return false;
    }
    public static boolean existOecdCode(String newCode, Journal journal) {
        List<String> list = journal.getListOecdCode();
        for (String existCode : list) {
            //System.out.println(" статья в DAO с номером " + article.getNumber() + " совпадает с номером = " + article.getNumber().equals(number) + " с прочитанной статьей " + number);
            //if (article.getNumber().equals(number) == true) return true;
            if (existCode.equals(newCode)) return true;
        }
        return false;
    }

    public static ArrayList<Integer> checkDataCorrect() {
        ArrayList<Integer> listCheck = new ArrayList<Integer>();
        List<Journal> listJournal = JournalDAO.listJournal();
        List<Cluster> listCluster = ClusterDAO.listCluster();
        List<Article> listArticle = ArticleDAO.listArticle();
        //проверяем общее количество статей в кластерах, суммируя count всех кластеров и сравнивая с суммой статей во всех журналах
        int countRepeatTotal = 0;
        int countTotalCluster = 0;

        for (Journal journal : listJournal) {
            countRepeatTotal = journal.getNumberRepeat() + countRepeatTotal;
        }

        for (Cluster cluster : listCluster) {

            countTotalCluster = cluster.getCount() + countTotalCluster;

        }

        listCheck.add(countRepeatTotal);
        listCheck.add(countTotalCluster);
        listCheck.add(listJournal.size());
        listCheck.add(listArticle.size());
        listCheck.add(listCluster.size());
        return listCheck;
    }

    private static void findSource(Cluster cluster) {

        if (cluster.isCorrect) {
            List<Article> listArticle = ArticleDAO.listArticle();
            List<Journal> listJournal = JournalDAO.listJournal();

            for (Article article : listArticle) {
                if (article.getCluster()==cluster){

                    cluster.addArticle(article);

                }
            }

            List<Article> listClusterArticle = cluster.getListArticle();

            int sourceNumber = listClusterArticle.get(0).getNumber();

            for (Article article : listClusterArticle) {
                if (article.getNumber() < sourceNumber) {
                    sourceNumber = article.getNumber();
                }
            }

            for (Article article : listClusterArticle) {

                for (Journal journal : listJournal) {
                    if (article.getJournalNumber()==journal.getNumber()){

                        article.setJournal(journal);
                        cluster.addJournal(article.getJournal());
                        journal.addArticle(article);
                        if (!existSrstiCode(article.getSrstiCode(), journal)) {
                            journal.addListSrstiCode(article.getSrstiCode());
                            journal.addListSrstiTopic(article.getSrstiTopic());
                        }
                        if (!existOecdCode(article.getOecdCode(), journal)) {
                            journal.addListOecdCode(article.getOecdCode());
                            journal.addListOecdTopic(article.getOecdTopic());
                        }

                        if (article.getNumber() == sourceNumber) {
                            article.setSource(true);

                            journal.addArticleListSource(article);
                        }
                        else journal.addArticleListCopy(article);
                    }

                }
            }
        }

    }

    public static boolean existJournals(int number, Cluster cluster) {
        List<Journal> list = JournalDAO.listJournal();
        for (Journal journal : list) {
            if (journal.getNumber()==number) {
                journal.setNumberRepeat(journal.getNumberRepeat() + 1);
                if (!journal.containCluster(cluster)) {
                    journal.addCluster(cluster);
                }
                return true;
            }
        }
        return false;
    }


    public static ArrayList<Integer> parse(String name) throws InterruptedException {
        InputStream in = null;
        XSSFWorkbook wb = null;
        String massivArticle = null;
        String massivJournals = null;
        int y = 1;
        try {

            FileInputStream file = new FileInputStream(new File(name));

            wb = new XSSFWorkbook(file);

            Sheet datatypeSheet = wb.getSheetAt(0);
            Iterator<Row> it = datatypeSheet.iterator();

            while (it.hasNext()) {
            try {
                Row row = it.next();

                if (row.getRowNum() == 0) {
                    Cell cell = row.createCell(15, CellType.STRING);
                    cell.setCellValue("number");

                    cell = row.createCell(16, CellType.STRING);
                    cell.setCellValue("read");

                    cell = row.createCell(17, CellType.STRING);
                    cell.setCellValue("list articles");

                    cell = row.createCell(18, CellType.STRING);
                    cell.setCellValue("list journals");
                    continue; //just skip the rows if row number is 0
                }
                //присваиваем порядковые номера кластерам и записываем в новый исходный файл
                Cell cell = row.createCell(15, CellType.NUMERIC);
                cell.setCellValue(y);

                Cluster newClaster = new Cluster(y);
                System.out.println("кластер номер " + y);

                if (row.getCell(1).getCellTypeEnum() == CellType.STRING) {
                    newClaster.setElastic(row.getCell(1).getStringCellValue());
                } else if (row.getCell(1).getCellTypeEnum() == CellType.NUMERIC) {
                    newClaster.setElastic(String.valueOf(row.getCell(1).getNumericCellValue()));
                }
                if (row.getCell(2).getCellTypeEnum() == CellType.STRING) {
                } else if (row.getCell(2).getCellTypeEnum() == CellType.NUMERIC) {
                    String count = String.valueOf(row.getCell(2).getNumericCellValue());
                    newClaster.setCount(Integer.parseInt(count.substring(0, count.indexOf("."))));
                }
                if (row.getCell(8).getCellTypeEnum() == CellType.STRING) {
                    massivJournals = row.getCell(8).getStringCellValue();
                    newClaster.setArrayJournal(massivJournals);
                } else if (row.getCell(8).getCellTypeEnum() == CellType.NUMERIC) {
                    newClaster.setArrayJournal(String.valueOf(row.getCell(8).getNumericCellValue()));
                }
                if (row.getCell(3).getCellTypeEnum() == CellType.STRING) {
                    newClaster.setAuthors(row.getCell(3).getStringCellValue());
                } else if (row.getCell(3).getCellTypeEnum() == CellType.NUMERIC) {
                    newClaster.setAuthors(String.valueOf(row.getCell(3).getNumericCellValue()));
                }
                try {
                    if (row.getCell(9).getCellTypeEnum() == CellType.STRING) {
                        newClaster.setSrstiCode(row.getCell(9).getStringCellValue());
                    } else if (row.getCell(9).getCellTypeEnum() == CellType.NUMERIC) {
                        newClaster.setSrstiCode(String.valueOf(row.getCell(9).getNumericCellValue()));
                    }
                }catch (NullPointerException e) {
                    newClaster.setSrstiCode("unknown");
                    e.printStackTrace();
                }

                try {
                    if (row.getCell(10).getCellTypeEnum() == CellType.STRING) {
                        newClaster.setSrstiTopic(row.getCell(10).getStringCellValue());
                    } else if (row.getCell(10).getCellTypeEnum() == CellType.NUMERIC) {
                        newClaster.setSrstiTopic(String.valueOf(row.getCell(10).getNumericCellValue()));
                    }
                } catch (NullPointerException e) {
                    newClaster.setSrstiTopic("unknown");
                    e.printStackTrace();
                }


                try {
                    if (row.getCell(11).getCellTypeEnum() == CellType.STRING) {
                        newClaster.setOecdCode(row.getCell(11).getStringCellValue());
                    } else if (row.getCell(11).getCellTypeEnum() == CellType.NUMERIC) {
                        newClaster.setOecdCode(String.valueOf(row.getCell(11).getNumericCellValue()));
                    }
                } catch (NullPointerException e) {
                    newClaster.setOecdCode("unknown");
                    e.printStackTrace();
                }

                try {
                    if (row.getCell(12).getCellTypeEnum() == CellType.STRING) {
                        newClaster.setOecdTopic(row.getCell(12).getStringCellValue());
                    } else if (row.getCell(12).getCellTypeEnum() == CellType.NUMERIC) {
                        newClaster.setOecdTopic(String.valueOf(row.getCell(12).getNumericCellValue()));
                    }
                } catch (NullPointerException e) {
                    newClaster.setOecdTopic("unknown");
                    e.printStackTrace();
                }

                if (row.getCell(0).getCellTypeEnum() == CellType.STRING) {
                    massivArticle = row.getCell(0).getStringCellValue();
                    newClaster.setArrayArticle(massivArticle);
                } else if (row.getCell(0).getCellTypeEnum() == CellType.NUMERIC) {
                    newClaster.setArrayArticle(String.valueOf(row.getCell(0).getNumericCellValue()));
                }
                String[] s2_Articles = massivArticle.substring(1, massivArticle.length() - 1).split("\\D+");
                //System.out.println("количество s2_Articles "+s2_Articles.length);
                // for (String s : s2_Articles) {System.out.println(s);}
                String[] s2_Journals = massivJournals.substring(1, massivJournals.length() - 1).split("\\D+");
                if (s2_Articles.length != s2_Journals.length) {
                    newClaster.setCorrect(false);
                } else {

                    try {
                        for (int i = 0; i < s2_Articles.length; i++) {
                            // System.out.println("к добавлению статья "+s2_Articles[i]+ " в журнале "+ s2_Journals[i]+" в кластере "+y);
                            int numberArticle = 0;
                            int numberJournal = 0;

                                numberArticle = Integer.parseInt(s2_Articles[i]);
                                numberJournal = Integer.parseInt(s2_Journals[i]);


                            //System.out.println("numberArticle "+numberArticle);
                            //newClaster.addArticle(cluster);
                            if (existArticles(numberArticle)) {
                                System.out.println("Article is exist.Number " + s2_Articles[i]);
                            } else {
                                if (!existJournals(numberJournal, newClaster)) {
                                    JournalDAO.list.add(new Journal(numberJournal, newClaster));

                                }
                                ArticleDAO.list.add(new Article(numberArticle, newClaster, y, numberJournal, newClaster.getSrstiCode(),
                                        newClaster.getSrstiTopic(), newClaster.getOecdCode(), newClaster.getOecdTopic()));
                            }
                        }
                    } catch (NumberFormatException e) {
                        newClaster.setCorrect(false);
                        e.printStackTrace();
                    }
                    findSource(newClaster);
                }
                ClusterDAO.list.add(newClaster);

                cell = row.createCell(16, CellType.BOOLEAN);
                cell.setCellValue(newClaster.isCorrect);

                if (newClaster.isCorrect) {

                    cell = row.createCell(17, CellType.STRING);
                    cell.setCellValue(newClaster.listArticleToString());

                    cell = row.createCell(18, CellType.STRING);
                    cell.setCellValue(newClaster.listJournalToString());
                }

            } catch (ArrayIndexOutOfBoundsException e) {
                e.printStackTrace();
                //System.out.println("ошибка в кластере " + y);}
            }


            y++;
        }

            file.close();

            FileOutputStream outFile =new FileOutputStream(new File("C:/Users/doc.xlsx"));
            wb.write(outFile);
            outFile.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return checkDataCorrect();
        //findSourceInJournals();


    }

    private static void findSourceInJournals() {


        List<Journal> list = JournalDAO.listJournal();
        for (Journal journal : list) {
            List<Article> listArticleJournal = journal.getListArticle();
            for (Article article : listArticleJournal) {
                if (article.isSource())journal.addArticleListSource(article);
                else journal.addArticleListCopy(article);

            }
        }
    }


}
