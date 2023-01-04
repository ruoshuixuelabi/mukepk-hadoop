package com.pk.scala.collection

/**
 * Tuple  元组
 * 封装了一系列元素的集合
 *
 * (1,2,a,b,c,d)元素个数可以有"多个（最多22个）"，数据类型可以是任意的
 *
 */
object TupleApp {
  def main(args: Array[String]): Unit = {
    val array = Array[Int](1, 2, 3, 4, 5, 6, 7, 8, 9, 10)

    /**
     * 根据输入的Int数组 ==> 最大值  最小值  平均值
     *
     * @param array
     */
    def method(array: Array[Int]) = {
      (array.max, array.min, array.sum / array.size)
    }

    val (max, min, avg) = method(array)
    println(max)
  }

  def test(): Unit = {
    // 第一种定义方式：()包起来的一系列的值  *****
    val a = (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22)
    //取出元素
    println(a._1)//取出第一个元素

    // 第二种定义方式
    val b = Tuple3("PK", 31, "BEIJING")
    for (i <- 0 until (b.productArity)) {
      println(b.productElement(i))
    }
    b._1
    b._2
    b._3
    // 第三种定义方式
    val t0 = 0 -> "zero"
    val t1 = 1 -> "one"
    val t2 = 2 -> "two"
    val t3 = 3 -> "three" -> "thrid"
    t3._1._1
    t3._1._2
    t3._2
    "a" -> "b"
    // 对偶Tuple/元组：只有2个元素的tuple叫做对偶
    val t = ("PK", "Flink")
    t.swap
  }
}