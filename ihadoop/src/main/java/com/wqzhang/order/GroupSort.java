package com.wqzhang.order;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by wqzhang on 2018/9/18.
 */
public class GroupSort {

    static class SortMapper extends Mapper<LongWritable,Text,OrderBean,NullWritable> {
        OrderBean bean = new OrderBean();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] fields = line.split(",");
            
            bean.set(new Text(fields[0]),new DoubleWritable(Double.parseDouble(fields[2])));
            context.write(bean,NullWritable.get());;
        }
    }
}
