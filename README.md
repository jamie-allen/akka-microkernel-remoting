# Akka Microkernel Remoting Example

## Overview
This is a project that creates has two nodes, local and remote, and allows the user to deploy two Akka applications in separate JVMs via Akka Microkernel.  

## Instructions
1. Download the <a href="http://typesafe.com/platform/runtime/akka/download">Akka distribution</a>.
2. Clone this project.
3. Run sbt on the remote project, and execute the dist command at the sbt prompt.
4. Copy the remote/target/microkernel-remoting-remote-dist/deploy/microkernel-remoting-remote-2.1.4 file to your Akka distribution
5. Start the remote node using this command from the folder where you downloaded and expanded the Akka distribution:
```
$ bin/akka org.jamieallen.akka.microkernel.RemoteKernel
```
You should start the remote node first, just so that it can be available when the local node tries to distribute work.
6. Run sbt on the local project, and execute the dist command at the sbt prompt.
7. Copy the local/target/microkernel-remoting-local-dist/deploy/microkernel-remoting-local-2.1.4 file to your Akka distribution
8. Start the local node using this command from the folder where you downloaded and expanded the Akka distribution:
```
$ bin/akka org.jamieallen.akka.microkernel.LocalKernel
```
9. You should see that work has been distributed to both nodes in the application.

##License
This code is open source software licensed under the <a href="http://www.apache.org/licenses/LICENSE-2.0.html">Apache 2.0 License</a>.