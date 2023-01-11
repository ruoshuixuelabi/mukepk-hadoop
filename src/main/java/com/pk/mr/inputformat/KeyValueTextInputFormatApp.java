package com.pk.mr.inputformat;

import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class KeyValueTextInputFormatApp {
    public static void main(String[] args) throws Exception {
        String input = "data/kv.data";
        String output = "out2";
        Configuration configuration = new Configuration();
        //默认的分隔符是TAB键,这里修改为,
        configuration.set(KeyValueLineRecordReader.KEY_VALUE_SEPARATOR, ",");
        Job job = Job.getInstance(configuration);
        FileUtils.delete(configuration, output);
        job.setJarByClass(KeyValueTextInputFormatApp.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
        //设置输入数据的InputFormat
        job.setInputFormatClass(KeyValueTextInputFormat.class);
        TextInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    public static class MyReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
        @Override
        protected void reduce(Text word, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int count = 0;
            for (IntWritable value : values) {
                count += value.get();
            }
            context.write(word, new IntWritable(count));
        }
    }

    public static class MyMapper extends Mapper<Text, Text, Text, IntWritable> {
        IntWritable ONE = new IntWritable(1);

        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            context.write(key, ONE);
        }
    }
}