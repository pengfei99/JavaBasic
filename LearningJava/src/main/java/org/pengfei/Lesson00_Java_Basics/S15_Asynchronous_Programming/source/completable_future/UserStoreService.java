package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.completable_future;

import java.util.HashMap;
import java.util.Map;

public class UserStoreService {
    private Map<String,User> userList;

    public UserStoreService(){
        userList=new HashMap<>();
        this.init();
    }

    private void init(){
        String name="foobar";
        userList.put(name,new User(name,200.0, 80, 180));


        userList.put("alice",new User("alice",600.0, 80,180));
        userList.put("bob",new User("bob",800.0, 80,180));
       userList.put("mike",new User("mike",1200.0, 60, 160));
        userList.put("charlie",new User("charlie",2200.0, 60,160));
    }

    public User getUser(String userId){
        return userList.getOrDefault(userId,new User("Jane Doe",888.88, 88, 188));
    }
}
