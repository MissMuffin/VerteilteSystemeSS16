#Verteilte Systeme SS16 

1. [Architecture of Distributed Systems](#architecture-of-distributed-systems)
1. [Communication Paradigms](#communication-paradigms)
1. [Basics](#basics)
1. [Networks](#networks)
2. [TCP/IP](#tcpip)
1. [**Glossary**](#glossary)



##Architecture of Distributed Systems
* Worin unterscheiden sich Client/Server- und Peer-to-Peer-Architekturen?
What are the differences between client/server and peer-to-peer architectures?

Client-Server: Server bietet Dienste/Services an, die der Client in Anspruch nimmt
Peer-to-Peer: alle Teilnehmer sind gleichberechtigt, jeder kann Dienste anieten und in Ansprich nehmen

* Was ist eine Session im Kontext einer verteilten Anwendung? Erläutern Sie, weshalb eine Anwendung "Web Shop XY" Sessions benötigt.
Explain the concept of sessions in a distributed application. Why does an application "Web Shop XY" need sessions?

Session = Folge von Anfragen innerhalb desselben Kontextes (gleichbleibender Client, Anwendungskontext und Authentifizierung)
Web Shop XY benutzt Sessions für das Verhalten eines Nutzers zu einem kohärenten Zeitpunkt?

* Geben Sie je ein Beispiel für eine 2-tier- und eine 3-tier-Architektur an.
Give an example for a 2-tier and a 3-tier architecture.

2-Tier: Client greift direkt auf zbsp Datenbankserver zu
3-Tier: Client kommuniziert mit Server, der zbsp Anfrage an Datenbankserver weiterleitet

* Was ist ein Proxy? Geben Sie ein Beispiel einer verteilten Anwendung an, bei der Proxies eingesetzt werden.
What is a proxy? Name a distributed applications which may use proxies.

Proxy = Vermittler zwischen 2(?) Rechnern ???
Anwendungsbeispiel: ???

* Was ist der Unterschied zwischen einer synchronen und einer asynchronen Kommunikation?
What are the differences between synchronous and asynchronous communication?

Synchron: Sender wartet auf Ergebnis von Empfänger
Asnychron: Sender wartet nicht

##Communication Paradigms
* Diskutieren Sie die Unterschiede zwischen den verschiedenen Communication Entities in einem verteilten System.
Discuss the differences between different communication entities in a distributed system.

Objekten, Komponente, Web-Services ???

* Was sind die Unterschiede zwischen "Interprocess Communication", "Remote Invocation" und "Indirect Communication"?
What are the differences between "interprocess communication", "remote invocation", and "indirect communication"?

Interprocess Communication = Kommunikation auf Betriebsebene in VS ???
Remote Invocation = remote Methodenaufruf ???
Indirect Communication = ???

* Welche Arten von IPC kennen Sie in einem Multiprocessing-System, welche in einem verteilten System?
Which kind of IPC do you know in a multiprocessing system, and in a distributed system?

Multiprocessing System: shared memory, message queues, pipes, semaphores ???
Distributed System: sockets, message passing 

* Beschreiben Sie verschiedene Formen der indirekten Kommunikation.
Describe the different forms of indirect communication.

???

Group communication: Nachrichten werden an gesamten Gruppe gesendet
Publish/subscribe: Rechner können einen Service abonnieren und bekommen dann Nachrichten zugesendet ???
Message queues: Nachrichten werden in Queue aufbewahrt und Empfänger holen sich dann Nachrichten ab

##Basics
* Wie kann man ein verteiltes System definieren?
What is a definition of a distributed system?

„Bei einem verteilten System arbeiten Komponenten zusammen, die sich auf vernetzten Computern befinden und ihre Aktionen durch den Austausch von Nachrichten koordinieren.“ [Coulouris] 

„Ein verteiltes System ist eine Ansammlung unabhängiger Computer, die den Benutzern wie ein einzelnes kohärentes System erscheinen.“ [Tanenbaum & van Steen]

* Was versteht man unter Skalierbarkeit?
What is scalability?

Skalierbarkeit = Wachsen eines Systems ohne dass dessen Architektur verändert werden muss

* Was versteht man unter Race Conditions? In welchen Situationen treten sie auf? Was für Lösungen gibt es?
What are race conditions? When do they occur? Solutions?

Race conditions = wenn Ergebnis von Zugriff auf eine Ressource abhängt, die gemeinsam im VS benutzt wird -> hängt dadurch von Reihenfolge oder Dauer anderer Ereignisse ab
Beim Zugriff im VS auf gemeinsame Ressourcen
Synchronisation, Threading, Middleware die Zugriffe regelt

* Was versteht man unter Heterogenität? In welcher Form kommt Heterogenität in verteilten Systemen vor?
What is heterogeneity? In what situations, in distributed systems, one has to deal with heterogeneity?

Heterogenität = Vielfältigkeit, Verschiedenartigkeit
Vertreten durch unterschiedliche Hardware, Netzwerke, Betriebssysteme, Programmiersprachen, Implementierungen, etc.

== diversity
is present when different hardware architectures, programming enfironments or communication protocols

##Networks
* Was ist der Vorteil einer Schichten-Architektur wie ISO/OSI?
What are the benefits of a layered architecture like ISO/OSI for system developers?

Modularität, Vereinfachung, Abstraktion

abstraction: each layer has different & independent functions
each layer uses information from the layer below it & provides serves to the layer above it

* Was sind die Dienste der ISO/OSI Schicht 2? Geben Sie mindestens zwei Protokolle an, die dieser Schicht zugeordnet werden können.
What are the services provided by ISO/OSI layer 2? Name two protocols of this layer.

Kommunikation innerhalb eines Netzwerkes ???
Übertragung von Paketen zwischen 2 direkt verbundenen Knoten: MAC, ATM, PPP

Data link: transmission of packages between directly connected nodes
Protocols: Ethernet MAC, ATM, PPP

* Was sind die Dienste der ISO/OSI Schicht 3? Geben Sie mindestens zwei Protokolle an, die dieser Schicht zugeordnet werden können.
What are the services provided by ISO/OSI layer 3? Name two protocols of this layer.

Überträgt Pakete/Frames aus der 2. Schicht, entweder direkt oder durch Routing (Umwege)
Übertragung & Routing zwischen Computern: IP, ICMP, ATM

Network: transport of data packages between computers without a direct connection -> routing
Protocols: IP, ICMP, ATM

* Was sind die Dienste der ISO/OSI Schicht 4? Geben Sie mindestens zwei Protokolle an, die dieser Schicht zugeordnet werden können.
What are the services provided by ISO/OSI layer 4? Name two protocols of this layer.

Kommunikation zwischen Komponenten: TCP, UDP

Transport: messages between communication ports  attachted to processes
Protocols: TCP, UDP

* Was versteht man unter Latenz? In welchem Bereich (Größenordnung) liegt die Latenz bei einer Kommunikation zwischen zwei Rechnern in einem Labor der Angewandten Informatik?
What is latency? What is a typical value for latency in a laboratory like C 624?

Latenz = Zeit, die für eine vollständige Datenübertragung vom Sender zum Empfänger benötigt wird
Liebt bei Laborrechner im einstelligen Millisekundenbereich

== delay after send - time it takes for an empty message to arrive at the receiver
???

* Was versteht man unter Bandbreite? In welchem Bereich (Größenordnung) liegt die Bandbreite bei einer Kommunikation zwischen zwei Rechnern in einem Labor der Angewandten Informatik?
What is bandwidth? What is a typical value for bandwidth in a laboratory like C 624?

Bandbreite = Anzahle der Bytes die innerhalb einer bestimmten Zeiteinheit über einen Übertragunskanal übertragen werden können 
Liegt bei Laborrechner bei ca 1/2 MB/s

==Data transfer rate
???

##TCP/IP

Wodurch unterscheidet sich der TCP/IP vom ISO/OSI Protocol Stack?
Discuss the differences between TCP/IP and ISO/OSI protocol stack.

Nennen Sie fünf Felder, welche im IPv4 Header vorkommen, mit einer kurzen Beschreibung.
Describe five fields in the IPv4 header.

Nennen Sie fünf Felder, welche im IPv6 Header vorkommen, mit einer kurzen Beschreibung.
Describe five fields in the IPv6 header.

Beschreiben Sie den Aufbau von IPv4 Adressen.
Describe the structure of IPv4 addresses.

Beschreiben Sie den Aufbau von IPv6 Adressen.
Describe the structure of IPv6 addresses.

Welchen Sinn macht die Aufteilung des Adressraums in Klassen A, B und C?
What's the idea of dividing IPv4 addresses in class A, class B, and class C addresses?

	definieren wie viele bits für die netid benutzt werden -> wie viele rechner möglich sind in einem netzwerk anzuschließen

Wie werden Subnetze definiert? Wie werden sie benutzt?
How are subnets defined? For what purpose?

Wird in einem IP-Header die Route eines Datenpakets gespeichert? Wie wird sichergestellt, dass Datenpakete nicht "ewig" geroutet werden?
Can the route of an IP packet be stored in the IP header? How can endless routing be avoided (e.g. in a loop)?

Nennen Sie drei wesentliche Änderungen / Vorteile von IPv6 gegenüber IPv4. Weshalb kann mit IPv6 effizienter geroutet werden?
Name three improvements of IPv6 on IPv4. Why is IPv6 better suited for routing?

	4 x 32 bits für verschiedene ip adressen

Beschreiben Sie das Konzept der Extension Headers.
Describe the concept of extension headers.

Was ist ein ephemeral Port? Welche Anwendungen benutzen ephemeral Ports (Beispiel)?
What are epehemeral ports? What kind of applications use ephemeral ports (example)?

Beschreiben Sie den 3-Wege-Handshake zur Etablierung einer TCP-Verbindung.
Describe the 3way handshake necessary to establish a TCP connection.

Was ist der Unterschied zwischen Packet Switching und Circuit Switching?
What is the difference between packet switching and circuit switching?

Was ist die Aufgabe der Root DNS Server? Weshalb sind sie essenziell für das Funktionieren des Internets?
What is the task of root DNS servers? Why are they so important?

Weshalb sollte der ARP Dienst Einträge (Zuordnung von MAC-Adressen) vergessen? Wie kann das realisiert werden?
Why should the ARP service forget mappings IP address --> MAC address? How can this be achieved?

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