package org.APIProgramsPractice.ex04_testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting016_TestNG_DependsOnMethod {
    @Test
    public void serverStartedOK()
    {
        System.out.println("I will run first");
        Assert.assertTrue(true);
    }

    @Test(dependsOnMethods = "serverStartedOK")
    public void method1()
    {
        System.out.println("method1");
       Assert.assertTrue(true);
    }
    @Test(dependsOnMethods = "serverStartedOK")
    public void method2()
    {
        System.out.println("method2");
        Assert.assertTrue(true);
    }
}
