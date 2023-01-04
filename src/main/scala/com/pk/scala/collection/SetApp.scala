package com.pk.scala.collection

/**
 * Scala中Set的使用
 *
 * 封装的数据是不可以重复的
 * Array List都是可以重复的
 */
object SetApp {

  def main(args: Array[String]): Unit = {
    //不可变的
    val a = Set(1, 1, 2, 3, 4, 5)
    println(a) //HashSet(5, 1, 2, 3, 4)
    //遍历
    for (elem <- a) {
      println(elem)
    }
    //可变的集合
    val s = scala.collection.mutable.Set(1, 2, 3)
    //增
    s.add(10)
    s += 11
    s += (11, 12)
    println(s) //HashSet(1, 2, 10, 3, 11)
    //删
    s -= 12
    s -= (11, 12) //已废弃，推荐使用subtractOne 但是测试发现subtractOne不能一下子删除2个元素
    println(s) //HashSet(1, 2, 3, 10)
    s.remove(1)
    val s1 = Set(30, 50, 70, 60, 10, 20)
    val s2 = Set(30, 5, 70, 2, 10, 7)
    // 并集
    println(s1.++(s2))//HashSet(5, 10, 20, 60, 70, 2, 7, 50, 30)
    println(s1 union s2)
    println(s1 | s2)

    // 交集
    s1.&(s2)

    // 差集
    s1 -- s2
    s1 diff s2
    s2 &~ s2
  }
}