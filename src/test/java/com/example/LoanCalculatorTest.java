package com.example;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class LoanCalculatorTest extends BaseTest {

    SoftAssert softAssert = new SoftAssert();

    // Clear the input field using Selenium actions
    private void clearInputBox(WebElement inputBox) {
        inputBox.sendKeys(Keys.CONTROL + "a");
        inputBox.sendKeys(Keys.BACK_SPACE);
    }

    // Move focus away to trigger the calculation
    private void moveFocusAndCalculate(WebElement moveAndCalculate) throws InterruptedException {
        moveAndCalculate.sendKeys(Keys.TAB);
        Thread.sleep(2000); // wait for 2 seconds
    }

    @Test
    public void openLoanPageTest() {
        Assert.assertTrue(driver.getTitle().contains("Taotlus | Bigbank"));
    }

    @Test
    public void DefaultLoanAmountTest() {
        WebElement loanAmountInput = driver.findElement(By.name("header-calculator-amount"));
        String loanAmount = loanAmountInput.getAttribute("value");
        Assert.assertEquals(loanAmount, "5,000", "Default loan amount should be 5000 EUR.");
    }

    @Test
    public void DefaultLoanPeriodTest() {
        WebElement loanPeriodInput = driver.findElement(By.name("header-calculator-period"));
        String loanPeriod = loanPeriodInput.getAttribute("value");
        Assert.assertEquals(loanPeriod, "60", "Default loan period should be 60 months.");
    }

    @Test
    public void UpdateLoanAmountTest() throws InterruptedException {
        WebElement loanAmountInput = driver.findElement(By.name("header-calculator-amount"));
        clearInputBox(loanAmountInput);

        loanAmountInput.sendKeys("10000");
        moveFocusAndCalculate(loanAmountInput);

        WebElement monthlyPayment = driver.findElement(By.cssSelector(".bb-calculator__result-for-sliders .bb-labeled-value__value"));
        Assert.assertEquals(monthlyPayment.getText(), "€253.21", "Monthly payment should be displayed as €253.21.");
    }

    @Test
    public void UpdateLoanPeriodTest() throws InterruptedException {
        WebElement loanPeriodInput = driver.findElement(By.name("header-calculator-period"));
        clearInputBox(loanPeriodInput);

        loanPeriodInput.sendKeys("48");
        moveFocusAndCalculate(loanPeriodInput);

        WebElement monthlyPayment = driver.findElement(By.cssSelector(".bb-calculator__result-for-sliders .bb-labeled-value__value"));
        Assert.assertEquals(monthlyPayment.getText(), "€148.44", "Monthly payment should be displayed as €148.44.");
    }

    @Test
    public void InvalidLoanAmountTest() throws InterruptedException {
        WebElement loanAmountInput = driver.findElement(By.name("header-calculator-amount"));
        clearInputBox(loanAmountInput);
        loanAmountInput.sendKeys("1000000"); // excessively large value
        moveFocusAndCalculate(loanAmountInput);

        WebElement maxLoanAmount = driver.findElement(By.cssSelector(".bb-slider__ranges span:last-child span"));
        WebElement monthlyPayment = driver.findElement(By.cssSelector(".bb-calculator__result-for-sliders .bb-labeled-value__value"));

        Assert.assertEquals(maxLoanAmount.getText(), "30,000 €", "The maximum loan amount can be 30,000 €.");
        Assert.assertEquals(monthlyPayment.getText(), "€752.64", "The monthly payment should be displayed as €752.64.");
    }

    @Test
    public void InvalidLoanPeriodTest() throws InterruptedException {
        WebElement loanPeriodInput = driver.findElement(By.name("header-calculator-period"));
        loanPeriodInput.clear();
        loanPeriodInput.sendKeys("200"); // excessively large period
        moveFocusAndCalculate(loanPeriodInput);

        // Verify the maximum loan period displayed in the slider range
        WebElement maxLoanPeriod = driver.findElement(By.cssSelector("#header-calculator-period > div.bb-slider__ranges > span:nth-child(2) > span"));
        WebElement monthlyPayment = driver.findElement(By.cssSelector(".bb-calculator__result-for-sliders .bb-labeled-value__value"));

        Assert.assertEquals(maxLoanPeriod.getText(), "120 KUUD", "The maximum loan amount can be 30,000 €.");
        Assert.assertEquals(monthlyPayment.getText(), "€90.55", "The monthly payment should be displayed as €90.55.");
    }
}
