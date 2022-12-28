package com.pk.mr.ser;

import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

/**
 * 八股文编程
 */
public class AccessDriver {

    public static void main(String[] args) throws Exception {

        String input = "data/access.log";
        String output = "out";

        Configuration configuration = new Configuration();

        FileUtils.delete(configuration, output);

        Job job = Job.getInstance(configuration);

        job.setJarByClass(AccessDriver.class);

        job.setMapperClass(AccessMapper.class);
        job.setReducerClass(AccessReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Access.class);

        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Access.class);

        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        job.waitForCompletion(true);
    }
}
