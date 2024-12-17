package org.APIProgramsPractice.ex04_Assertions;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class APITesting022_TestNG_Assertions {
    @Test
    public void test_hardAssertExample()
    {
        System.out.println("Start of the program");
        Boolean isMale=false;
        //Assert.assertTrue(isMale);
        Assert.assertEquals("India","India");
        System.out.println("End of the program");
        Assert.assertEquals("India","india");
    }

    @Test
    public void test_softAssertExample()
    {
        System.out.println("Start of the program");
        SoftAssert softAssert=new SoftAssert();
        softAssert.assertTrue(false);

        System.out.println("This line will be executed");
        softAssert.assertAll();
    }
}
