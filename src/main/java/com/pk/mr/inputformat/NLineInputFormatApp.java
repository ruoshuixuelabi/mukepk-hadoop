package com.pk.mr.inputformat;

import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.NLineInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class NLineInputFormatApp {

    public static void main(String[] args) throws Exception {
        String input = "data/wc/wc1.data";
        String output = "out2";

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        FileUtils.delete(configuration, output);

        job.setJarByClass(NLineInputFormatApp.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        // 设置每个分片划分的记录条数
        NLineInputFormat.setNumLinesPerSplit(job, 5);

        // 设置NLineInputFormat的处理格式
        job.setInputFormatClass(NLineInputFormat.class);
        TextInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    public static class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text word, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for(IntWritable value : values) {
                count += value.get();
            }
            context.write(word, new IntWritable(count));
        }
    }

    public static class MyMapper extends Mapper<LongWritable, Text, Text, IntWritable> {

        IntWritable ONE = new IntWritable(1);

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split(",");
            for(String word : splits) {
                context.write(new Text(word), ONE);
            }
        }
    }
}
