package org.pengfei.Lesson01_Creating_And_Destroying_Objects.S01_staticFactoryMethod;

public class MyServiceImplementation implements MyService{
    @Override
    public void doSomething() {
        System.out.println("In MyServiceImplementation class and doSomething method");
    }
}
