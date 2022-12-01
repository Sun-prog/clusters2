import java.util.*;

public class Journal {
    int        number;
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

    boolean isUniTitle;
    boolean isUniRince;
    boolean isUniRus;
    boolean isRus;
    boolean isRatingRSCI;

    String nameJournalUni;
    String nameJournalRince;
    String nameJournalRSCI;

    String oesdRSCI;
    double quartileRSCI;
    double ratingRSCI;

    String place;
    String city;
    int numberIssues;
    int uniQuantityArticles;
    int uniQuantityArticlesFullText;
    int numberCitations;
    int numberArticlesPerIssue;
    int numberIssuesPerYear;

    HashMap<Integer, Double> probabilityQuotingReading = new HashMap<Integer, Double>();
    double medianProbabilityQuotingReading = 0;
    double maxProbabilityQuotingReading;

    HashMap<Integer, Double> twoYearImpactFactorCore = new HashMap<Integer, Double>();
    double medianTwoYearImpactFactorCore = 0;
    double maxTwoYearImpactFactorCore;

    HashMap<Integer, Double> twoYearImpactFactorCoreWithoutCitations = new HashMap<Integer, Double>();
    double medianTwoYearImpactFactorCoreWithoutCitations = 0;
    double maxTwoYearImpactFactorCoreWithoutCitations;

    HashMap<Integer, Double> twoYearImpactFactor = new HashMap<Integer, Double>();
    double medianTwoYearImpactFactor = 0;
    double maxTwoYearImpactFactor;

    HashMap<Integer, Double> twoYearImpactFactorWithoutCitations = new HashMap<Integer, Double>();
    double medianTwoYearImpactFactorWithoutCitations = 0;
    double maxTwoYearImpactFactorWithoutCitations;

    HashMap<Integer, Double> twoYearImpactFactorWithCitations = new HashMap<Integer, Double>();
    double medianTwoYearImpactFactorWithCitations = 0;
    double maxTwoYearImpactFactorWithCitations;

    HashMap<Integer, Double> twoYearСoefficientAuthorSelfCitation = new HashMap<Integer, Double>();
    double medianTwoYearСoefficientAuthorSelfCitation = 0;
    double maxTwoYearСoefficientAuthorSelfCitation;

    HashMap<Integer, Double> twoYearСoefficientAuthorCitation = new HashMap<Integer, Double>();
    double medianTwoYearСoefficientAuthorCitation = 0;
    double maxTwoYearСoefficientAuthorCitation;


    HashMap<Integer, Double> tenYearHirschIndex = new HashMap<Integer, Double>();
    double medianTenYearHirschIndex = 0;
    double maxTenYearHirschIndex;


    HashMap<Integer, Double> indexGini = new HashMap<Integer, Double>();
    double medianIndexGini = 0;
    double maxIndexGini;


    HashMap<Integer, Double> indexHerfindahlAuthorOrganizations = new HashMap<Integer, Double>();
    double medianIndexHerfindahlAuthorOrganizations = 0;
    double maxIndexHerfindahlAuthorOrganizations;


    HashMap<Integer, Double> placeScienceIndexRanking = new HashMap<Integer, Double>();
    double medianPlaceScienceIndexRanking = 0;
    double maxPlaceScienceIndexRanking;


    HashMap<Integer, Double> totalNumberCitationsCurrentYear = new HashMap<Integer, Double>();
    double medianTotalNumberCitationsCurrentYear = 0;
    double maxTotalNumberCitationsCurrentYear;


    HashMap<Integer, Double> totalNumberCitationsCurrentYearSelf = new HashMap<Integer, Double>();
    double medianTotalNumberCitationsCurrentYearSelf = 0;
    double maxTotalNumberCitationsCurrentYearSelf;




    public Journal(int number, Cluster cluster) {
        this.number = number;
        addCluster(cluster);
        listArticleSource  = new ArrayList<Article>();
        listArticleCopy  = new ArrayList<Article>();
        listSrstiCode = new ArrayList<String>();
        listSrstiTopic = new ArrayList<String>();
        listOecdCode = new ArrayList<String>();
        listOecdTopic = new ArrayList<String>();
        this.isUniTitle = false;
        this.isUniRince = false;
        this.isUniRus = false;
        this.isRus = false;
        this.nameJournalUni = "null";
        this.nameJournalRince = "null";
        this.place = "null";
        this.city = "null";
        this.numberIssues = 0;
        this.uniQuantityArticles = 0;
        this.uniQuantityArticlesFullText = 0;
        this.numberCitations = 0;
        this.numberArticlesPerIssue = 0;
        this.numberIssuesPerYear = 0;
        this.isRatingRSCI = false;
        this.quartileRSCI=-1;
        this.ratingRSCI=-1;
        this.oesdRSCI="null";

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
            listString += a + ";";
        }
        return listString;
    }

    public String listSrstiTopicToString() {
        String listString = "";
        for (String a : listSrstiTopic)
        {
            listString += a + ";";
        }
        return listString;
    }
    public String listOecdCodeToString() {
        String listString = "";
        for (String a : listOecdCode)
        {
            listString += a + ";";
        }
        return listString;
    }

    public String listOecdTopicToString() {
        String listString = "";
        for (String a : listOecdTopic)
        {
            listString += a + ";";
        }
        return listString;
    }

    public String getProbabilityQuotingReading() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(probabilityQuotingReading);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public String getTwoYearImpactFactorCore() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(twoYearImpactFactorCore);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public String getTwoYearImpactFactorCoreWithoutCitations() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(twoYearImpactFactorCoreWithoutCitations);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public String getTwoYearImpactFactor() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(twoYearImpactFactor);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public String getTwoYearImpactFactorWithoutCitations() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(twoYearImpactFactorWithoutCitations);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public String getTwoYearImpactFactorWithCitations() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(twoYearImpactFactorWithCitations);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public String getTwoYearСoefficientAuthorSelfCitation() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(twoYearСoefficientAuthorSelfCitation);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public String getTwoYearСoefficientAuthorCitation() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(twoYearСoefficientAuthorCitation);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public void addProbabilityQuotingReading(Integer year, double value) {
        probabilityQuotingReading.put(year, value);
    }

    public void addTwoYearImpactFactorCore(Integer year, double value) {
        twoYearImpactFactorCore.put(year, value);
    }

    public void addTwoYearImpactFactorCoreWithoutCitations(Integer year, double value) {
        twoYearImpactFactorCoreWithoutCitations.put(year, value);
    }

    public void addTwoYearImpactFactor(Integer year, double value) {
        twoYearImpactFactor.put(year, value);
    }

    public void addTwoYearImpactFactorWithoutCitations(Integer year, double value) {
        twoYearImpactFactorWithoutCitations.put(year, value);
    }
    public void addTwoYearImpactFactorWithCitations(Integer year, double value) {
        twoYearImpactFactorWithCitations.put(year, value);
    }

    public void addTwoYearСoefficientAuthorSelfCitation(Integer year, double value) {
        twoYearСoefficientAuthorSelfCitation.put(year, value);
    }

    public void addTenYearHirschIndex(Integer year, double value) {
        tenYearHirschIndex.put(year, value);
    }

    public void addPlaceScienceIndexRanking(Integer year, double value) {
        placeScienceIndexRanking.put(year, value);
    }

    public void addTotalNumberCitationsCurrentYear(Integer year, double value) {
        totalNumberCitationsCurrentYear.put(year, value);
    }

    public void addTotalNumberCitationsCurrentYearSelf(Integer year, double value) {
        totalNumberCitationsCurrentYearSelf.put(year, value);
    }

    public void addIndexGini(Integer year, double value) {
        indexGini.put(year, value);
    }


    public void addIndexHerfindahlAuthorOrganizations(Integer year, double value) {
        indexHerfindahlAuthorOrganizations.put(year, value);
    }

    public void addTwoYearСoefficientAuthorCitation(Integer year, double value) {
        twoYearСoefficientAuthorCitation.put(year, value);
    }

    public String getTenYearHirschIndex() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(tenYearHirschIndex);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public double getMedianTenYearHirschIndex() {
        medianTenYearHirschIndex = getMedian(tenYearHirschIndex);
        return medianTenYearHirschIndex;
    }

    public double getMaxTenYearHirschIndex() {
        maxTenYearHirschIndex = getMax(tenYearHirschIndex);
        return maxTenYearHirschIndex;
    }

    public String getIndexGini() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(indexGini);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public double getMedianIndexGini() {
        medianIndexGini = getMedian(indexGini);
        return medianIndexGini;
    }

    public double getMaxIndexGini() {
        maxIndexGini = getMax(indexGini);
        return maxIndexGini;
    }

    public String getIndexHerfindahlAuthorOrganizations() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(indexHerfindahlAuthorOrganizations);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public double getMedianIndexHerfindahlAuthorOrganizations() {
        medianIndexHerfindahlAuthorOrganizations = getMedian(indexHerfindahlAuthorOrganizations);
        return medianIndexHerfindahlAuthorOrganizations;
    }

    public double getMaxIndexHerfindahlAuthorOrganizations() {
        maxIndexHerfindahlAuthorOrganizations = getMax(indexHerfindahlAuthorOrganizations);
        return maxIndexHerfindahlAuthorOrganizations;
    }

    public String getPlaceScienceIndexRanking() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(placeScienceIndexRanking);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public double getMedianPlaceScienceIndexRanking() {
        medianPlaceScienceIndexRanking = getMedian(placeScienceIndexRanking);
        return medianPlaceScienceIndexRanking;
    }

    public double getMaxPlaceScienceIndexRanking() {
        maxPlaceScienceIndexRanking = getMax(placeScienceIndexRanking);
        return maxPlaceScienceIndexRanking;
    }

    public String getTotalNumberCitationsCurrentYear() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(totalNumberCitationsCurrentYear);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public double getMedianTotalNumberCitationsCurrentYear() {
        medianTotalNumberCitationsCurrentYear = getMedian(totalNumberCitationsCurrentYear);
        return medianTotalNumberCitationsCurrentYear;
    }

    public double getMaxTotalNumberCitationsCurrentYear() {
        maxTotalNumberCitationsCurrentYear = getMax(totalNumberCitationsCurrentYear);
        return maxTotalNumberCitationsCurrentYear;
    }

    public String getTotalNumberCitationsCurrentYearSelf() {
        TreeMap<Integer, Double> sorted = new TreeMap<Integer, Double>();
        sorted.putAll(totalNumberCitationsCurrentYearSelf);
        String result ="";
        // Display the TreeMap which is naturally sorted
        for (Map.Entry<Integer, Double> entry : sorted.entrySet())
            result = result+ entry.getKey() +
                    ":" + entry.getValue()+ "|";

        return result;
    }

    public double getMedianTotalNumberCitationsCurrentYearSelf() {
        medianTotalNumberCitationsCurrentYearSelf = getMedian(totalNumberCitationsCurrentYearSelf);
        return medianTotalNumberCitationsCurrentYearSelf;
    }

    public double getMaxTotalNumberCitationsCurrentYearSelf() {
        maxTotalNumberCitationsCurrentYearSelf = getMax(totalNumberCitationsCurrentYearSelf);
        return maxTotalNumberCitationsCurrentYearSelf;
    }

    public double getMaxTwoYearСoefficientAuthorSelfCitation() {
        maxTwoYearСoefficientAuthorSelfCitation = getMax(twoYearСoefficientAuthorSelfCitation);
        return maxTwoYearСoefficientAuthorSelfCitation;
    }

    public double getMaxTwoYearСoefficientAuthorCitation() {
        maxTwoYearСoefficientAuthorCitation = getMax(twoYearСoefficientAuthorCitation);
        return maxTwoYearСoefficientAuthorCitation;
    }

    public double getMedianTwoYearImpactFactorCore() {
        medianTwoYearImpactFactorCore = getMedian(twoYearImpactFactorCore);
        return medianTwoYearImpactFactorCore;
    }

    public double getMaxTwoYearImpactFactorCore() {
        maxTwoYearImpactFactorCore = getMax(twoYearImpactFactorCore);
        return maxTwoYearImpactFactorCore;
    }

    public double getMedianTwoYearImpactFactorCoreWithoutCitations() {
        medianTwoYearImpactFactorCoreWithoutCitations = getMedian(twoYearImpactFactorCoreWithoutCitations);
        return medianTwoYearImpactFactorCoreWithoutCitations;
    }

    public double getMaxTwoYearImpactFactorCoreWithoutCitations() {
        maxTwoYearImpactFactorCoreWithoutCitations = getMax(twoYearImpactFactorCoreWithoutCitations);
        return maxTwoYearImpactFactorCoreWithoutCitations;
    }

    public double getMedianTwoYearImpactFactor() {
        medianTwoYearImpactFactor = getMedian(twoYearImpactFactor);
        return medianTwoYearImpactFactor;
    }

    public double getMaxTwoYearImpactFactor() {
        maxTwoYearImpactFactor = getMax(twoYearImpactFactor);
        return maxTwoYearImpactFactor;
    }

    public double getMedianTwoYearImpactFactorWithoutCitations() {
        medianTwoYearImpactFactorWithoutCitations = getMedian(twoYearImpactFactorWithoutCitations);
        return medianTwoYearImpactFactorWithoutCitations;
    }

    public double getMaxTwoYearImpactFactorWithoutCitations() {
        maxTwoYearImpactFactorWithoutCitations = getMax(twoYearImpactFactorWithoutCitations);
        return maxTwoYearImpactFactorWithoutCitations;
    }

    public double getMedianTwoYearImpactFactorWithCitations() {
        medianTwoYearImpactFactorWithCitations = getMedian(twoYearImpactFactorWithCitations);
        return medianTwoYearImpactFactorWithCitations;
    }

    public double getMaxTwoYearImpactFactorWithCitations() {
        maxTwoYearImpactFactorWithCitations = getMax(twoYearImpactFactorWithCitations);
        return maxTwoYearImpactFactorWithCitations;
    }

    public double getMedianTwoYearСoefficientAuthorSelfCitation() {
        medianTwoYearСoefficientAuthorSelfCitation = getMedian(twoYearСoefficientAuthorSelfCitation);
        return medianTwoYearСoefficientAuthorSelfCitation;
    }


    public double getMedianTwoYearСoefficientAuthorCitation() {
        medianTwoYearСoefficientAuthorCitation = getMedian(twoYearСoefficientAuthorCitation);
        return medianTwoYearСoefficientAuthorCitation;
    }

    public double getMaxProbabilityQuotingReading(){
        maxProbabilityQuotingReading = getMax(probabilityQuotingReading);
        return maxProbabilityQuotingReading;
    }
    public double getMax(HashMap<Integer, Double> map){
        double max = 0;
        List<Double> list = new ArrayList<Double>();
        for (double value : map.values()) {
            if (!(value<0)){
                list.add(value);}
        }
        Double[] sortedArray = sortArray(list);
        if (sortedArray.length!=0) {
            max = sortedArray[sortedArray.length-1];
        } else {
            max = -1.0;
        }
        return max;
    };
    public double getMedianProbabilityQuotingReading(){
        medianProbabilityQuotingReading = getMedian(probabilityQuotingReading);
        return medianProbabilityQuotingReading;
    }

    public double getMedian(HashMap<Integer, Double> map){
        double median;
        List<Double> list = new ArrayList<Double>();
        for (double value : map.values()) {
            if (!(value<0)){
            list.add(value);}
        }
        Double[] sortedArray = sortArray(list);
        if (sortedArray.length==0) {
            median=-1.0;
        } else {
            int middle = sortedArray.length/2;
            //float medianValue = 0; //declare variable
            if (sortedArray.length%2 == 1)
                median = sortedArray[middle];
            else
                median = (sortedArray[middle-1] + sortedArray[middle]) / 2;
        }
        return median;
    };


    public Double[] sortArray(List<Double> list){
        Double[] double_array = new Double[list.size()];
        list.toArray(double_array);
        Arrays.sort(double_array);//
        return double_array;
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

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
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

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


    public boolean isUniTitle() {
        return isUniTitle;
    }

    public void setUniTitle(boolean uniTitle) {
        isUniTitle = uniTitle;
    }

    public boolean isUniRince() {
        return isUniRince;
    }

    public void setUniRince(boolean uniRince) {
        isUniRince = uniRince;
    }

    public boolean isUniRus() {
        return isUniRus;
    }

    public void setUniRus(boolean uniRus) {
        isUniRus = uniRus;
    }

    public String getNameJournalUni() {
        return nameJournalUni;
    }

    public void setNameJournalUni(String nameJournalUni) {
        this.nameJournalUni = nameJournalUni;
    }

    public String getNameJournalRince() {
        return nameJournalRince;
    }

    public void setNameJournalRince(String nameJournalRince) {
        this.nameJournalRince = nameJournalRince;
    }

    public int getNumberIssues() {
        return numberIssues;
    }

    public void setNumberIssues(int numberIssues) {
        this.numberIssues = numberIssues;
    }

    public int getUniQuantityArticles() {
        return uniQuantityArticles;
    }

    public void setUniQuantityArticles(int uniQuantityArticles) {
        this.uniQuantityArticles = uniQuantityArticles;
    }

    public int getUniQuantityArticlesFullText() {
        return uniQuantityArticlesFullText;
    }

    public void setUniQuantityArticlesFullText(int uniQuantityArticlesFullText) {
        this.uniQuantityArticlesFullText = uniQuantityArticlesFullText;
    }

    public int getNumberCitations() {
        return numberCitations;
    }

    public void setNumberCitations(int numberCitations) {
        this.numberCitations = numberCitations;
    }

    public int getNumberArticlesPerIssue() {
        return numberArticlesPerIssue;
    }

    public void setNumberArticlesPerIssue(int numberArticlesPerIssue) {
        this.numberArticlesPerIssue = numberArticlesPerIssue;
    }

    public int getNumberIssuesPerYear() {
        return numberIssuesPerYear;
    }

    public void setNumberIssuesPerYear(int numberIssuesPerYear) {
        this.numberIssuesPerYear = numberIssuesPerYear;
    }

    public boolean isRatingRSCI() {
        return isRatingRSCI;
    }

    public void setRatingRSCI(boolean ratingRSCI) {
        isRatingRSCI = ratingRSCI;
    }

    public String getNameJournalRSCI() {
        return nameJournalRSCI;
    }

    public void setNameJournalRSCI(String nameJournalRSCI) {
        this.nameJournalRSCI = nameJournalRSCI;
    }

    public String getOesdRSCI() {
        return oesdRSCI;
    }

    public void setOesdRSCI(String oesdRSCI) {
        this.oesdRSCI = oesdRSCI;
    }

    public double getQuartileRSCI() {
        return quartileRSCI;
    }

    public void setQuartileRSCI(double quartileRSCI) {
        this.quartileRSCI = quartileRSCI;
    }

    public double getRatingRSCI() {
        return ratingRSCI;
    }

    public void setRatingRSCI(double ratingRSCI) {
        this.ratingRSCI = ratingRSCI;
    }
}
