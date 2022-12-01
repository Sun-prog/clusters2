import java.util.ArrayList;
import java.util.List;

public class MessageDAO {
    static List<String> list = new ArrayList<String>();
    static int numberReadRSCI=0;

    public static List<String> listMessage() {
       // bubbleSort(list);

        return list;
    }

    public static int getNumberReadRSCI() {
        return numberReadRSCI;
    }

    public static void setNumberReadRSCI(int numberReadRSCI) {
        MessageDAO.numberReadRSCI = numberReadRSCI;
    }
}
