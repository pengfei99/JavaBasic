package org.pengfei.Lesson02_Creating_And_Destroying_Objects.S01_staticFactoryMethod;

/**
 * Hello world!
 *
 */
public class L01_S01_Static_Factory_methods {
    /********************************************1.1 Static Factory methods for creating an instance ******************/

    /* The traditional way for a class to allow a client to obtain an instance is to provide a public constructor.
    * There is another technique that should be a part of every programmer’s toolkit. A class can provide a public
    * static factory method, which is simply a static method that returns an instance of the class
    *
    * Note that a static factory method is not the same as the Factory Method pattern from Design Patterns. The
    * static factory method described in this item has no direct equivalent in Design Patterns.
    *
    * Static factory method compares to public constructor has advantages and disadvantages:
    *
    * Advantages : 1. Static factory method can have names, with a well-chosen name, it is easier to use and the
    *                 resulting client code easier to read.
    *                 For public constructor, first it can't have name. Second a class can have only a single
    *                 constructor with a given signature (e.g String arg1, Int arg2), There is a work around we can
    *                 change the argument order. This is a bad idea!!!, it's really hard to use this kind of the class.
    *
    *              2. Unlike constructors, Static Factory methods are not required to create a new object each time they
    *                 are invoked. This allows immutable classes to use preconstructed instances, or to cache instances
    *                 as they're constructed. This avoid creating unnecessary duplicate objects. This technique is
    *                 similar to the Flyweight pattern
    *
    *              3. Unlike constructors, Static Factory methods can return an object of any subtype of their return
    *                 type. This gives you great flexibility in choosing the class of the returned object. One
    *                 application of this flexibility is that an API can return objects without making their classes
    *                 public. This techique lends itself to "interface-based frameworks"
    *
    *              4. The class of the return object can vary from call to call as a function of the input parameters.
    *                 Any sub-type of the declared return type is permissible. The class of the returned object can also
    *                 vary from release to release.
    *
    *              5. The class of the returned object need not exist when the class containing the method is written.
    *                 Such flexible static factory methods from the basis of "service provider framework", like JDBC api.
    *                 (See the getService method)
    *
    * Dis-advantage:
    *
    *              1. The main limitation of providing only static factory methods is that classes without public or
    *                 protected constructors cannot be subclassed.
    *
    *              2. The static factory methods are hard for programmers to find. So it can be difficult to figure out
    *                 how to instantiate a class that only provides static factory methods. To overcome this problems,
    *                 we need to draw attention to static factories in class or interface documentation and by adhering
    *                 to common naming conventions. Below list are some common names for static factory methods.
    *
    *                 -- from: A type-conversion method that takes a single parameter and returns a corresponding
    *                         instance of this type, for example:
    *                                    Date d = Date.from(instant);
    *
    *                 -- of: An aggregation method that takes multiple parameters and returns an instance of this
    *                        type that incorporates them, for example:
    *                            Set<Rank> faceCards = EnumSet.of(JACK, QUEEN, KING);
    *
    *                 -- valueOf: A more verbose alternative to from and of, for example:
    *                          BigInteger prime = BigInteger.valueOf(Integer.MAX_VALUE);
    *
    *                 -- instance or getInstance: Returns an instance that is described by its parameters (if any) but
    *                                             cannot be said to have the same value, for example:
    *                                             StackWalker luke = StackWalker.getInstance(options);
    *                                             create or newInstance—Like instance or getInstance, except that the
    *                                             method guarantees that each call returns a new instance, for example:
    *                                             Object newArray = Array.newInstance(classObject, arrayLen);
    *
    *                 -- getType: Like getInstance, but used if the factory method is in a different class. Type is the
    *                             type of object returned by the factory method, for example:
    *                             FileStore fs = Files.getFileStore(path);
    *
    *                 -- newType: Like newInstance, but used if the factory method is in a different class. Type is
    *                             the type of object returned by the factory method, for example:
    *                             BufferedReader br = Files.newBufferedReader(path);
    *
    *                 -- type: A concise alternative to getType and newType, for example:
    *                          List<Complaint> litany = Collections.list(legacyLitany);
    *
    * In summary, static factory methods and public constructors both have their uses, and it pays to understand
    * their relative merits. Often static factories are preferable, so avoid the reflex to provide public constructors
    * without first considering static factories.
    *
    *
    * */

    public static void main( String[] args )
    {
        /* We call the static factory method without creating the object */
        Boolean valueOfTrue = StaticFacMethodsDemo.valueOf(true);
        System.out.println("Value of true is :"+valueOfTrue);

        /* The name we give to method is more human readable*/
        Boolean reverseValueOfTrue = StaticFacMethodsDemo.reverseValueOf(true);
        System.out.println("Reverse Value of true is :"+reverseValueOfTrue);
         // you can also set system property staticlly with "java -DServiceImplemetationClassName=MyServiceImplementation MyApp"

        System.setProperty("ServiceImplementationClassName","org.pengfei.Lesson01_Creating_And_Destroying_Objects.S01_staticFactoryMethod.MyServiceImplementation");
        /* //check the property value
        String className = System.getProperty("ServiceImplemetationClassName");
        System.out.println("className value stored in the Property: "+className);*/

        /* With the getService method, when we deliver the lib, we don't need to provide the implementation of MyService
        * The client of the lib can implement the MyService locally and specify it when using the lib by setting system
        * property. */
        MyService myService=StaticFacMethodsDemo.getService();
        myService.doSomething();

    }

    /************************************************************************************************************
     * *************************************Annexe ***********************************************************
     * *********************************************************************************************************/

    /******************* A.1 Service provider framework pattern *****************************************/
    /* A service provider framework is a system in which multiple service providers implement a service,
    * and the system makes the implementations available to its clients, decoupling them from the implementations.
    *
    * There are 3 essential components of a service provider framework:
    * - A service interface, which providers implement.
    * - A provider registration API, which the system uses to register implementations, giving clients access to them.
    * - A service access API, which client chooses to obtain an Instance of the service.
    *
    * In this lesson code example, we use MyService as the service interface.
    * In class StaticFacMethodsDemo, we have a static factory method(getService) which plays the role of the service
    * access API. In the main method, we use system setProperty method to play the role of provider registration API.
     * */



}
