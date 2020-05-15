package org.pengfei.Lesson00_Java_Basics.S15_Asynchronous_Programming.source.completable_future;

import java.util.concurrent.CompletableFuture;

public class WebScrapingService {

    public static CompletableFuture<String> downloadWebPage(String url){
        return CompletableFuture.supplyAsync(()->{
            return "Download web page of "+url;
        });
    }

}
