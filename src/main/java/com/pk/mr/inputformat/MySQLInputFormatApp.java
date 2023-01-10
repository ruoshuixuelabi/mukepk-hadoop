package com.pk.mr.inputformat;

import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.db.DBConfiguration;
import org.apache.hadoop.mapreduce.lib.db.DBInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class MySQLInputFormatApp {
    public static void main(String[] args) throws Exception {
        String output = "out2";
        Configuration configuration = new Configuration();
        DBConfiguration.configureDB(configuration,
                "com.mysql.jdbc.Driver",
                "jdbc:mysql://hadoop000:3306/ruozedata", "root", "!Ruozedata123");
        Job job = Job.getInstance(configuration);
        FileUtils.delete(configuration, output);
        job.setJarByClass(MySQLInputFormatApp.class);
        job.setMapperClass(MyMapper.class);
        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(DeptWritable.class);
        String[] fields = {"deptno", "dname", "loc"};
        DBInputFormat.setInput(job, DeptWritable.class, "dept", null, null, fields);
        FileOutputFormat.setOutputPath(job, new Path(output));
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable, DeptWritable, NullWritable, DeptWritable> {
        @Override
        protected void map(LongWritable key, DeptWritable value, Context context) throws IOException, InterruptedException {
            context.write(NullWritable.get(), value);
        }
    }
}