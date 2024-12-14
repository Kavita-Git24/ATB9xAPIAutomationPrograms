package org.APIProgramsPractice.ex01_sampleCheck;

public class APITesting004_BuilderPatternDesignPattern {
    public APITesting004_BuilderPatternDesignPattern step1()
    {
        System.out.println("Step1");
        return this;
    }
    public APITesting004_BuilderPatternDesignPattern step2()
    {
        System.out.println("Step2");
        return this;
    }
    public APITesting004_BuilderPatternDesignPattern step3(String param)
    {
        System.out.println("Step3");
        return this;
    }

    public static void main(String[] args) {
        APITesting004_BuilderPatternDesignPattern bp=new APITesting004_BuilderPatternDesignPattern();
        bp.step1().step2().step3("Welcome");
    }
}
