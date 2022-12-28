package com.pk.mr.partitioner;

import com.pk.mr.ser.Access;
import com.pk.mr.ser.AccessMapper;
import com.pk.mr.ser.AccessReducer;
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


        // 设置自定义分区规则
        job.setPartitionerClass(AccessPartitioner.class);
        job.setNumReduceTasks(2);

        /**
         * reduce数量和分区数量(3)的关系
         * 1）reduce数量 = partition数量   3个文件
         * 2）reduce数量(决定了最终文件输出的个数) > partition数量   5个文件(有2个是空的)
         * 3）reduce数量 = 1
         * 4）1<reduce数量<partition数量  报错
         */
        FileInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        job.waitForCompletion(true);
    }
}
