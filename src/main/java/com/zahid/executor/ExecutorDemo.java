package com.zahid.executor;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class ExecutorDemo {
    public static void show() {
        var executor = Executors.newFixedThreadPool(2);
        System.out.println(executor.getClass().getName());

        try {

            var future = executor.submit(() -> {
                LongTask.simulate();  //submit method is not going wait but will return immediately
                return 1;
            });

            System.out.println("Do more work");
            var result = future.get();
            System.out.println(result);

        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }


    }

    public static CompletableFuture<String> getUserEmailAsync() {
        return CompletableFuture.supplyAsync(() -> "email");
    }

    public static CompletableFuture<String> getPlayListAsync(String email) {
        return CompletableFuture.supplyAsync(() -> email + ": PlayList");
    }

    public static void onComplete() {
        //id -> email
        //email -> playlist

        //CompletableFuture.supplyAsync(() -> "email").thenCompose(email -> CompletableFuture.supplyAsync(() -> "playlist")).
        //      thenAccept(playlist -> System.out.println(playlist));

        getUserEmailAsync().thenCompose(ExecutorDemo::getPlayListAsync).thenAccept(System.out::println);

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void combineTasks() {
        var first = CompletableFuture.supplyAsync(() -> "20USD").thenApply(price ->
                Integer.parseInt(price.replace("USD", ""))
        );
        var second = CompletableFuture.supplyAsync(() -> 0.9);

        first.thenCombine(second, (price, exchangeRate) -> price * exchangeRate).thenAccept(System.out::println);
    }

}
