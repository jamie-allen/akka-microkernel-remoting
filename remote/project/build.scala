import sbt._
import Keys._
import akka.sbt.AkkaKernelPlugin
import akka.sbt.AkkaKernelPlugin.{ Dist, outputDirectory, distJvmOptions}
 
object HelloKernelBuild extends Build {
  val Organization = "org.jamieallen"
  val Version      = "2.1.4"
  val ScalaVersion = "2.10.1"
 
  lazy val HelloKernel = Project(
    id = "microkernel-remoting-remote",
    base = file("."),
    settings = defaultSettings ++ AkkaKernelPlugin.distSettings ++ Seq(
      libraryDependencies ++= Dependencies.microkernelRemoting,
      distJvmOptions in Dist := "-Xms256M -Xmx1024M",
      outputDirectory in Dist := file("target/microkernel-remoting-remote-dist")
    )
  )
 
  lazy val buildSettings = Defaults.defaultSettings ++ Seq(
    organization := Organization,
    version      := Version,
    scalaVersion := ScalaVersion,
    crossPaths   := false,
    organizationName := "Jamie Allen",
    organizationHomepage := Some(url("http://www.typesafe.com"))
  )
  
  lazy val defaultSettings = buildSettings ++ Seq(
    resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/",
 
    // compile options
    scalacOptions ++= Seq("-encoding", "UTF-8", "-deprecation", "-unchecked"),
    javacOptions  ++= Seq("-Xlint:unchecked", "-Xlint:deprecation")
 
  )
}
 
object Dependencies {
  import Dependency._
 
  val microkernelRemoting = Seq(
    akkaActor, akkaRemote, akkaKernel, akkaSlf4j, logback
  )
}
 
object Dependency {
  // Versions
  object V {
    val Akka      = "2.1.4"
  }
 
  val akkaActor  = "com.typesafe.akka" %% "akka-actor"     % V.Akka
  val akkaRemote = "com.typesafe.akka" %% "akka-remote"    % V.Akka
  val akkaKernel = "com.typesafe.akka" %% "akka-kernel"    % V.Akka
  val akkaSlf4j  = "com.typesafe.akka" %% "akka-slf4j"     % V.Akka
  val logback    = "ch.qos.logback"    % "logback-classic" % "1.0.0"
}
