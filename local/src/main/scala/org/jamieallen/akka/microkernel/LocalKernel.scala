package org.jamieallen.akka.microkernel

import akka.actor.{ Actor, ActorSystem, Props }
import akka.kernel.Bootable
import akka.routing.FromConfig
import com.typesafe.config.ConfigFactory
import akka.actor.ActorLogging

class Hello extends Actor with ActorLogging {
  def receive = {
    case x => log.warning("Received {} at {}", x, self)
  }
}

class LocalKernel extends Bootable {
  val sys1 = ActorSystem("sys1", ConfigFactory.load)

  def startup = {
    val hello = sys1.actorOf(Props[Hello].withRouter(FromConfig), "hello")
    for (i <- 1 to 10) hello ! "hello remote!"
  }

  def shutdown = {
    sys1.shutdown()
  }
}