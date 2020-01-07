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
     * javac --module-path appmodules -d appmodules/appstartappsrc/appstart/module-info.java
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

 /** 14.1.1.2 A closer look at the module Example
 *
  * We have seen the two main foundational features of the module system in the previous example:
  * - Provide packages as dependencies which can be used by other modules (exports packageName). As a module can contain
  *     more than one package, so we need to be specific when exporting. In a exported package, all public and protected
  *     types(including their public and protected members) are accessible. In a non-exported package, all types are
  *     only accessible to that module. For example, in a module, a public class are accessible by its package and other
  *     packages inside the module, but it's not accessible by other modules.
  *
  * - Consume packages as dependencies which are provided by other modules (requires moduleName). The requires keyword
  *       specifies the name of a module as the dependency of the current module. This means that the required module
  *       must be present in order for the current module to compile. In the language of modules, the current module
  *       is said to read the module specified in the requires statement. In general, the requires statement gives
  *       you a way to ensure that your program has access to the modules that it needs.
  *
  * The two features depends each other, if either is missing, the compilation will fail.
  *
  * Note, the requires and exports statements must occur only within a module statement(in module-info.java file).
 * */

 /** 14.1.2  The platform modules
  *
  * Beginning with JDK 9, the Java API packages have been incorporated into modules. The Java official API incorporated
  * modules are referred as "platform modules", and their names all begin with the prefix java. Here are some examples:
  * - java.base
  * - java.desktop
  * - java.xml
  *
  * By modularizing the API, it becomes possible to deploy an application with only the packages that it requires,
  * rather than the entire Java Runtime Environment(JRE).
  *
  * The fundamental packages of Java such as
  * - java.lang
  * - java.io
  * - java.util
  * - etc.
  * are included in java.base module, and java.base is automatically accessible to all modules. And, all other modules
  * automatically require java.base(no need to do requires java.base statement in a module declaration, but it's
  * not wrong to explicitly specify java.base, it's just not necessary). It's like java.lang is automatically available
  * to all programs without the use of an import statement.
  *
  * In our previous example, java.base module contains java.lang package, and java.lang contains System class. That's
  * why MyModAppDemo can use System.out.println() without an explicit requires and import. The same applies to Math.
  *
  * Beginning with JDK 9, the documentation for the Java API tells you the name of the module in which a package is
  * contained. If the package is not in java.base module, then you need to include the desired module with requires
  * statement in module-info.java file.
  * */

    /************************************ 14.2 Legacy code and the unnamed module ***********************************/

    /*
    * Support for legacy code is provided by two key features:
    * 1. The unnamed module-> When you use code that is not part of a named module, it automatically becomes part of
    *              the unnamed module. The unnamed module has two important attributes. First, all of the packages in
    *              the unnamed module are automatically exported. Second, the unnamed module can access any and all
    *              other modules. Thus, when a program does not use modules, all API modules in the Java platform are
    *              automatically accessible through the unnamed module.
    * 2. Automatic use of the class path -> When you compile a program that does not use modules, the class path
    *              mechanism is employed.
    *
    * Because of the unnamed module and the automatic use of the class path, there was no need to declare any modules
    * for the programs when they are not needed. Thus, even though modules are a recent feature that has a significant
    * impact on Java, compatibility with legacy code is maintained. This approach also provides a smooth, nonintrusive,
    * nondisruptive transition path to modules. Thus, it enables you to move a legacy application to modules at your
    * own pace or don't use modules at all.
    * */

    /************************************ 14.3 Exporting to a specific module ***********************************/

    /*
    * The basic form of the exports statement makes a package accessible to any and all other modules. It may be
    * required to make a package accessible to only a specific set of modules, not all other modules.
    *
    * The general form is
    * exports packageName to moduleName1, moduleName2, ...
    *
    * The exports statement, the to clause specifies a list of one or more modules that have access to the exported
    * package.
    *
    * */

    /** 14.3.1 Exporting to a specific module example
     *
     * For example, if we changed the module-info.java file for the appfuncs module.
     *
     * //new appfuncs module description:
     * module appfuncs{
     * //exports the package appfuncs.simplefuncs only to appstart module and to no other modules.
     * exports appfuncs.simplefuncs to appstart;
     * }
     *
     * Now, simplefuncs is exported only to appstart and to no other modules. After making this change, you can
     * recompile the application by using this javac command:
     *
     * javac -d appmodules --module-source-path appsrc appsrc/appstart/appstart/mymodappdemo/MyModAppDemo.java
     *
     * The option --module-source-path specifies the top of the module source tree, it automatically complies the files
     * in the tree under the specified directory, which is appsrc in this example. The ­­module­source­path option must
     * be used with the ­d option to ensure that the compiled modules are stored in their proper directories under
     * appmodules. This form of javac is called multimodule mode because it enables more than one module to be
     * compiled at a time.
     *
     * Multimodule compilation mode
     * The multimodule compilation mode is especially helpful here because the to clause refers to a specific
     * module, and the requiring module must have access to the exported package. Thus, in this case, both appstart
     * and appfuncs are needed to avoid warnings and/or errors during compilation. Multimodule mode avoids this problem
     * because both modules are being compiled at the same time. The multimodule mode of javac has another advantage.
     * It automatically finds and compiles all source files for the application, creating the necessary output
     * directories.
     * */

    /*
    * Note
    * As a general rule, qualified export is a special case feature. Most often, your modules will either provide
    * unqualified export of a package or not export the package at all, keeping it inaccessible. As such, qualified
    * export is discussed here primarily for the sake of completeness. Also, qualified export by itself does not
    * prevent the exported package from being misused by malicious code in a module that masquerades as the targeted
    * module. The security techniques required to prevent this from happening are beyond the scope of this tutorial.
    * Consult the Oracle documentation for details on security in this regard and Java security details in general.
    * */

    /************************************ 14.4 Using requires transitive ***********************************/

    /*
    * Consider a situation in which there are three modules, A, B, and C, that have the following dependencies:
    * - A requires B.
    * - B requires C.
    * Given this situation, it is clear that since A depends on B and B depends on C, A has an indirect dependence on C.
    * As long as A does not directly use any of the contents of C, then you can simply have A require B in its
    * module­info file, and have B export the packages required by A in its module­info file, as shown here:
    *
    * // A's module-info.java file
    * module A{
    *   requires B;
    * }
    *
    * // B's module-info.java file
    * module B{
    *   exports somepack;
    *   requires C;
    * }
    *
    * Here, somepack is a placeholder for the package exported by B and used by A. Although this works as long as
    * module A does not need to use anything defined in C, a problem occurs if A does want to access a type in C. In
    * this case, there are two solutions.
    *
    * 1st solution:
    * We simply add a requires C statement in A's module-info.java file
    * // A's new version of module-info.java file
    * module A{
    *   requires B;
    *   requires C;
    * }
    *
    * This solution certainly works, but if B will be used by many modules, you must add requires C to all module
    * definitions that require B. This is not only tedious; it is also error prone.
    *
    * 2nd solution:
    * We can create an implied dependence on C. Implied dependence is also referred to as implied readability. To
    * create an implied dependence, add the "transitive" keyword after requires in the clause that requires the module
    * upon which an implied readability is needed. In the case of this example, you would change B’s module­info file
    * as shown here:
    * // B's module-info.java file
    * module B{
    *   exports somepack;
    *   requires transitive C;
    * }
    * Module C is now required as transitive. After making this change, any module that depends on B will also
    * automatically depend on C. Thus, A would automatically have access to C.
    *
    * As a point of interest, because of a special exception in the Java syntax, in a requires statement, if transitive
    * is immediately followed by a separator (such as a semicolon), it is interpreted as an identifier (for example,
    * in "requires transitive;" statement, transitive will be considered as a module name) rather than a keyword.
    * */

    /******************************************** 14.5 Using services *****************************************/

    /*
    * In programming, it is often useful to separate what must be done from how it is done. As you learned in Section 7
    * (Interface), one way this is accomplished in Java is through the use of interfaces. The interface specifies the
    * what, and the implementing class specifies the how. This concept can be expanded so that the implementing class
    * is provided by code that is outside your program, through the use of a plug­in. Using such an approach, the
    * capabilities of an application can be enhanced, upgraded, or altered by simply changing the plug­in. The core of
    * the application itself remains unchanged. One way that Java supports a pluggable application architecture is
    * through the use of "services" and "service providers". Because of their importance, especially in large,
    * commercial applications, Java’s module system provides support for them.
    * */

    /** 14.5.1 Service and Service Provider Basics
     *
     * In java, a service is a program unit whose functionality is defined by an interface or an abstract class. Thus
     * a service specifies in a general way some form of program activity.
     *
     * A concrete implementation of a service is supplied by a service provider. In other words, a service defines
     * the form of some action, and the service provider supplies that action.
     *
     * Services are often used to support a plugable architecture. For example, a service might be used to support
     * the translation of one language into another. In this case, the services supports translation in general. The
     * service provider supplies a specific translation, such as German to English or Chinese to French. Because all
     * service providers implement the same interface, different translators can be used to translate different
     * languages without having to change the core of the application. You can simply change the service provider.
     *
     * Service provider are supported by the ServicesLoader class. ServiceLoader is a generic class packaged in
     * java.util. It is declared like this:
     * class ServiceLoader<S>
     * Here, S specifies the service type. Service providers are loaded by the load() method. It has serveral forms;
     * the one we will use is here:
     * public static <S> ServiceLoader<S> load(Class<S> serviceType)
     * Here, serviceType specifies the Class object for the desired service type. Recall from section 12, that "Class"
     * is a class that encapsulate information about a class. There are a variety of ways to obtain a "Class" instance.
     * We use the class literal which has general form:
     * className.class
     * Here, className specifies the name of the class.
     *
     * When load() is called, it returns a ServiceLoader instance for the application. This object supports iteration
     * and can be cycled through by use of a for-each for loop. So we can find a specific service provider by using a
     * loop.
     *
     *
     * */

    /** 14.5.2 The Service-Based keywords
     * Modules support services through the use of the keywords "provides", "uses", and "with". Essentially, a module
     * specifies that it provides a service with a "provides" statement. A module indicates that it requires a service
     * with a "uses" statement. The specific type of service provider is declared by "with". When used together, they
     * enable you to specify a module that provides a service, a module that needs that service, and the specific
     * implementation of that service. Furthermore, the module system ensures that the service and service providers
     * are available and will be found.
     *
     * The general form of a module provides service:
     * Module A {
     *     provides serviceType with implementationTypes;
     * }
     * Here, serviceType specifies the type of the service, which is often an interface, although abstract classes are
     * also used. A comma-separated list of the implementation types is specified by teh implementationTypes.
     *
     * The general form of a module uses a service:
     * Module B {
     *     uses serviceType;
     * }
     * here, serviceType specifies the type of the service required.
     *
     * */

    /** 14.5.3 A module based service example
     *
     * We retake the previous module app example and add a new service module. Below is the new project structure
     * appsrc
     * ├── appfuncs
     * │   ├── appfuncs
     * │   │   └── simplefuncs
     * │   │       └── SimpleMathFuncs.java
     * │   └── module-info.java
     * ├── appstart
     * │   ├── appstart
     * │   │   └── mymodappdemo
     * │   │       └── MyModAppDemo.java
     * │   └── module-info.java
     * ├── userfuncs
     * │   ├── module-info.java
     * │   └── userfuncs
     * │       └── binaryfuncs
     * │           ├── BinaryFunc.java
     * │           └── BinFuncProvider.java
     * └── userfuncsimp
     *     ├── module-info.java
     *     └── userfuncsimp
     *         └── binaryfuncsimp
     *             ├── AbsMinus.java
     *             ├── AbsMinusProvider.java
     *             ├── AbsPlus.java
     *             └── AbsPlusProvider.java
     *
     * We add two new module:
     * - userfuncs : Contains two interface -> BinaryFunc is the service interface which declares the form of a binary
     *                            function. It has a func() method which takes two integers and do some binary function.
     *                            It has a getName() method which returns the name of the function.
     *                            BinFuncProvider is the service interface which declares the form of the service
     *                            provider
     *
     * - userfuncsimp : Contains four class. AbsMinus and AbsPlus implements the BinaryFunc interface, AbsMinusProvider
     *                  and AbsPlusProvider implements the BinFuncProvider.
     */

     /**  14.5.3.1 Service code in Modules
     *
     ********************* BinaryFunc.java ********************************
     * package userfuncs.binaryfuncs;
     *
     * public interface BinaryFunc {
     *     // Obtain the name of the function
     *     public String getName();
     *
     *     // This is the function to perform. It will be provided by specific implementation
     *     public int func(int a, int b);
     * }
     *
     * ********************* AbsPlus.java **********************************
     * package userfuncsimp.binaryfuncsimp;
     *
     * import userfuncs.binaryfuncs.BinaryFunc;
     *
     * public class AbsMinus implements BinaryFunc {
     *
     *     // return the name of this function
     *     public String getName() {
     *         return "absMinus";
     *     }
     *
     *     // Implement the AbsPlus function
     *     public int func(int a, int b) {
     *         return Math.abs(a) - Math.abs(b);
     *     }
     * }
     *
     * ******************* BinFuncProvider.java ***************************
     * package userfuncs.binaryfuncs;
     *
     * import userfuncs.binaryfuncs.BinaryFunc;
     *
     * public interface BinFuncProvider {
     *     // Obtain a BinaryFunc.
     *     public BinaryFunc get();
     * }
     *
     *
     * ********************* AbsPlusProvider.java *************************
     * package userfuncsimp.binaryfuncsimp;
     *
     * import userfuncs.binaryfuncs.*;
     *
     * public class AbsPlusProvider implements BinFuncProvider {
     *     // provide an AbsPlus object
     *     public BinaryFunc get() {
     *         return new AbsPlus();
     *     }
     * }
     *
     * The full code can be found in source/module_service_example.zip
     *
     *
     * */

    /** 14.5.3.2 Module definition files
     * Now, we need to edit the module definition files. First we need to export the package binaryfuncs of module
     * userfuncs. Because the userfuncsimp module need to implement two interfaces.
     *
     * *********************** module-info.java (userfuncs) **********************
     * // module definition for userfuncs module
     * module userfuncs {
     * // exports the package binaryfuncs of the module userfuncs
     *     exports userfuncs.binaryfuncs;
     * }
     *
     *
     * Second, we need to declare services which the module userfuncsimp provide
     *
     * ************************* module-info.java (userfuncsimp) *******************
     * // module definition of module userfuncsimp
     * module userfuncsimp {
     * // this module depends on module userfuncs, because it implements the two interfaces
     *     requires userfuncs;
     *
     * // and it provides one service with two different implementations
     *     provides userfuncs.binaryfuncs.BinFuncProvider with userfuncsimp.binaryfuncsimp.AbsPlusProvider,
     *                                                        userfuncsimp.binaryfuncsimp.AbsMinusProvider;
     * }
     *
     * Note that, the userfucsimp module provide the service not the userfuncs module, userfuncs(module name).binaryfuncs
     * (package name).BinFuncProvider(service interface name),
     * */

    /** 14.5.3.3 Call the service in our app
     * To demonstrate the use of the services, the main() method of MyModAppDemo is expanded to use AbsPlus and
     * AbsMinus. It does so by loading them at run time by use of ServiceLoader .load(). Here is the updated code:
     *
     * public class MyModAppDemo {
     *     public static void main(String[] args) {
     *
     * // old built-in functions
     *         if (SimpleMathFuncs.isFactor(2, 10)) System.out.println("2 is a factor of 10");
     *         int a = 35;
     *         int b = 105;
     *
     *         System.out.println("Smallest common factor of " + a + " and " + b + " is " + SimpleMathFuncs.lcf(a, b));
     *         System.out.println("Largest common factor of " + a + " and " + b + " is " + SimpleMathFuncs.gcf(a, b));
     *
     * // new service based, user defined operations
     * // Get a service loader for bianry functions
     *         ServiceLoader<BinFuncProvider> ldr = ServiceLoader.load(BinFuncProvider.class);
     *
     *         BinaryFunc binOp = null;
     *
     * // Find the provider for absPlus and obtain the function
     *         for (BinFuncProvider bfp : ldr) {
     *             if (bfp.get().getName().equals("absPlus")) {
     *                 binOp = bfp.get();
     *                 break;
     *             }
     *         }
     *         if (binOp != null) System.out.println("Result of absPlust function: " + binOp.func(12, -4));
     *         else System.out.println("absPlus function not found");
     *
     * //Now, find the provider for absMinus
     *         binOp = null;
     *         for (BinFuncProvider bfp : ldr) {
     *             if (bfp.get().getName().equals("absMinus")) {
     *                 binOp = bfp.get();
     *                 break;
     *             }
     *         }
     *         if (binOp != null) System.out.println("Result of absMinus function: " + binOp.func(12, -4));
     *         else System.out.println("absMinus function not found");
     *     }
     * }
     *
     * We also need to modify the module-info.java of the appstart module to use the service, here is the full code
     *
     * module appstart {
     * // module def for the main appalication module, here we specify it depends on appfuncs module
     *     requires appfuncs;
     *
     * // appstart module depends userfuncs module
     *     requires userfuncs;
     *
     * // appstart uses BinFuncProvider
     *     uses userfuncs.binaryfuncs.BinFuncProvider;
     * }
     * */

    /** 14.5.3.4 Compile and run the application
     *
     * To compile all modules, we use the following command
     * javac -d appmodules --module-source-path appsrc appsrc/userfuncsimp/module-info.java
     *        appsrc/appstart/appstart/mymodappdemo/MyModAppDemo.java
     *
     * Note we only compile MyModAppDemo.java of the module appstart, but all required modules are compiled. Because,
     * the option --module-source-path check the module-info.java, it will compile all required module defined in
     * module-info.java. -d option specified the output directory of the compiled modules.
     *
     * To run the MyModAppDemo app, use the following command
     * java --module-path mymodapp/appmodules -m appstart/appstart.mymodappdemo.MyModAppDemo
     *
     * --module-path specifies the path of compiled module. -m option specifies which class to run.
     * */


    /************************************** 14.6 Additional moudle features ***********************************/

    /*
    * There are three more features that require a brief introduction:
    * - Open module
    * - opens statement
    * - requires static
    * */

    /** 14.6.1 Open modules
     *
     * An open module is declared by using the keyword open, it has the following general form
     * open module module-name{
     *     // module definition
     * }
     *
     * In an open module, classes(types) in all packages are accessible at run time. However, only those packages that
     * are explicitly exported are available at compile time. Thus, the open modifier affects only run-time
     * accessibility.
     *
     * The primary reason for an open module is to enable the packages in the module to be accessed through reflection.
     * Reflection is the feature that lets a program analyze code at run time. Although the topic of and techniques
     * required to use reflection are beyond the scope of this tutorial, it can be quite important to certain types
     * of programs that require run­time access to a third­party library.
     * */

    /** 14.6.2 The opens statement
     *
     * It is possible for a module to open a specific package for run­time access by other modules and for reflective
     * access rather than opening an entire module. To do so, use the opens statement, shown here:
     * opens packageName;
     *
     * Here, packageName specifies the package to open. It is also possible to include a to clause, which names those
     * modules for which the package is opened.
     *
     * It is important to understand that opens does not grant compile­time access. It is used only to open a package
     * for run­time and reflective access. One other point: an opens statement cannot be used in an open module.
     * Remember, all packages in an open module are already open.
     * */

    /** 14.6.3 requires static
     *
     * As you know, requires specifies a dependence that, by default, is enforced both during compilation and at
     * run time. However, it is possible to relax this requirement in such away that a module is not required at
     * run time. This is accomplished by use of the static modifier in a requires statement. For example, this
     * specifies that mymod is required for compilation, but not at run time:
     * requires static mymod;
     *
     * In this case, the addition of static makes mymod optional at run time. This can be helpful in a situation in
     * which a program can utilize functionality if it is present, but not require it.
     *
     * */

    /** 14.6.4 Module graph
     *
     * During compilation, the compiler resolves the dependence relationships between modules by creating a module
     * graph that represents the dependencies. The process ensures that all dependencies are resolved, including those
     * that occur indirectly. For example, if module A requires module B and B requires module C, then the module graph
     * will contain module C even if A does not use it directly.
     *
     * Module graphs can be depicted visually in a drawing to illustrate the relationship between modules, and you
     * will likely encounter one as you continue on in Java. Here is a simple example. It is the graph for the first
     * module example in this section. (Because java.base is automatically included, it is not shown in the
     * diagram.)
     *
     * appstart->appfuncs
     *
     * In java, the arrows point from the dependent module to the required module. Thus, a drawing of a module graph
     * depicts what modules have access to what other modules.
     * */

    /************************************** 14.7 Continuing your study of modules ***********************************/

    /*
    * Beginning with JDK 9, the JDK includes the jlink tool that assembles a modular application into a run­time image
    * that has only those modules related to the application. This saves both space and download time. A modular
    * application can be packaged into a JAR file. (JAR stands for Java ARchive. It is a file format typically used for
    * application deployment.) As a result, the jar tool now has options that support modules. For example, it can now
    * recognize a module path. A JAR file that contains a module­info.class file is called a modular JAR file.
    * For specialized advanced work with modules, you will want to learn about layers of modules, automatic modules,
    * and the technique by which modules can be added during compilation or execution.
    *
    *
    * */


}
