package com.pk.utils;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Random;

/**
 * 造数据   这个技能是一定要掌握的
 */
public class MockData {

    public static void main(String[] args) throws Exception{
        String words[] = new String[]{"pk","zhangsan","lisi"};

        Random random = new Random();
        BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("data/big.txt")
                )
        );

        for (int i = 0; i < 1000000; i++) {
            for (int j = 0; j <= 30; j++) {
                writer.write(words[random.nextInt(words.length)]);
                writer.write(",");
            }
            writer.newLine();
        }
        writer.flush();
        writer.close();
    }
}
