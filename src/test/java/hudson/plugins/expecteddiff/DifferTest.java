package hudson.plugins.expecteddiff;

import org.junit.Test;
import org.jvnet.hudson.test.HudsonTestCase;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DifferTest {
    @Test
    public void testDiffy() throws Exception {
        assertEquals("aa[\nbb\nc1]c",diffy("junit.framework.ComparisonFailure: expected:&lt;aa[\n" +
                "bb\n" +
                "c1]c> but was:&lt;aa[aa\n" +
                "bbbb\n" +
                "ccc]c>\n" +
                "\tat junit.framework.Assert.assertEquals(Assert.java:85)\n" +
                "\tat junit.framework.Assert.assertEquals(Assert.java:91)\n" +
                "\tat OTest.shouldOutputMultiline(OTest.java:17)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:601)\n" +
                "\tat org.junit.runners.model.FrameworkMethod$1.runReflectiveCall(FrameworkMethod.java:44)\n" +
                "\tat org.junit.internal.runners.model.ReflectiveCallable.run(ReflectiveCallable.java:15)\n" +
                "\tat org.junit.runners.model.FrameworkMethod.invokeExplosively(FrameworkMethod.java:41)\n" +
                "\tat org.junit.internal.runners.statements.InvokeMethod.evaluate(InvokeMethod.java:20)\n" +
                "\tat org.junit.runners.ParentRunner.runLeaf(ParentRunner.java:263)\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:69)\n" +
                "\tat org.junit.runners.BlockJUnit4ClassRunner.runChild(BlockJUnit4ClassRunner.java:48)\n" +
                "\tat org.junit.runners.ParentRunner$3.run(ParentRunner.java:231)\n" +
                "\tat org.junit.runners.ParentRunner$1.schedule(ParentRunner.java:60)\n" +
                "\tat org.junit.runners.ParentRunner.runChildren(ParentRunner.java:229)\n" +
                "\tat org.junit.runners.ParentRunner.access$000(ParentRunner.java:50)\n" +
                "\tat org.junit.runners.ParentRunner$2.evaluate(ParentRunner.java:222)\n" +
                "\tat org.junit.runners.ParentRunner.run(ParentRunner.java:292)\n" +
                "\tat org.apache.maven.surefire.junit4.JUnit4TestSet.execute(JUnit4TestSet.java:35)\n" +
                "\tat org.apache.maven.surefire.junit4.JUnit4Provider.executeTestSet(JUnit4Provider.java:115)\n" +
                "\tat org.apache.maven.surefire.junit4.JUnit4Provider.invoke(JUnit4Provider.java:97)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke0(Native Method)\n" +
                "\tat sun.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:57)\n" +
                "\tat sun.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43)\n" +
                "\tat java.lang.reflect.Method.invoke(Method.java:601)\n" +
                "\tat org.apache.maven.surefire.booter.ProviderFactory$ClassLoaderProxy.invoke(ProviderFactory.java:103)\n" +
                "\tat $Proxy0.invoke(Unknown Source)\n" +
                "\tat org.apache.maven.surefire.booter.SurefireStarter.invokeProvider(SurefireStarter.java:150)\n" +
                "\tat org.apache.maven.surefire.booter.SurefireStarter.runSuitesInProcess(SurefireStarter.java:91)\n" +
                "\tat org.apache.maven.surefire.booter.ForkedBooter.main(ForkedBooter.java:69)").getExpected()) ;
    }

    private Diff diffy(String text) {
        return new Differ().diffy(text);
    }
}
