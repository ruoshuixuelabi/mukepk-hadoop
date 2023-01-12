package com.pk.mr.outputformat;

import com.pk.utils.FileUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PKOutputFormatApp {
    public static void main(String[] args) throws Exception {
        String input = "data/click.log";
        String output = "out2";
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        FileUtils.delete(configuration, output);
        job.setJarByClass(PKOutputFormatApp.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        job.setOutputFormatClass(PKOutputFormat.class);
        TextInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));
        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            context.write(value, NullWritable.get());
        }
    }

    public static class MyReducer extends Reducer<Text, NullWritable, Text, NullWritable> {
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Reducer<Text, NullWritable, Text, NullWritable>.Context context) throws IOException, InterruptedException {
            for (NullWritable value : values) {
                context.write(new Text(key.toString() + "\r\n"), NullWritable.get());
            }
        }
    }

    public static class PKOutputFormat extends FileOutputFormat<Text, NullWritable> {
        @Override
        public RecordWriter<Text, NullWritable> getRecordWriter(TaskAttemptContext job) throws IOException, InterruptedException {
            return new PKRecordWriter(job);
        }
    }

    public static class PKRecordWriter extends RecordWriter<Text, NullWritable> {
        FileSystem fileSystem = null;
        FSDataOutputStream googleOut = null;
        FSDataOutputStream otherOut = null;

        public PKRecordWriter(TaskAttemptContext job) {
            try {
                fileSystem = FileSystem.get(job.getConfiguration());
                googleOut = fileSystem.create(new Path("out/google.com"));
                otherOut = fileSystem.create(new Path("out/other.com"));
            } catch (Exception e) {

            }

        }

        @Override
        public void write(Text key, NullWritable value) throws IOException, InterruptedException {
            if (key.toString().contains("google.com")) {
                googleOut.write(key.toString().getBytes());
            } else {
                otherOut.write(key.toString().getBytes());
            }
        }

        @Override
        public void close(TaskAttemptContext context) throws IOException, InterruptedException {
            IOUtils.closeStream(googleOut);
            IOUtils.closeStream(otherOut);
            IOUtils.closeStream(fileSystem);
        }
    }
}