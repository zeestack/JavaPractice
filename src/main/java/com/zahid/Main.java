package com.zahid;

public class Main {

    public static void main(String[] args) {

        Excercises practice = new ExcercisesImp();

        String str = "hello world where are you pass231 321password 321password21";
        String password = practice.largestPassword(str);
        System.out.println("Password: " + password + "\nlength:" + password.length());
        int[] a = new int[]{1, 3, 6, 4, 1, 2};
        System.out.println(practice.smallestInteger(a));
        System.out.println("Even base: 8, exp: 4: " + practice.power(8, 4));
        System.out.println("Even base: 7, exp: 3: " + practice.power(7, 3));
    }

}
