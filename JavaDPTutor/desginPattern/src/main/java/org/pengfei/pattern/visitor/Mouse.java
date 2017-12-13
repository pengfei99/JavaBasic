package org.pengfei.pattern.visitor;

public class Mouse implements ComputerPart {
    public Mouse(){
        System.out.println("Mouse building mouse");
    }
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}
