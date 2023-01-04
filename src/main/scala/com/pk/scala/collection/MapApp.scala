package com.pk.scala.collection

/**
 * Scala中Map：和Java类似  KV 映射
 * <p>
 * 不可变
 * <p>
 * 可变
 */
object MapApp {
  /**
   * abstract class Option
   * <p>
   * case class Some extends Option   取到
   * <p>
   * case object None extends Option[Nothing]  没取到
   */
  def main(args: Array[String]): Unit = {
    val map = Map("pk" -> 31, "xiaohong" -> 18)
    for ((k,v) <- map) {
      println(k+"-------->"+v)
    }
    val value = map.getOrElse("pk1", -99) // 在map取值中要注意的，建议使用getOrElse
    println(value)
    val b = scala.collection.mutable.Map("pk" -> 31, "xiaohong" -> 18)
    val c = scala.collection.mutable.Map[String, Int]()
    b += ("zhangsan" -> 10)
    b.put("lisi", 20)
    b -= "lisi"
  }
}