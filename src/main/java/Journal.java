import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Journal {
    String        number;
    List<Article> listArticle  = new ArrayList<Article>();
    List<Article> listArticleSource;
    List<Article> listArticleCopy ;
    List<Cluster> listJournalCluster  = new ArrayList<Cluster>();
    Map           repeatCluster;

    List<String> listSrstiCode;
    List<String> listSrstiTopic;
    List<String> listOecdCode;
    List<String> listOecdTopic;

    String        srstiCode;
    String        srstiTopic;
    String        oecdTopic;
    String        oecdCode;
    int           numberRepeat =1;

    public Journal(String number, Cluster cluster) {
        this.number = number;
        addCluster(cluster);
        listArticleSource  = new ArrayList<Article>();
        listArticleCopy  = new ArrayList<Article>();
        listSrstiCode = new ArrayList<String>();
        listSrstiTopic = new ArrayList<String>();
        listOecdCode = new ArrayList<String>();
        listOecdTopic = new ArrayList<String>();

    }
    public String listArticleSourceToString() {
        String listString = "";
        for (Article a : listArticleSource)
        {
            listString += a.getNumber() + " ";
        }
         return listString;
    }
    public String listArticleCopyToString() {
        String listString = "";
        for (Article a : listArticleCopy)
        {
            listString += a.getNumber() + " ";
        }
        return listString;
    }
    public String listArticleToString() {
        String listString = "";
        for (Article a : listArticle)
        {
            listString += a.getNumber() + " ";
        }
        return listString;
    }

    public String listClusterToString() {
        String listString = "";
        for (Cluster a : listJournalCluster)
        {
            listString += a.getNumber() + " ";
        }
        return listString;
    }

    public void addListSrstiCode(String code) {
        listSrstiCode.add(code);
    }
    public void addListOecdCode(String code) {
        listOecdCode.add(code);
    }
    public void addListSrstiTopic(String topic) {
        listSrstiTopic.add(topic);
    }
    public void addListOecdTopic(String topic) {
        listOecdTopic.add(topic);
    }

    public List<String> getListSrstiCode() {
        return listSrstiCode;
    }

    public List<String> getListSrstiTopic() {
        return listSrstiTopic;
    }

    public List<String> getListOecdCode() {
        return listOecdCode;
    }

    public List<String> getListOecdTopic() {
        return listOecdTopic;
    }

    public String listSrstiCodeToString() {
        String listString = "";
        for (String a : listSrstiCode)
        {
            listString += a + " ";
        }
        return listString;
    }

    public String listSrstiTopicToString() {
        String listString = "";
        for (String a : listSrstiTopic)
        {
            listString += a + " ";
        }
        return listString;
    }
    public String listOecdCodeToString() {
        String listString = "";
        for (String a : listOecdCode)
        {
            listString += a + " ";
        }
        return listString;
    }

    public String listOecdTopicToString() {
        String listString = "";
        for (String a : listOecdTopic)
        {
            listString += a + " ";
        }
        return listString;
    }

    public void addArticleListSource(Article article) {
        listArticleSource.add(article);
    }
    public void addArticleListCopy(Article article) {
        listArticleCopy.add(article);
    }
    public void addArticle(Article article) {
        listArticle.add(article);
    }

    public void addCluster(Cluster cluster) {
        listJournalCluster.add(cluster);
    }

    public List<Article> getListArticle() {
        return listArticle;
    }

    public boolean containCluster(Cluster cluster) {
        if (listJournalCluster.contains(cluster))
        return true;
        return false;
    }

    public void setNumberRepeat(int numberRepeat) {
        this.numberRepeat = numberRepeat;
    }

    public List<Cluster> getListJournalCluster() {
        return listJournalCluster;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Map getRepeatCluster() {
        return repeatCluster;
    }

    public void setRepeatCluster(Map repeatCluster) {
        this.repeatCluster = repeatCluster;
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

    public int getNumberRepeat() {
        return numberRepeat;
    }

    public void increaseNumberRepeat() {
        numberRepeat++;
    }
}
