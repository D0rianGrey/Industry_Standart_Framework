package com.letskodeit.pageclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    /***
     * Variables
     * Locators
     * URL
     */
    private WebDriver driver;
    private String LOGIN_LINK = "//a[@href='/sign_in']";
    private String EMAIL_FIELD = "user_email";
    private String PASSWORD_FIELD = "user_password";
    private String LOG_IN_BUTTON = "commit";

    /***
     * Methods
     */

    public void open(){
        driver.findElement(By.xpath(LOGIN_LINK)).click();
    }

    public NavigationPage signInWith(String email, String password) {
        WebElement emailField = driver.findElement(By.id(EMAIL_FIELD));
        emailField.clear();
        emailField.sendKeys(email);

        WebElement passwordField = driver.findElement(By.id(PASSWORD_FIELD));
        passwordField.clear();
        passwordField.sendKeys(password);

        WebElement loginButton = driver.findElement(By.name(LOG_IN_BUTTON));
        loginButton.click();

        return new NavigationPage(driver);
    }
}