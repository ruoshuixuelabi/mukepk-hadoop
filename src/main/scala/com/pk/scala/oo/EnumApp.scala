package com.pk.scala.oo

/**
  * 枚举类型
  *
  * Enumeration
  */
object EnumApp {

  def main(args: Array[String]): Unit = {


    println(WeekDay.isWorkingDay(WeekDay.Mon))
  }

}

object WeekDay extends Enumeration {
  type WeekDay = Value

  val Mon, Tue, Wed, Thu, Fri, Sat, Sun = Value


  /**
    * 判断是否是工作日
    */
  def isWorkingDay(d: WeekDay) = ! (d == Sat || d == Sun)
}
