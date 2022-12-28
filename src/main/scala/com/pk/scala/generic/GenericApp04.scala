package com.pk.scala.generic

/**
  * 了解即可
  *
  *
  * 逆变(- 子到父)  协变(+  父到子)
  */
object GenericApp04 {
  def main(args: Array[String]): Unit = {
    val test: Test[User] = new Test[Person]
    println(test)
  }
}

class Test[-User]

