package com.boomball;

import com.boomball.view.InputView;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        String pobiPages = inputView.readPages();
        String crongPages = inputView.readPages();

        // handler
        List<Integer> pobi = handlePages(pobiPages);
        List<Integer> crong = handlePages(crongPages);

        // solution
        System.out.println(solution(pobi, crong));
    }

    public static List<Integer> handlePages(String pages) {
        String regex = "\\[\\d{1,3},\\s?\\d{1,3}\\]";
        if (!pages.matches(regex)) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(pages.replaceAll("[\\[\\]]", "")
                .split(",")).map(Integer::parseInt).toList();
    }

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        // validate
        if (pobi.size() != 2 || crong.size() != 2) {
            return -1;
        }
        if (!isSequential(pobi) || !isSequential(crong)) {
            return -1;
        }
        if (pobi.get(0) % 2 == 0 || crong.get(0) % 2 == 0) {
            return -1;
        }

        // calculate
        int maxPobi = 0;
        int maxCrong = 0;

        for (int page : pobi) {
            maxPobi = Math.max(maxPobi, calculate(page));
        }

        for (int page : crong) {
            maxCrong = Math.max(maxCrong, calculate(page));
        }

        return getWinner(maxPobi, maxCrong);
    }

    private static int getWinner(int pobi, int crong) {
        if (pobi == crong) {
            return 0;
        }
        if (pobi > crong) {
            return 1;
        }
        return 2;
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

