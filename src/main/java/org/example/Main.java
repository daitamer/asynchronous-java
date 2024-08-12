package org.example;

import java.util.concurrent.CompletableFuture;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {


       CompletableFuture<String> hello = CompletableFuture.supplyAsync(() ->{
         {
               try {
                   Thread.sleep(3000);
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }

             return "Hello";
           }
       });

        CompletableFuture<String> world = CompletableFuture.supplyAsync(() ->{
            {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                return "World";
            }
        }) ;

        CompletableFuture<String> future = hello.thenCombine(world, (expression1, expression2) -> expression1 + " " + expression2);
        System.out.println(future.join());



       } // end public static void main(String[] args)

    }

