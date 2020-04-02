package org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source;

import org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source.zoo.Goat;

import java.lang.reflect.*;

public class ReflectionApplicationExample {
    public static void exp1() {
        try {
            //get a class information at runtime based on its full qualified name
            //the forName() method of Class to get a class object for java.awt.Dimension.
            Class c = Class.forName("java.awt.Dimension");

            System.out.println("Constructors information:");
            Constructor[] constructors = c.getConstructors();
            for (Constructor constructor : constructors) {
                System.out.println(constructor);
            }

            System.out.println("Fields information:");
            Field[] fields = c.getFields();
            for (Field field : fields) System.out.println(field);

            System.out.println("Methods information:");
            Method[] methods = c.getMethods();
            for (Method method : methods) System.out.println(method);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void exp2() {
        MyObject o1 = new MyObject("foo", 18);
        Class c = o1.getClass();

        /** get public methods information*/
        System.out.println("Public methods information:");
        /*
         * getDeclaredMethods() method returns an array of Method objects that describe only the methods declared by
         * this class. Methods inherited from superclasses such as Object are not included.
         * */
        Method[] methods = c.getDeclaredMethods();
        for (Method method : methods) {
            int modifier = method.getModifiers();
            /*
             * The Modifier class provides a set of isX methods. The full list can be found at
             * https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Modifier.html
             * For example, we use isPublic to check if method is public or not*/
            if (Modifier.isPublic(modifier)) System.out.println(" " + method.getName());

            /*
             * Modifier also includes a set of static methods that return the type of modifiers that can be applied to
             * a specific type of program element. For example, methodModifiers() returns the modifiers that can be
             * applied to a method. Each method returns flags, packed into an int, that indicate which modifiers are
             * legal. The modifier values are defined by constants in Modifier, which include PROTECTED, PUBLIC,
             * PRIVATE, STATIC, FINAL, and so on.
             * */
        }


        /** get declared field*/

        Field[] fields = c.getDeclaredFields();
        for (Field field : fields) {
            String name = field.getName();
            int modifier = field.getModifiers();
            System.out.println("Modifier: "+modifier+" Name: "+name);
        }
    }

    public static void exp3(){
        // we create an instance of class MyObject
        MyObject obj1=new MyObject("foo",18);
        MyObject obj2=new MyObject("bar",88);
        // use reflection to get the class based on an instance
        Class cls=obj1.getClass();

        System.out.println("Before changeAge method are called: ");
        obj2.showAge();

        /** use reflection to create a method object and invoke this method to make changes to an object*/
        try {
            // creates an object of a method of a class by providing the
            // method name and parameter class as arguments to the getDeclaredMethod
            Method methodCallChangeAge = cls.getDeclaredMethod("changeAge", int.class);
             // remove the modifier access control, try to comment this line, you will see illegalAccessException.
            methodCallChangeAge.setAccessible(true);
            // invokes the method at runtime
            methodCallChangeAge.invoke(obj2, 19);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println("After changeAge method are called: ");
        obj2.showAge();

        /** use reflection to create a field object of a class and use it to make changes to an object*/

        System.out.println("Before field 'name' change are made: ");
        obj2.showAll();
        // creates object of the desired field by providing the name of field as argument to the getDeclaredField method
        Field field = null;
        try {
            field = cls.getDeclaredField("name");
            // allows the object to access the field irrespective of the access specifier used with the field
            field.setAccessible(true);
            // takes object and the new value to be assigned to the field as arguments
            field.set(obj2, "JAVA");
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("After field 'name' change are made: ");
        obj2.showAll();

    }

    public static void exp4(){
        Goat goat=new Goat("toto");
        Class<? extends Goat> goatClass = goat.getClass();

        /** A. Get class name
        * 1. returns basic name of the object as it would appear in it's declaration.
        * 2 and 3 returns fully qualified class name*/
        System.out.println("Simple name: "+goatClass.getSimpleName());
        System.out.println("full name: "+goatClass.getName());
        System.out.println("Canonical name: "+goatClass.getCanonicalName());

        /**
         *  B. Create an Class object of the Goat class if we only know it's fully qualified class name
         * In most cases, we may need to use the forName approach rather than the full blown instantiation since
         * that would be an expensive process in the case of memory heavy classes.
         * */
        try {
            Class<?> gClass = Class.forName("org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source.zoo.Goat");
            System.out.println("Simple name: "+goatClass.getSimpleName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        /** C. Get package info
         * */
        Package pkg = goatClass.getPackage();
        System.out.println("Package name of goat class: "+pkg.getName());

        /** D. Get super class info*/
        Class<?> animalClass = goatClass.getSuperclass();
        System.out.println("Super class name of goat class: "+animalClass.getName());
        // we can also apply it to java predefine class
        String str="toto";
        System.out.println("Super class name of String: "+str.getClass().getSuperclass().getName());

        /** E. Get implemented interface info*/
        Class<?>[] goatImpInterface = goatClass.getInterfaces();
        Class<?>[] animalImpInterface = animalClass.getInterfaces();
        for(Class c:goatImpInterface) System.out.println("Goat class implemented interface: "+c.getName());
        for(Class c:animalImpInterface) System.out.println("Animal class implemented interface: "+c.getName());

        /** F. create an goat object by using constructor reflection*/
        try {
            Constructor<? extends Goat> goatConstructor = goatClass.getConstructor(String.class);
            Goat goatObj2 = goatConstructor.newInstance("foo bar");
            System.out.println("New goat has name: "+goatObj2.getName());
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
