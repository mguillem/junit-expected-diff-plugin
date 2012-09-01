package hudson.plugins.expecteddiff;

public class Diff {
    private final String expected;
    private final String actual;

    public Diff(String expected, String actual) {
        this.expected = expected;
        this.actual = actual;
    }

    public String getExpected() {
        return expected;
    }

    public String getActual() {
        return actual;
    }
}
