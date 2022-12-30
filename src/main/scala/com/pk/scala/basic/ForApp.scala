package com.pk.scala.basic

import scala.language.postfixOps

/**
  * for循环 *****
  */
object ForApp {
  def main(args: Array[String]): Unit = {

    // 循环守卫
    for(i <- 1 to 10 if i != 5) {
//        println(i)
    }

    // 99乘法口诀表

//    for(i <- 1 to 9) {
//      for(j <- 1 to i) {
//        print(s"$j * $i = ${i * j}\t")
//      }
//      println()
//    }

//    for(i <-1 to 9; j <- 1 to i) {
//      print(s"$j * $i = ${i * j}\t")
//      if(i == j) println()
//    }


    // for这种写法是没有返回值的
//    val res = for(i <- 1 to 10) i * i
//    println(res)

    // 循环返回值  使用yield关键字
//    val res = for(i <- 1 to 10) yield i * i
//    println(res)


    // ==> upper
    val str = "abcd"
//    val res = for(s <- str) yield s.toUpper
//    println(res)
    val res = str.map(x => x.toUpper).mkString("")
    println(res)
  }

  def test01(): Unit = {
    // to [a,b]
    for (i <- 1 to 10) {
      //      println(i)
    }

    // 1.to(10)  ==>  1 to 10
    for (i <- 1.to(10)) {
      //      println(i)
    }

    // until [a,b)  1.until(10) ==> 1 until 10
    for (i <- 1.until(10)) {
      //      println(i)
    }

    // to的第二个参数是步长
    for (i <- 1.until(10, 2)) {
      //      println(i)
    }

    for (i <- 1 to 10 by 2) {
      //      println(i)
    }

    for (i <- 1 to 10 reverse) {
      //      println(i)
    }

    for (i <- 10 to 1 by -1) {
      println(i)
    }
  }

}
