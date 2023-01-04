package com.pk.scala.collection

import scala.+:
import scala.collection.mutable.ArrayBuffer

/**
 * Scala中数组的使用
 */
object ArrayApp {

  def main(args: Array[String]): Unit = {
    // 不可变  val 名字 = new Array[类型](长度)
    val a = new Array[String](5)
    val array = Array[Int](1, 2, 3, 4)
    val array2 = Array[Int](5,6)
    val a1 = array :+ 100
    println(a1.mkString("Array(", ", ", ")")) //Array(1, 2, 3, 4, 100)
    val a2 = 100 +: array
    println(a2.mkString("Array(", ", ", ")")) //Array(100, 1, 2, 3, 4)
    val array3 = array ++ array2
    println(array3.mkString("Array(", ", ", ")"))//Array(1, 2, 3, 4, 5, 6)
    // 可变 val 名字 = ArrayBuffer[类型]()
    val c = ArrayBuffer[Int]()
    //从后面删除2个元素，已废弃,推荐dropRightInPlace
    c.trimEnd(2)
    c.dropRightInPlace(2)

    /**
     * 面试题：
     * 把b2的数据加到b1中去，更新的是b1
     * b1 ++= b2
     * 把b2的数据加到b1中去，更新的是b2
     * b1 ++=: b2
     */
    c.toArray.toBuffer
  }
}