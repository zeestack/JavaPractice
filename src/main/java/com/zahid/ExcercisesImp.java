package com.zahid;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.function.Supplier;
import java.util.stream.IntStream;

public class ExcercisesImp implements Excercises {
    
    public String largestPassword(String s) {
        String[] words = s.split(" ");
        String longestPassword = "";
        for (String word : words) {
            if (word.matches("^[a-zA-Z0-9]+")) {
                if (word.matches("^[a-zA-Z0-9].*[0-9]|^[0-9].*[a-zA-Z0-9]+")) {
                    int digitCount = 0;
                    for (int i = 0; i < word.length(); i++) {
                        if (word.charAt(i) >= 48 && word.charAt(i) <= 57)
                            digitCount++;
                    }
                    int letterCount = word.length() - digitCount;
                    if (letterCount > 0 && digitCount > 0) {
                        if (letterCount % 2 == 0 && digitCount % 2 == 1) {
                            longestPassword = word;
                        }
                    }
                }
            }
        }

        return longestPassword;
    }

    public int smallestInteger(int[] A) {
        Supplier<IntStream> streamSupplier = () -> Arrays.stream(A);
        OptionalInt min = streamSupplier.get().min();
        int minNumber = min.isPresent() ? min.getAsInt() : 0;
        boolean result = true;
        int smallestInt = 1;
        for (int i = minNumber + 1; result; i++) {
            result = streamSupplier.get().anyMatch(Integer.valueOf(i)::equals);
            if (!result) {
                if (i <= 0) {
                    result = true;
                    continue;
                }
                smallestInt = i;
            }
        }
        return smallestInt;
    }

    public int power(int base, int exp) {
        if (exp == 0) return 1;
        else if (exp % 2 == 0) {
            return power(base * base, exp / 2);
        } else
            return base * power(base * base, (exp - 1) / 2);

    }
}
