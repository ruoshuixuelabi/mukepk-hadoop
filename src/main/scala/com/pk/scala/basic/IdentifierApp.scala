package com.pk.scala.basic

/**
 * <p>
 * 标识符(不要死记硬背)：最根本的原则是【见名知意】
 * <p>
 * 最佳实践：起名 那就是用单词即可，多个单词之间请采用驼峰标识
 * <p>
 * 一般情况下
 * 在代码层面，不建议用_或者-，而应该是采用驼峰标识  UserService userService = new....
 * 在SQL层面，字段名一般是采用_，而不是驼峰
 * <p>
 * 只要是你起的类名、方法名、变量等  只要是涉及到起名字的东西
 */
object IdentifierApp {

  def main(args: Array[String]): Unit = {
    /**
     * 字母或者下划线开头：后面可以是数字、字母、下划线
     */
    val name123age_ = "PK哥"
    val _ab = ""
    val Int = "" // 稍微注意下，这个是可以这么定义的
    var _: String = "" // 在scala中  _是一个非常非常重要的小玩意
    /**
     * 对于特殊的Scala中已定义的关键字，如何你真的要使用的话，请使用飘
     */
    val `try` = "try"
    println(`try`)
    //    val +-*/!#23 = 0
  }
}