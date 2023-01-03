package com.pk.scala.oo

import scala.beans.BeanProperty

object SimpleObjectApp {

  def main(args: Array[String]): Unit = {
    // 初始化一个对象
    val user = new User
    user.name = "PK哥"
    //    user.name_$eq("大PK哥")
    //    user.setName("大大PK哥")
    println(user.name + "==>" + user.age)
    // 对象的方法调用
    user.printInfo()
  }
}

/**
 * 在scala中定义类
 * class 类名 {
 * 属性
 * 方法
 * }
 */
class User {
  // _在scala中有一个功能：占位符  必须要明确数据类型 占位符不允许使用val
  var name: String = _
  val age: Int = 30
  private val money = 100000000L

  def eat(): Unit = {
    println(s"....$name ... eat")
  }

  def printInfo(): Unit = {
    println(s"$name 有： $money 钱")
  }
}