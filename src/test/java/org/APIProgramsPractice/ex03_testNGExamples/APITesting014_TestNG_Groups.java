package org.APIProgramsPractice.ex03_testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting014_TestNG_Groups {
    @Test(groups = {"sanity","qa","prod"})
    public void sanityRun()
    {
        System.out.println("Sanity");
        System.out.println("QA");
        Assert.assertTrue(true);
    }

    @Test(groups = {"reg"})
    public void RegRun()
    {
        System.out.println("Regrun");
       // Assert.assertTrue(false);
    }

    @Test(groups = {"qa"})
    public void SmokeRun()
    {
        System.out.println("Smoke");
        Assert.assertTrue(false);
    }

}
