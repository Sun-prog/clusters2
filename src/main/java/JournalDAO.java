import java.util.ArrayList;
import java.util.List;

public class JournalDAO {
    static List<Journal> list = new ArrayList<Journal>();

    public static List<Journal> listJournal() {
       // bubbleSort(list);

        return list;
    }

    public static List<Journal> bubbleSort(List<Journal> list){
        // System.out.println("list.size() "+list.size());
        if (list.size()>1) {
            for (int i = 0; i < list.size() - 1; i++) {
               // System.out.println("i= "+i);
                for (int j = 0; j < list.size() - i - 1; j++) {
                 //   System.out.println("list.get(j + 1).getNumber()= "+list.get(j + 1).getNumber()+"list.get(j).getNumber()"+list.get(j).getNumber());
                    if (list.get(j + 1).getNumber() < list.get(j).getNumber()) {

                       // int swap = list.get(j).getNumber();
                       // System.out.println("swap "+swap);
                        list.set(j, list.get(j + 1));
                        list.set(j + 1, list.get(j));
                    }
                }
            }
        }
        return list;
    }

}
