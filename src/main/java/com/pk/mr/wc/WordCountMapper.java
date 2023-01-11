package com.pk.mr.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * KEYIN：输入数据的KEY的数据类型
 * <p>
 * VALUEIN：输入数据的VALUE的数据类型
 * <p>
 * KEYOUT：输出数据的KEY的数据类型
 * <p>
 * VALUEOUT：输出数据的VALUE的数据类型
 * <p>
 * LongWritable：就是文件中数据的偏移量
 * <p>
 * Text：每一行数据的内容
 */
public class WordCountMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    IntWritable ONE = new IntWritable(1);

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        System.out.println("--------setup--------");
    }

    @Override
    protected void cleanup(Context context) throws IOException, InterruptedException {
        System.out.println("--------cleanup--------");
    }

    /**
     * @param key     每行数据的偏移量，不是行号
     * @param value   每行数据
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("key ====>" + key.get());
        System.out.println("value ====>" + value.toString());
        // 获取每行数据,然后按照指定分隔符进行切分
        String[] splits = value.toString().split(",");
        // 循环输出
        for (String word : splits) {
            context.write(new Text(word), ONE);
        }
    }
}