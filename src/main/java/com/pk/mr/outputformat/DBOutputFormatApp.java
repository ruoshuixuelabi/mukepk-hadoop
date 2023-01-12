package com.pk.mr.outputformat;

import com.pk.mr.inputformat.DeptWritable;
import com.pk.mr.inputformat.FileInputFormatApp;
import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBOutputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class DBOutputFormatApp {
    public static void main(String[] args) throws Exception{
        String input = "data/dept.txt";
        Configuration configuration = new Configuration();
        DBConfiguration.configureDB(configuration,
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://hadoop000:3306/ruozedata", "root", "!Ruozedata123");
        Job job = Job.getInstance(configuration);
        job.setJarByClass(DBOutputFormat.class);
        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);
        job.setMapOutputKeyClass(LongWritable.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(DeptWritable.class);
        job.setOutputValueClass(NullWritable.class);
        TextInputFormat.setInputPaths(job, new Path(input));
        job.setOutputFormatClass(DBOutputFormat.class);
        DBOutputFormat.setOutput(job,"dept","deptno","dname","loc");
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }
    public static class MyMapper extends Mapper<LongWritable, Text, NullWritable, Text> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            context.write(NullWritable.get(), value);
        }
    }
    public static class MyReducer extends Reducer<LongWritable, Text, DeptWritable, NullWritable> {
        @Override
        protected void reduce(LongWritable key, Iterable<Text> values, Reducer<LongWritable, Text, DeptWritable, NullWritable>.Context context) throws IOException, InterruptedException {
            for (Text value : values) {
                String[] split = value.toString().split(",");
                DeptWritable deptWritable=new DeptWritable();
                deptWritable.setDeptno(Integer.parseInt(split[0].trim()));
                deptWritable.setDname(split[1].trim());
                deptWritable.setLoc(split[2].trim());
                context.write(deptWritable,NullWritable.get());
            }
        }
    }
}
