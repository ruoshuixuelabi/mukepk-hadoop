package com.pk.scala.akka

class WorkerInfo(val id:String, val memory:Int, val cores:Int) {

  var lastUpdateTime:Long = _

  override def toString: String = s"WorkInfo($id, $memory, $cores)"

}
