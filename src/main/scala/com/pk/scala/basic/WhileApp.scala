package com.pk.scala.basic

import scala.util.control.Breaks._

/**
 * Scala中的while循环
 */
object WhileApp {
  def main(args: Array[String]): Unit = {
    var index = 0
    while (index < 10) {
      breakable {
        index += 1
        if (index % 2 != 0) {
          println("------" + index)
        } else {
          println("continue")
          break()
        }
      }
    }
    println("退出...")
  }

  def test02(): Unit = {
    var index = 1
    breakable {
      while (index < 10) {
        println("----" + index)
        index += 1
        if (index == 5) {
          break()
        }
      }
    }
    println("退出...")
  }

  def test(): Unit = {
    var a = 1
    while (a <= 10) {
      println("你好，PK哥" + a)
      a += 1
    }
    do {
      println("你好，PK哥" + a)
      a += 1
    } while (a <= 10)
    /**
     * if是有返回值的，while是没有返回值的
     */
    // 方法体中的最后一行作为返回值，不需要return
    val res: Unit = while (a <= 10) {
      println("你好，PK哥" + a)
      a += 1
      a
    }
    println(res)
    var sum = 0
    var num = 100
    while (num > 0) {
      sum = sum + num
      num = num - 1
    }
    println(s"和是: $sum")

    // 如何终止循环
    var flag = true
    var index = 1
    while (flag) {
      if (index > 5) {
        flag = false
      }
      println("----------------" + index)
      index += 1
    }
  }
}