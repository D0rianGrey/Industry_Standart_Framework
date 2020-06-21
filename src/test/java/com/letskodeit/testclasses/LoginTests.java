package com.letskodeit.testclasses;

import com.letskodeit.base.BaseTest;
import com.letskodeit.base.CheckPoint;
import com.letskodeit.utilities.Constants;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class LoginTests extends BaseTest {

    @BeforeClass
    public void setUp() {
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("****** After Method ******");
        if (navigationPage.isUserLoggedIn()) {
            navigationPage.logout();
            navigationPage.login();
        }
    }

    @Test
    public void testLogin() {
        navigationPage = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean headerResult = navigationPage.verifyHeader();
        CheckPoint.mark("test-01", headerResult, "header verification");
        boolean result = navigationPage.isUserLoggedIn();
        CheckPoint.markFinal("test-01", result, "login verification");
    }

    @Test(enabled = false)
    public void testInvalidLogin() {
        navigationPage = login.signInWith(Constants.DEFAULT_USERNAME, Constants.DEFAULT_PASSWORD);
        boolean result = navigationPage.isUserLoggedIn();
        Assert.assertFalse(result);
    }
}