package hudson.plugins.expecteddiff;

import hudson.tasks.junit.TestAction;
import hudson.tasks.test.TestObject;

import java.util.logging.Logger;

public class DiffTestAction extends TestAction {

    private final TestObject testObject;
    private final static Logger LOG = Logger.getLogger(DiffTestAction.class.getName());

    public DiffTestAction(TestObject testObject) {
        this.testObject = testObject;
    }

    public String getDisplayName() {
        return "Expected - Actual diff";
    }

    public String getIconFileName() {
        return null;
    }

    public String getUrlName() {
        return null;
    }

    public TestObject getTestObject() {
        return testObject;
    }
}
