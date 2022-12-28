package com.pk.scala.oo

/**
  * 构造器
  */
object ConstructorExtendsApp {
  def main(args: Array[String]): Unit = {

//    val person = new Person("PK哥", 31, "M")
//    println(person.name + " : "
//      + person.age + " : "
//      + person.school + " : "
//      + person.gender)


    val student = new Student("PK哥", 31, "CS")
    println(student.toString)
//    println(student.name + " : "
//          + student.age + " : "
//          + student.school + " : "
//          + student.gender  + " : "
//        + student.major)
  }

}


/**
  * 继承  extends
  *
  * 是子类和父类之间的关系
  *
  * 子类是可以重写父类中的属性或者方法的，需要加上override关键字进行修饰
  *
  */
class Student(name:String, age:Int, val major:String) extends Person(name, age) {
  println("====Student enter=====")

  // 子类重写父类的属性
  override val school: String = "nudt"

  override def toString: String = s"$name, $age, $major, $school"


  println("====Student leave=====")

}

/**
  * 主构造器
  */
class Person(val name:String, val age:Int) {

  println("====Person enter=====")

  val school = "pku"
  var gender:String = _


  /**
    * 附属构造器
    *
    * 每个附属构造器首行必须调用已经存在的主构造器或者其他附属构造器
    */
  def this(name:String, age:Int, gender:String) {
    this(name, age)
    this.gender = gender
  }

  println("====Person leave=====")
}