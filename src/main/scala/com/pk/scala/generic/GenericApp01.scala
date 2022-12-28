package com.pk.scala.generic

import com.pk.scala.generic.CupEnum.CupEnum

object GenericApp01 {
  def main(args: Array[String]): Unit = {

    // 实例化时需要传入具体的类型
    val mm1 = new MM[Int,CupEnum, Int](90, CupEnum.D, 175)
    val mm2 = new MM[Int,CupEnum, Int](60, CupEnum.E, 160)
    val mm3 = new MM[Int,CupEnum, Int](80, CupEnum.B, 180)

    println(mm1)

  }

}


object CupEnum extends Enumeration {
  type CupEnum = Value
  val B, C, D, E, F = Value
}

abstract class Msg[T](content:T)

class WebChatMsg[String](content:String) extends Msg[String](content)

class DigitMsg[Int](content:Int) extends Msg(content)

// 定义一个泛型类：定义时需要的参数类型可以是泛型
class MM[A, B, C](val face:A, val cup:B, val height:C) {
  override def toString: String = face + "\t" + cup  + "\t" + height
}

