package com.pk.mr.combiner;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class WordCountCombiner extends Reducer<Text, IntWritable, Text, IntWritable> {

    @Override
    protected void reduce(Text word, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int count = 0;

        // 累加求和
        for(IntWritable value : values) {
            count += value.get();
        }

        // 输出
        context.write(word, new IntWritable(count));
    }
}
