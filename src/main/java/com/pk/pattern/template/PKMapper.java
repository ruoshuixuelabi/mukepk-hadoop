package com.pk.pattern.template;

public class PKMapper extends Mapper {
    @Override
    void setup() {
        System.out.println("====PKMapper.setup====");
    }

    @Override
    void cleanup() {
        System.out.println("====PKMapper.cleanup====");
    }

    @Override
    void map() {
        System.out.println("====PKMapper.map====");
    }
}
