/**
 * Created by wqzhang on 2018/5/28.
 */

public class App {

    public static void main(String[] args) {
        Formula formula = new Formula() {
            @Override
            public double calculate(int a) {
                return sqrt(a);
            }
        };

        System.out.println(formula.calculate(100));
        System.out.println(formula.sqrt(16));


        MySort mySort = new MySort();
        mySort.sort();


        Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        Integer converted = converter.convert("123");
        System.out.println(converted);

        Converter<String ,Integer> converter1 = Integer::valueOf;
        Integer converted2 = converter1.convert("456");
        System.out.println(converted2);

    }
}
