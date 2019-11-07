package org.pengfei.Lesson00_Java_Basics.S12_Generics.source;

import static java.lang.Math.*;

// We restrict type of T to class Number or subclass of Number, Number is the upperbound type of T.
public class NumericFunctions<T extends Number> {
    T num;

    public NumericFunctions(T num){
        this.num=num;
    }

    // Return the reciprocal
    double reciprocal(){

        // we can use method doubleValue here, because we know T extends Number, without this,
        // you will see compile error method is unknown.
        return 1 / num.doubleValue();
    }

    // return the factional component
    double fraction(){
        // same issue for method intValue
        return num.doubleValue()-num.intValue();
    }

    public boolean absEqual(NumericFunctions<?> ob){
        if(abs(num.doubleValue())==abs(ob.num.doubleValue())) return true;
        else return false;
    }

}
