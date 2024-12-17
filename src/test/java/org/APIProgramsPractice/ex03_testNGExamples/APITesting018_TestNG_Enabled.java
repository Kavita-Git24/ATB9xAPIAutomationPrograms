package org.APIProgramsPractice.ex03_testNGExamples;

import org.testng.Assert;
import org.testng.annotations.Test;

public class APITesting018_TestNG_Enabled {
    @Test
    public void t1()
    {
        System.out.println("1");
        Assert.assertTrue(true);
    }

    @Test(enabled = false)
    public void t2()
    {
        System.out.println("2");
        Assert.assertTrue(true);
    }

    @Test
    public void t3()
    {
        System.out.println("3");
        Assert.assertTrue(true);
    }
    @Test
    public void t4()
    {
        System.out.println("4");
        Assert.assertTrue(true);
    }
}
