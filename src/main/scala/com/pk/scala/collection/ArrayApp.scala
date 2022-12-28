package com.pk.scala.collection

import scala.collection.mutable.ArrayBuffer

/**
  * Scala中数组的使用
  */
object ArrayApp {

  def main(args: Array[String]): Unit = {


    // 不可变  val 名字 = new Array[类型](长度)
    val a = new Array[String](5)


    // 可变 val 名字 = ArrayBuffer[类型]()
    val c = ArrayBuffer[Int]()
    c.trimEnd(2)


    /**
      * 面试题：
      * 把b2的数据加到b1中去，更新的是b1
      * b1 ++= b2
      *
      * 把b2的数据加到b1中去，更新的是b2
      * b1 ++=: b2
      */


    c.toArray.toBuffer

  }

}
