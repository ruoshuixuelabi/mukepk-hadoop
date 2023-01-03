package com.pk.scala.oo

object AppApp extends App {
  util.Properties.setProp("scala.time", "true")
  println("PK哥，你好....")
  Thread.sleep(3000)

  def foo(): Unit = {
    println("foo")
  }

  foo()
}