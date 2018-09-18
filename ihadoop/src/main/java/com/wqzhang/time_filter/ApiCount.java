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
public class ApiCount {
    static class ApiTimeCountMapper extends Mapper<LongWritable, Text, Text, ApiAvgBean> {
        @Override
        protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, ApiAvgBean>.Context context) throws IOException, InterruptedException {
            // 2018-08-05 02:58:01,803 /v5/game/getGameStatus   558.722734451294
//            String line = "2018-09-13 12:07:37,447 /v5/game/getMurderList/238256/2/0 246.7191219329834";
            String line = value.toString();
            line = line.replace("\t", " ");

            String[] lines = line.split(" ");
            try {
                if (lines.length > 3) {
                    String url = lines[2];
                    Double time = Double.valueOf(lines[lines.length-1]);
                    if (!url.equals("")) {
                        String[] fields = url.split("\\/");
                        if (fields.length >= 4) {
                            String reqKey = "/" + fields[1] + "/" + fields[2] + "/" + fields[3];
                            context.write(new Text(reqKey), new ApiAvgBean(1, time));
                        }
                    }
                } else {
//                System.out.println(line);
                }
            }catch (Exception e){
                System.out.println(line);
            }


        }
    }

    static class TimeCountReducer extends Reducer<Text, ApiAvgBean, Text, ApiAvgBean> {


        @Override
        protected void reduce(Text key, Iterable<ApiAvgBean> values, Reducer<Text, ApiAvgBean, Text, ApiAvgBean>.Context context) throws IOException, InterruptedException {
            int count = 0;
            Double AllTime = 0.0;

            for (ApiAvgBean bean : values) {
                count += bean.getCount();
                AllTime += bean.getTime();
            }
            Double avgTime = AllTime / count;
            context.write(key, new ApiAvgBean(count, avgTime));
        }
    }

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "ApiCount");
        job.setJarByClass(ApiCount.class);

        job.setMapperClass(ApiTimeCountMapper.class);
        job.setReducerClass(TimeCountReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(ApiAvgBean.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(ApiAvgBean.class);

        FileInputFormat.setInputPaths(job, new Path(args[0]));
        FileOutputFormat.setOutputPath(job, new Path(args[1]));
        boolean b = job.waitForCompletion(true);
        if (!b) {
            System.out.println("ApiCount task fail!");
        }
    }
}
