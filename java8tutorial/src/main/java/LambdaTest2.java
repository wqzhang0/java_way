/**
 * Created by wqzhang on 2018/5/29.
 */
@FunctionalInterface
interface Print<T> {
    public void print(T x);
}

public class LambdaTest2 {
    public static void printString(String x, Print<String> print) {
        print.print(x);

    }

    public static void main(String[] args) {
        printString("test", (x) -> {
            System.out.println(x);
        });
    }

}
