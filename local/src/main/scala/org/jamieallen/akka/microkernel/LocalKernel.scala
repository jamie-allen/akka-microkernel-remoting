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

class LocalKernel extends Bootable {
  val config1 = ConfigFactory.parseString("""
			akka.actor.provider = akka.remote.RemoteActorRefProvider
			akka.remote.netty.hostname = "127.0.0.1"
			akka.remote.netty.port = 2553
			akka.actor.deployment./hello {
			  router = round-robin
			  nr-of-instances = 10
			  target.nodes = ["akka://sys1@127.0.0.1:2553","akka://sys2@127.0.0.1:2552"]
			}
	  """)

  val sys1 = ActorSystem("sys1", config1)

  def startup = {
    val hello = sys1.actorOf(Props[Hello].withRouter(FromConfig), "hello")
    for (i <- 1 to 10) hello ! "hello remote!"
  }

  def shutdown = {
    sys1.shutdown()
  }
}