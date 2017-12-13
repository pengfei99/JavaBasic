package org.pengfei.pattern.visitor;

public class ComputerPartDisplayVisitor implements ComputerPartVisitor{
    public void visit(Keyboard keyboard) {
        System.out.println("DispalyVisitor show keyboard");
    }

    public void visit(Monitor monitor) {
        System.out.println("DispalyVisitor show monitor");
    }

    public void visit(Mouse mouse) {
        System.out.println("DispalyVisitor show mouse");
    }

    public void visit(Computer computer) {
        System.out.println("DispalyVisitor show computer");
    }
}
