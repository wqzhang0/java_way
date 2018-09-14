package com.wqzhang.other;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.mapreduce.lib.partition.HashPartitioner;

/**
 * Created by wqzhang on 2018/9/12.
 */
//本程序的目的是通过MapReduce对Hmbbs中的日志数据进行清洗时
public class HmbbsCleaner
{
    public static String path1="";//指定文件的输入路径
    public static String path2="";//指定日志的输出路径
    public static void main(String[] args) throws Exception
    {
        path1 = args[0];
        path2 = args[1];

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", "hdfs://hadoop11:9000/");
        FileSystem fileSystem = FileSystem.get(conf);
        if(fileSystem.exists(new Path(path2)))
        {
            fileSystem.delete(new Path(path2), true);
        }
        Job job = Job.getInstance(conf, "com.wqzhang.other.HmbbsCleaner");
        job.setJarByClass(HmbbsCleaner.class);//jar包
        //编写驱动
        FileInputFormat.setInputPaths(job, new Path(path1));
        job.setInputFormatClass(TextInputFormat.class);
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(1);//指定Reducer的任务数量为1
        job.setPartitionerClass(HashPartitioner.class);
        job.setReducerClass(MyReducer.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);
        FileOutputFormat.setOutputPath(job, new Path(path2));
        job.setOutputFormatClass(TextOutputFormat.class);

        //提交任务
        job.waitForCompletion(true);
    }
    public static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable>
    {
        protected void map(LongWritable k1, Text v1,Context context)throws IOException, InterruptedException
        {
            String string = v1.toString();//获取待记录
            Parselogs parselogs = new Parselogs();
            try
            {
                String[] sub = parselogs.parseString(string);
                if(sub[2].startsWith("GET /static")||sub[2].startsWith("GET /uc_server"))
                    return ;//对于静态的记录直接过滤掉，不进行任何处理

                if(sub[2].startsWith("GET /"))
                {
                    sub[2] = sub[2].substring("GET /".length());
                }
                if(sub[2].startsWith("POST /"))
                {
                    sub[2] = sub[2].substring("POST /".length());
                }//过滤掉了开头和结尾的标志信息
                if(sub[2].endsWith(" HTTP/1.1"))
                {
                    sub[2] = sub[2].substring(0, sub[2].length()-" HTTP/1.1".length());
                }
                if(sub[2].endsWith(" HTTP/1.0"))
                {
                    sub[2] = sub[2].substring(0, sub[2].length()-" HTTP/1.0".length());
                }
                Text k2 = new Text();
                k2.set(sub[0]+"\t"+sub[1]+"\t"+sub[2]);//三个字段之间以制表符进行分开
                context.write(k2, NullWritable.get());
            }

            catch (ParseException e)
            {
                e.printStackTrace();
            }
        }
    }
    public static class MyReducer extends Reducer<Text, NullWritable, Text, NullWritable>
    {
        protected void reduce(Text k2, Iterable<NullWritable> v2s,Context context)throws IOException, InterruptedException
        {
            for (NullWritable v2 : v2s)
            {
                Text k3 = k2;
                context.write(k3, NullWritable.get());
            }
        }
    }
}
class Parselogs //Parselogs这个类用来对字符串进行解析
{
    public String[]  parseString(String str) throws ParseException
    {
        String  str1 = parseIp(str);
        String  str2 = parseDate(str);
        String  str3 = parseUrl(str);
        //String  str4 = parseStatus(str);
        //String  str5 = parseFlow(str);
        //String[] str66 = new String[]{str1,str2,str3,str4,str5};
        String[] str66 = new String[]{str1,str2,str3};//在这里只获取与本次项目有关的数据
        return str66;
    }
    public String parseIp(String str)//对ip地址进行解析的方法
    {
        String[] splited = str.split(" - - ");//用指定的正则表达式进行切分，获取我们需要的字段
        return splited[0];
    }
    public String parseDate(String str) throws ParseException
    {
        String[] splited = str.split(" - - ");//用指定的正则表达式进行切分，获取我们需要的字段
        int index1 = splited[1].indexOf("[");
        int index2 = splited[1].indexOf("]");
        String substring = splited[1].substring(index1+1, index2);//到此获取了时间字段30/May/2013:17:38:20 +0800
        SimpleDateFormat simple1 = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss", Locale.ENGLISH);//匹配我们给定的字符串，并将其解析成对应的时间
        SimpleDateFormat simple2 = new SimpleDateFormat("yyyyMMddHHmmss");//匹配我们给定的字符串
        Date parse = simple1.parse(substring);
        String format = simple2.format(parse);
        return format;
    }
    public String parseUrl(String str)//获取访问的url
    {
        int index1 = str.indexOf("]");
        int index2= str.lastIndexOf("\"");
        String substring = str.substring(index1+3, index2);
        return substring;
    }
    public String parseStatus(String str)//获取访问的状态
    {
        int index1= str.lastIndexOf("\"");
        String str2 = str.substring(index1+1).trim();
        String[] splited = str2.split(" ");
        return splited[0];
    }
    public String parseFlow(String str)//获取访问的状态
    {
        int index1= str.lastIndexOf("\"");
        String str2 = str.substring(index1+1).trim();
        String[] splited = str2.split(" ");
        return splited[1];
    }
}