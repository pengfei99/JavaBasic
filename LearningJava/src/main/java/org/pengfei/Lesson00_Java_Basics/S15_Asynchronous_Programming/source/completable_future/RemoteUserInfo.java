package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.completable_future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class RemoteUserInfo {

    private static UserStoreService userStore=new UserStoreService();

    //Simulate it get info from a remote database, it can take times to run
    public static CompletableFuture<User> getUsersDetail(String userId) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return userStore.getUser(userId);
        });
    }

   public static CompletableFuture<Double> getCreditRating(User user) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return user.getCreditRating();
        });
    }

    public static CompletableFuture<Double> getUserHeight(String userId){
        return CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return userStore.getUser(userId).getHeight();
        });
    }

    public static CompletableFuture<Double> getUserWeight(String userId){
        return CompletableFuture.supplyAsync(()->{
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return userStore.getUser(userId).getWeight();
        });
    }
}
