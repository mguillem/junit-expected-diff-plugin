package hudson.plugins.expecteddiff;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FormatterTest {
    private static final Object OBJECT_EXPECTED = "Mobo@23a4835a[\n" +
            "one=aaaa,\n" +
            "onehalf=12,\n" +
            "two={},\n" +
            "three=[1, 2, 3.0, false]]";

    @Test
    public void testFormat() throws Exception {
        assertEquals(OBJECT_EXPECTED, new Formatter().format("Mobo@23a4835a[one=aaaa,onehalf=12,two={},three=[1, 2, 3.0, false]]"));
    }
}
