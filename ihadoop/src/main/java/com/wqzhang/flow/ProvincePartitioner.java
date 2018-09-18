package com.wqzhang.flow;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

import java.util.HashMap;

/**
 * Created by wqzhang on 2018/9/18.
 */
public class ProvincePartitioner extends Partitioner<Text, FlowBean> {

    public static HashMap<String, Integer> proviceDict = new HashMap<>();

    static {
        proviceDict.put("137", 0);
        proviceDict.put("133", 1);
        proviceDict.put("138", 2);
        proviceDict.put("135", 3);
        proviceDict.put("139", 4);
    }

    @Override
    public int getPartition(Text text, FlowBean flowBean, int numPartitions) {
        String prefix = text.toString().substring(0, 3);
        Integer provinceId = proviceDict.get(prefix);

        return provinceId == null ? 5 : provinceId;
    }
}
