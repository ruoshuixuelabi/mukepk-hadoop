package com.pk.scala.implicitconversion

import java.io.File

/**
  * 封装了用到的所有的隐式转换函数
  */
object ImplicitAspect {

  /**
    * implicit  2 = to  定义隐式转换函数
    *
    * implicit def a2B(a:A):B = new B(a)
    */
  implicit def man2SuperMan(man:Man):SuperMan = new SuperMan(man.name)

  implicit def file2RichFile(file:File):RichFile = new RichFile(file)

}
