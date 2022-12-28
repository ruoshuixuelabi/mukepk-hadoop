package com.pk.scala


/**
  * 在scala中可以为每个包定义一个同名的包对象
  * 可以定义一些方法，属性
  *
  * 在对应包下所有的class和object都可以直接访问
  */
package object oo {


  def dataFrame2HBase(dataFrame:String) = {
    println("将DF的数据保存到HBase中...." + dataFrame)
  }

}
