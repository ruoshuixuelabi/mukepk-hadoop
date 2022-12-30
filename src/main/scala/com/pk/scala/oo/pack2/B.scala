package com.pk.scala.oo.pack2

import java.util.ArrayList

import com.pk.scala.oo.pack1.{A, AA}

/**
  * 导入包的方式
  *
  */
object B {

  def main(args: Array[String]): Unit = {


    val list = new ArrayList[String]()
    list.add("PK")
    list.add("hello")


    /**
      * 隐式转换
      *
      * scala和java相互转换的互操作
      *
      * 注意新老版本的用法
      */
//    import scala.collection.JavaConversions._
//    for(ele <- list) {
//      println(ele)
//    }

  }

  def test(): Unit ={
    // 最前面import
    val a1 = new A()
    a1.methodA()

    // 在使用的时候导入
    val a2 = new com.pk.scala.oo.pack1.A()
    a2.methodB()

    // 导入的时候改名字
    import com.pk.scala.oo.pack1.{A => PKA}
    val a3 = new PKA()
    a3.methodB()

    val aa = new AA()
    aa.methodC()
  }

}
