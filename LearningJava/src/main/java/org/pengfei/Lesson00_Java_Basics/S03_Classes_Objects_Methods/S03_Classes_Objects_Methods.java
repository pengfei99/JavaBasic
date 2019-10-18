package org.pengfei.Lesson00_Java_Basics.S03_Classes_Objects_Methods;

import org.pengfei.Lesson00_Java_Basics.S03_Classes_Objects_Methods.source.AdvVehicle;
import org.pengfei.Lesson00_Java_Basics.S03_Classes_Objects_Methods.source.ThisExp;
import org.pengfei.Lesson00_Java_Basics.S03_Classes_Objects_Methods.source.Vehicle;

public class S03_Classes_Objects_Methods {
    /******************************************** 3. Introduction *******************************************/

    /*
     * In this section, we will learn the class, which defines the nature of an object. Within a class we define data
     * and code that acts upon that data. The code is contained in methods. More precisely, we will learn
     * - The fundamentals of the class
     * - Object creation
     * - Understand how reference variables are assigned
     * - Create methods, return values, and use parameters
     * - Use the return keyword, return a value from a method
     * - Add parameters to a method
     * - Utilize constructors
     * - Create parameterized constructors
     * - Understand new
     * - Understand garbage collection
     * - Use the this keyword
     * */

    /******************************************* 3.1 Class fundamentals ************************************/

    /*
     * A class is a template that defines the form of an object. It specifies both the data and code that will
     * operate on that data. Java uses a class specification to construct objects.
     *
     * Objects are instances of a class. Thus, a class is essentially a set of plans that specify how to build an
     * object. A class is a logical abstraction. Only after an object of that class has been created, a physical
     * representation of that class exists on memory.
     *
     * Methods and fields (variables inside a class) that constitute a class are called members of the class. The data
     * members are also referred to as instance variables. */

    /** 3.1.1 General form of a Class
     * class <name>{
     *     // declare instance variables
     *     type var1;
     *     type var2;
     *     ...
     *     // declare methods
     *     type method1(params,...) {
     *         statements
     *     }
     *     ...
     * }
     *
     * Although there is no syntactic rule that enforces it, a well-designed class should define one and only one
     * logical entity. For example, a class that stores information about a person should not contain information about
     * the stock market which the person is buying. Putting information of different logical entity into the same class
     * will quickly destructed your code.
     *
     * You can image building classes is like building the table diagram of a database, each class is a table.
     * */

    /** 3.1.2 Defining a class
     *
     * To illustrate classes, we will develop a class that encapsulate information about vehicles. We called this class
     * Vehicle. It contains following information:
     * - max number of passengers
     * - fuel capacity
     * - avg fuel consumption(mile/gallon)
     *
     *  Check the class Vehicle,
     *
     *  A class definition creates a new data type, which is called Vehicle. Remember class is only a type definition.
     *  It does not create an actual object. To actually create a Vehicle object, we need the following code:
     *  Vehicle minivan= new Vehicle(); After this code executes, an object will be created in JVM, "minivan"
     *  refers to  this object which is an instance of Vehicle.
     *
     *  Each instance of a class has its own attributes which are independent from other instance. To access theses
     *  attributes, we need to use the .(dot) operator. It has the normal form: objectName.attributeName. For example
     *  minivan.mpg will return the fuel consumption value of the minivan object.
     *
     *  Check the DefiningClassExp() and Vehicle class for a complete example
     * */

    /** 3.1.3 How objects are created
     *  For example, the statement Vehicle minivan=new Vehicle(7,16,21); performs two functions.
     *
     *  First it declares a variable of type Vehicle. It does not define an object, it can refer to an object.
     *
     *  Second action starts with new keyword, new operator dynamically allocates(allocates at run time) memory for
     *  an object and return a reference to it. This reference is, essentially, the address in memory of the allocated
     *  by new. In java, all class objects must be dynamically allocated.
     *
     *  If we look new operator more precisely, when we call new className, new operator will call the class constructor,
     *  if the class does not have constructor, the default constructor supplied by java will be called.
     *
     *  Since memory is finite, it is possible that new will not be able to allocate memory for an object because
     *  insufficient memory exists. If this happens, a run-time exception will occur.
     *
     * */

    /************************************** 3.2 Reference variables and assignment ********************************/
    /*
     * In an assignment operation, object reference variable act differently than primitive type variable reference.
     *
     * For primitive type, when an assignment is called, the left side variable receives a copy(not the same obj) of the
     * value of the right side.
     *
     * For object, the situation is more complicated, because you are changing the object that the reference variable
     * refers to. For example, check RefVarAssignmentExp(), the variable car1 and car2, refer to the same object, no
     * copy is done, which means if I modify car2 attributes value, car1 attributes value changes too.
     *
     * Although car1 and car2 refers to the same object they are not linked in any other way. We can change car2 to
     * refer another object without impacting car1 at all.
     * */

    /**************************************** 3.3 Methods *********************************************************/

    /*
    * Methods contains the logic which can manipulate the data inside a Class. The most common methods are getters and
    * setters which allows other program to get and set data of a Class.
    *
    * The general form of a method is:
    * return-type method-name (param-list){
    *   statement
    * }
    *
    * The return-type can be any valid type, including primitive types, classes in jdk, or classes that you created.
    * The method-name can be any legal identifier other than those already used by other items within the current scope.
    * The param-list is a sequence of type and identifier pairs separated by commas. Parameters are essentially
    * variables that receive the value of the arguments passed to the method when it is called. If the method has
    * no parameters, the parameter list will be empty.
    * */

    /** 3.3.1 Adding getter and setter method for Vehicle class attributes
     * To illustrate how we create methods in class Vehicle, we created a new Class AdVehicle. In this new
     * representation, we have a setter and getter for each attributes, thus we don't need to expose attributes as
     * public.
     *
     * */

    /** 3.3.2 Using parameters
     * It is possible to pass one or more values to a method when the method is called. Recall that a value passed
     * to a method is called an argument. Inside the method, the variable that receives the argument is called a
     * parameter. Parameters are declared inside the parentheses that follow the method’s name.
     *
     * For example, we create a new method called fuelConsumption in AdvVehicle class, it takes a range(int) as argument
     * this method returns the fuel consumption of a vehicle to run the given range.
     * */

    /**************************************** 3.4 Constructors *********************************************************/

    /*
    *  A constructor initializes an object when it is created. It has the same name as its class and is syntactically
    * similar to a method without a return type. Typically, you will use a constructor to give initial values to
    * instance variables defined by the class, or to perform any other startup procedures required to create a
    * fully formed object.
    *
    * In java, all classes have constructors, whether you define one or not. Because java automatically provides a
    * default constructor. In this case, non-initialized member variables have their default vales, which are
    * 0,nll and false. Once you define your own constructor, the default constructor is no longer used.*/


    /**************************************** 3.5 Garbage collection *******************************************/

    /* As we have seen, objects are dynamically allocated from a pool of free memory by using the new operator.
    * As explained, memory is not infinite, and the free memory can be exhausted. Java use Garbage collection to
    * free memory from unused objects automatically.
    *
    * To explain GC mechanism simply, when no references to an object exist, that object is assumed to be no longer
    * needed, and will be destroyed. the occupied memory is released.
    *
    * Garbage collection occurs only sporadically during the execution of your program. It will not occur simply
    * because one or more objects exists that are no longer used. For efficiency, the garbage collector will
    * usually run only when two conditions are both met:
    * - there are objects to recycle,
    * - there is a reason to recycle them(no more memory).
    *
    * Note, GC takes time and cpu heavy, so the Java run-time system does it only when it is appropriate. Thus, you
    * can't know precisely when GC will take place
    * */

    /**
     * No new operator for primitive types
     * Java's primitive types are not implemented as objects. Rather, because of efficiency concerns, they are
     * implemented as "normal" variables. A variable of a primitive type actually contains the value that you have
     * given it. So no object creation needed.
     * */

    /*************************************** 3.6 The this keyword ******************************************/

    /*
    * When a method is called inside an "object", an implicit argument is automatically passed to the method. This
    * argument is a reference to the invoking object(that is, the object on which the method is called.). This argument
    * is "this"
    *
    * We often use "this" to call class field if there are local variable which have the same identifier. Check
    * the thisExp class */

    public static void main(String args[]) {
        S03_Classes_Objects_Methods main = new S03_Classes_Objects_Methods();

         // Defining Class example
         //main.DefiningClassExp();

        // Reference var assignment example
        // main.RefVarAssignmentExp();

        // method example
       //  main.MethodExp();

        // this statement example
        main.ThisStatementExp();
    }

    public void DefiningClassExp() {
        // create a minivan object
        Vehicle minivan = new Vehicle(7, 16, 21);
        int maxRange = minivan.getMaxRange();
        System.out.println("Minivan can carry " + minivan.maxPassengers + " people, with a range of " + maxRange);

        // create a sportscar object
        Vehicle sportscar = new Vehicle(2, 14, 12);
        int sMaxRange = sportscar.getMaxRange();
        System.out.println("Sportscar can carry " + sportscar.maxPassengers + " people, with a range of " + sMaxRange);


    }

    public void RefVarAssignmentExp() {
        Vehicle car1 = new Vehicle(7, 16, 21);
        Vehicle car2 = car1;

        // change car2 max Passenger
        car2.maxPassengers = 2;
        // show the car1 mas passenger, init value is 7
        System.out.println("Car 1 has max passenger : " + car1.maxPassengers);
    }

    public void MethodExp(){
        AdvVehicle minivan = new AdvVehicle(7, 16, 21);
        int maxRange = minivan.getMaxRange();
        // we use getter to get the value of attribute
        System.out.println("Minivan can carry " + minivan.getMaxPassengers() + " people, with a range of " + maxRange);

        int range=900;
        double fuelNum=minivan.fuelConsumption(900);
        System.out.println("Minivan needs " + fuelNum + " gallons of fuel to run a range of " + range+" miles");

    }

    // this statement example
    public void ThisStatementExp(){
        ThisExp tmp=new ThisExp();
        tmp.getLocalVarValue();
        tmp.getFieldValue();
    }
}
