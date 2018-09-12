import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

/**
 * Created by wqzhang on 2018/5/28.
 */
class MyComparator<String> implements Comparator{

    @Override
    public int compare(Object o1, Object o2) {
        return 0;
    }

}
public class MySort {
    List<String> names1 = Arrays.asList("peter", "anna", "mike", "xenia");
    List<String> names2 = Arrays.asList("peter", "anna", "mike", "xenia");



    public void sort() {

        Collections.sort(names1, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o2.compareTo(o1);
            }
        });
        for (String tmp : names1) {
            System.out.println(tmp);
        }

        //way 2
        Collections.sort(names2, (String a, String b) -> {
            return b.compareTo(a);
        });
        //way 3
        names2.sort((a,b)->b.compareTo(a));
        names2.forEach(System.out::println);
//        for (String tmp : names2) {
//            System.out.println(tmp);
//        }

    }
}
