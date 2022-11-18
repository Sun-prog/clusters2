import java.util.ArrayList;
import java.util.List;

public class ArticleDAO {
    static List<Article> list = new ArrayList<Article>();

    public static List<Article> listArticle() {
        return list;
    }
}
