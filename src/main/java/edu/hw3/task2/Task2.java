package edu.hw3.task2;

import java.util.ArrayList;
import java.util.Collection;

public final class Task2 {
    private Task2() {
    }

    public static Collection<String> clusterize(String string) {
        if (string == null || !isValid(string)) {
            return null;
        }

        ArrayList<String> clusters = new ArrayList<>();

        int openBrackets = 0;
        int closeBrackets = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                openBrackets++;
            } else if (string.charAt(i) == ')') {
                if (openBrackets == ++closeBrackets) {
                    clusters.add(string.substring(i - (openBrackets + closeBrackets - 1), i + 1));
                    openBrackets = 0;
                    closeBrackets = 0;
                }
            }
        }

        return clusters;
    }

    private static boolean isValid(String string) {
        int openBrackets = 0;
        int closeBrackets = 0;

        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                openBrackets++;
            } else if (string.charAt(i) == ')') {
                if (openBrackets < ++closeBrackets) {
                    return false;
                }
            } else {
                return false;
            }
        }

        return openBrackets == closeBrackets;
    }
}
