package org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages;

import org.pengfei.Lesson00_Java_Basics.S07_Interfaces_Packages.source.*;

import java.util.Arrays;

public class S07_Interfaces_Packages {

    /******************************************** 7. Introduction *******************************************/

    /*
    * In this section, we learn two Java's features: packages and interfaces. Packages are groups of related classes.
    * Packages help organize your code and provide another layer of encapsulation. An interface defines a set of
    * methods that will be implemented by a class. Thus, an interface gives you a way to specify what a class will
    * do, but not how it will do it. Packages and interfaces give you greater control over the organization of your
    * program. We will learn the following key concepts:
    * - Use packages and Understand how packages affect access
    * - Apply the protected access modifier
    * - Import packages
    * - Know Java’s standard packages
    * - Understand interface fundamentals
    * - Implement an interface
    * - Apply interface references
    * - Understand interface variables
    * - Extend interfaces
    * - Create default, static, and private interface methods
    *
    * */

    /******************************************** 7.1 Packages *******************************************/

    /*
    * In programming, it is often helpful to group related pieces of a program together. In Java, this can be
    * accomplished by using a package. A package serves two purposes:
    * 1. Organize related programs as a unit. Classes defined within a package must be accessed through their package
    *    name. Thus, a package provides a way to name a collection of classes.
    * 2. Participate Java's access control mechanism. Classes defined within a package can be made private to that
    *    package and not accessible by code outside the package.
    *
    * In general, when you name a class, you are allocating a name from the namespace. A namespace defines a
    * declarative region. In Java, no two classes can use the same name from the same namespace. Thus, within a given
    * namespace, each class name must be unique. In another word, you can classes with same name in different packages
    * */

    /** 7.1.1 Defining a Package
     * All classes in Java belong to some package. When no package statement is specified, the default package is used.
     * Furthermore, the default package has no name, which makes the default package transparent.
     *
     * To create a package, put a package command at the top of a Java source file. The classes declared within that
     * file will then belong to the specified package. Since a package defines a namespace, the names of the classes
     * that you put into the file become part of that package’s namespace. It has the following form;
     * package pkg_name
     *
     * Typically, Java uses the file system to manage packages, with each package stored in its
     * own directory, and this is the approach assumed by the discussions and examples of
     * packages in this book. For example, the .class files for any classes you declare to be
     * part of mypack must be stored in a directory called mypack.
     *
     * Like the rest of Java, package names are case sensitive. This means that the directory in which a package is
     * stored must be precisely the same as the package name.
     *
     * More than one file can include the same package statement. The package statement
     * simply specifies to which package the classes defined in a file belong. It does not
     * exclude other classes in other files from being part of that same package. Most real­
     * world packages are spread across many files.
     *
     * Packages hierarchy
     * The general form of a multilevel package statement is shown here:
     * package pack1.pack2.pack3...packN;
     *
     * */

    /** 7.1.2 Finding packages and classpath
     * As just explained, packages are typically mirrored by directories. This raises an important question: How does
     * the Java run­time system know where to look for packages that you create?
     * The answer has three parts:
     * 1. By default, the Java run­time system uses the current working directory as its starting point. Thus,
     *    if your package is in a subdirectory of the current directory, it will be found.
     * 2. You can specify a directory path or paths by setting the CLASSPATH environmental variable.
     * 3. Third, you can use the ­classpath option with java and javac to specify the path to your classes. It is
     *    useful to point out that, beginning with JDK 9, a package can be part of a module, and thus found on the
     *    module path.
     *
     * For example, assuming the following package specification:
     * package mypack
     *
     * In order for a program to find mypack, the program can be executed from a directory immediately above mypack, or
     * CLASSPATH must be set to include the path to mypack, or the -classpath option must specify the path to mypack
     * when the program is run via command java.
     * */

    /** 7.1.3 Packages and member access
     * Before we continue, it is important to note that the modules feature added by JDK 9 also offers another dimension
     * to accessibility, but here we focus strictly on the interplay between packages and classes. The following table
     * shows the class member access rules:
     *                                       Private member  |  No modifier member | Protected member | Public member
     * access within class                          Yes               Y                    Y                Y
     * access within package by subclass            No                Y                    Y                Y
     * access within package                        No                Y                    Y                Y
     * access outside package by subclass           No                No                   Y                Y
     * access outside package                       No                No                  No                Y
     *
     * For a class, it can only have two modifier:
     * - public : it is accessible outside its package
     * - no modifier (default): it is accessible only by other code within its same package.
     *
     * Note, a class that is declared public must reside in a file by the same name.
     * */

    /** 7.1.4 Understanding protected members
     * New comers to Java are sometimes confused by the meaning and use of protected. As explained, the protected
     * modifier creates a member that is accessible within its package and to subclasses in other packages. Thus,
     * a protected member is available for all subclasses to use but is still protected from arbitrary access by code
     * outside its package.
     */

    /********************************* 7.2 Importing packages *************************************/

    /*
    * When you use a class from another package, you can fully qualify the name of the class with the name of its
    * package. However, if the package hierarchy is too long, the code is difficult to read. Java provide the import
    * statement. Using import, you can bring one or more members of a package into view. Here is the general form of
    * the import statement:
    * import pkg.classname;
    * If you want to import the entire contents of a package, use an asterisk(*) as the classname.
    *
    * In a Java source file, import statements occur immediately following the package statement(if it exists) and
    * before any class definitions.
    * */

    /********************************* 7.3 Java's standard packages *************************************/

    /*
    * Java defines a large number of standard classes that are available to all programs. This class library is
    * often referred to as the Java API (Application Programming Interface). The Java API is stored in packages.
    * At the top of the package hierarchy is java. Descending from java are several subpackages. Here are a few
    * examples:
    * - java.lang: Contains a large number of general-purpose classes
    * - java.io: Contains I/O classes
    * - java.net: Contains classes that support networking
    * - java.util: Contains a large number of utility classes, including the Collection Framework.
    *
    * The System class which we use to print belongs to java.lang. The java.lang package is unique because it is
    * imported automatically into every Java program. This is why we do need to do import java.lang to call System.
    * All other packages, we need to import explicitly if we want to use class which they provide.
    * */

    /************************************** 7.4 Java's interfaces *************************************/
    /*
    * In object­oriented programming, it is sometimes helpful to define what a class must do but not how it will do it.
    * You have already seen an example of this: the abstract method. In an abstract class, the abstract method specifies
    * the interface to the method but not the implementation. In Java, you can fully separate a class’ interface from
    * its implementation by using the keyword interface.
    *
    * An interface is syntactically similar to an abstract class, in that you can specify one or more methods that
    * have no method body. Those methods must be implemented by a class in order for their actions to be defined. Thus,
    * an interface specifies what must be done, but not how to do it. Once an interface is defined, any number of
    * classes can implement it. Also, one class can implement any number of interfaces.
    *
    * Two classes might implement the same interface in different ways, but each class still supports the same
    * set of methods. Thus, code that has knowledge of the interface can use objects of either class since the
    * interface to those objects is the same. By providing the interface keyword, Java allows you to fully utilize
    * the “one interface, multiple methods” aspect of polymorphism.
    *
    * Interface has the following general form:
    * access-modifier interface interface-name{
    * type var1=value;
    * ...
    * return-type method-name(param-list);
    * ...
    * }
    *
    * Note, similar to class, interface can only have public or no-modifier(default) for access control. With no-modifier,
    * interface is available only to other members of its package. When it's declared as public, the source file must
    * have the same name. And "var" can't be used as the interface name.
    *
    * In the traditional form of an interface, methods are declared using only their return type and signature.
    * They are, essentially, abstract methods. Thus, each class that includes such an interface must implement all of
    * its methods.
    * */

    /** 7.4.1 JDK 8 interfaces feature changes
     * JDK 8 added a feature to interface that made a significant change to its capabilities. Prior to JDK 8, an interface
     * could not define any implementation whatsoever. JDK 8 changed this. Today, it is possible to add a default
     * implementation to an interface method. Furthermore, static interface methods are now supported, and beginning
     * with JDK 9, an interface can also include private methods. Thus, it is now possible for interface to specify
     * some behavior. However, such methods constitute what are, in essence, special­use features, and the original
     * intent behind interface still remains. Therefore, as a general rule, you will still often create and use
     * interfaces in which no use is made of these new features. For this reason, we will begin by discussing the
     * interface in its traditional form. New interface features are described at the end of this section.
     * */

    /** 7.4.2 Methods and variables in interface
     * In an interface, methods are all (implicitly) public.
     * Variables declared in an interface are not instance variables. Instead, they are implicitly public, final, and
     * static and must be initialized. Thus, they are essentially constants.
     *
     * Check the Interface Series, it has three method.
     * */

    /************************************** 7.4 Implementing interfaces *************************************/

    /*
    * Once an interface has been defined, one or more classes can implement that interface. The implements clause looks
    * like this:
    * class class-name extends superclass implements interface-name1,interface-name2{
    *  // class body
    * }
    *
    * The methods that implement an interface must be declared public. The type signature of the implementing method
    * must match exactly the type signature in the interface definition. Check the class breakingBad to see an interface
    * implementation example.
    *
    * It is both permissible and common for classes that implement interfaces to define additional members of their
    * own. For example, we can add the method getPrevious() in class BreakingBad.
    * */

    /************************************** 7.5 Using interfaces References *************************************/

    /*
    * We can create an interface reference variable which can refer to any object that implements its interface.
    * When you call a method on an object through an interface reference, it is the version of the method implemented
    * by the object that is executed. It's very similar to the superclass and subclass reference mechanism.
    * Check the code fragment 7.5
    * */

    /************************************** 7.6 Variables in interfaces *************************************/

    /*
    * As mentioned, variables can be declared in an interface, but they are implicitly public, static, and final.
    * At first glance, you might think that there would be very limited use for such variables, but the opposite is
    * true. Large programs typically make use of several constant values that describe such things as array size,
    * various limits, special values, and the like. Since a large program is typically held in a number of separate
    * source files, there needs to be a convenient way to make these constants available to each file. In Java,
    * interface variables offer one solution.
    *
    * Check the interface Series, we added a variable int MAX_EPISODE_NUM=100, as you can see, we don't need to add
    * public static final, but int MAX_EPISODE_NUM=100 in an interface is equal to public static final int MAX_EPISODE_NUM=100,
    *
    * Note that the technique of using an interface to define shared constants is controversial. Use it wisely.
    * */

    /************************************** 7.7 Interfaces can be extended *************************************/

    /*
    * One interface can inherit another by use of the keyword extends. The syntax is the same as for inheriting
    * classes. When a class implements an interface that inherits another interface, it must provide implementations
    * for all methods required by the interface inheritance chain.
    * */

    /************************************** 7.8 Default interfaces methods *************************************/

    /*
    * As mentioned before, JDK8 added default method to interface, during its development, the default method was also
    * referred as extension method.
    *
    * A primary motivation for the default method was to provide a means by which interfaces could be expanded
    * without breaking existing code. Recall that there must be implementations for all methods defined by an
    * interface. In the past, if a new method were added to a popular, widely used interface, then the addition of
    * that method would break existing code because no implementation would be found for that method. The default
    * method solves this problem by supplying an implementation that will be used if no other implementation is
    * explicitly provided. Thus, the addition of a default method will not cause preexisting code to break.
    *
    * Another motivation for the default method was the desire to specify methods in an interface that are,
    * essentially, optional, depending on how the interface is used. For example, an interface might define a group
    * of methods that act on a sequence of elements. One of these methods might be called remove( ), and its purpose
    * is to remove an element from the sequence. However, if the interface is intended to support both modifiable
    * and non­modifiable sequences, then remove( ) is essentially optional because it won’t be used by non­modifiable
    * sequences. In the past, a class that implemented a non­modifiable sequence would have had to define an empty
    * implementation of remove( ), even though it was not needed. Today, a default implementation for remove( ) can be
    * specified in the interface that either does nothing or reports an error. Providing this default prevents a class
    * used for non­modifiable sequences from having to define its own, placeholder version of remove(). Thus, by
    * providing a default, the interface makes the implementation of remove() by a class optional.
    *
    * It is important to point out that the addition of default methods does not change a key aspect of interface: an
    * interface still cannot have instance variables. Thus, the defining difference between an interface and a class
    * is that a class can maintain state information, but an interface cannot. Furthermore, it is still not possible
    * to create an instance of an interface by itself. It must be implemented by a class. Therefore, even though,
    * beginning with JDK 8, an interface can define default methods, the interface must still be implemented by a
    * class if an instance is to be created.
    * */

    /** 7.8.1 Define a default method in an interface
     * We used the keyword default to define a default method. Check getMaxEpisodeNum() in class Series. As it is a
     * default method, the class which implements this interface do not need to implement it and can use it directly.
     * Check example in code fragment 7.8. The gt object is type GameOfThrone which implements interface Series.
     * Inside Series, we have defined a default method getNextArray. Notice Class GameOfThrone does not implement
     * this method, and object gt can use it directly
     *
     * In this example, we see two major benefits:
     * 1. We can gracefully evolve interfaces over time without breaking existing code.
     * 2. It provides optional functionality without requiring that a class provide a placeholder implementation when
     *    that functionality is not needed.
     * */

    /************************************** 7.9 Multiple Inheritance issues *************************************/

    /*
    * As explained before, Java does not support the multiple inheritance of classes (one subclass can extend from only
    * one super class). Now an interface can include default methods, you might be wondering if an interface can provide
    * a work around of this restriction. The answer is no. The key difference between abstract class and interface is:
    * a class can maintain state information, but an interface cannot.
    *
    * Default method in interface does produce multiple inheritance problems: name conflict. For example, if we have a
    * class called MyClass which implement two interfaces A and B. A and B both provide a default method called reset().
    * Which version of the method reset() will be used in MyClass? Or, what if MyClass provides its own implementation
    * of the method?
    *
    * Java defines a set of rules that resolve above conflicts:
    * 1. In all cases, a class implementation takes priority over an interface default implementation.
    * 2. If a class inherits two interfaces that both have the same default method, if the class does not override
    *    that method, then an error will result.
    * 3. In cases in which one interface inherits another, with both defining a common default method, the inheriting
    *    interface's version of the method takes precedence.
    *
    * It's possible to refer explicitly to a default implementation by using a new form of super. Its general form is
    * shown here: InterfaceName.super.methodName()
    * For example, if B extends A, and B wants to refer to A's default method for reset(). it can use this statement:
    * A.super.reset()
    *
    * */

    /************************************** 7.10 Use static methods in an interface ********************************/

    /*
    * JDK 8 added another new feature to interface: define one or more static methods. Like static methods in a class,
    * a static method defined by an interface can be called independently of any object. Thus, no implementation of
    * the interface is necessary, and no instance of the interface is required in order to call a static method.
    * Instead, a static method is called by specifying the interface name, followed by a period, followed by the
    * method name. Here is the general form:
    * InterfaceName.staticMethodName
    *
    * Check interface A. It has a static method getInterfaceId. In code fragment 7.10. we can call the static method
    * with interface name without any class or object implementation.
    * */

    /************************************** 7.11 private interface methods ********************************/

    /*
    * Beginning with JDK 9, an interface can include a private method. A private interface method can be called only
    * by a default method or another private method defined by the same interface. It can not be accessed outside of
    * the interface including the sub-interface.
    *
    * The key benefit of a private interface method is that it lets two or more default methods use a common piece of
    * code, thus avoiding code duplication.
    *
    * Check the interface AdvanceSeries, Notice that both getNextArray( ) and skipAndGetNextArray( ) use the private
    * getArray( ) method to obtain the array to return. This prevents both methods from having to duplicate the same
    * code sequence. Keep in mind that because getArray( ) is  private, it cannot be called by code outside Series.
    * Thus, its use is limited to the default methods inside Series.
    * */

    public static void main(String[] args){

        /** 7.4 Implementing interface*/
        BreakingBad bb=new BreakingBad();
        for(int i=0;i<5;i++){
            System.out.println("next episode is :"+bb.getNext());
        }
        bb.setStart(15);
        for(int i=0;i<5;i++){
            System.out.println("next episode is :"+bb.getNext());
        }

        /** 7.5 Using interface references*/
        Series mySeries;
        GameOfThrone gt=new GameOfThrone();

        mySeries=gt;
        for(int i=0;i<5;i++){
            System.out.println("My series is :"+mySeries.getClass().getName());
            System.out.println("next episode is :"+mySeries.getNext());
        }

        mySeries=bb;
        for(int i=0;i<5;i++){
            System.out.println("My series is :"+mySeries.getClass().getName());
            System.out.println("next episode is :"+mySeries.getNext());
        }

        /** 7.8 default method*/
        System.out.println("Max episode number is :"+bb.getMaxEpisodeNum());
        int[] nextNEpisode = gt.getNextArray(3);
        System.out.println("Next n episode is : "+ Arrays.toString(nextNEpisode));


        /** 7.9 Multiple Inheritance*/
        MultipleInheritanceExp mlExp=new MultipleInheritanceExp();
        mlExp.reset();
        mlExp.getAReset();

        /** 7.10 Static method in interface*/
        int id = A.getInterfaceId();
        System.out.println("Interface id is "+id );
    }

}
