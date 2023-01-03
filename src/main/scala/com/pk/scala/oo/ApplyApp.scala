package com.pk.scala.oo

/**
 * Java中static 但是Scala是没有这static概念
 *
 * class 类名    需要new出来的
 * <p>
 * object        不需要new的，可以直接调用
 * <p>
 * 封装一些工具类  可以使用object
 */
object ApplyApp {
  def main(args: Array[String]): Unit = {
    //    println(Timer.inr())
    //    println(Timer.inr())
    //    println(Timer.inr())
    //    ApplyTest.static
    //    for(i <- 1 to(10)) {
    //      ApplyTest.inc
    //    }
    //    println(ApplyTest.cnt)
    //    val test = new ApplyTest()
    //    test.test()
    val a = ApplyTest() //  object()  ==> object apply
    a.test()

    val b = new ApplyTest // new class
    println(b) // toString
    println(b()) // 对象() ==> class apply

    /**
     * 伴生类  和  伴生对象   同名的class和object
     * 类名()  ==> object apply     用的非常非常非常非常非常多的
     * ==> new class
     * 对象()  ==> class apply      一般情况下很少用的
     */
  }
}

object Timer {
  var cnt = 0

  def inr() = {
    cnt += 1
    cnt
  }
}

/**
 * 同名，但是一个是object 一个是class
 * <p>
 * 那么这两个就互为伴生类  伴生对象
 */
object ApplyTest {
  /**
   * object中的apply 里面 new 类
   */
  def apply() = {
    println("---ApplyTest object apply---")
    new ApplyTest
  }

  var cnt = 0

  def static: Unit = {
    println("I am as static method...")
  }

  def inc = {
    cnt += 1
  }
}

class ApplyTest {
  def apply() = {
    "---ApplyTest class apply---"
  }

  def test(): Unit = {
    println("---ApplyTest.test---")
  }
}