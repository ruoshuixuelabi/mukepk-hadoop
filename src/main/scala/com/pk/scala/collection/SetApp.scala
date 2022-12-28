package com.pk.scala.collection

/**
  * Scala中Set的使用
  *
  * 封装的数据是不可以重复的
  * Array List都是可以重复的
  */
object SetApp {

  def main(args: Array[String]): Unit = {

    val a = Set(1,1,2,3,4,5)

    val s = scala.collection.mutable.Set(1,2,3)
    s.remove(1)

    val s1 = Set(30,50,70,60,10,20)
    val s2 = Set(30,5,70,2,10,7)

    // 并集
    s1.++(s2)
    s1 union  s2
    s1 | s2

    // 交集
    s1.&(s2)

    // 差集
    s1 -- s2
    s1 diff s2
    s2 &~ s2


  }

}
