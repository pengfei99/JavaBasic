package org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source.zoo;

public class Goat extends Animal implements Locomotion {
    @Override
    protected String getSound() {
        return "Mia";
    }

    @Override
    public String eats() {
        return "Grass";
    }

    public Goat(String name) {
        super(name);
    }

    @Override
    public String getLocomotion() {
        return "walk and run";
    }
}
