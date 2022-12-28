package com.pk.mr.ser;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/**
 * 按照分隔符进行拆分
 * 把每一行数据转换成一个Access对象
 * <phone, Access>
 */
public class AccessMapper extends Mapper<LongWritable, Text, Text, Access>{
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String[] splits = value.toString().split("\t");

        /**
         * 一定要从代码的健壮性角度出发  TODO...
         */

        String phone = splits[1].trim();
        long up = Long.parseLong(splits[splits.length - 3]);
        long down = Long.parseLong(splits[splits.length - 2]);

        context.write(new Text(phone), new Access(phone, up, down));
    }
}
