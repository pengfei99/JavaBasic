package org.pengfei.pattern.visitor;

/*
* The visitor design pattern allow us to inject same code (visitor  code)to a set of objects
* which accept the same visitor.
*
* In this example, computer and computer parts accept two kinds of visitor. ComputerPartDisplayVisitor shows the type of computer parts
* ComputerPartRepairVisitor repair the computer parts.
*
* The computer part object code are not polluted by the display and repair code, and these code are centralized on the visitor object
*
* */
public class main {
    public static void main(String[] args){
        Computer computer=new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
        computer.accept(new ComputerPartRepairVisitor());
    }
}
