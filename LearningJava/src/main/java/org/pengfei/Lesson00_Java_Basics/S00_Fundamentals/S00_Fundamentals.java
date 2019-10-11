package org.pengfei.Lesson00_Java_Basics.S00_Fundamentals;

public class S00_Fundamentals {
    /*
    * In this Lesson, we will learn the fundamentals of JAVA:
    * - Understand the importance of bytecode
    * - Know the Java buzzwords
    * - Understand the foundational principles of object­oriented programming
    * - Create, compile, and run a simple Java program
    * - Use variables
    * - Use the if and for control statements
    * - Create blocks of code
    * - Understand how statements are positioned, indented, and terminated
    * - Know the Java keywords
    * Understand the rules for Java identifiers
    *
    * */

    /************************************** 0.1 The Bytecode *********************************************************/

    /*
    *  The key that allowed Java to address both security and portability problems is that the output of a Java compiler
    * is not executable code, it's bytecode. Bytecode is highly optimized set of instructions designed to be executed by
    * JVM.
    * In essence, the original JVM was designed as an interpreter for bytecode. With different platform, the bytecode
    * remains the same, only the JVM needs to be changed based on different platform. Also JVM can create a restricted
    * execution environment, called the sandbox, that contains the program, which prevents unrestricted access to the
    * machine.
    *
    * Although Java was designed as an interpreted language, there is nothing about Java that prevents on­the­fly
    * compilation of bytecode into native code in order to boost performance. For this reason, the HotSpot JVM was
    * introduced not long after Java’s initial release. HotSpot includes a just­in­time (JIT) compiler for bytecode.
    * When a JIT compiler is part of the JVM, selected portions of bytecode are compiled into executable code in real
    * time on a piece­by­piece demand basis. That is, a JIT compiler compiles code as it is needed during execution.
    * Furthermore, not all sequences of bytecode are compiled—only those that will benefit from compilation. The
    * remaining code is simply interpreted. However, the just­in­time approach still yields a significant performance
    * boost. Even when dynamic compilation is applied to bytecode, the portability and safety features still apply
    * because the JVM is still in charge of the execution environment.
    *
    * Note, Beginning with JDK 9, some Java environments will also support an ahead­of­time compiler that can be
    * used to compile bytecode into native code prior to execution by the JVM. (Need to be studied with more details
    * afterwards)
    *
    *
    * */

    /************************************ 0.2 The Java Buzzwords **************************************************/

    /*
    * The key considerations were summed up by the Java design team in the following list of buzzwords.
    * - Simple: Java has a concise, cohesive set of features that makes it easy to learn and use.
    * - Secure: Java provides a secure means of creating Internet applications
    * - Portable: Java program can execute in nay environment for which there is a Java run-time system.
    * - Object-oriented: Java embodies the modern object-oriented programming philosophy.
    * - Robust: Java encourage error-free programming by being strictly typed and performing run-time checks.
    * - Multithreaded: Java provides integrated support for multithreaded programming.
    * - Architecture-neutral: Java is not tied to a specific machine or operating system architecture.
    * - Interpreted: Java supports cross-platform code through the use of Java bytecode.
    * - High performance: The Java bytecode is highly optimized for speed of execution.
    * - Distributed: Java was designed with the distributed environment of the internet in mind
    * - Dynamic: Java programs carry with them substantial amounts of run-time type information that is used to
    *            verify and resolve access to objects on the run time.
    *
    * */

    /********************************** 0.3 Object-oriented programming ***************************************/
    /*
    * In the most general sense, a program ca be organized in one of two ways:
    * - around its code (what is happening)
    * - around its data (what is being affected)
    *
    * Structured programming language such as C and Pascal are organized around code. Object-oriented programming
    * language are organized around data.
    *
    * Object-oriented programming use the principle "data controlling access to code", which means you define the data
    * and the routines that are permitted to act on that data. Thus, a data type defines precisely what sort of
    * operations can be applied to that data.
    *
    * As a result, all OOP languages, including Java have three traits in common:
    * - encapsulation
    * - polymorphism
    * - inheritance
    *
    * */

    /* Encapsulation
    * Encapsulation is a programming mechanism that binds together code and the data it manipulates, and that keeps
    * both safe from outside interference and misuse.
    *
    * Java's basic unit of encapsulation is the class. A class defines the form of an object. It specifies both the
    * data and the code that will operate on that data. Java uses a class specification to construct objects.
    * Objects are instances of a class. Thus, a class is essentially a set of plans that specify how to build an object.
    *
    * Within an object, code, data, or both may be private to that object or public. Private code or data is known to
    * and accessible by only another part of the object. That is, private code or data cannot be accessed by a piece
    * of the program that exists outside the object. When code or data is public, other parts of your program can
    * access it even though it is defined within an object. Typically, the public parts of an object are used to
    * provide a controlled interface to the private elements of the object.
    * */

    /* Polymorphism (from Greek, meaning “many forms”)
    * Polymorphism is the quality that allows one interface to access a general class of actions. More generally, the
    * concept of polymorphism is often expressed by the phrase "one interface, multiple methods". This means that it is
    * possible to design a generic interface to a group of related activities. Interface does not need to know how these
    * methods are implemented. This reduce the complexity of the usage of these complexities.
    *
    * For example, we have a printer interface which has a print method. When I want to print something, I only need to
    * call the print method of the printer interface. The compiler will select the specification of each printer
    * instance (e.g. laser printer, ink-jet printer, etc.).
    * */

    /* Inheritance
    * Inheritance is the process by which one object can acquire the properties of another object. This is important
    * because it supports the concept of hierarchical classification. For example, a shop sells fruit, which includes
    * various types of apples. The natural hierarchical classification is fruit->apple->(type1 apple, type2 apple)
    *
    * Using inheritance, an object need only define those qualities that it unique within its class. Without the use of
    * hierarchies, each object would have to explicitly define all of its characteristics.
    * */


/***************************************** 0.4 Create, compile, and run a simple Java program ***************************/

    public static void main(String[] args){
        /* The "public" keyword is an access modifier, which determines whether other classes can use a particular field
         *  or invoke a particular method. There are two levels of access control:
         * - At the top level (class)
         * - At the member level (fields, methods)
         *
         * At the top level, a class may be declared with:
         * - public: class is visible to all other classes everywhere
         * - no modifier: is the default value, aka. package-private, class is visible only within its own package
         *
         * At the member level, fields/methods has four modifier(class only has two):
         * - public: fields/methods can be accessed by code outside this class.
         * - no modifier: fields/methods can be accessed by code inside the package.
         * - private: fields/methods can't be accessed by code outside this class.
         * - protected: fields/methods can be accessed by code inside the package, and by a subclass of its class in other
         *              packages.
         * */

        /* The "static" keyword indicates that the particular member (in our case, it's the main method) belongs to a
        * type(Class) itself, rather than to an instance of that type(Class). This means that only one instance of
        * that static member is created which is shared across all instances of the class.
        *
        * The "static" keyword can be applied to variables, methods, blocks and nested class. Check S00_StaticKeyword
        * for more details.*/
        System.out.println("");
    }
}
