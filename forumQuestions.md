#Verteilte Systeme SS16 

1. [Architecture of Distributed Systems](#architecture-of-distributed-systems)
1. [Communication Paradigms](#communication-paradigms)
1. [Basics](#basics)
1. [Networks](#networks)
1. [**Glossary**](#glossary)



##Architecture of Distributed Systems
* Worin unterscheiden sich Client/Server- und Peer-to-Peer-Architekturen?
What are the differences between client/server and peer-to-peer architectures?

`test`

* Was ist eine Session im Kontext einer verteilten Anwendung? Erläutern Sie, weshalb eine Anwendung "Web Shop XY" Sessions benötigt.
Explain the concept of sessions in a distributed application. Why does an application "Web Shop XY" need sessions?

* Geben Sie je ein Beispiel für eine 2-tier- und eine 3-tier-Architektur an.
Give an example for a 2-tier and a 3-tier architecture.

* Was ist ein Proxy? Geben Sie ein Beispiel einer verteilten Anwendung an, bei der Proxies eingesetzt werden.
What is a proxy? Name a distributed applications which may use proxies.

* Was ist der Unterschied zwischen einer synchronen und einer asynchronen Kommunikation?
What are the differences between synchronous and asynchronous communication?

##Communication Paradigms
* Diskutieren Sie die Unterschiede zwischen den verschiedenen Communication Entities in einem verteilten System.
Discuss the differences between different communication entities in a distributed system.

* Was sind die Unterschiede zwischen "Interprocess Communication", "Remote Invocation" und "Indirect Communication"?
What are the differences between "interprocess communication", "remote invocation", and "indirect communication"?

* Welche Arten von IPC kennen Sie in einem Multiprocessing-System, welche in einem verteilten System?
Which kind of IPC do you know in a multiprocessing system, and in a distributed system?

* Beschreiben Sie verschiedene Formen der indirekten Kommunikation.
Describe the different forms of indirect communication.

##Basics
* Wie kann man ein verteiltes System definieren?
What is a definition of a distributed system?

* Was versteht man unter Skalierbarkeit?
What is scalability?

* Was versteht man unter Race Conditions? In welchen Situationen treten sie auf? Was für Lösungen gibt es?
What are race conditions? When do they occur? Solutions?

* Was versteht man unter Heterogenität? In welcher Form kommt Heterogenität in verteilten Systemen vor?
What is heterogeneity? In what situations, in distributed systems, one has to deal with heterogeneity?

== diversity
is present when different hardware architectures, programming enfironments or communication protocols

##Networks
* Was ist der Vorteil einer Schichten-Architektur wie ISO/OSI?
What are the benefits of a layered architecture like ISO/OSI for system developers?

abstraction: each layer has different & independent functions
each layer uses information from the layer below it & provides serves to the layer above it

* Was sind die Dienste der ISO/OSI Schicht 2? Geben Sie mindestens zwei Protokolle an, die dieser Schicht zugeordnet werden können.
What are the services provided by ISO/OSI layer 2? Name two protocols of this layer.

Data link: transmission of packages between directly connected nodes
Protocols: Ethernet MAC, ATM, PPP

* Was sind die Dienste der ISO/OSI Schicht 3? Geben Sie mindestens zwei Protokolle an, die dieser Schicht zugeordnet werden können.
What are the services provided by ISO/OSI layer 3? Name two protocols of this layer.

Network: transport of data packages between computers without a direct connection -> routing
Protocols: IP, ICMP, ATM

* Was sind die Dienste der ISO/OSI Schicht 4? Geben Sie mindestens zwei Protokolle an, die dieser Schicht zugeordnet werden können.
What are the services provided by ISO/OSI layer 4? Name two protocols of this layer.

Transport: messages between communication ports  attachted to processes
Protocols: TCP, UDP

* Was versteht man unter Latenz? In welchem Bereich (Größenordnung) liegt die Latenz bei einer Kommunikation zwischen zwei Rechnern in einem Labor der Angewandten Informatik?
What is latency? What is a typical value for latency in a laboratory like C 624?

== delay after send - time it takes for an empty message to arrive at the receiver
???

* Was versteht man unter Bandbreite? In welchem Bereich (Größenordnung) liegt die Bandbreite bei einer Kommunikation zwischen zwei Rechnern in einem Labor der Angewandten Informatik?
What is bandwidth? What is a typical value for bandwidth in a laboratory like C 624?

==Data transfer rate
???

***
##Glossary

[TCP](#tcp)
[UDC](#udc)
[Round trip time RTT](#rtt)
[OSI layer 1](#osi1)
[OSI layer 2](#osi2)
[OSI layer 3](#osi3)
[OSI layer 4](#osi4)
[OSI layer 5](#osi5)
[OSI layer 6](#osi6)
[OSI layer 7](#osi7)
[Latency](#latency)
[Bandwidth](#bandwidth)
[](#)
[](#)
[](#)

*<a name="tcp"></a>`TCP - Transmission Control Protocol`*
numbers packages, ensures receiver gets packages w/ acknowledgements, retransmission if packages are lost, 3-way handshake to set up socket connection, datastream, heavy weight connection

*<a name="udc"></a>`UDC - User Datagram Protocol`*
light weight transport layer, unnumbered and unordered packages, does not handle lost/not received packages (no handling of acknowledgment, time out, retransmission), sends data in packets, unreliable service, compatible with broadcasts and multicasting

*<a name="rtt"></a>`Round trip time`*
Time it takes for a data packet to arrive at a receiver of network and for the receiver to sent an acknowldgement

*<a name="osi1"></a>`OSI Layer 1: Physical`*
Transmission of binary data (signaling)

*<a name="osi2"></a>`OSI Layer 2: Data link`*
Transmission of packets(frames) between directly connected nodes
Examples: Ethernet MAC, ATM, PPP

*<a name="osi3"></a>`OSI Layer 3: Network`*
Transfer of data packets between computer, no direct connection -> routing
Examples: IP, ICMP, ATM

*<a name="osi4"></a>`OSI Layer 4: Transport`*
Messages between (communication ports attachet to) processes, connection-oriented or connectionless(unreliable)
Examples: TCP, UDP

*<a name="osi5"></a>`OSI Layer 5: Session`*
Session management, error recovery

*<a name="osi6"></a>`OSI Layer 6: Presentation`*
Network representation, encryption
Examples: NDR, XDR, SSL

*<a name="osi7"></a>`OSI Layer 7: Applications`*
Protocols on application elvel, interface to service
Examples: HTTP, SMTP, NTP

*<a name="latency"></a>`Latencc`*

*<a name="bandwidth"></a>`Bandwidth`*

*<a name="name"></a>`title`*