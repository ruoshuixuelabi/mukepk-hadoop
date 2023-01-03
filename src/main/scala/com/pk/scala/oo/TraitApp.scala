package com.pk.scala.oo

/**
 * trait 特质   某种特征   interface
 * 可以有抽象的属性/方法，也可以有具体的属性/方法
 * 一个类可以混入多个trait  mixin
 */
object TraitApp {
  def main(args: Array[String]): Unit = {
    test04()
  }
  def test01(): Unit = {
    val staticMemoryManager = new StaticMemoryManager
    println(staticMemoryManager.maxOnHeapStorageMemory)
    println("--------")
    println(new UnifiedMemoryManager().maxOnHeapStorageMemory)
  }
  def test02(): Unit = {
    val smm = new StaticMemoryManager2
    println(smm.exception)
  }
  /**
   * Self-types 自身类型
   */
  def test04(): Unit = {
    new StuDAO().login(new Stu("PK", 30))
  }
  class Stu(val name: String, val age: Int)
  trait DAO {
    def save(stu: Stu): Unit = {
      println(s"保存${stu.name}到数据库")
    }
  }
  trait StuApp {
    _: DAO =>
    def login(stu: Stu): Unit = {
      println(s"${stu.name}登录成功...")
      this.save(stu)
    }
  }
  class StuDAO extends StuApp with DAO
  // 动态混入
  def test03(): Unit = {
    class PK
    val pk = new PK with Print {
      override def printInfo(): Unit = {
        println("---------")
      }
    }
    pk.printInfo()
  }
  trait Print {
    def printInfo()
  }
  trait AAA {
    def info(): Unit = {
      println("AAA")
    }
  }
  trait BBB extends AAA {
    override def info(): Unit = {
      println("BBB")
    }
  }
  trait CCC extends AAA {
    override def info(): Unit = {
      println("CCC")
    }
  }
}
// 定义了一个trait
trait MemoryManager {
  println("-----MemoryManager------")
  val name: String
  def maxOnHeapStorageMemory: Long
}
class PKLogger {
  println("-----PKLogger------")
  def print(): Unit = {
    println("开始打印日志")
  }
}
trait PKException {
  println("-----PKException------")

  def exception: Exception
}

class StaticMemoryManager2 extends PKLogger with PKException with MemoryManager {
  println("-----StaticMemoryManager2 子类------")
  override val name: String = "静态内存管理"
  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存...")
    100L
  }
  override def exception: Exception = {
    new RuntimeException(s"$name 异常")
  }
}
// 一个类可以混入多个trait  extends  with
class StaticMemoryManager extends MemoryManager {
  println("-----StaticMemoryManager 子类------")
  override val name: String = "静态内存管理"
  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存...")
    100L
  }
}
class UnifiedMemoryManager extends MemoryManager {
  println("-----UnifiedMemoryManager 子类------")
  override val name: String = "统一内存管理"
  override def maxOnHeapStorageMemory: Long = {
    println(s"$name 获取内存...")
    200L
  }
}