package edu.hw3.task4;

@SuppressWarnings({"MagicNumber", "ParameterAssignment"})
public final class Task4 {
    private static final int MINIMAL_ROMAN_NUMBER = 1;
    private static final int MAXIMAL_ROMAN_NUMBER = 3999;
    private static final int TENTHS_BITS_SUPPORTED = 4;

    private Task4() {
    }

    public static String convertToRoman(int number) {
        if (!isNumberValid(number)) {
            return null;
        }

        StringBuilder result = new StringBuilder();

        for (int i = TENTHS_BITS_SUPPORTED; i > 0; i--) {
            int delim = (int) Math.pow(10, i - 1);

            if (number >= delim) {
                if (delim == 1000) {
                    result.append(RomanLetter.M.name.repeat(number / 1000));
                } else {
                    result.append(getLetters(
                        number,
                        delim,
                        RomanLetter.getFromIndex(i * 2 - 2),
                        RomanLetter.getFromIndex(i * 2 - 1),
                        RomanLetter.getFromIndex(i * 2)
                    ));
                }

                number %= delim;
            }
        }

        return result.toString();
    }

    private static String getLetters(int num, int delim, String currentLetter, String fiveLetter, String nextLetter) {
        int repetition = num / delim;

        return switch (repetition) {
            case 1, 2, 3 -> currentLetter.repeat(repetition);
            case 4 -> currentLetter + fiveLetter;
            case 5 -> fiveLetter;
            case 6, 7, 8 -> fiveLetter + currentLetter.repeat(repetition - 5);
            case 9 -> currentLetter + nextLetter;
            default -> throw new IllegalStateException("Unexpected value: " + repetition);
        };
    }

    private static boolean isNumberValid(int number) {
        return number >= MINIMAL_ROMAN_NUMBER && number <= MAXIMAL_ROMAN_NUMBER;
    }

    private enum RomanLetter {
        I("I"), V("V"), X("X"), L("L"), C("C"), D("D"), M("M");

        private final String name;

        RomanLetter(String name) {
            this.name = name;
        }

        public static String getFromIndex(int i) {
            return values()[i % values().length].name;
        }
    }
}
