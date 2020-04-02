package org.pengfei.Lesson01_Java_Standard_API.S12_Reflection.source.zoo;

public abstract class Animal implements Eating{
    public static String CATEGORY = "domestic";
    private String name;

    public Animal(String name) {
        this.name = name;
    }

    public static String getCATEGORY() {
        return CATEGORY;
    }

    public static void setCATEGORY(String CATEGORY) {
        Animal.CATEGORY = CATEGORY;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    protected abstract String getSound();
}
