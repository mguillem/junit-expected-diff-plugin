package hudson.plugins.expecteddiff;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.kohsuke.stapler.DataBoundConstructor;

import hudson.Extension;
import hudson.FilePath;
import hudson.Launcher;
import hudson.model.Descriptor;
import hudson.model.Run;
import hudson.model.TaskListener;
import hudson.tasks.junit.CaseResult;
import hudson.tasks.junit.TestAction;
import hudson.tasks.junit.TestDataPublisher;
import hudson.tasks.junit.TestObject;
import hudson.tasks.junit.TestResult;
import hudson.tasks.junit.TestResultAction;

public class DiffPublisher extends TestDataPublisher {

    @DataBoundConstructor
    public DiffPublisher() {
    }

    @Override
    public TestResultAction.Data contributeTestData(Run<?, ?> run, FilePath workspace,
    		Launcher launcher, TaskListener listener, TestResult testResult) throws IOException, InterruptedException {
        return new Data();
    }

    public static class Data extends TestResultAction.Data {

        @Override
        public List<TestAction> getTestAction(TestObject testObject) {
             if (testObject instanceof CaseResult) {
                 CaseResult cr = (CaseResult) testObject;
                 return Collections.<TestAction> singletonList(new DiffTestAction(cr));
            } else {
                return Collections.emptyList();
            }
        }
    }

    @Extension
    public static class DescriptorImpl extends Descriptor<TestDataPublisher> {

        @Override
        public String getDisplayName() {
            return "Expected - Actual diff view";
        }

    }
}
