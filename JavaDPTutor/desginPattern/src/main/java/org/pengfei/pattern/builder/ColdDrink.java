package org.pengfei.pattern.builder;

public abstract class ColdDrink implements Item {


    public Packing packing() {
        return new Bottle();
    }

}
