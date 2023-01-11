package com.pk.pattern.template;

public class DPKMapper extends Mapper {
    @Override
    void setup() {
        System.out.println("====DPKMapper.setup====");
    }

    @Override
    void cleanup() {
        System.out.println("====DPKMapper.cleanup====");
    }

    @Override
    void map() {
        System.out.println("====DPKMapper.map====");
    }
}