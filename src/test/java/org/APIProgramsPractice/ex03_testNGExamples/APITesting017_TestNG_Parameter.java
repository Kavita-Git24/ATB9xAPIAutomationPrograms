package org.APIProgramsPractice.ex03_testNGExamples;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class APITesting017_TestNG_Parameter {
    @Parameters("browser")
    @Test
    public void demo1(String value)
    {
        System.out.println("Browser is " +value);
        if(value.equalsIgnoreCase("chrome")){
            System.out.println("Start my Testing in Chrome");
        }
        if(value.equalsIgnoreCase("firefox")){
            System.out.println("Start my Testing in Firefox");
        }
    }


}
