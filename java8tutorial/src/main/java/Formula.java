/**
 * Created by wqzhang on 2018/5/28.
 */
public interface Formula {
    double calculate(int a);

    /**
     * 平方根
     * @param a
     * @return
     */
    default double sqrt(int a){
        return  Math.sqrt(a);
    }
}
