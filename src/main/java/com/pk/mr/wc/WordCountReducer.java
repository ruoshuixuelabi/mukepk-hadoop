package com.pk.mr.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        System.out.println("hello");
    }

    /**
     * Iterable<IntWritable> values
     * <p>
     * pk,pk,pk    hello,hello   world
     * <p>
     * <pk,1> <pk,1> <pk,1> ==> <pk,<1,1,1>>
     * <p>
     * <hello,1> <hello,1> ==> <hello, <1,1>>
     * <p>
     * <world,1>==> <world, <1>>
     * <p>
     * 相同的key经过shuffle之后，会在同一个reduce中进行聚合
     * <p>
     * 默认字典序
     *
     * @param word    单词
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text word, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;
        // 累加求和
        for (IntWritable value : values) {
            count += value.get();
        }
        // 输出
        context.write(word, new IntWritable(count));
    }
}