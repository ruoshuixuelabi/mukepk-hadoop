package com.pk.scala.akka

import java.util.UUID
import akka.actor.{Actor, ActorSelection, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.concurrent.duration._
import scala.language.postfixOps

class DataNode extends Actor {

  var masterRef:ActorSelection = _
  val WORKER_ID = UUID.randomUUID().toString

  // 生命周期方法
  override def preStart(): Unit = {

    masterRef = context.actorSelection("akka.tcp://MASTER_ACTOR_SYSTEM@localhost:9527/user/MASTER_ACTOR")

    masterRef ! RegisterWorker(WORKER_ID, 4096, 8)
  }

  override def receive: Receive = {
    case RegisteredWorker => {
      println("Worker接收到Master返回给Worker的注册成功的消息....")

      import context.dispatcher

      context.system.scheduler.schedule(
        0 milliseconds,
        5000 milliseconds,
        self,
        SendHeartBeat
      )

    }

    case SendHeartBeat => {
      masterRef ! HeartBeat(WORKER_ID)
    }
  }
}

object DataNode{
  def main(args: Array[String]): Unit = {

      val host = "localhost"
      val port = 9530
      val conf = ConfigFactory.parseString(
        s"""
           |
        |akka.loglevel=ERROR
           |akka.actor.provider="akka.remote.RemoteActorRefProvider"
           |akka.remote.netty.tcp.hostname="$host"
           |akka.remote.netty.tcp.port="$port"
           |
      """.stripMargin)
      val actorSystem = ActorSystem("WORKER_ACTOR_SYSTEM", conf)

      val workerActor = actorSystem.actorOf(Props[DataNode], "WORKER_ACTOR")

//      workerActor ! "hi"
  }
}
