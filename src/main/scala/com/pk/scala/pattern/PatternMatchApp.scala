package com.pk.scala.pattern

import scala.io.StdIn
import scala.util.Random

/**
 * 模式匹配
 */
object PatternMatchApp {

  def main(args: Array[String]): Unit = {
    test10()
  }

  /**
   * 结合Spark来讲解模式匹配
   */
  def test10(): Unit = {
    case class SubmitTask(id: String, name: String)
    case class HeartBeat(time: Long)
    case object CheckTimeOutTask
    val inputs = Array(CheckTimeOutTask, HeartBeat(1000), SubmitTask("001", "Task001"))
    1.to(10).map(x => {
      inputs(Random.nextInt(inputs.length)) match {
        case CheckTimeOutTask => println("CheckTimeOutTask")
        case HeartBeat(time) => println(s"HeartBeat:$time")
        case SubmitTask(id, name) => println(s"SubmitTask: $id, $name")
      }
    })
  }

  /**
   * 对case class匹配
   *
   * 务必务必务必要掌握的
   */
  def test09(): Unit = {
    case class User(name: String, age: Int)
    val user = User("PK", 31)
    user match {
      case User(name, age) => println(name + "==>" + age)
    }
  }

  /**
   * 对class做匹配  了解
   */
  def test08(): Unit = {
    class Person(val name: String, val age: Int)
    object Person {
      def unapply(person: Person): Option[(String, Int)] = {
        if (null != person) {
          Some(person.name, person.age)
        } else {
          None
        }
      }
    }
    val person = new Person("PK", 31)
    person match {
      // 此处调用的是 Person 的伴生对象中的 unapply 方法
      case Person(name, age) => println(name + "," + age)
      case _ =>
    }
  }

  /**
   * 对List进行匹配
   *
   * 和Array部分是非常相似
   */
  def test07(): Unit = {
    def greeting(list: List[String]): Unit = {
      list match {
        case "zhangsan" :: Nil => println("Hi:zhangsan")
        case x :: y :: Nil => println(s"Hi: $x, $y")
        case "zhangsan" :: tail => println(s"Hi zhangsan and others..")
        case _ => println("hello....")
      }
    }

    //    greeting(List("zhangsan"))
    //    greeting(List("pk","spark"))
    //    greeting(List("zhangsan","pk","spark"))
    //    greeting(List("pk","zhangsan","spark"))
    val l = List(10, 20, 30, 40)
    l match {
      case a :: b :: c => {
        println(a)
        println(b)
        println(c)
      }
    }
  }

  /**
   * 对Tuple进行匹配
   */
  def test06(): Unit = {
    val infos = ("PK", 31)
    //    infos match {
    //      case (name, age) => {
    //        println(name)
    //        println(age)
    //      }
    //    }
    infos match {
      case (name: String, _) => println(name)
    }
  }

  /**
   * 对array进行匹配
   */
  def test05(): Unit = {
    def greeting(array: Array[String]): Unit = {
      array match {
        case Array("zhangsan") => println("Hi:zhangsan")
        case Array(x, y) => println(s"Hi: $x, $y")
        case Array("zhangsan", _*) => println(s"Hi zhangsan and others..")
        case _ => println("hello....")
      }
    }

    //    greeting(Array("zhangsan"))
    //    greeting(Array("pk","spark"))
    //    greeting(Array("zhangsan","pk","spark"))
    //    greeting(Array("pk","zhangsan","spark"))
    val array = Array(1, 2, 3, 4)
    array match {
      case Array(1, 2, other@_*) => println(other.toList)
    }
  }

  /**
   * 根据类型进行匹配
   */
  def test04(): Unit = {
    def matchType(obj: Any): Unit = {
      obj match {
        case x: Int => println("Int")
        case s: String => println("String")
        case m: Map[_, _] => println("Map")
        case _ => println("其他类型")
      }
    }

    //    matchType(1)
    //    matchType("PK")
    //    matchType(200L)
    //    matchType(Map("name"->"PK"))
    // >10:+10   字符串=>小写
    val array = Array(1, 20, 3, "PK", true)
    1.to(10).map(x => {
      array(Random.nextInt(array.length)) match {
        case a: Int if a > 10 => println(s"$a ==> ${a + 10}")
        case a: Int => println(s"$a ==> $a")
        case s: String => println(s"$s => ${s.toLowerCase}")
        case _ => println("不动...")
      }
    })
  }

  /**
   * 匹配时可以添加if
   */
  def test03(): Unit = {
    def judgeGrade(grade: String, name: String): Unit = {
      grade match {
        case "A" => println("非常棒...")
        case "B" => println("好")
        case "C" => println("一般般了")
        case _ if (name == "PK") => println("你是个相当不错的好孩子...")
        case _ => println("你需要加油了...")
      }
    }

    judgeGrade("A", "xiaowang")
    judgeGrade("D", "xiaozhang")
    judgeGrade("E", "PK")
  }

  /**
   * 根据内容、值进行匹配
   */
  def test02(): Unit = {
    val teachers = Array("Rola Takizawa", "YuiHatano", "Aoi Sala", "PK")

    1.to(10).map(x => {
      val teacher = teachers(Random.nextInt(teachers.length))
      println("teacher ==>" + teacher)
      teacher match {
        case "Aoi Sala" => println("仓老师...")
        case "YuiHatano" => println("波老师...")
        case "Rola Takizawa" => println("龙老师...")
        case _ => println("大数据启蒙老师....哈哈哈哈")
      }
    })
  }

  def test01(): Unit = {
    val a = 9
    val b = 3
    val op = StdIn.readLine("请输入运算符:")
    val res = op match {
      case "+" => a + b
      case "-" => a - b
      case "*" => a * b
      case "/" => {
        println("enter /")
        a / b
      }
      case _ => -1
    }
    println(s"结果是: $res")
  }
}
