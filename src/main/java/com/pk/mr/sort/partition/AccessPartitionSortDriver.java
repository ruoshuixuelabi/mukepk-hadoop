package com.pk.mr.sort.partition;

import com.pk.mr.sort.all.Access;
import com.pk.mr.sort.all.AccessSortMapper;
import com.pk.mr.sort.all.AccessSortReducer;
import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class AccessPartitionSortDriver {
    public static void main(String[] args) throws Exception {
        String input = "data/access-stat.data";
        String output = "out";
        Configuration configuration = new Configuration();
        FileUtils.delete(configuration, output);
        Job job = Job.getInstance(configuration);
        job.setJarByClass(AccessPartitionSortDriver.class);
        job.setMapperClass(AccessSortMapper.class);
        job.setReducerClass(AccessSortReducer.class);
        job.setMapOutputKeyClass(Access.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Access.class);
        job.setPartitionerClass(AccessSortPartitioner.class);
        job.setNumReduceTasks(3);
        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));
        job.waitForCompletion(true);
    }
}