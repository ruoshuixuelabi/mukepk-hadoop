package com.pk.scala

/**
 * 文档注释：30-50%  是一个非常好的习惯，是需要平时就要养成的
 * 类上的注释：这个类的职责是什么
 * 方法上的注释：这个方法的职责是什么？入参是什么？出参是什么？
 *
 *
 * main方法：Scala程序执行的入口点
 * 区分大小写
 * Java源码：User.java   Scala源码: Student.scala
 * 在Scala中每行结尾并不需要显示的使用分号
 * 一行可以写多条语句：除了最后一条语句外，其他语句中间需要使用分号(了解即可)
 *
 *
 * 如何根据注释生成文档： $SCALA_HOME/bin/scaladoc HelloScala.scala
 */
object HelloScala {
  /**
   * 这是scala类的入口点
   *
   * @param args
   * @author PK哥
   */
  def main(args: Array[String]): Unit = {
    // == 是Java API   单行注释
    System.out.println("PK哥 你好....")
    // == 是Scala API
    println("Hello Scala...")
    println("Hello PK哥...")
    println("PK哥...")
    /* 多行注释 */
    val teacher = "PK哥"
  }
}