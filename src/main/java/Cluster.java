import java.util.ArrayList;
import java.util.List;

public class Cluster {
    int        number;
    List<Article> listArticle;
    List<Journal> listJournal;

    String elastic;
    Integer count;
    String yearsDiff;
    String yearsMax;
    String retracted;
    String srstiCode;
    String srstiTopic;
    String oecdTopic;
    String oecdCode;
    String arrayArticle;
    String authors;
    boolean isCorrect;


    public void setElastic(String elastic) {
        this.elastic = elastic;
    }

    public boolean isCorrect() {
        return isCorrect;
    }

    public void setCorrect(boolean correct) {
        isCorrect = correct;
    }

    public String getAuthors() {
        return authors;
    }

    public void setAuthors(String authors) {
        this.authors = authors;
    }

    public void addArticle(Article article) {
        listArticle.add(article);
    }
    public void addJournal(Journal journal) {
        listJournal.add(journal);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Article> getListArticle() {
        return listArticle;
    }

    public void setListArticle(List<Article> listArticle) {
        this.listArticle = listArticle;
    }

    public List<Journal> getListJournal() {
        return listJournal;
    }

    public void setListJournal(List<Journal> listJournal) {
        this.listJournal = listJournal;
    }

    public String getElastic() {
        return elastic;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getYearsDiff() {
        return yearsDiff;
    }

    public void setYearsDiff(String yearsDiff) {
        this.yearsDiff = yearsDiff;
    }

    public String getYearsMax() {
        return yearsMax;
    }

    public void setYearsMax(String yearsMax) {
        this.yearsMax = yearsMax;
    }

    public String getRetracted() {
        return retracted;
    }

    public void setRetracted(String retracted) {
        this.retracted = retracted;
    }

    public String getSrstiCode() {
        return srstiCode;
    }

    public void setSrstiCode(String srstiCode) {
        this.srstiCode = srstiCode;
    }

    public String getSrstiTopic() {
        return srstiTopic;
    }

    public void setSrstiTopic(String srstiTopic) {
        this.srstiTopic = srstiTopic;
    }

    public String getOecdTopic() {
        return oecdTopic;
    }

    public void setOecdTopic(String oecdTopic) {
        this.oecdTopic = oecdTopic;
    }

    public String getOecdCode() {
        return oecdCode;
    }

    public void setOecdCode(String oecdCode) {
        this.oecdCode = oecdCode;
    }

    public String getArrayArticle() {
        return arrayArticle;
    }

    public void setArrayArticle(String arrayArticle) {
        this.arrayArticle = arrayArticle;
    }

    public String getArrayJournal() {
        return arrayJournal;
    }

    public void setArrayJournal(String arrayJournal) {
        this.arrayJournal = arrayJournal;
    }

    public String listArticleToString() {
        String listString = "";
        for (Article a : listArticle)
        {
            listString += a.getNumber() + " ";
        }
        return listString;
    }

    public String listJournalToString() {
        String listString = "";
        for (Journal a : listJournal)
        {
            listString += a.getNumber() + " ";
        }
        return listString;
    }

    String arrayJournal;

    public Cluster(int number) {

        this.number = number;
        this.listArticle= new ArrayList<Article>();
        this.listJournal= new ArrayList<Journal>();
        this.isCorrect = true;
    }
/*
    public Cluster(int number, String elastic, String count, String yearsDiff, String yearsMax, String retracted, String srstiCode, String srstiTopic, String oecdCode, String oecdTopic, String arrayArticle, String arrayJournal) {
        this.number = number;
        this.elastic = elastic;
        this.count = count;
        this.yearsDiff = yearsDiff;
        this.yearsMax = yearsMax;
        this.retracted = retracted;
        this.srstiCode = srstiCode;
        this.srstiTopic = srstiTopic;
        this.oecdTopic = oecdTopic;
        this.oecdCode = oecdCode;
        this.arrayArticle = arrayArticle;
        this.arrayJournal = arrayJournal;
    }

 */

}
