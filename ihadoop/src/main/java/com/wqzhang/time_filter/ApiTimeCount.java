package com.wqzhang.time_filter;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

// run param  input/phone_flow out/phone_flow/
public class ApiTimeCount {
    static class ApiTimeCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            // 2018-08-05 02:58:01,803 /v5/game/getGameStatus   558.722734451294
            String line = value.toString();
            String[] fields = line.split(" ");
            String reqKey = fields[0] + " "+fields[1].substring(0,5);
//            String time = fields[0];

            context.write(new Text(reqKey), new IntWritable(1));
        }
    }

    static class TimeCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {


        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value : values) {
                count += value.get();
            }
            context.write(key, new IntWritable(count));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(ApiTimeCount.class);

        job.setMapperClass(ApiTimeCountMapper.class);
        job.setReducerClass(TimeCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean b = job.waitForCompletion(true);
        if (!b) {
            System.out.println("wordcount task fail!");
        }
    }
}
