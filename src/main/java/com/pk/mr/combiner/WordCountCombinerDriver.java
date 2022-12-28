package com.pk.mr.combiner;

import com.pk.mr.wc.WordCountMapper;
import com.pk.mr.wc.WordCountReducer;
import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 八股文编程
 * 面向套路编程
 */
public class WordCountCombinerDriver {

    public static void main(String[] args) throws Exception {
        String input = "data/wc.data";
        String output = "out2";

        // 获取Job对象
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        // 删除事先存在的输出目录
        FileUtils.delete(configuration, output);

        // 设置运行类
        job.setJarByClass(WordCountCombinerDriver.class);

        // 设置Mapper和Reducer
        job.setMapperClass(WordCountMapper.class);
        job.setReducerClass(WordCountReducer.class);

        // 设置Combiner类
        job.setCombinerClass(WordCountReducer.class);

        // 设置Mapper的输出数据的KEY和VALUE类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 设置Reducer的输出数据的KEY和VALUE类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);



        // 设置作业的输入和输出路径
        TextInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        // 提交Job
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
}
