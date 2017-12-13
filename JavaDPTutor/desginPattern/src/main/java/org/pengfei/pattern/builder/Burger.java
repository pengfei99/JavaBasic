package org.pengfei.pattern.builder;

public abstract class Burger implements Item {

    public Packing packing() {
        return new Wrapper();
    }

}
