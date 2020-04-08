package org.pengfei.Lesson01_Java_Standard_API.S06_Networking;

public class S06_Networking {
    /********************************************6.0 Java Networking Introduction ******************/

    /*
    * One of the most important reasons that Java became the premier language for network programming are the
    * classes defined in the java.net package. They provide a convenient means by which programmers of all skill
    * levels can access network resources. Beginning with JDK 11, Java has also provided enhanced networking support
    * for HTTP clients in the java.net.http package in a module by the same name. Called the HTTP Client API, it
    * further solidifies Java’s networking capabilities.
    * */

    /********************************************6.1 Networking Basics ***************************************/

    /*
    * Before we begin, it will be useful to review some key networking concepts and terms:
    * - Socket: A socket identifies an endpoint in a network. The socket paradigm was part of the 4.2BSD Berkeley UNIX
    *           release in the early 1980s. Because of this, the term Berkeley socket is also used. Sockets are at the
    *           foundation of modern networking because a socket allows a single computer to serve many different
    *           clients at once, as well as to serve many different types of information. This is accomplished through
    *           the use of a port, which is a numbered socket on a particular machine. A server process is said to
    *           "listen" to a port until a client connects to it. A server is allowed to accept multiple clients
    *           connected to the same port number, although each session is unique. To manage multiple client
    *           connections, a server process must be multithreaded or have some other means of multiplexing the
    *           simultaneous I/O.
    * - Protocol: Socket communication takes place via a protocol. Internet Protocol (IP) is a low-level routing
    *           protocol that breaks data into small packets and sends them to an address across a network, which does
    *           not guarantee to deliver said packets to the destination.
    *           Transmission Control Protocol (TCP) is a higher-level protocol that manages to robustly string together
    *           these packets, sorting and retransmitting them as necessary to reliably transmit data.
    *           User Datagram Protocol (UDP), sits next to TCP and can be used directly to support fast, connectionless,
    *           unreliable transport of packets.
    *
    * TCP/IP reserves the lower 1,024 ports for specific protocols. Many of these will seem familiar to you. Port
    * number 21 is for FTP; 23 is for Telnet; 25 is for e-mail; 43 is for whois; 80 is for HTTP; 119 is for netnews.
    *
    * For example, here is how HTTP protocol works. When a client requests a file from an HTTP server, an action known
    * as a hit, it simply sends the name of the file in a special format to a predefined port and reads back the
    * contents of the file. The server also responds with a status code to tell the client whether or not the request
    * can be fulfilled and why.
    *
    * Just as the numbers of an IP address describe a network hierarchy, the name of an Internet address, called its
    * domain name, describes a machine’s location in a name space. For example, www.HerbSchildt.com is in the COM
    * top-level domain (used by U.S. commercial sites); it is called HerbSchildt, and www identifies the server for
    * web requests. An Internet domain name is mapped to an IP address by the Domain Naming Service (DNS). This
    * enables users to work with domain names, but the Internet operates on IP addresses.
    * */

    /************************************* 6.2 Java.net classes and interfaces ***************************************/

}
