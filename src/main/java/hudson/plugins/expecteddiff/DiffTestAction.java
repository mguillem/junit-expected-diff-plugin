package hudson.plugins.expecteddiff;

import groovy.json.StringEscapeUtils;
import hudson.tasks.junit.TestAction;
import hudson.tasks.test.TestObject;

import java.util.logging.Level;
import java.util.logging.Logger;

import static groovy.json.StringEscapeUtils.escapeJavaScript;

public class DiffTestAction extends TestAction {

    private static final String OUTPUT_DIV_START = "<div id=\"comparison\"";
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
        return null;
    }

    @Override
    public String annotate(String text) {
        LOG.log(Level.INFO,text);
        Diff diff = diffier.diffy(text);
        return diff != null ?
                outputDiv(
                        diff.getExpected(),
                        diff.getActual()) + text
                :
                text;
    }

    private String outputDiv(String expected, String actual) {
        LOG.log(Level.INFO,expected);
        LOG.log(Level.INFO,actual);
        return OUTPUT_DIV_START + " expected=\"" + expected + "\" actual=\""+ actual +"\"" + OUTPUT_DIV_END;
    }

    public TestObject getTestObject() {
        return testObject;
    }
}
