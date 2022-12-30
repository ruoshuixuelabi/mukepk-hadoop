package com.pk.scala.akka

import akka.actor.{Actor, ActorRef, ActorSystem, Props}
import com.typesafe.config.ConfigFactory

import scala.collection.mutable
import scala.concurrent.duration._
import scala.language.postfixOps


class NameNode extends Actor{


  val id2workers = new mutable.HashMap[String, WorkerInfo]()

  override def preStart(): Unit = {
    import context.dispatcher

    context.system.scheduler.schedule(
      0 milliseconds,
      10000 milliseconds,
      self,
      CheckTimeOutWorker
    )
  }


  // 接收消息
  override def receive: Receive = {

    case CheckTimeOutWorker => {
      val current = System.currentTimeMillis()
      val deadWorkers = id2workers.values.filter(x => {
        current - x.lastUpdateTime > 10000
      })

      deadWorkers.foreach(x => {
        id2workers -= x.id
      })

      println(s"当前活的Worker数量：${id2workers.size}")

    }

    // worker发送过来的消息
    case RegisterWorker(id, memory, cores) => {
      println(s"Master接收到Worker[$id] 发送过来的注册消息...")

      val workerInfo = new WorkerInfo(id, memory, cores)
      println(workerInfo)
      id2workers(id) = workerInfo

      sender() ! RegisteredWorker
    }

    case HeartBeat(workerId) => {
      println(s"接收到[$workerId]发送过来的心跳消息...")
      if(id2workers.contains(workerId)) {
        val workerInfo = id2workers(workerId)

        // 更新最近一次心跳时间
        workerInfo.lastUpdateTime = System.currentTimeMillis()
      }
    }

  }
}

object NameNode {
  def main(args: Array[String]): Unit = {

    val host = "localhost"
    val port = 9527
    val conf = ConfigFactory.parseString(
      s"""
        |
        |akka.loglevel=error
        |akka.actor.provider="akka.remote.RemoteActorRefProvider"
        |akka.remote.netty.tcp.hostname="$host"
        |akka.remote.netty.tcp.port="$port"
        |
      """.stripMargin)
    val actorSystem = ActorSystem("MASTER_ACTOR_SYSTEM", conf)

    val masterActor = actorSystem.actorOf(Props[NameNode], "MASTER_ACTOR")

//    masterActor ! "hello"
//    masterActor ! "world"
  }
}
