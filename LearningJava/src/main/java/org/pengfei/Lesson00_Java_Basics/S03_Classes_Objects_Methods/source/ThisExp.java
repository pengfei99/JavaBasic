package org.pengfei.Lesson00_Java_Basics.S03_Classes_Objects_Methods.source;

public class ThisExp {

    private String var1="This is the field value";

    /* In this method, the var1 value is overwrite by local var, if we call var1 directly, we will print
    * the local var value */
    public void getLocalVarValue(){
        String var1="This is the local variable value";
        System.out.println("var1 value is "+var1);
    }

    /* In this method, the var1 value is overwrite by local var, We can use this.var1 to get the field value. */
    public void getFieldValue(){
        String var1="This is the local variable value";
        System.out.println("var1 value is "+this.var1);
    }
}
