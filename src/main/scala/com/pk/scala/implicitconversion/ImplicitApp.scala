package com.pk.scala.implicitconversion

import java.io.File

import scala.io.Source
import ImplicitAspect._

/**
  * 最核心部分：定义隐式转换函数  implicit def a2B(a:A):B = new B(a)
  */
object ImplicitApp {

  def main(args: Array[String]): Unit = {

    val man = new Man("灰太狼")
    man.fly()


    val file = new File("data/wc.data")
    val content = file.read()
    println(content)
  }
}


class Man(val name:String)

class SuperMan(val name:String) {
  def fly(): Unit = {
    println(s"$name fly...")
  }
}

/**
  * RichXxx
  */
class RichFile(val file:File) {
  def read() = Source.fromFile(file.getPath).mkString
}