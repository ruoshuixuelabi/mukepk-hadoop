package com.pk.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.io.IOUtils;
import org.junit.*;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;

public class HDFSApp02 {

    FileSystem fileSystem;

    @Before
    public void setup() throws Exception {
        System.out.println("setup");
        System.setProperty("HADOOP_USER_NAME", "hadoop");
        Configuration configuration = new Configuration();
        configuration.set("fs.defaultFS", "hdfs://hadoop000:8020");
        configuration.set("dfs.client.use.datanode.hostname", "true");

        fileSystem = FileSystem.get(configuration);
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("tearDown");
        if (null != fileSystem) {
            fileSystem.close();
        }
    }

    @Test
    public void put() throws Exception {
        Path src = new Path("data/wc.data");
        Path dst = new Path("/hdfsapi02/test");
        fileSystem.copyFromLocalFile(src, dst);
    }

    /**
     * 下载文件
     */
    @Test
    public void get() throws Exception {
        Path src = new Path("/hdfsapi02/wc3.data");
        Path dst = new Path("out/wc.data");
        fileSystem.copyToLocalFile(src, dst);
    }

    /**
     * rename：改名、移动到其他地方去
     */
    @Test
    public void rename() throws Exception {
        Path src = new Path("/hdfsapi02/wc3.data-new");
        Path dst = new Path("/hdfsapi/wc.data");
        fileSystem.rename(src, dst);
    }


    /**
     * 展示
     */
    @Test
    public void list() throws Exception {
        RemoteIterator<LocatedFileStatus> files = fileSystem.listFiles(new Path("/hdfsapi02"), true);
        while (files.hasNext()) {
            LocatedFileStatus fileStatus = files.next();
            String isDir = fileStatus.isDirectory() ? "文件夹" : "文件";
            short replication = fileStatus.getReplication(); // 副本信息
            String permission = fileStatus.getPermission().toString(); // 权限
            long len = fileStatus.getLen(); // 文件大小
            String path = fileStatus.getPath().toString();// 文件路径

            System.out.println(isDir + "\t" + permission
                    + "\t" + replication
                    + "\t" + len
                    + "\t" + path);

            BlockLocation[] blockLocations = fileStatus.getBlockLocations();
            for (BlockLocation blockLocation : blockLocations) {
                String[] hosts = blockLocation.getHosts();
                for (String host : hosts) {
                    System.out.println(host);
                }
            }
        }
    }


    /**
     * 删除操作
     */
    @Test
    public void delete() throws Exception {
        fileSystem.delete(new Path("/hdfsapi02"), true);
    }


    /**
     * 使用IO流的方式拷贝文件到HDFS文件系统
     */
    @Test
    public void copyFromLocalIO() throws Exception {

        BufferedInputStream in = new BufferedInputStream(new FileInputStream(new File("data/wc.data")));
        FSDataOutputStream out = fileSystem.create(new Path("/hdfsapi/wc-io.data"));

        // 流的拷贝
        IOUtils.copyBytes(in, out, 4096);

        IOUtils.closeStream(out);
        IOUtils.closeStream(in);
    }


    @Test
    public void copyToLocalIO() throws Exception {
        FSDataInputStream in = fileSystem.open(new Path("/hdfsapi/wc-io.data"));
        FileOutputStream out = new FileOutputStream(new File("out/b.txt"));

        // 流的拷贝
        IOUtils.copyBytes(in, out, 4096);

        IOUtils.closeStream(out);
        IOUtils.closeStream(in);
    }
}
