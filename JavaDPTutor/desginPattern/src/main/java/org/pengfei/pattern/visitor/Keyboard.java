package org.pengfei.pattern.visitor;

public class Keyboard implements ComputerPart {
    public Keyboard(){
        System.out.println("Keyboard building keyboard");
    }
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
