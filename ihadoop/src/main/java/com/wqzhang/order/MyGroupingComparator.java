package com.wqzhang.order;

import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

/**
 * Created by wqzhang on 2018/9/18.
 */
public class MyGroupingComparator extends WritableComparator {

    public MyGroupingComparator() {
        super(OrderBean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        OrderBean ob1 = (OrderBean) a;
        OrderBean ob2 = (OrderBean) b;
        return ob1.getItemid().compareTo(ob2.getItemid());
    }
}
