package com.pk.scala.basic

import scala.io.StdIn

/**
 * 分支
 */
object IfApp {
  def main(args: Array[String]): Unit = {
    val a = 11
    // 单分支
    if (a % 2 == 0) {
      //      println(s"$a 是偶数...")
    }
    // 双分支
    if (a % 2 == 0) {
      //      println(s"$a 是偶数...")
    } else {
      //      println(s"$a 是奇数...")
    }
    // 多分支
    //    println("请输入你的期末考试成绩:")
    //    val grade = StdIn.readInt()
    //
    //    if(grade == 100) {
    //      println("满分，棒极了...")
    //    } else if(grade >= 90 && grade < 100) {
    //      println("相当不错，要加油哦....")
    //    } else if(grade >= 60 && grade < 90) {
    //      println("一般般哦，需要继续努力哦....")
    //    } else {
    //      println("哎....")
    //    }
    val x = 20
    val y = 30
    var max = x
    if (x >= y) {
      max = x
    } else {
      max = y
    }
    println(s"最大值是:$max")
    val maxValue = if (x >= y) x else y
    println(s"最大值是:$maxValue")
    // 嵌套
    println("请输入你的分组")
    val group = StdIn.readChar()
    if (group == '1') {
      println("你是一组的人哦...")
    } else {
      println("你不是一组的人哦...")
      println("请输入你的成绩")
      val score = StdIn.readDouble()
      if (score > 85) {
        println("相当不错，要加油哦....")
      } else {
        println("哎....")
      }
    }
  }
}