package com.boomball;

import java.util.List;

public class Main {
    public static void main(String[] args) {

    }

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        // validate
        if (pobi.size() != 2 || crong.size() != 2) {
            return -1;
        }
        if (!isSequential(pobi) || !isSequential(crong)) {
            return -1;
        }
        if (pobi.get(0) % 2 != 0 || crong.get(0) % 2 != 0) {
            return -1;
        }

        // calculate

        return 0;
    }

    private static int calculate(Integer pageNumber) {
        int sum = 0;
        int mul = 1;

        while (pageNumber != 0) {
            sum += pageNumber % 10;
            mul *= pageNumber % 10;
            pageNumber /= 10;
        }
        return Math.max(sum, mul);
    }

    private static boolean isSequential(List<Integer> pages) {
        return pages.get(0) == pages.get(1) - 1;
    }
}

