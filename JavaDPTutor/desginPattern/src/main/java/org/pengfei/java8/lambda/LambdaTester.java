package org.pengfei.java8.lambda;

public class LambdaTester
{
    public static void main (String[] args){
        MathOperation addition= (int a, int b)->a+b;
        GreetingService sayHello=(message, user) -> System.out.println("Hello " + message+" to "+user);
        LambdaTester tester= new LambdaTester();
        System.out.println(tester.operate(5,3,addition));
        sayHello.sayHello("greetings","pengfei");
    }

    public interface MathOperation{
        int operation(int a, int b);
    }

    public interface GreetingService{
        //void sayMessage(String message);
        void sayHello(String message, String user);
    }

    private int operate(int a, int b, MathOperation mathOperation){
        return mathOperation.operation(a,b);
    }


}
