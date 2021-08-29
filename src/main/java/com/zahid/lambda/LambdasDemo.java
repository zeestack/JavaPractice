package com.zahid.lambda;


import java.util.List;
import java.util.function.*;

public class LambdasDemo {

    //consumer interface --- gets value but doesnt return anything
    //supplier interface --  supply the value
    //function interface -- gets and returns
    //predicate interface -- provides boolean


    public void show() {

        greet(System.out::println);

        //chaining consumers
        List<String> list = List.of("a", "b", "c");
        Consumer<String> print = System.out::println;
        Consumer<String> printUpperCase = item -> System.out.println(item.toUpperCase());
        list.forEach(print.andThen(printUpperCase));

        //supplier interface

        // returns T get()
        Supplier<Double> getRandom = Math::random;
        System.out.println(getRandom.get());
        //DoubleSupplier
        //IntSupplier
        //BooleanSupplier

        //Functional Interface
        Function<String, Integer> map = String::length;

        var length = map.apply("Sky");
        System.out.println(length);
        //Declarative Programming
        Function<String, String> replaceColon = str -> str.replace(":", "=");
        Function<String, String> addBraces = str -> "{" + str + "}";
        var result = replaceColon.andThen(addBraces).apply("Hello:World");
        System.out.println(result);

        result = addBraces.compose(replaceColon).apply("Zahid:Hussain");
        System.out.println(result);

        //Predicate Interface

        Predicate<String> isLongerThan5 = str -> str.length() > 5;
        System.out.println(isLongerThan5.test("Hello World"));

        //Binary Operator interface
        //Java.util.function

        BinaryOperator<Integer> add = (a, b) -> a + b;
        Function<Integer, Integer> square = a -> a * a;
        var res = add.andThen(square).apply(1, 2);
        System.out.println(res);

        //Unary Operator
        UnaryOperator<Integer> sqr = n -> n * n;
        UnaryOperator<Integer> increment = n -> n + 1;
        res = increment.andThen(sqr).apply(1);
        System.out.println(res);

    }

    public void greet(Printer p) {
        p.print("Hello World");
    }
}
