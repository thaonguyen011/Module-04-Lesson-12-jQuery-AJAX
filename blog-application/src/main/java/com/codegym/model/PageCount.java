package com.codegym.model;

public class PageCount {
    private static int count = 2;
    public static int count() {
        count = count + 1;
        return count;
    }

}
