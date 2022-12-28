package com.pk.scala.generic

object GenericApp02 {


  def main(args: Array[String]): Unit = {
    pkTest03(new Child)
  }

  // >: 下限  和Java太一样，传递数据时，什么都可以  ==> 反编译出来看看 这是为什么呢？
  def pkTest03[T >: User](t: T): Unit = {
    println(t)
  }

  // <: 上限
  def pkTest02[T <: User](t: T): Unit = {
    println(t)
  }

  def pkTest01[T](t: T): Unit = {
    println(t)
  }
}

class Person
class User extends Person
class Child extends User
