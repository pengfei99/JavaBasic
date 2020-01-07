package org.pengfei.Lesson02_Creating_And_Destroying_Objects.S01_staticFactoryMethod;


public class StaticFacMethodsDemo {

    public static Boolean valueOf(boolean b){
        return b ? Boolean.TRUE:Boolean.FALSE;
    }

    public static Boolean reverseValueOf(boolean b){
        if (b) return Boolean.FALSE;
        else return Boolean.TRUE;
    }

    public static String StringValueOf(boolean b){
        return b?  "TRUE":"FALSE";
    }

    public static MyService getService() {
        MyService myService;
        try {
            /* Beware create object using reflection techniques requires the fully qualified class name. And the
            * fully qualified class name has the form packagename.classname. In our example, we must do a dynamic property setting
            * System.setProperty("ServiceImplementationClassName","org.pengfei.Lesson01_Creating_And_Destroying_Objects.S01_staticFactoryMethod.MyServiceImplementation");
        */
            Class<?> serviceImplementationClass = Class.forName(System.getProperty("ServiceImplementationClassName"));
            myService=(MyService) serviceImplementationClass.newInstance();
        } catch (Throwable t) {
            throw new Error(t);
        }
        return myService;
    }
}

/*/DATA/IdeaProjects/LearningJava/src/main/java/org/pengfei/*/
