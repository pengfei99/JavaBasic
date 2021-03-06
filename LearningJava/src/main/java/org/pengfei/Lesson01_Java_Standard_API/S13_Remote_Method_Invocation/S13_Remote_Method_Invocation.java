package org.pengfei.Lesson01_Java_Standard_API.S13_Remote_Method_Invocation;

public class S13_Remote_Method_Invocation {

    /********************************************* 13.1 Introduction ***********************************************/

    /*
    * RMI stands for Remote Method Invocation. It is a mechanism that allows an object residing in one system (JVM)
    * to access/invoke an object running on another JVM.
    *
    * RMI is used to build distributed applications; it provides remote communication between Java programs. It is
    * provided in the package java.rmi.
    *
    * */

    /************************************** 13.2 Architecture of an RMI application **********************************/
    /*
    * In an RMI application, we write two programs:
    * - a server program (resides on the server): Inside the server program, a remote object is created and reference
    *              of that object is made available for the client (using the registry).
    * - a client program (resides on the client): The client program requests the remote objects on the server and
    *              tries to invoke its methods.
    *
    * Check the graph at https://www.tutorialspoint.com/java_rmi/java_rmi_introduction.htm
    *
    * */

    /** 13.2.1 Terminology
     *
     * - Transport Layer:  This layer connects the client and the server. It manages the existing connection and also
     *                   sets up new connections.
     * - Stub: A stub is a representation (proxy) of the remote object at client. It resides in the client system; it
     *                   acts as a gateway for the client program.
     * - Skeleton: This is the object which resides on the server side. stub communicates with this skeleton to pass
     *                   request to the remote object.
     * - RRL(Remote Reference Layer) − It is the layer which manages the references made by the client to the remote
     *                   object.
     * */

    /** 13.2.2 Working of an RMI Application
     *
     * The following points summarize how an RMI application works:
     *
     * 1. When the client makes a call to the remote object, it is received by the stub which eventually passes this
     *   request to the RRL.
     * 2. When the client-side RRL receives the request, it invokes a method called invoke() of the object remoteRef.
     *   It passes the request to the RRL on the server side.
     * 3. The RRL on the server side passes the request to the Skeleton (proxy on the server) which finally invokes
     *    the required object on the server.
     * 4. The result is passed all the way back to the client.
     * */

    /** 13.2.3 Marshalling and Unmarshalling
     *
     * Whenever a client invokes a method that accepts parameters on a remote object, the parameters are bundled into
     * a message before being sent over the network. These parameters may be of primitive type or objects. In case of
     * primitive type, the parameters are put together and a header is attached to it. In case the parameters are
     * objects, then they are serialized. This process is known as marshalling.
     *
     * At the server side, the packed parameters are unbundled and then the required method is invoked. This process
     * is known as unmarshalling.
     *
     * */

    /******************************************** 13.3 RMI Registry ********************************************/

    /*
    * RMI registry is a namespace on which all server objects are placed. Each time the server creates an object,
    * it registers this object with the RMI registry (using bind() or reBind() methods). These are registered using
    * a unique name known as bind name.
    *
    * To invoke a remote object, the client needs a reference of that object. At that time, the client fetches the
    * object from the registry using its bind name (using lookup() method).
    *
    * */

    /******************************************** 13.4 Goals of RMI ********************************************/

    /*
    * Following are the goals of RMI:
    * - To minimize the complexity of the application.
    * - To preserve type safety.
    * - Distributed garbage collection.
    * - Minimize the difference between working with local and remote objects.
    * */

    /******************************************** 13.5 A simple example ********************************************/

    /*
    * In this example, client send a request specifies two numbers. The server adds these together and returns the sum.
    *
    * */

    /** 13.5.1 AddServerInterface.java
     *
     * It defines the remote interface that is provided by the server. It contains one method that accepts two double
     * arguments and returns their sum. All remote interfaces must extend the Remote interface, which is part of
     * java.rmi. Remote interface defines no members. Its purpose is simply to indicate that an interface uses
     * remote methods. All remote methods can throw a RemoteException.
     *
     * Note, remote interface must always be public and extends Remote interface. All methods described in the Remote
     * Interface must list RemoteException in their throws clause. It contains all the methods that the Server implements
     * */

    /** 13.5.2 AddServerImpl.java
     *
     * It implements the remote interface AddServerInterface. The implementation of the add() method is straightforward.
     * Remote objects typically extend UnicastRemoteObject, which provides functionality that is needed to make
     * objects available from remote machines.
     * */

    /** 13.5.3 Server.java
     *
     * It contains the main program for the server machine. Its primary function is to update the RMI registry on that
     * machine. This is done by using the rebind() method of the Naming class (found in java.rmi). That method
     * associates a name with an object reference. The first argument to the rebind() method is a string that names
     * the server as "AddServer". Its second argument is a reference to an instance of AddServerImpl.
     * */

    /** 13.5.4 Client.java
     *
     * It implements the client side of this distributed application. You must provide the server ip address. If you run
     * the client and server on the same machine, just use 127.0.0.1 as ip address. We use this Ip to build a URL. This
     * URL uses the rmi protocol. The string includes "AddServer", which is the name of server registered in the RMI
     * registry.
     *
     * */

    /** 13.5.5 Run the application
     *
     * First, we need to start the rmi registry.
     * In windows, use command " start rmiregistry"
     * In linux, use command "rmiregistry &"
     *
     * Second, run Sever, you should see Server Starting: in the console
     *
     * Third, run Client, you should see Value of arg1: 1.0, arg2: 2.0 The sum is: 3.0
     *
     * If you see argument unmarshalling exception, it means the remote interface or distributed class can not be
     * found by the jvm where registry runs. For example, if you use console to run like "java Server". You need to make
     * sure AddServerInterface.class and AddServerImpl.class are also in the same folder. For the client, when you
     * run "java Client", You also need to make sure AddServerInterface.class and AddServerImpl.class are present.
     *
     * If you use a IDE such as IntelliJ IDEA, you need to add the classes to the CLASSPATH.
     * For example: export CLASSPATH=$CLASSPATH:/home/pliu/IdeaProjects/JavaBasic/LearningJava/target/classes
     * */

    /************************************** 13.6 A more complex example ********************************************/

    /*
    * In this example, we will offer a remote book store for search and list books
    *
    * */

    /** 13.6.1 The Remote Interface
     * BookStoreRemoteInterface is the remote interface of this example. it contains all the methods that the Server
     * implements. The Interface must always be public and extend Remote. All methods described in the Remote Interface
     * must list RemoteException in their throws clause.
     * */

    /** 13.6.2 The Distributed Object class
     *
     * This is the class of the Object that the Server and Client will exchange, and must implement the Serializable
     * Interface. It is also extremely important that this class declares an explicit serialVersionUID value to
     * guarantee the consistency across different java compiler implementations.
     *
     * If we ignore that or if the Distributed Object class of the Server declares a different serialVersionUID than
     * the Distributed Object class of the Client, then the deserialization process will result in an
     * InvalidClassException.
     * */

    /** 13.6.3 The Sever side
     *
     * The Server side has two classes:
     * - BookStoreSeverImplementation: It extends UnicastRemoteObject and implements the BookStoreRemoteInterface.
     * - BookServer: It has the main method where we bind the server on localhost with the name “MyBookstore”.  We fill
     *               a List with MyBook type Objects that represent the books our Bookstore.
     *
     * We also need to add a serialVersionUID for the BookStoreSeverImplementation, but since the Server we designed
     * in this example intends to exist only on one machine we don’t need to think about it twice, we can just set a
     * default serialVersionUID. If, however, the Server Class was also distributed, we would have to do make sure
     * the serialVersionUID for the class was the same across all platforms that implemented it.
     * */

    /** 13.6.4 The Client side
     *
     * The Client “finds” the Server through the remote interface(i.e. BookStoreRemoteInterface) Object that “looks”
     * for a reference to the remote object associated with the name we pass as parameter. What we just described is
     * what Naming.lookup("//localhost/MyBookstore"); does.
     *
     * The rest of the code for the Client is just to make a working example that you could hopefully experiment with.
     * */
}
