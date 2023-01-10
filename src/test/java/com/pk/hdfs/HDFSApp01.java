package com.pk.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.PartHandle;
import org.apache.hadoop.fs.Path;
import org.junit.Assert;
import org.junit.Test;

import java.net.URI;

public class HDFSApp01 {
    /**
     * 创建HDFS文件夹
     */
    @Test
    public void mkdir() throws Exception {
        Configuration configuration = new Configuration();
        // 获取HDFS客户端对象
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://172.18.30.88:9000"), configuration, "root");
        // 在HDFS上创建路径
        boolean isSuccess = fileSystem.mkdirs(new Path("/hdfsapi"));
        Assert.assertTrue(isSuccess);
        // 关闭资源
        if (null != fileSystem) fileSystem.close();
    }

    @Test
    public void mkdir02() throws Exception {
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://172.18.30.88:9000");
        FileSystem fileSystem = FileSystem.get(configuration);
        boolean isSuccess = fileSystem.mkdirs(new Path("/hdfsapi02"));
        Assert.assertEquals(true, isSuccess);
        if (null != fileSystem) fileSystem.close();
    }

    @Test
    public void put() throws Exception {
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://172.18.30.88:9000");
//        configuration.set("dfs.client.use.datanode.hostname", "true");
        configuration.set("dfs.replication", "1");
        FileSystem fileSystem = FileSystem.get(configuration);
        Path src = new Path("data/wc.data");
        Path dst = new Path("/hdfsapi02/wc2.data");
        fileSystem.copyFromLocalFile(src, dst);
        if (null != fileSystem) fileSystem.close();
    }

    @Test
    public void replication() throws Exception {
        System.setProperty("HADOOP_USER_NAME", "root");
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://172.18.30.88:9000");
        configuration.set("dfs.client.use.datanode.hostname", "true");
        configuration.set("dfs.replication", "1");
        FileSystem fileSystem = FileSystem.get(configuration);
        String result = fileSystem.getConf().get("dfs.replication");
        System.out.println(result);
        if (null != fileSystem) fileSystem.close();
    }
}
