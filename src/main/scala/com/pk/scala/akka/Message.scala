package com.pk.scala.akka


case class RegisterWorker(id:String, memory:Int, cores:Int)

case object RegisteredWorker

case class HeartBeat(workerId:String) // worker ==> master
case object SendHeartBeat  // worker内部

case object CheckTimeOutWorker