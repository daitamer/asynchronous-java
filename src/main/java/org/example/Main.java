package org.example;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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

        String data = "85671 34262 92143 50984 24515 68356 77247 12348 56789 98760";
        // String data = "1 2 3 4 5 6";
        List<BigInteger> results = new ArrayList<>();

        CompletableFuture.supplyAsync(() -> Arrays.stream(data.split(" ")).map(BigInteger::new).toList())
                .thenApply(x -> {
                    for (BigInteger num : x) {
                        results.add(calculateFactorial(num));
                    }
                    return results;
                })
                .thenAccept(System.out::println)
                .join();
       } // end public static void main(String[] args)

    private static BigInteger calculateFactorial(BigInteger num) {
        BigInteger result = BigInteger.ONE;
        for (BigInteger i = BigInteger.ONE; i.compareTo(num) <= 0; i = i.add(BigInteger.ONE)) {
            result = result.multiply(i);
        }
        return result;
    }

    }

