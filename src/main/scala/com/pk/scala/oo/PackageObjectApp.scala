package com.pk.scala.oo

object PackageObjectApp {

  def main(args: Array[String]): Unit = {
    dataFrame2HBase("df1")

    new PackageObjectApp02().foo()
  }

}
