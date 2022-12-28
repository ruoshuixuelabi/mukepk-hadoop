package com.pk.mr.sort.all;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class AccessSortReducer extends Reducer<Access, Text, NullWritable, Access> {

    @Override
    protected void reduce(Access key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
        for(Text text : values) {
            context.write(NullWritable.get(), key);
        }
    }
}
