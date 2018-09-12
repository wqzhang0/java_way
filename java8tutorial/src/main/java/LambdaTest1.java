/**
 * Created by wqzhang on 2018/5/29.
 */
public class LambdaTest1 {

    @FunctionalInterface
    public interface TestInterface {
        void test1();

        default void test2() {
            System.out.println("default");
        }
    }

    public static void dosomethings(TestInterface test) {
        test.test1();
    }

    public static void main(String[] args) {

        dosomethings(() -> System.out.println("yoyoy"));
    }
}
