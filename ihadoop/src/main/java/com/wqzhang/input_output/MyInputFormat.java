//package com.wqzhang.input_output;
//
//import org.apache.hadoop.fs.FileSystem;
//import org.apache.hadoop.fs.Path;
//import org.apache.hadoop.io.BytesWritable;
//import org.apache.hadoop.io.NullWritable;
//import org.apache.hadoop.mapred.FileInputFormat;
//
//public class MyInputFormat extends FileInputFormat<NullWritable,BytesWritable> {
//
//    @Override
//    protected boolean isSplitable(FileSystem fs, Path filename) {
//        //设置每个文件不可分片，保证一个小文件生成一个key-valu键值对
//        return false;
//    }
//
//
//}
