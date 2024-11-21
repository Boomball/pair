package com.boomball.util;

import java.util.Scanner;

public class Console {

    private static Scanner scanner;

    private Console() {
    }

    public static void close() {
        if (scanner != null) {
            scanner.close();
            scanner = null;
        }
    }

    public static String readline() {
        return getInstance().nextLine();
    }

    private static Scanner getInstance() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }
}
