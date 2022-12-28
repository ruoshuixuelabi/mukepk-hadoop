package com.pk.scala.basic

import java.io.{BufferedReader, InputStreamReader}
import java.util.Scanner

import scala.io.StdIn

/**
  * 需求：接收终端传递过来的数据
  */
object StdInApp {

  def main(args: Array[String]): Unit = {

//    val reader = new BufferedReader(new InputStreamReader(System.in, "utf-8"))
//    val line = reader.readLine()
//    println(line)

//    val scanner: Scanner = new Scanner(System.in)
//    println(scanner.next())

    println("请输入你的大名:")
    val name = StdIn.readLine()

    println("请输入你的芳龄:")
    val age = StdIn.readInt()

    println("请输入你的银行卡密码:")
    val password = StdIn.readLine()

    println(s"大名:$name, 芳龄:$age, 银行卡密码:$password")


    val s1 = "PK"
    val s2 = new String("PK")
    println(s1 == s2)
    println(s1.eq(s2))

  }

}
