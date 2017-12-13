package org.pengfei.pattern.visitor;

public class Monitor implements ComputerPart {

    public Monitor(){
        System.out.println("Monitor building monitor");
    }

    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
