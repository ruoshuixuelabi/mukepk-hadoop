package com.pk.mr.inputformat;

import com.pk.mr.wc.WordCountDriver;
import com.pk.mr.wc.WordCountMapper;
import com.pk.mr.wc.WordCountReducer;
import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * TextInputFormat是FileInputFormat的一个实现，按行读取数据
 * 重写了两个方法：
 * createRecordReader
 * isSplitable
 */
public class FileInputFormatApp {

    public static void main(String[] args) throws Exception {
        String input = "data/wc";
        String output = "out2";

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        FileUtils.delete(configuration, output);

        job.setJarByClass(FileInputFormatApp.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

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
        protected void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split(",");
            for(String word : splits) {
                context.write(new Text(word), ONE);
            }
        }
    }
}
