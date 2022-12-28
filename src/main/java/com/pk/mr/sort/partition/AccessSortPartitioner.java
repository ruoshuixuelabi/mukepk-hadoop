package com.pk.mr.sort.partition;

import com.pk.mr.sort.all.Access;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

public class AccessSortPartitioner extends Partitioner<Access, Text> {
    @Override
    public int getPartition(Access access, Text text, int numPartitions) {
        String phone = text.toString();
        if(phone.startsWith("13")) {
            return 0;
        }  else if (phone.startsWith("15")) {
            return 1;
        } else {
            return 2;
        }
    }
}

