package com.wqzhang.time_filter;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

public class ApiAvgBean implements Writable {
    private Integer count;
    private Double time;

    public ApiAvgBean() {
    }

    public ApiAvgBean(Integer count, Double time) {
        this.count = count;
        this.time = time;
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(count);
        dataOutput.writeDouble(time);
    }

    public void readFields(DataInput dataInput) throws IOException {
        count = dataInput.readInt();
        time = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return count + "\t" + time;
    }


    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }
}
