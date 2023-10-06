package edu.hw1;

public final class Task4 {
    private Task4() {
    }

    public static String fixString(String raw) {
        if (raw == null) {
            return null;
        }

        var builder = new StringBuilder();

        for (int i = 0; i < raw.length() - 1; i += 2) {
            builder
                .append(raw.charAt(i + 1))
                .append(raw.charAt(i));
        }

        if (raw.length() % 2 == 1) {
            builder.append(raw.charAt(raw.length() - 1));
        }

        return builder.toString();
    }
}
