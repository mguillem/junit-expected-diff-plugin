package hudson.plugins.expecteddiff;

import hudson.tasks.junit.TestAction;
import hudson.tasks.test.TestObject;

import java.util.logging.Logger;

public class DiffTestAction extends TestAction {

    private static final String OUTPUT_DIV_START = "<div id=\"diffoutput\" style=\"width:100%\"";
    private static final String OUTPUT_DIV_END = "> </div>";

    private final TestObject testObject;
    private final Differ diffier = new Differ();
    private final static Logger LOG = Logger.getLogger(DiffTestAction.class.getName());

    public DiffTestAction(TestObject testObject) {
        this.testObject = testObject;
    }

    public String getDisplayName() {
        return "Expected - Actual diff";
    }

    public String getIconFileName() {
        return "package.gif";
    }

    public String getUrlName() {
        return "diff";
    }

    @Override
    public String annotate(String text) {
        LOG.info("Diff: " + text);
        Diff diff = diffier.diffy(text);
        if (diff != null) {
            return outputDiv(diff.getExpected(),diff.getActual()) + text;
        } else
            return text;
    }

    private String outputDiv(String expected, String actual) {
        return OUTPUT_DIV_START + " expected=\"" + expected + "\" actual=\""+ actual+"\"" + OUTPUT_DIV_END;
    }

    public TestObject getTestObject() {
        return testObject;
    }
}
