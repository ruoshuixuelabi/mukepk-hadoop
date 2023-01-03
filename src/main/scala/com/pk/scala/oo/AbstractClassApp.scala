package com.pk.scala.oo

/**
 * 抽象类的使用
 *
 * 类的一个或者多个方法/属性没有完整的实现（只有定义，没有实现）
 * 使用abstract class来定义抽象类
 * 子类重写父类的属性或者方法时，override可以有(不是必须的)
 *
 * 抽象类不能直接new，而需要通过子类或者匿名子类的方式来完成初始化
 */
object AbstractClassApp {

  def main(args: Array[String]): Unit = {
    //    val student = new Student2()
    //    student.speak
    //    println(s" ${student.name}")
    val person = new Person2() {
      override def speak: Unit = println("匿名子类 speak")

      override val name: String = "大PK哥"
    }

    person.speak
    println(person.name)
  }

}

abstract class Person2 {
  def speak

  val name: String
}

class Student2 extends Person2 {
  def speak: Unit = {
    println("Student2 speak")
  }

  val name: String = "PK哥"
}