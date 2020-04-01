package org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ReflectionApplicationExample {
    public static void exp1(){
        try {
            //get a class information at runtime based on its full qualified name
            //the forName() method of Class to get a class object for java.awt.Dimension.
            Class c=Class.forName("java.awt.Dimension");

            System.out.println("Constructors information:");
            Constructor[] constructors = c.getConstructors();
            for(Constructor constructor:constructors){
                System.out.println(constructor);
            }

            System.out.println("Fields information:");
            Field[] fields = c.getFields();
            for(Field field:fields) System.out.println(field);

            System.out.println("Methods information:");
            Method[] methods = c.getMethods();
            for(Method method:methods) System.out.println(method);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void exp2(){
        MyObject o1=new MyObject();
        Class c=o1.getClass();

        /**/
        System.out.println("Public methods information:");
        /*
        * getDeclaredMethods() method returns an array of Method objects that describe only the methods declared by
        * this class. Methods inherited from superclasses such as Object are not included.
        * */
        Method[] methods = c.getDeclaredMethods();
        for(Method method:methods){
            int modifier = method.getModifiers();
            /*
            * The Modifier class provides a set of isX methods. The full list can be found at
            * https://docs.oracle.com/javase/8/docs/api/java/lang/reflect/Modifier.html
            * For example, we use isPublic to check if method is public or not*/
            if(Modifier.isPublic(modifier)) System.out.println(" "+method.getName());

            /*
            * Modifier also includes a set of static methods that return the type of modifiers that can be applied to
            * a specific type of program element. For example, methodModifiers() returns the modifiers that can be
            * applied to a method. Each method returns flags, packed into an int, that indicate which modifiers are
            * legal. The modifier values are defined by constants in Modifier, which include PROTECTED, PUBLIC,
            * PRIVATE, STATIC, FINAL, and so on.
            * */
        }
    }
}
