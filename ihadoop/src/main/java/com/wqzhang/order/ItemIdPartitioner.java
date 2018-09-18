package com.wqzhang.order;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * Created by wqzhang on 2018/9/18.
 */
public class ItemIdPartitioner extends Partitioner<OrderBean, NullWritable> {
    @Override
    public int getPartition(OrderBean orderBean, NullWritable nullWritable, int numPartitions) {
        return (orderBean.getItemid().hashCode() & Integer.MAX_VALUE) % numPartitions;
    }
}
