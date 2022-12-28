package com.pk.scala.basic

/**
  * Scala中的String
  */
object DataTypeApp {

  def main(args: Array[String]): Unit = {

    /**
      * Unit: 用作不返回任何结果的方法    只有一个实例值 ()
      *
      * Null：只有一个实例值 null  可以给AnyRef赋值，不能给AnyVal赋值
      *
      * Nothing：数据类型层级的最底端，可以返回任何数据类型的子类型
      */
    var user = new User
    user = null

    def sayHello: Unit = {
      println("PK哥")
    }

    println(sayHello)

    def test():Nothing = {
      throw new Exception
    }

    test()

  }

  class User{}

  def test(): Unit ={


    val name:String = "PK哥"
    val age = 31

    /**
      * 面试题：为什么字符串拼接不建议采用+ ==> 字节码(JVM)
      */
    val info = "name = " + name + " , age = " + age
    //    println(info)

    // 字符串插值
    val info2 = s"name = $name ,  age = $age"
    println(info2)

    println(s"name = $name ,  age = $age - 2")
    println(s"name = $name ,  age = ${age - 2}")

    // 多行字符串
    val welcome = "你好呀"+
      "我是PK哥"
    println(welcome)


    val welcome2 =
      """
        |你好呀
        |我是PK哥
        |欢迎大家学习我的课程...
      """.stripMargin
    println(welcome2)

    val sql =
      """
        |select
        |e.empno,e.ename,e.deptno,d.dname
        |from
        |emp e join dept d
        |on e.deptno = d.deptno
      """.stripMargin

    println(sql)

    val money = 47382
    printf("格式化 %d", money)
  }

}
