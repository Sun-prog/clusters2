import java.util.List;

public class Article {
    int        number;
    List<Cluster> listCluster;
    String        srstiCode;
    String        srstiTopic;
    String        oecdTopic;
    String        oecdCode;
    Journal       journal;
    int        journalNumber;
    Cluster       cluster;
    int           clusterNumber;
    boolean source = false;

    public Article(int number, Cluster cluster, int clusterNumber, int journalNumber, String srstiCode, String srstiTopic, String oecdCode, String oecdTopic) {
        this.number = number;
        this.cluster = cluster;
        this.clusterNumber = clusterNumber;
        this.journalNumber = journalNumber;
        this.srstiCode = srstiCode;
        this.srstiTopic = srstiTopic;
        this.oecdCode = oecdCode;
        this.oecdTopic = oecdTopic;
    }


    public Article(int number, String srstiCode, String srstiTopic, String oecdTopic, String oecdCode) {
        this.number = number;
        this.srstiCode = srstiCode;
        this.srstiTopic = srstiTopic;
        this.oecdTopic = oecdTopic;
        this.oecdCode = oecdCode;
    }

    public Article(int number) {
        this.number = number;
    }

    public Journal getJournal() {
        return journal;
    }

    public void setJournal(Journal journal) {
        this.journal = journal;
    }

    public Cluster getCluster() {
        return cluster;
    }

    public boolean isSource() {
        return source;
    }

    public void setSource(boolean source) {
        this.source = source;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Cluster> getListCluster() {
        return listCluster;
    }

    public void setListCluster(List<Cluster> listCluster) {
        this.listCluster = listCluster;
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

    public int getJournalNumber() {
        return journalNumber;
    }
}
