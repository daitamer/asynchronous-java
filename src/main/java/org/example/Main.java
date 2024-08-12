package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


       CompletableFuture<String> hello = CompletableFuture.supplyAsync(() ->{
         {
             int sleepTime = ThreadLocalRandom.current().nextInt(1, 11);

               try {
                    // Random sleep time between 1 and 10 seconds
                   Thread.sleep(sleepTime*1000L);
                   System.out.println(sleepTime);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

             return "Hello";
           }
       });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() ->{
            {
                int sleepTime = ThreadLocalRandom.current().nextInt(1, 11);
                try {
                    Thread.sleep(10000);
                    System.out.println(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "World";
            }
        }) ;

        CompletableFuture<String> future = hello
                .thenCombine(world, (expression1, expression2) -> expression1 + " " + expression2)
                .orTimeout(10, TimeUnit.SECONDS)
                .exceptionally(e -> {
                    if (e instanceof TimeoutException) {
                        System.err.println("Error: Combined execution time exceeded 10 seconds.");
                    }
                   return null;
                });
        System.out.println(future.join());



       } // end public static void main(String[] args)

    }

