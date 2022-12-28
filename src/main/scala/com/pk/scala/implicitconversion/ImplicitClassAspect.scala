package com.pk.scala.implicitconversion

import java.io.File
import java.time.LocalDate

import scala.io.Source

object ImplicitClassAspect {

  implicit class RichDate(day:Int) {

    def days(when:String) = {
      if("ago" == when) {
        LocalDate.now().plusDays(-day).toString
      } else if("later" == when) {
        LocalDate.now().plusDays(day).toString
      } else {
        LocalDate.now().toString
      }
    }
  }

  implicit class FileEnhance(file:File) {
    def read() = Source.fromFile(file.getPath).mkString
  }

  implicit class Calulator(x:Int) {
    def add(a:Int) = x + a
  }
}
