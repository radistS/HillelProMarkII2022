package com.hillel.lesson_12.exception;

public class Doublereturn {
    public static void main(String[] args) {
        System.out.println(test());
    }

    private static Boolean test(){
        try {
            return true;
        } finally {
            return false;
        }
    }
}
