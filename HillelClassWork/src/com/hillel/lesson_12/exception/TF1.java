package com.hillel.lesson_12.exception;

public class TF1 {
    public static void main(String[] args) {
        try {
            throw new BussinesException("test");
        } finally {
            System.out.println("finaly");
        }
    }
}
