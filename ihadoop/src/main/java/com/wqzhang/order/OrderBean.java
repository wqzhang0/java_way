package com.wqzhang.order;

import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * Created by wqzhang on 2018/9/18.
 */
public class OrderBean implements WritableComparable<OrderBean> {
    private Text itemid;
    private DoubleWritable amount;

    public OrderBean() {
    }

    public OrderBean(Text itemid, DoubleWritable amount) {
        this.set(itemid, amount);
    }


    public void set(Text itemid, DoubleWritable amount) {
        this.itemid = itemid;
        this.amount = amount;
    }

    public Text getItemid() {
        return itemid;
    }

    public void setItemid(Text itemid) {
        this.itemid = itemid;
    }

    public DoubleWritable getAmount() {
        return amount;
    }

    public void setAmount(DoubleWritable amount) {
        this.amount = amount;
    }


    @Override
    public int compareTo(OrderBean o) {
        int ret = this.itemid.compareTo(o.getItemid());
        if (ret == 0) {
            ret = -this.amount.compareTo(o.getAmount());
        }
        return ret;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(this.itemid.toString());
        dataOutput.writeDouble(amount.get());
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        this.itemid = new Text(dataInput.readUTF());
        this.amount = new DoubleWritable(dataInput.readDouble());
    }
}
