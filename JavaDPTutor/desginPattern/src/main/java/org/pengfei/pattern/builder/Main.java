package org.pengfei.pattern.builder;

/*
* The builder pattern aims to divide a huge build to ordered small build
* In this example, we divide an meal into 3 items, burgers packing and drinks
* */
public class Main {
    public static void main(String[] args){
        MealBuilder mealBuilder=new MealBuilder();
        Meal vegMeal = mealBuilder.prepareVegMeal();
        vegMeal.showItems();
        System.out.println("Veg meal cost: "+vegMeal.getCost());
        Meal chikenMeal= mealBuilder.prepareNonVegMeal();
        chikenMeal.showItems();
        System.out.println("Chiken meal cost: "+chikenMeal.getCost());




    }
}
