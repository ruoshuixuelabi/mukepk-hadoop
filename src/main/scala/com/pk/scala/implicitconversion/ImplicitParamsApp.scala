package com.pk.scala.implicitconversion

/**
  * 隐式参数(了解即可，前面的两种必须掌握)
  */
object ImplicitParamsApp {

  def add(a:Int)(b:Int) = a + b
  def add2(a:Int)(implicit b:Int) = a + b
  def add3(a:Int)(implicit b:Int, c:Int) = a + b + c


  def main(args: Array[String]): Unit = {

    implicit val x = 100
    println(add3(2))

  }

}
