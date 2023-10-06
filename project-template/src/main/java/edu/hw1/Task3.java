package edu.hw1;

public final class Task3 {
    private Task3() {
    }

    public static boolean isNestable(int[] firstArray, int[] secondArray) {
        if (firstArray.length > 0 && secondArray.length > 0) {
            var minAndMaxFirstArray = getMinAndMax(firstArray);
            var minAndMaxSecondArray = getMinAndMax(secondArray);

            return minAndMaxFirstArray[0] > minAndMaxSecondArray[0] || minAndMaxFirstArray[1] < minAndMaxSecondArray[1];
        }

        return false;
    }

    private static int[] getMinAndMax(int[] array) {
        var min = Integer.MAX_VALUE;
        var max = Integer.MIN_VALUE;

        for (int element : array) {
            min = Math.min(min, element);
            max = Math.max(max, element);
        }

        return new int[] {min, max};
    }
}
