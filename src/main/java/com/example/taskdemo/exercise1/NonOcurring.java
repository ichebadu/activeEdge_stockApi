package com.example.taskdemo.exercise1;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

@Slf4j
public class NonOcurring {

    public static void main(String[] args) {
        int[] array1 = {1, 3, 6, 4, 1, 2};
        int[] array2 = {5, -1, -3};

        log.info("Smallest non-occurring integer in Array1: " + findSmallestNonOccurringInteger(array1));
        log.info("Smallest non-occurring integer in Array2: " + findSmallestNonOccurringInteger(array2));

    }

    public static int findSmallestNonOccurringInteger(int[] array) {
        Set<Integer> set = new HashSet<>();

        for (int value : array) {
            set.add(value);
        }

        int smallestNonOccurring = 1;
        while (set.contains(smallestNonOccurring)) {
            smallestNonOccurring++;
        }
        return smallestNonOccurring;
    }
}
