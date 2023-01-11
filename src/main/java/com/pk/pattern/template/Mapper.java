package com.pk.pattern.template;

public abstract class Mapper {
    /**
     * 初始化方法
     */
    abstract void setup();

    /**
     * 资源释放方法
     */
    abstract void cleanup();

    /**
     * 业务逻辑方法
     */
    abstract void map();

    /**
     * 只定义操作的轮廓/骨架，具体实现推迟到子类中去完成
     */
    public void run() {
        setup();
        map();
        cleanup();
    }
}