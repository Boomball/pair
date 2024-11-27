package com.boomball;

import com.boomball.util.Separator;
import com.boomball.view.InputView;
import com.boomball.view.OutputView;
import java.util.List;

public class Application {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();

        String pobiPages = inputView.readPages();
        String crongPages = inputView.readPages();

        // handler
        List<Integer> pobi = Separator.separatePages(pobiPages);
        List<Integer> crong = Separator.separatePages(crongPages);

        // solution
        outputView.showWinner(solution(pobi, crong));
    }

    public static int solution(List<Integer> pobi, List<Integer> crong) {
        // validate
        try {
            validate(pobi, crong);
        } catch (IllegalArgumentException e){
            return -1;
        }

        // calculate
        int maxPobi = getMax(pobi.getFirst(), pobi.getLast());
        int maxCrong = getMax(crong.getFirst(), crong.getLast());

        return getWinner(maxPobi, maxCrong);
    }

    private static int getMax(int leftPage, int rightPage) {
        return Math.max(calculate(leftPage), calculate(rightPage));
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

    private static void validate(List<Integer> pobi, List<Integer> crong) {
        // validate
        if (pobi.size() != 2 || crong.size() != 2) {
            throw new IllegalArgumentException();
        }
        if (isNonSequential(pobi) || isNonSequential(crong)) {
            throw new IllegalArgumentException();
        }
        if (pobi.getFirst() % 2 == 0 || crong.getFirst() % 2 == 0) {
            throw new IllegalArgumentException();
        }
    }

    private static boolean isNonSequential(List<Integer> pages) {
        return pages.get(0) != pages.get(1) - 1;
    }
}

