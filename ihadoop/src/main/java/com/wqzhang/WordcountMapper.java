package com.wqzhang;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * Created by wqzhang on 2018/9/12.
 */
public class WordcountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

        String line = value.toString();
//        100.97.74.181 - - [14/Sep/2018:11:20:50 +0800] "POST /v5/mall/postScriptMallOrder/ HTTP/1.1" 200 37 "-" "okhttp/3.6.0" "123.113.204.61"
//        100.97.74.203 - - [09/Sep/2018:03:18:22 +0800] "GET /vfour/echo_once/2/?params=5a20dce6-b379-11e8-aafa-00163e13ebb0 HTTP/1.1" 101 555 "-" "okhttp/3.6.0" "36.23.207.67"

        String[] words = line.split(" ");
//        if (line.contains("servicewechat")){
//            if( words.length <30){
//                System.out.println(line);
//            }else{
//                context.write(new Text(words[words.length-1]), new IntWritable(1));
//            }
//        }else{
//            context.write(new Text(words[12]), new IntWritable(1));
//        }
        String word = words[words.length - 1].split("\"")[1];
        if (!word.equals("-") ){

            context.write(new Text(word), new IntWritable(1));
        }

//        context.write(new Text(words[6]), new IntWritable(1));
//
//        for (String word : words) {
//            context.write(new Text(word), new IntWritable(1));
//        }
    }
}
