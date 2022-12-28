package com.pk.scala.hof

import scala.io.Source


object FunctionApp {

  def main(args: Array[String]): Unit = {

    val l = List(1,2,3,4,5,6,7)
    l.find(_> 3).foreach(println)



  }

  /**
    * 请使用scala算子完成wc统计
    * 只取排名靠前的2个
    */

  def wc(): Unit = {
    Source.fromFile("data/wc.data","utf-8")
      .getLines()
      .toList
      .flatMap(_.split(" "))
      .map(_.trim)
      .groupBy(x => x)
      .mapValues(_.size)
      .toList
      .sortBy(-_._2)
      .take(2)
  }

  def test07(): Unit = {
    val l = List(1,2,3,4,5,6,7,8)

    /**
      * map：对传入的每个元素都进行映射(一进一出)
      */
    l.map((x:Int) => x * 2)
    l.map(_ * 2) // _表示l中的每个元素

    val courses = List("Hadoop","Spark","Flink")

    def lower(course:String) = {
      course.toLowerCase
    }

    courses.map(_.toLowerCase)

    // age + 2
    val array = List(Array(("PK",30)), Array(("张三",18)))
    array.map(x => x.map(y => (y._1, y._2 + 2)))


    /**
      * filter：对传入的元素进行过滤，返回结果为true的元素
      */

    l.map(_ * 2).filter(_ > 8)
    l.filter(_ > 3).filter(_ % 2 == 0)
    l.filter(x => x>3 && x % 2 == 0)

    /**
      * foreach
      */
    l.map(_ * 2).foreach(x=>println(x))

    /**
      * map + filter
      *
      * 结果只要：数值类型 * 2
      *
      * 分析：
      * 第一步：取出数值类型
      * 第二步：对数值类型的值 * 2
      */
    val list = List(30, "PK", 50, true, 10, 20)
    list.filter(_.isInstanceOf[Int]).map(_.asInstanceOf[Int] * 2)


    /**
      * flatten：扁平化
      */
    val l2 = List(List(1,2),List(3,4),List(5,6))
    l2.flatten

    /**
      * flatMap: flatten + map
      * 这Spark、Flink开发中都有
      */
    l2.flatten.map(_ * 2)
    l2.flatMap(_.map(_ * 2))
    l2.map(_.map(_ * 2))


    /**
      * reduce相关
      *
      * def reduce(op: (A1, A1) => A1): A1 = reduceLeft(op)
      * 两两操作
      */
    l.reduce(_ - _)
    l.reduceLeft((x,y) => {
      println(s"$x      $y")
      x - y
    })

    /**
      * fold
      *
      * def fold(z: A1)(op: (A1, A1) => A1): A1 = foldLeft(z)(op)
      *
      * currying
      * 第一个参数：初始值
      * 第二个参数：
      *
      */
    l.fold(10)(_*_)
    l.foldLeft(0)(_+_)
    l.foldRight(0)(_+_)

    l./:(0)(_+_)  // foldLeft
    (l:\0)(_+_)  // foldRight


    /**
      * zip：合并/拉链
      *
      * 拉相同位置的元素，如果元素个数不对等，只会拉对等的元素，多的略掉
      */
    val a = List(1,2,3,4)
    val b = List("A","B","C","D")
    a.zip(b)
    val c = List("A","B","C","D","E")
    a.zipAll(c, "-", "~")
    b.zipWithIndex

    /**
      * groupBy 自定义分组
      */
    val l3 = List(30,5,7,60,1,20)
    l.groupBy(x => if(x%2==0) "偶数" else "奇数")

    val arr = Array(("a",100),("b",10),("a",190),("d",10))
    arr.groupBy(x => x._1)

    /**
      * 统计每个单词出现的次数
      * 分析
      * 1）flatMap ： split(" ")
      * 2）groupBy(word)
      */
    val words = List("hello pk","hello pk","hello world")
    //Map(hello -> List(hello, hello, hello), pk -> List(pk, pk), world -> List(world))
    words.flatMap(_.split(" ")).groupBy(x=>x).map(x => (x._1, x._2.size))

    /**
      * mapValues  一定是使用在kv的数据结构上
      */
    words.flatMap(_.split(" ")).groupBy(x=>x).mapValues(_.size)

    /**
      * 排序
      *
      * sorted: 字符串按字典序  数值类型按升序
      * sortBy: 自定义排序
      * sortWith:
      */
    val l4 = List("c","b","a")
    l4.sorted

    val l5 = List(30,50,70,60,10,20)
    l5.sortBy(x => x)(Ordering.Int.reverse)

    val l6 = List("hello","world","hello","aaa","b","zzzz")
    l6.sortBy(x => (x.length, x))


    class Person(val age:Int, val name:String) {
      override def toString: String = s"$age , $name"
    }

    val persons = List(new Person(10,"A"),new Person(30,"D"),new Person(10,"B"),new Person(8,"C"))
    persons.sortBy(x => (x.age, x.name))(Ordering.Tuple2(Ordering.Int, Ordering.String.reverse))

    l5.sortWith((x,y) => x < y)
  }

  def test06(): Unit = {
    val array = Array(1,2,3,4,5,6,7,8)

    // foreach

    def foreach(array:Array[Int], op:Int => Unit) = {
      for(ele <- array) {
        op(ele)
      }
    }
    //    foreach(array, println)

    def filter(array:Array[Int], op:Int => Boolean) = {
      for (ele <- array if op(ele)) yield ele
    }

    //    filter(array, _ > 6).foreach(println)

    /**
      * map关于地图的意思
      * 一一映射
      * 1 2 3 4 5 6 7 8
      * ==> * 2
      * 2 4 ......
      */
    def map(array:Array[Int], op:Int => Int) = {
      for(ele <- array) yield op(ele)
    }

    // map(array, x => x * x * x).foreach(println)


    /**
      * op:(c1, c2)
      * c1: 上次聚合的结果
      * c2: 本次参与聚合的元素
      *
      * 两两干嘛
      *
      * 建议：千万不能做成是API调用工程师
      * 面试题：不允许使用已有的API，请手工实现
      */
    def reduce(array:Array[Int], op:(Int,Int) => Int) = {
      var first = array(0)
      for(i <- 1 until(array.length)) {
        /**
          * index: 0 1
          * c1 2
          * c1 3
          */
        first = op(first, array(i))
      }
      first
    }

    println(reduce(array, (x,y) => {
      println(s"$x ==== $y")
      x + y
    }))
  }

  /**
    * 科里化  currying
    *
    * 用法：Spark SQL
    */
  def test05(): Unit = {

    def sum(a:Int, b:Int) = a + b
    println(sum(3,4))

    def sum2(a:Int)(b:Int) = a + b
    println(sum2(3)(4))

    def pkEq(x:String)(y:String) = {
      // 思考题： Scala竟然能直接使用 == 来判断？ Java里面可以吗？
      x.toLowerCase == y.toLowerCase
    }

    println(pkEq("pk")("PK"))
    println(pkEq("pk")("大PK"))
  }


  def test04(): Unit = {
    val a = () => println("PK哥")
    def foo(f:() => Unit): Unit = {
      f()
    }

    foo(() => println("PK哥"))
    foo(a)


    def foo2(add:(Int,Int) => Int) = {
      println(add(10, 20))
    }

    foo2((a:Int, b:Int) => a + b)
    foo2((a, b) => a + b)
    foo2(_ + _)
    foo2(_ * _)

    /**
      * 第一个_ 表示第一个参数
      * 第二个_ 表示第一个参数
      * 每个参数只能使用一次 ==> 往后面放下
      */



    def calculator(a:Int, b:Int, operator:(Int, Int) => Int):Int = {
      operator(a, b)
    }

    def add(x:Int, y:Int) = {
      x + y
    }

    def multi(x:Int, y:Int) = {
      x * y
    }

    // 函数作为参数
    println(calculator(2, 3, add))
    println(calculator(2, 3, multi))

    // 匿名函数作为参数
    println(calculator(2, 3, (x: Int, y: Int) => x - y))
    println(calculator(6, 3, _/_))
  }

  def test03(): Unit = {

    def sayHello(name:String): Unit = {
      println(s"Hello: $name")
    }

    // 将方法赋值给一个函数： 空格 下划线
    val sayHelloFunc = sayHello _
    sayHelloFunc("PK哥")


    def add(a:Int, b:Int) = a + b
    val add1 = add _
    println(add1(2, 4))

    val add2:(Int, Int) => Int = add

    // 把函数当入参了。 参数是函数的函数叫做高阶函数
    def foo(op:(Int,Int) => Int) = {
      op(10, 20)
    }

    println(foo(add2))

    def foo2(op:() => Unit) = {
      op()
    }

    def m = println("Hello")

    foo2(m _)


    // _是方法和函数的转换桥梁
  }

  /**
    * Scala中函数的定义方式二
    *
    * val/var 函数名称:(入参类型) => 返回值类型 = (入参的引用) => {函数体}
    *
    *
    * 经典考题：谈谈你对Scala中函数vs方法
    * 1）定义：
    *   方法：def
    *   函数：=>
    * 2）方法本质上是一个特殊的函数
    * 3）函数是一等公民，函数可以当做值来传递，方法的参数、返回值都可以是函数 *****
    *
    */
  def test02(): Unit = {
    // f2: (Int, Int) => Int = $Lambda$1050/1622066826@25a7fedf
    val f2:(Int, Int) => Int = (a:Int, b:Int) => { a + b }
    println(f2(3, 9))

    val f3:(Int, Int) => Int = (a, b) => { a + b }
    println(f3(3, 19))
  }

  /**
    * Scala中函数的定义方式一
    * val/var 函数名称 =  (参数列表) => {函数体}
    */
  def test01(): Unit = {

    /**
      * f1: (Int, Int) => Int = $Lambda$1026/1445758842@67001148
      *
      * f1是函数名
      * (Int, Int) 是函数的入参
      * Int：返回值
      */
    val f1 = (a:Int, b:Int) => { a + b }


    println(f1(4, 6))
  }

}
