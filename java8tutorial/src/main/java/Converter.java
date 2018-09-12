/**
 * Created by wqzhang on 2018/5/29.
 */
@FunctionalInterface
interface Converter<K, T> {
    T convert(K from);
}
