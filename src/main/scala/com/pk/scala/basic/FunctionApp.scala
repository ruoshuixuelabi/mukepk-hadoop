package com.pk.scala.basic

object FunctionApp {

  def main(args: Array[String]): Unit = {
    /**
     * 可变参数  变长参数 *****
     *
     * 必须是放在最后一个参数的位置
     */
    // 1 2 3 4 5 6
    def sum2(x: Int, nums: Int*) = {
    }

    def sum(nums: Int*) = {
      var res = 0;
      for (num <- nums) {
        res += num
      }
      res
    }

    println(sum(1, 2, 3))
    println(sum(1 to 100: _*))
    val array = Array("Hadoop", "Hive", "Spark", "Flink")

    def printCourses(courses: String*): Unit = {
      courses.foreach(println)
    }

    printCourses(array: _*)
  }

  /**
   * 命名参数   会用即可
   */
  def namedArg(): Unit = {
    def speed(distance: Float, time: Float) = {
      distance / time
    }

    println(speed(100, 10))
    println(speed(distance = 100, time = 10))
    println(speed(time = 10, distance = 100))
  }

  /**
   * 默认参数
   */
  def defaultValue(): Unit = {
    def sayWorld(name: String = "PK哥"): Unit = {
      println(s"你好：$name")
    }

    //    sayWorld("大PK哥")
    def loadConf(conf: String = "spark-default.conf"): Unit = {
      println(conf)
    }

    loadConf("spark-pk.conf")
  }

  /**
   * 没有返回值的
   */
  def sayHello(): Unit = {
    println("hello....pk....")
  }

  /**
   * 有返回值的
   */
  def test02(): Unit = {
    def add(x: Int, y: Int) = {
      x + y // 方法体中最后一行表示返回值，并不需要显示的调用return关键字
    }

    println(add(3, 5))

    def three = 1 + 2 // 定义

    println(three) // three方法没有入参，在调用时可以省略括号
  }

  def test01(): Unit = {
    // ==> 抽象、抽取出来，形成 方法， 以后在用的时候就可以直接调用了...
    val a = 10
    val b = 20
    val oper = "-"
    if (oper == "+") {
      println(a + b)
    } else if (oper == "-") {
      println(a - b)
    } else if (oper == "*") {
      println(a * b)
    }
  }
}