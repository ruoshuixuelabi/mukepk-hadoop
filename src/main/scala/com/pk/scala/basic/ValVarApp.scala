package com.pk.scala.basic

/**
  * val 名称[:类型] = xyz
  *     赋值后不可变
  *
  * var 名称[:类型] = xyz
  *     赋值后是可以改变
  *
  *
  * 最佳实践： 优先使用val
  */
object ValVarApp {

  def main(args: Array[String]): Unit = {

    class User{
      var name = "PK哥"
    }

    /**
      * 修饰的是一个引用
      * 父类引用指向子类对象   Animal animal = new Dog();
      * val 修饰的引用中指向的对象的属性是可以改的
      */
    val user = new User()
    user.name = "PK哥哥"
    println(user.name)
//    user = null

    var user2 = new User()
    user2.name = "大PK哥哥"
    println(user2.name)
    user2 = null
    println(user2.name) // NullPointerException ==> NPE
  }

  def test01(): Unit ={

    /**
      * 名称： money
      * :
      * 类型： Int
      */
    val money:Int = 100
    //    money = 105

    var name:String = "PK哥"
    name = "PK哥哥"
    println(name)

    /**
      * 在大部分场景，数据类型是可以不用显示的写出来的
      * 因为Scala具备类型推导的能力
      */
    val age = 30
  }

}
