package org.pengfei.pattern.visitor;

public class ComputerPartRepairVisitor implements ComputerPartVisitor {
    public void visit(Keyboard keyboard) {
        System.out.println("Repaire keyboard");
    }

    public void visit(Monitor monitor) {
        System.out.println("Repaire monitor");
    }

    public void visit(Mouse mouse) {
        System.out.println("Repaire mouse");
    }

    public void visit(Computer computer) {
        System.out.println("Repaire computer");
    }
}
