package com.pk.scala.implicitconversion

import java.io.File
import ImplicitClassAspect._
object ImplicitClassApp {

  def main(args: Array[String]): Unit = {
    val content = new File("data/wc.data").read
    println(content)
  }

}
