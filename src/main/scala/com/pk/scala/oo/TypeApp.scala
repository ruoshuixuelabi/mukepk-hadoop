package com.pk.scala.oo

object TypeApp {

  def main(args: Array[String]): Unit = {

//    val pig = new Pig
//    println(pig.isInstanceOf[Pig])
//    println(pig.isInstanceOf[Animal])
//    println(pig.isInstanceOf[Object])

//    val animal = new Animal
//    println(animal.isInstanceOf[Pig])
//    println(animal.isInstanceOf[Animal])
//    println(animal.isInstanceOf[Object])
//    println(animal.isInstanceOf[AnyRef])
//    println(animal.isInstanceOf[Any])


//    val pig = new Pig
//    val isPig = pig.isInstanceOf[Pig]
//    if(isPig) {
//      println(pig.asInstanceOf[Pig])
//    }

    // 获取类的Class对象
//    val pig = new Pig
//    println(pig.getClass)
//
//    println(classOf[Pig] == pig.getClass)


    // 定义一个新的类型  ==> 起了个别名

    type S = String
    val name:S = "PK"


    def test():S = "spark"

    println(name)
    println(test())
  }

}


class Animal {
  override def toString: String = "这是一个动物..."
}

class Pig extends Animal {
  override def toString: String = "这是一只猪..."
}