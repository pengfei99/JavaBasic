package org.pengfei.Lesson00_Java_Basics.S14_Modules;

public class S14_Modules {

    /******************************************** 14. Introduction *******************************************/
    /*
    * Beginning with JDK 9, an important feature called modules was added to Java. Modules give you a way to
    * describe the relationships and dependencies of the code that comprises an application. Modules also let
    * you control which parts of a module are accessible to other modules and which are not. Through the use
    * of modules you can create more reliable, scalable programs.
    *
    * As a general rule, modules are most helpful to large applications because they help reduce the management
    * complexity often associated with a large software system. However, small programs also benefit from modules
    * because the Java API library has now been organized into modules. Thus, it is now possible to specify which
    * parts of the API are required by your program and which are not. This makes it possible to deploy programs
    * with a smaller run­time footprint, which is especially important when creating code for small devices, such as
    * those intended to be part of the Internet of Things (IoT).
    *
    * In this section, we will learn the following key concepts of modules:
    * - The definition of a module and Java’s module­related keywords
    * - Declare a module by use of the module keyword
    * - Use requires and exports
    * - Understand the purpose of module­info.java
    * - Use javac and java to compile and run module­based programs
    * - Understand the purpose of java.base
    * - Understand how pre­module legacy code is supported
    * - Export a package to a specific module
    * - Use implied readability
    * - Use services in a module
    * */

    /******************************************** 14.1 Module Basics *******************************************/

    /*
    * In its most fundamental sense, a module is a grouping of packages and resources that can be collectively
    * referred to by the module’s name. A module declaration specifies the name of a module and defines the
    * relationship a module and its packages have to other modules. Module declarations are program statements in a
    * Java source file and are supported by several module­related keywords (added to Java by JDK 9) such as:
    * - exports
    * - module
    * - open
    * - opens
    * - provides
    * - requires
    * - to
    * - transitive
    * - uses
    * - with
    *
    * Note that these keywords are recognized as keywords only in the context of a module declaration. Otherwise,
    * they are interpreted as identifiers in other situations. Thus, the keyword module could, for example, also be
    * used as a parameter name, but such a use is certainly not now recommended.
    * This design is to prevents problems with preexisting code that uses one or more of module related keywords as
    * identifiers.
    *
    * Module declaration file
    * A module declaration is contained in a file called module­info.java(Java source file). This file is then compiled
    * by javac into a class file and is known as a module descriptor. The module­info.java file must contain only a
    * module definition. It is not a general­purpose file.
    *
    * Module declaration General form
    * module moduleName{
    *  // module definition
    * }
    * */

    /** 14.1.1 A simple module example
     * A module have two main features:
     * 1. Module dependencies: A module can specify that it depends on another module. A dependence relationship is
     *    specified by use of a "requires" statement. By default, the presence of the required module is checked at both
     *    compile time and run time.
     * 2. Module access control: A module can specify that its packages are accessible or not by another module. This
     *    is accomplished by use of the exports keyword. The public and protected types within a package are accessible
     *    to other modules only if they are explicitly exported.
     *
     * First we will only use command line tools, because each IDE has its own way to do it. Then we will show how to
     * do it inside a IntelliJ IDEA.
     * */

    /** 14.1.1.1 Command line module Example
     * Create a root dir "mymodapp", under this dir, create the following subdir and java source file
     * appsrc
     *         ├── appfuncs
     *         │   ├── appfuncs
     *         │   │   └── simplefuncs
     *         │   │       └── SimpleMathFuncs.java
     *         │   └── module-info.java
     *         └── appstart
     *             ├── appstart
     *             │   └── mymodappdemo
     *             │       └── MyModAppDemo.java
     *             └── module-info.java
     *
     * You can find the above dir hierarchy in this section's source folder. SimpleMathFuncs.java provide two function
     * to calculate the common factor of two numbers. MyModAppDemo.java is the main function which calls the
     * SimpleMathFuncs.java.
     *
     * The idea here is create two module, one named appfuncs which contains the package appfuncs.simplefuncs. In it,
     * we have the class SimpleMathFuncs. The other module named appstart
     *
     * appfuncs module description:
     * module appfuncs{
     * //exports the package appfuncs.simplefuncs, allows other modules to use the exported package
     *     exports appfuncs.simplefuncs;
     * }
     *
     * appstart module description:
     * module appstart{
     * // module def for the main appalication module, here we specify it depends on appfuncs module
     *     requires appfuncs;
     * }
     *
     * Once we have the above architecture, we can start to compile the two modules with javac
     *
     * 1. compile the SimpleMathFuncs.java and module-info for module appfuncs, using the below command:
     * cd /path/to/mymodapp
     * javac -d appmodules/appfuncs appsrc/appfuncs/appfuncs/simplefuncs/SimpleMathFuncs.java
     * javac -d appmodules/appfuncs appsrc/appfuncs/module-info.java
     *
     * The -d option tells javac where to put the output .class file.
     *
     * 2. compile the MyModAppDemo.java and module-info for module appstart, using the below command:
     * javac --module-path appmodules -d appmodules/appstart
     *     appsrc/appstart/module-info.java
     *     appsrc/appstart/appstart/mymodappdemo/MyModAppDemo.java
     *
     * In this command, we compile two files at the same time, and we use option --module-path option to specifies the
     * module path. The compiler will use this path to find dependent module required by the target module which it
     * compiles. Also, notice that it specifies the output directory as appmodules\appstart. This means that the
     * module­info.class file will be in the appmodules\appstart module directory and MyModAppDemo.class will be in
     * the appmodules\appstart\appstart\mymodappdemo package directory.
     *
     * After all compilation, you should see the following hierarchy:
     * mymodapp
     *     ├── appmodules
     *     │   ├── appfuncs
     *     │   │   ├── appfuncs
     *     │   │   │   └── simplefuncs
     *     │   │   │       └── SimpleMathFuncs.class
     *     │   │   └── module-info.class
     *     │   └── appstart
     *     │       ├── appstart
     *     │       │   └── mymodappdemo
     *     │       │       └── MyModAppDemo.class
     *     │       └── module-info.class
     *     └── appsrc
     *         ├── appfuncs
     *         │   ├── appfuncs
     *         │   │   └── simplefuncs
     *         │   │       └── SimpleMathFuncs.java
     *         │   └── module-info.java
     *         └── appstart
     *             ├── appstart
     *             │   └── mymodappdemo
     *             │       └── MyModAppDemo.java
     *             └── module-info.java
     *
     *
     * Call the module with java
     * java --module-path appmodules -m appstart/appstart.mymodappdemo.MyModAppDemo
     *
     * You should see the following output:
     * 2 is a factor of 10
     * Smallest common factor of 35 and 105 is 5
     * Largest common factor of 35 and 105 is 7
     *
     * Here, the ­­module­path option specifies the path to the application’s modules. As mentioned, appmodules is the
     * directory at the top of the compiled modules tree. The ­m option specifies the class that contains the entry
     * point of the application and, in this case, the name of the class that contains the main() method
     *
     * You can find all above files and dirs in the module_example.zip file in source
     * */

}
