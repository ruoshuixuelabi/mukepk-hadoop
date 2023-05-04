package com.pk.scala.pattern

import scala.io.Source

/**
 * Scala中的异常处理
 */
object ExceptionApp {
  def main(args: Array[String]): Unit = {
    try {
      // 打开资源： 读文件  Connection
      //val i = 10 / 0
      val list = Source.fromFile("data/wc1.data", "utf-8").getLines().toList
      println(list)
    } catch {
      case e: ArithmeticException => {
        println("发生了算术异常:" + e.getMessage)
      }
      case e: Exception => {
        println("异常..." + e.getMessage)
      }
      case _ =>
    } finally {
      // 不管是否发生异常，都会走到这里
      println("finally")
      // 关闭资源：io流关闭  connection关闭掉
    }
  }
}