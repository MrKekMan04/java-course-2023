package edu.project3.ui;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TextFormatterTest {
    private static final TextFormatter MARKDOWN = new MarkdownFormatter();
    private static final TextFormatter ADOC = new AsciiDocFormatter();

    @Test
    public void assertThatGetExtensionReturnedRightResult() {
        assertEquals("md", MARKDOWN.getExtension());
        assertEquals("adoc", ADOC.getExtension());
    }

    @Test
    public void assertThatTitleReturnedRightResult() {
        String title = "Title";

        assertEquals("# Title", MARKDOWN.title(title));
        assertEquals("== Title", ADOC.title(title));
    }

    @Test
    public void assertThatMonospaceReturnedRightResult() {
        String text = "text";

        assertEquals("`text`", MARKDOWN.monospace(text));
        assertEquals("``text``", ADOC.monospace(text));
    }

    @Test
    public void assertThatTableReturnedRightResult() {
        final String row1Column1 = "Row1Column1";
        final String row1Column2 = "Row1Column2";
        final String row2Column1 = "Row2Column1";
        final String row2Column2 = "Row2Column2";
        final String[][] tableData = new String[][] {
            new String[] {row1Column1, row1Column2},
            new String[] {row2Column1, row2Column2},
        };

        final String expectedMarkdown = '|' + row1Column1 + '|' + row1Column2 + '|' + '\n'
            + '|' + (":-:" + '|').repeat(2) + '\n'
            + '|' + row2Column1 + '|' + row2Column2 + '|' + '\n';

        final String expectedAdoc = "[width=\"100%\", cols=\"2\", options=\"header\"]\n"
            + '|' + "=".repeat(10) + '\n'
            + '|' + row1Column1 + '\n'
            + '|' + row1Column2 + '\n'
            + '|' + row2Column1 + '\n'
            + '|' + row2Column2 + '\n'
            + '|' + "=".repeat(10) + '\n';

        assertEquals(expectedMarkdown, MARKDOWN.table(2, 2, tableData));
        assertEquals(expectedAdoc, ADOC.table(2, 2, tableData));
    }
}
