package com.pk.pattern.template;

public class Client {

    public static void main(String[] args) {

        Mapper mapper = new PKMapper();
        mapper.run();
        System.out.println("-----");
        new DPKMapper().run();

    }
}
