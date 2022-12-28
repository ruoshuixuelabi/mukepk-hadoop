package com.pk.project.mapreduce;

import com.pk.project.domain.UserAccess;
import com.pk.utils.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Counter;
import org.apache.hadoop.mapreduce.CounterGroup;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;

/**
 * 数据清洗
 *
 * 通常情况下，MR程序是由Mapper和Reducer构成的
 *
 * MR只有Mapper没有Reducer？  MapJoin
 * 数据清洗程序只需要Mapper，并不需要Reducer
 *  data ==> Mapper ==> HDFS
 */
public class DataCleanApp {
    public static void main(String[] args) throws Exception {

        String input = "data/user-access.log";
        String output = "out";

        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);

        FileUtils.delete(configuration, output);

        job.setJarByClass(DataCleanApp.class);
        job.setMapperClass(MyMapper.class);
        job.setNumReduceTasks(0);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);

        TextInputFormat.setInputPaths(job, new Path(input));
        FileOutputFormat.setOutputPath(job, new Path(output));

        boolean result = job.waitForCompletion(true);

        CounterGroup dataCleanCounterGroup = job.getCounters().getGroup("data-clean");
        Iterator<Counter> iterator = dataCleanCounterGroup.iterator();

        while (iterator.hasNext()) {
            Counter counter = iterator.next();
            System.out.println(counter.getName() + " ==> " + counter.getValue());
        }
        
        System.exit(result ? 0 : 1);


    }

    public static class MyMapper extends Mapper<LongWritable, Text, Text, NullWritable> {
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {

            try {

                context.getCounter("data-clean", "totals").increment(1L);

                String[] splits = value.toString().split("\t");
                String time = splits[0].trim();
                String ip = splits[1].trim();
                String proxyIp = splits[2].trim();
                String responseTime = splits[3].trim();
                String referer = splits[4].trim();
                String method = splits[5].trim();
                String url = splits[6].trim();
                String httpCode = splits[7].trim();
                String requestSize = splits[8].trim();
                String responseSize = splits[9].trim();
                String cache = splits[10].trim();

                UserAccess access = new UserAccess();
                access.setIp(ip);
                access.setProxyIp(proxyIp);
                access.setMethod(method);
                access.setReferer(referer);
                access.setHttpCode(httpCode);
                access.setCache(cache);


                /**
                 * 规范：严格要求的字段responseSize ，如果不符合要求 就丢弃
                 *
                 * 非严格要求responseTime，requestSize 如果转换失败，就赋默认值
                 */

                try {
                    access.setResponseTime(Long.parseLong(responseTime));
                } catch (Exception e) {
                    access.setResponseTime(0);
                }

                try {
                    access.setRequestSize(Long.parseLong(requestSize));
                } catch (Exception e) {
                    access.setRequestSize(0);
                }

                // responseSize 是 -
                access.setResponseSize(Long.parseLong(responseSize));

                SimpleDateFormat format = new SimpleDateFormat("[dd/MM/yyyy:HH:mm:ss +0800]");
                Date date = format.parse(time);

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(date);
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH) + 1;
                int day = calendar.get(Calendar.DATE);
                access.setYear(year + "");
                access.setMonth(month < 10 ? "0" + month : month + "");
                access.setDay(day < 10 ? "0" + day : day + "");

                URL u = new URL(url);
                access.setDomain(u.getHost());
                access.setHttp(u.getProtocol());
                access.setPath(u.getPath());
                access.setParams(StringUtils.isEmpty(u.getQuery()) ? "-": u.getQuery());

                // TODO...
                access.setProvince("-");
                access.setCity("-");
                access.setIsp("-");


                context.getCounter("data-clean", "formats").increment(1L);

                context.write(new Text(access.toString()), NullWritable.get());

            } catch (Exception e) {
//                e.printStackTrace();
                context.getCounter("data-clean", "errors").increment(1L);
            }
        }
    }

}
