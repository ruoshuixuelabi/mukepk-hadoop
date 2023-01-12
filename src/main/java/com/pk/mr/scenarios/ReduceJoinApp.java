package com.pk.mr.scenarios;

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
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.util.ArrayList;

public class ReduceJoinApp {

    public static void main(String[] args) throws Exception {
        String input = "data/join";
        String output = "out";

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        FileUtils.delete(configuration, output);

        job.setJarByClass(ReduceJoinApp.class);

        job.setMapperClass(MyMapper.class);
        job.setReducerClass(MyReducer.class);

        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Info.class);

        job.setOutputKeyClass(Info.class);
        job.setOutputValueClass(NullWritable.class);

        TextInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable, Text, IntWritable, Info> {
        String name;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            name = fileSplit.getPath().toString();
            System.out.println("=====" + name);
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split("\t");
            if (name.contains("emp")) { // 来自于emp
                int empno = Integer.parseInt(splits[0].trim());
                String ename = splits[1].trim();
                int deptno = Integer.parseInt(splits[7].trim());
                Info info = new Info();
                info.setEmpno(empno);
                info.setEname(ename);
                info.setDeptno(deptno);
                info.setDname("");
                info.setFlag(1);
                context.write(new IntWritable(deptno), info);
            } else {  // 来自于dept
                Info info = new Info();
                int deptno = Integer.parseInt(splits[0].trim());
                String dname = splits[1].trim();
                info.setDeptno(deptno);
                info.setDname(dname);
                info.setEmpno(0);
                info.setEname("");
                info.setFlag(2);
                context.write(new IntWritable(deptno), info);
            }
        }
    }

    public static class MyReducer extends Reducer<IntWritable, Info, Info, NullWritable> {
        @Override
        protected void reduce(IntWritable key, Iterable<Info> values, Context context) throws IOException, InterruptedException {
            ArrayList<Info> infos = new ArrayList<>();
            String dname = "";
            for (Info info : values) {
                if (info.getFlag() == 1) {    // emp
                    /*
                     * 此处一定要new一个新的Info对象
                     * 否则结果不对
                     * 如果不对？为什么不对？
                     */
                    Info tmp = new Info();
                    tmp.setEmpno(info.getEmpno());
                    tmp.setEname(info.getEname());
                    tmp.setDeptno(info.getDeptno());
                    infos.add(tmp);
                } //
                else if (info.getFlag() == 2) {  // dept
                    dname = info.getDname();
                }
            }
            for (Info bean : infos) {
                bean.setDname(dname);
                context.write(bean, NullWritable.get());
            }
        }
    }
}