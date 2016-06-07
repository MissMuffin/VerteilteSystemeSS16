XML

- gültiges XML:
	- wohlgeformt (alle Tags müssen geschlossen sein, keine verschachtelten offenen Tags, nur ein tag dass keine Elemente beinhaltet muss kein schließsendes Tag haben)
	- Unterschied zu html: 
		XML ist Metasprache = ist frei, man kann selber eine XML Sprache definieren für bestimmte Anwendungen
		HMLT nicht Sprache, erfüllt nicht alle Regeln von XML (dagegen: XHTML ist XML sprache)
	- XML heute Standard weil: Inhaltsbezogene Objektdarstellung
- XML:
	- Datenaustausch
	- Datenspeicherung
- SAX Parser
- DOM Parser
	- baut DOM Baum auf
- SAX vs DOM:
	* DOM: 
		- sehr schnell
        - Baumaufbau sehr aufwändig
    * SAX:
        - kein Baum, geht sequenziell durch

WEB SERVICES

- no clear definition
- early definition:
    + public interfaces written in XML
    + identified by URI
    + potential directory accessW3C definition
    + XML based communication
- W3C definition:
    + web service = independent of framework, programming language, components
    + has unique address (URI) to be identified
- SOA (Service Oriented Architecture) may have web services as basis
- SOAP (Simple Object Access Protocol)
    - alternative to SOAP: RESTful web services
    - stateless protocol (like HTTP) = can't refer to previously made request
    - any interaction can be defined via SOAP -> no predefined interaction
    - one-way communication between SOAP nodes, no broadcast
    - can be used on top of various transport protocls
        + HTTP
        + SMTP
        + JMS (Java Messaging Protocol)
        + FTP
        + HTTPS
    - SOAP extensions:
        + SOAP + MIME
        + MTOM (Message Transmission Optimization Mechanism)
        + Web Service Security
            * most important one
            * end to end encryption
            * you can sign web services
* WSDL Web Service Descritpion Language
    - XML language
    - was developed for SOAP, to descripe SOAP web services
* RESTful web services (Representational State Transfer)
    - HTTP API methods:
        + GET
        + POST
        + PUT
        + DELETE
    - ressources defined by URI
    - resources may refer to other resources within a resource tree (XLINK)
    - no client state !
    - support for caching previous requests
    - separation of resource and representation
    - vs SOAP:
        + REST is simpler & has direct addressing of resources, allows various media types
            * no extra protocol layer needed (Example: SOAP on top of HTML)
        + SOAP: 
            * more flexible with transport protocols -> messaging service, advantageous i.e. because async, not available in HTTP
            * restricted to XML: 
                - can be an advantage: XML is well defined, can be validated
            * you can define any kind of service
                - REST: restricted to put, post, get, delete
            * you can describe your service using WSDL -> more information can be provided with SOAP

REMOTE INVOCATION

* RPC (Remote Process Call)
* part of distributed application -> joined computing: call methods on object on client and call methods on object on server, together they compute something
* for distributed computing middleware is needed
* middleware is transparent to developer
* call by reference methods: change parameter in funciton changes value outisde of function
* call by value methods: value is copied, change of value in function does not lead to change in value outside of function
    - JAVA only has call by values
* 
