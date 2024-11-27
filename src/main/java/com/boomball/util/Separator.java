package com.boomball.util;

import java.util.Arrays;
import java.util.List;

public class Separator {

    public static List<Integer> separatePages(String pages) {
        String regex = "\\[\\d{1,3},\\s?\\d{1,3}\\]";
        if (!pages.matches(regex)) {
            throw new IllegalArgumentException();
        }
        return Arrays.stream(pages.replaceAll("[\\[\\]]", "")
                .split(",")).map(Integer::parseInt).toList();
    }
}
