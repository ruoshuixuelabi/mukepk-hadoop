package com.pk.mr.sort.all;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class AccessSortMapper extends Mapper<LongWritable, Text, Access, Text>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split(",");

        String phone = splits[0].trim();
        long up = Long.parseLong(splits[1]);
        long down = Long.parseLong(splits[2]);

        context.write(new Access(phone, up, down), new Text(phone));
    }
}
