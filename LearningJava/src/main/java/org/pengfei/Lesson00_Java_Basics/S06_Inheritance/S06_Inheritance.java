package org.pengfei.Lesson00_Java_Basics.S06_Inheritance;

import org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source.ColorTriangle;
import org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source.Triangle;
import org.pengfei.Lesson00_Java_Basics.S06_Inheritance.source.TwoDimensionShape;

public class S06_Inheritance {
    /******************************************** 6. Introduction *******************************************/

    /*
    * Inheritance is one of the three foundation principles of object-oriented programming. Because it allows the
    * creating of hierarchical classification. Using inheritance, you can create a general class that defines traits
    * common to a set of related items. This class can then be inherited by other, more specific classes, each adding
    * those things that are unique to it.
    *
    * In the language of Java, a class that is inherited is called a superclass. The class that does the inheriting
    * is called a subclass. Therefore, a subclass is a specialized version of a superclass. It inherits all of the
    * variables and methods defined by the superclass and adds its own, unique elements.
    *
    * In this section, we will learn the following key concepts:
    * - Understand inheritance basics
    * - Call superclass constructors
    * - Use super to access superclass members
    * - Create a multilevel class hierarchy
    * - Know when constructors are called
    * - Understand superclass references to subclass objects
    * - Override methods
    * - Use overridden methods to achieve dynamic method dispatch
    * - Use abstract classes
    * - Use final
    * - Know the Object class
    * */

    /******************************************** 6.1 Inheritance basics *******************************************/

    /*
    * Java supports inheritance by allowing one class to incorporate another class into its declaration. This is done
    * by using the extends keyword. Thus, the subclass adds to (extends) the superclass.
    *
    * In the following example, we use a super class TwoDimensionShape to define the attributes of a "generic" two
    * dimensional shape. The Triangle class create a specific type of TwoDimensionShape. The Triangle class includes
    * all of TwoDimensionShape and adds the field style, and the method area(), showStyle().
    *
    * Notice that, the access modifier of super class is inherited too.
    *
    * Super class is completely independent, stand-alone class. Being a superclass for a subclass does not mean that
    * the superclass cannot be used by itself. Check t1 and twoDShape object.
    *
    * Of course, an object of TwoDimensionShape has no knowledge of or access of any subclasses of TwoDimensionShape.
    *
    * You can specify only one superclass for any subclass that you create. Java does not support the inheritance of
    * multiple superclasses into a single subclass(Unlike C++). No class can be a superclass of itself.
    *
    *
    * */

    /** 6.1.1 Member access and inheritance
     * Inheriting a class does not overrule the private access restriction. Thus, even though a subclass includes all
     * of the members of its superclass, it cannot access those members of the superclass that have been declared
     * private. For example, if we change public double width to private, subclass such as Triangle can no longer access
     * the field width, method area will not compile again.
     *
     * Remember that a class member that has been declared private will remain private to its class. It is not
     * accessible by any code outside its class, including subclasses. If subclasses want to access the private super
     * class member, we need to use accessor methods.
     *
     * There are two general principles to decide a member is private or not:
     * - If an instance variable is to be used only by methods defined within its class.
     * - If an instance variable must be within certain bounds, then it should be private and made available only
     *   through accessor methods.
     * */

    /**************************** 6.2 Using super to call superclass constructors *******************************/

    /*
    * In a hierarchy, it is possible for both superclasses and subclasses to have their own constructors. This
    * raises an important question: What constructor is responsible for building an object of the subclass—the one
    * in the superclass, the one in the subclass, or both? The answer is this: The constructor for the superclass
    * constructs the superclass portion of the object, and the constructor for the subclass constructs the subclass
    * part. This makes sense because the superclass has no knowledge of or access to any element in a subclass. Thus,
    * their construction must be separate.
    *
    * A subclass can call a constructor defined by its superclass by use of the following form of super:
    * super(parameter-list);
    * The parameter-list specifies any parameters needed by the constructor in the superclass. The super() statement
    * must always be the first statement executed inside a subclass constructor. Check the Triangle constructor to
    * see an example.
    *
    * Triangle calls super() with parameter width, height, this causes the TwoDimensionShape constructor to be called.
    * Triangle only initialize the value of style.
    *
    * Any form of constructor defined by the superclass can be called by super(). The constructor executed will be the
    * one that matches the arguments. Check TwoDimensionShape constructor without parameters.
    *
    * */

    /**************************** 6.3 Using super to access superclass members *******************************/

    /*
    * There is a second form of super that acts somewhat like "this.class-member", except that it always refers
    * to the superclass of the subclass in which it is used. This usage has the following general form:
    * super.class-member
    * where class-member can be a method or an instance variable. This form of super is most applicable to situations
    * in which member names of a subclass hide members by the same name in the superclass. Check the showClassName and
    * showSuperClassName to see how we use super and this to get the className of super class and current class.
    *
    * Notice that super can not be used in a static method.
     * */

    /**************************** 6.4 Creating a multilevel hierarchy *******************************/
    /*
    * We can build hierarchies that contain as many layers of inheritance as you like. As mentioned, it is perfectly
    * acceptable to use a subclass as a superclass of another. For example, given three classes called A, B, and C,
    * C can be a subclass of B, which is a subclass of A. When this type of situation occurs, each subclass inherits
    * all of the traits found in all of its superclasses. In this case, C inherits all aspects of B and A
    *
    * For example, we can create a class ColorTriangle which extends Triangle and adds a field called color.
    * This example illustrates one other important point: super() always refers to the constructor in the closest
    * superclass. The super() in ColorTriangle calls the constructor in Triangle. The super( ) in Triangle calls the
    * constructor in TwoDimensionShape. In a class hierarchy, if a superclass constructor requires parameters, then
    * all subclasses must pass those parameters "up the line." This is true whether or not a subclass needs parameters
    * of its own.
    * */

    /**************************** 6.5 When are constructors executed? *******************************/

    /*
    * An important question may have occurred to you: When a subclass object is created, whose constructor is
    * executed first, the one in the subclass or the one defined by the superclass?
    *
    * The answer is that in a class hierarchy, constructors complete their execution in order of derivation, from
    * superclass to subclass. Further, since super() must be the first statement executed in a subclass’ constructor,
    * this order is the same whether or not super( ) is used. If super( ) is not used, then the default (parameterless)
    * constructor of each superclass will be executed.
    *
    * For example C extends B, B extends A, when we create an object of Class C, then the constructor of A will be first
    * executed, then B, then C.
    * */

    /**************************** 6.6 Super class references and subclass objects *******************************/

    /*
    * As you know, Java is a strongly typed language. Aside from the standard conversions and automatic promotions
    * that apply to its primitive types, type compatibility is strictly enforced. Therefore, a reference variable for
    * one class type cannot normally refer to an object of another class type.
    *
    * There is, however, an important exception to Java’s strict type enforcement. A reference variable of a superclass
    * can be assigned a reference to an object of any subclass derived from that superclass. In other words, a
    * superclass reference can refer to a subclass object.
    *
    * Check code section 6.6, We created a reference shapeRef, then we assign it with a ColorTriangle object. If
    * TwoDimensionShape is not the super class of ColorTriangle. The compiler will not compile. Because a
    * TwoDimensionShape reference requires a TwoDimensionShape object or its subclass object.
    *
    * */

    /***************************************** 6.7 Method overriding *************************************/

    /*
    * In a class hierarchy, when a method in a subclass has the same return type and signature as a method in its
    * superclass, then the method in the subclass is said to override the method in the superclass. When an overridden
    * method is called from within a subclass, it will always refer to the version of that method defined by the
    * subclass. The version of the method defined by the superclass will be hidden.
    *
    * Check the showMessage method which is presented at both TwoDimensionShape and Triangle. When we call showMessage
    * in Triangle, it's the subclass version of showMessage which is executed. If we want to use the super class version
    * we need to use the keyword super.
    * */

    /********************************* 6.8 Method overriding support polymorphism ********************************/

    /*
    * Method overriding forms the basis for one of Java’s most powerful concepts: dynamic method dispatch. Dynamic
    * method dispatch is the mechanism by which a call to an overridden method is resolved at run time rather than
    * compile time. Dynamic method dispatch is important because this is how Java implements run­time polymorphism.
    *
    * Let’s begin by restating an important principle: a superclass reference variable can refer to a subclass object.
    * Java uses this fact to resolve calls to overridden methods at run time. Here’s how. When an overridden method
    * is called through a superclass reference, Java determines which version of that method to execute based upon
    * the type of the object being referred to at the time the call occurs. Thus, this determination is made at
    * run time. When different types of objects are referred to, different versions of an overridden method will be
    * called. In other words, it is the type of the object being referred to (not the type of the reference variable)
    * that determines which version of an overridden method will be executed. Therefore, if a superclass contains a
    * method that is overridden by a subclass, then when different types of objects are referred to through a
    * superclass reference variable, different versions of the method are executed
    *
    * For example, in code fragment 6.8. The version of shapeRefExp.showMessage(); will be determined at run time by
    * the type of object being referred to.
    * */

    /** 6.8.1 Why overridden methods
     * As stated earlier, overridden methods allow Java to support run­time polymorphism. Polymorphism is essential
     * to object­oriented programming for one reason: it allows a general class to specify methods that will be
     * common to all of its derivatives, while allowing subclasses to define the specific implementation of some or
     * all of those methods. Overridden methods are another way that Java implements the “one
     * interface, multiple methods” aspect of polymorphism. Part of the key to successfully
     * applying polymorphism is understanding that the superclasses and subclasses form a
     * hierarchy that moves from lesser to greater specialization. Used correctly, the
     * superclass provides all elements that a subclass can use directly. It also defines those
     * methods that the derived class must implement on its own. This allows the subclass the
     * flexibility to define its own methods, yet still enforces a consistent interface. Thus, by
     * combining inheritance with overridden methods, a superclass can define the general
     * form of the methods that will be used by all of its subclasses.
     *
     *
     * */

    /*************************************** 6.9 Using abstract classes ***************************************/

    /**/

    public static void main(String[] args){

        /** 6.1 Inheritance basics*/
        Triangle t1=new Triangle(10.0,20.0,"filled");
        TwoDimensionShape twoDShape= new TwoDimensionShape(15.3,16.3);

        System.out.println("Triangle has width : "+t1.width+" height : "+t1.height+" style : "+t1.style);
        System.out.println(" Two dimension shape has width : "+twoDShape.width+" height : "+twoDShape.height);

        /** 6.3 super get member*/
        t1.showClassName();
        t1.showSuperClassName();

        /** 6.4 Multilevel hierarchy*/
        ColorTriangle ct=new ColorTriangle(8.8,6.6,"filled","red");
        ct.showColor();

        /** 6.6. Super class references*/
        TwoDimensionShape shapeRef;
        shapeRef=ct;

        /** 6.7 Method overriding*/
        twoDShape.showMessage();
        t1.showMessage();
        t1.showSuperMessage();

        /** 6.8 Method overriding support polymorphism */

        //Create a TwoDimensionShape reference
        TwoDimensionShape shapeRefExp;

        //assign it to an obj TwoDimensionShape
        shapeRefExp=twoDShape;
        shapeRefExp.showMessage();

        //assign it to an obj Triangle
        shapeRefExp=t1;
        shapeRefExp.showMessage();

        //assign it to an obj ColorTriangle
        shapeRefExp=ct;
        shapeRefExp.showMessage();

    }
}
