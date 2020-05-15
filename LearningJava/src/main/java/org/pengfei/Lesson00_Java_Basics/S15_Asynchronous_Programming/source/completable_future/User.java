package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.completable_future;

public class User {

    private final String userId;
    private final double creditRating;
    private final double weight;
    private final double height;

    public User(String userId, double creditRating, double weight, double height) {
        this.userId = userId;
        this.creditRating = creditRating;
        this.weight = weight;
        this.height = height;

    }

    public String getUserId() {
        return userId;
    }

    public double getCreditRating() {
        return creditRating;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }
}
