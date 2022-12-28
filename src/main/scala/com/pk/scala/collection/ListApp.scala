package com.pk.scala.collection

/**
  * List中的数据是可以重复的
  */
object ListApp {

  def main(args: Array[String]): Unit = {

    val l6 = scala.collection.mutable.ListBuffer[Int]()

    val iterator = l6.iterator
    while(iterator.hasNext) {
      val it = iterator.next()
      println(it)
    }


    // 滑窗
    val l = List(30,50,70,-60,10,20)


    l.sortBy(x => x)


    /**
      * 30,50
      * 60,10
      */
    val iter = l.sliding(2,3)
    for(ele <- iter) {
      println(ele)
    }
  }

}
