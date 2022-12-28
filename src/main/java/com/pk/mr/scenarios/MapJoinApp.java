package com.pk.mr.scenarios;

import com.pk.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class MapJoinApp {

    public static void main(String[] args) throws Exception {
        String input = "data/join/emp.txt";
        String output = "out";

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        FileUtils.delete(configuration, output);

        job.setJarByClass(MapJoinApp.class);

        job.setMapperClass(MyMapper.class);

        job.setMapOutputKeyClass(Info.class);
        job.setMapOutputValueClass(NullWritable.class);

        job.setNumReduceTasks(0);

        // 加载小表的数据
        job.addCacheFile(new URI("data/join/dept.txt"));

        TextInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        boolean result = job.waitForCompletion(true);
        System.exit(result ? 0 : 1);
    }

    public static class MyMapper extends Mapper<LongWritable, Text, Info, NullWritable> {

        // deptno ==> dname
        Map<String,String> cache = new HashMap();

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            URI[] cacheFiles = context.getCacheFiles();
            String path = cacheFiles[0].getPath();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            new FileInputStream(path), "UTF-8"));

            String line;
            while (StringUtils.isNotEmpty(line = reader.readLine())) {
                String[] splits = line.split("\t");
                cache.put(splits[0].trim(), splits[1].trim());
            }

            IOUtils.closeStream(reader);
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] splits = value.toString().split("\t"); // 大表的数据   emp.txt

            int empno = Integer.parseInt(splits[0].trim());
            String ename = splits[1].trim();
            int deptno = Integer.parseInt(splits[7].trim());

            Info info = new Info();
            info.setEmpno(empno);
            info.setEname(ename);
            info.setDeptno(deptno);

            info.setDname(cache.get(deptno + ""));

            context.write(info, NullWritable.get());
        }
    }

}
