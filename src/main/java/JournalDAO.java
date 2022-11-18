import java.util.ArrayList;
import java.util.List;

public class JournalDAO {
    static List<Journal> list = new ArrayList<Journal>();

    public static List<Journal> listJournal() {
        return list;
    }
}
