package org.jamieallen.akka.microkernel

import akka.actor.{ Actor, ActorSystem, Props }
import akka.kernel.Bootable
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory
import akka.actor.ActorLogging

class Hello extends Actor with ActorLogging {
  def receive = {
    case x => log.warning("received {} at {}", x, self)
  }
}

class RemoteKernel extends Bootable {
  val config2 = ConfigFactory.parseString("""
			akka.actor.provider = akka.remote.RemoteActorRefProvider
			akka.remote.netty.hostname = "127.0.0.1"
			akka.remote.netty.port = 2552
	      """)

  val sys2 = ActorSystem("sys2", config2)

  def startup = {
    println("Started remote node!")
  }

  def shutdown = {
    sys2.shutdown()
  }
}