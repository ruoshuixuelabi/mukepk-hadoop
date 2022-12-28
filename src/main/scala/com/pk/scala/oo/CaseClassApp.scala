package com.pk.scala.oo

/**
  * 样例类
  * case class
  *
  * 使用场景：
  * 1）模式匹配   后续章节单独介绍
  * 2) Spark SQL  大量的使用
  *
  *
  * 经典面试题：
  * case class  vs  class
  * 1）toString  equals  hashcode重写
  * 2）不用new
  * 3）默认实现了序列化接口
  *
  * case class  vs case object
  * 1) case class修饰的类，必须要有参数列表
  * 2) case object修饰的对象，必须不能有参数列表
  */
object CaseClassApp {
  def main(args: Array[String]): Unit = {
//    println(Dog("旺财").name)


    val per1 = new Per("pk", 31)
    val per2 = new Per("pk", 31)
    println(per1 == per2)
    println(per1)  // com.pk.scala.oo.Per@3b088d51

    val per3 = Per2("pk", 31)
    val per4 = Per2("pk", 31)
    println(per3 == per4)
    println(per3)  // Per2(pk,31)
  }

}

case class Dog(name:String)

class Per(name:String, age:Int)
case class Per2(name:String, age:Int)
