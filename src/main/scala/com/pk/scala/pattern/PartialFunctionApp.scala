package com.pk.scala.pattern

/**
  * 偏函数
  *
  * 偏爱，这地方有你感兴趣的东西
  *
  *
  * trait PartialFunction[-A, +B]
  *
  * 被包在花括号内的没有match的一组case语句
  *
  */
object PartialFunctionApp {

  def main(args: Array[String]): Unit = {
    test05()
  }

  def test05(): Unit ={
    val l = List(1,2,3,4,"abc")

    l.collect{
      case i:Int => i + 10
    }.foreach(println)
  }

  def test04(): Unit ={
    val l = List(1,2,3,4,"abc")

    val add = new PartialFunction[Any, Int] {
      // 只对返回值为true的元素进行处理，false不care
      override def isDefinedAt(x: Any): Boolean = {
        if(x.isInstanceOf[Int]) true else false
      }

      override def apply(v1: Any): Int = v1.asInstanceOf[Int] + 10
    }

    // 使用collect调用偏函数
    l.collect(add).foreach(println)


  }

  /**
    * List(1,2,3,4,"abc")  数值类型+10  字符串类型抛弃
    *
    * 分析：
    * 1）判断是否是数值类型
    * 2）Any类型要转成数值类型
    * 3）数值类型的值 + 10
    *
    */
  def test03(): Unit = {
    val l = List(1,2,3,4,"abc")

    def f1(n:Any) = n.isInstanceOf[Int]
    def f2(n:Any) = n.asInstanceOf[Int]
    def f3(n:Int) = n + 10

    l.filter(f1).map(f2).map(f3).foreach(println)

  }

  def test02(): Unit = {

    def sayChinese2:PartialFunction[String,String] = {
      case "Aoi Sala" => "仓老师..."
      case "YuiHatano" => "波老师..."
      case "Rola Takizawa" => "龙老师..."
      case _ => "大数据启蒙老师....哈哈哈哈"
    }

    println(sayChinese2("PK"))
  }

  def test01(): Unit = {

    def sayChinese(name:String) = name match {
      case "Aoi Sala" => println("仓老师...")
      case "YuiHatano" => println("波老师...")
      case "Rola Takizawa" => println("龙老师...")
      case _ => println("大数据启蒙老师....哈哈哈哈")
    }

    sayChinese("Aoi Sala")

  }

}
