package jbehave.steps;

import java.awt.AWTException;
import java.io.IOException;

import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.When;
import org.jbehave.core.annotations.Then;
import org.junit.Assert;
import org.sikuli.script.FindFailed;

import net.thucydides.core.annotations.Steps;
import pages.LoginPage;

public class Login {
	@Steps
    private LoginPage loginPage;

    @Given("Login as '$username' with password '$password'")
    public void Login_As_With_Password (String username, String password)  {
        loginPage.login(username, password);
    }    
    @Given("Login with '$iRowNo' in '$EnvURL'")
    public void LoginToSiebel (String iRowNo,String EnvURL) throws IOException, InterruptedException, AWTException, FindFailed  {
    	//int rowNo = Integer.parseInt(iRowNo);
        loginPage.Login(iRowNo,EnvURL);
    }
    @Given("Verify the login is successful")
    public void Verify_the_login_is_successful()  {
        Assert.assertTrue(loginPage.isLoginSuccessful());
    }
    @Given("Verify the login is unsuccessful")
    public void Verify_the_login_is_unsuccessful()  {
        Assert.assertFalse(loginPage.isLoginSuccessful());
    }    
    @When("ReLogin with '$iRowNo' in '$EnvURL'")
    public void ReLoginToSiebel (String iRowNo,String EnvURL) throws IOException, InterruptedException, AWTException, FindFailed  {
        loginPage.ReLogin(iRowNo, EnvURL);
    }
    @Given("Login to OSM with '$iRowNo' in '$EnvURL'")
    public void LoginToOSM (String iRowNo,String EnvURL) throws IOException, InterruptedException, AWTException  {
        loginPage.LoginToOSM(iRowNo,EnvURL);
    }
}
