package com.pk.scala.generic

object GenericApp03 {
  def main(args: Array[String]): Unit = {
    val s1 = new Student2("pk", 31)
    val s2 = new Student2("zhangsan", 32)

    // 通过这个隐式转换将普通的Student2(没有实现Comparable)转成具有Ordered功能的Student2
    implicit def student2Ordered(student: Student2) = new Ordered[Student2] {
      override def compare(that: Student2): Int = student.age - that.age
    }

    println(new MaxValueOrdered(s1, s2).compare)
  }



  // <% 视图界定  int2Integer
  class MaxValue2[T <% Comparable[T]] (a:T, b:T) {
    def compare = if(a.compareTo(b)>0) a else b
  }

  class MaxValueOrdered[T <% Ordered[T]] (a:T, b:T) {
    def compare = if(a.compareTo(b)>0) a else b
  }
}

class Student(val name:String, val age:Int) extends Ordered[Student] {
  override def compare(that: Student): Int = this.age - that.age

  override def toString: String = this.name + "\t" + this.age
}

class Student2(val name:String, val age:Int) {

  override def toString: String = this.name + "\t" + this.age
}

