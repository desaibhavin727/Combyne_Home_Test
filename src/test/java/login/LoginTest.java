package login;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver driver;

	@BeforeMethod
	public void intiate() {
		System.setProperty("webdriver.chrome.driver", "/Users/sai/Downloads/chromedriver_mac64/chromedriver");
		driver = new ChromeDriver();
		driver.get("https://demo.combyne.ag/login");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@Test(priority=1)
	public void loginPageTitleTest() 
	{
		String ExpectedTitle = "Combyne";
		String ActualTitle = driver.getTitle();
		
		System.out.println("Title of page is" +" "+ ActualTitle);
		
		Assert.assertEquals(ExpectedTitle, ActualTitle);
	}
		

	@Test(priority=2)
	public void loginPageUrlTest() 
	{	
		String ExpectedUrl = "https://demo.combyne.ag/login";
		String ActualUrl   =  driver.getCurrentUrl();
		
		System.out.println("Url of Page is" +" "+ActualUrl);
		
		Assert.assertEquals(ExpectedUrl, ActualUrl);
	}
	

	@Test(priority=3)
	public void LoginValidEmail_ValidPasswod() throws InterruptedException 
	{
		Thread.sleep(15000);
		WebElement Email = driver.findElement(By.xpath("//input[@type='text']"));
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		WebElement Log_in = driver.findElement(By.xpath("//button[@type='submit']"));

		Email.sendKeys("abc@gmail.com");
		password.sendKeys("jcnwkndwnd");
		Log_in.click();	
		
		System.out.println("User on HomePage");
	}


	@Test(priority=4)
	public void LoginwithEmptyEmail_EmptyPassword() throws InterruptedException 
	{
		Thread.sleep(15000);
		driver.findElement(By.xpath("//input[@type='text']"));
		driver.findElement(By.xpath("//input[@type='password']"));
		WebElement Log_in = driver.findElement(By.xpath("//button[@type='submit']"));

		Log_in.click();	
		
		String EmailPasswordErrExpected = driver.findElement(By.xpath("//div[@class='Input_caption__53xdW']")).getText();
		String EmailErrActual = "field required";
		String PasswordErrActual = "field required";
		
		System.out.println(EmailPasswordErrExpected);
		Assert.assertEquals(EmailPasswordErrExpected, EmailErrActual);
		Assert.assertEquals(EmailPasswordErrExpected, PasswordErrActual);
	}

	
	@Test(priority=5)
	public void LoginInValidEmail_Passwordwith5Character() throws InterruptedException 
	{
		Thread.sleep(15000);
		WebElement Email = driver.findElement(By.xpath("//input[@type='text']"));
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		WebElement Log_in = driver.findElement(By.xpath("//button[@type='submit']"));

		Email.sendKeys("xyz@gmail.com");
		password.sendKeys("dbnwkd");   //5 character Invalid password
		Log_in.click();	
		
		String PasswordErrExpected = driver.findElement(By.xpath("//div[@class='Input_caption__53xdW']")).getText();
		String PasswordErrActual = "ensure this value has at least 8 characters";
		
		System.out.println(PasswordErrExpected);
		
		Assert.assertEquals(PasswordErrExpected, PasswordErrActual);
		
	}
	
	@Test(priority=6)
	public void LoginInValidEmail_Passwordwith8Character() throws InterruptedException 
	{
		Thread.sleep(15000);
		WebElement Email = driver.findElement(By.xpath("//input[@type='text']"));
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		WebElement Log_in = driver.findElement(By.xpath("//button[@type='submit']"));

		Email.sendKeys("xyz@gmail.com");
		password.sendKeys("dbnwkdhhj");   //8 character Invalid password
		Log_in.click();	
		
		String EmailAlertErrExpected = driver.findElement(By.xpath("//div[@class='Input_caption__53xdW']")).getText();
		String EmailAlertErrActual = "Username not found";
		System.out.println(EmailAlertErrExpected);
		
		Assert.assertEquals(EmailAlertErrExpected, EmailAlertErrActual);
		
	}
	
	
	@Test(priority=11)
	public void LoginValueisNotEmail_WithPassword() throws InterruptedException 
	{
		Thread.sleep(15000);
		WebElement Email = driver.findElement(By.xpath("//input[@type='text']"));
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		WebElement Log_in = driver.findElement(By.xpath("//button[@type='submit']"));

		Email.sendKeys("xyz");
		password.sendKeys("dbnwkdhhj");   
		Log_in.click();	
		
		String EmailValueErrExpected = driver.findElement(By.xpath("//div[@class='Input_caption__53xdW']")).getText();
		String EmailValueErrActual = "value is not a valid email address";
		System.out.println(EmailValueErrExpected);
		
		Assert.assertEquals(EmailValueErrExpected, EmailValueErrActual);
		
	}
	
	
	@Test(priority=7)
	public void ClickonForgetUrPassword() throws InterruptedException 
	{
		Thread.sleep(15000);
		WebElement ForgotPassword = driver.findElement(By.xpath("//div[@class='Login_forget__6nfx9']"));
		ForgotPassword.click();
		
		String ResetPasswordExpetedUrl = "https://demo.combyne.ag/login/forget-password";
		String ResetPasswordActualUrl = driver.getCurrentUrl();
		
		System.out.println(ResetPasswordActualUrl);
		
		Assert.assertEquals(ResetPasswordExpetedUrl, ResetPasswordActualUrl);
	
	}
	
	@Test(priority=8)
	public void ClickonCreateAccount() throws InterruptedException 
	{
		Thread.sleep(15000);
		WebElement CreateAccount = driver.findElement(By.xpath("//a[@target='_self']"));
		CreateAccount.click();
		
		String UserOnExpectedUrl = "https://demo.combyne.ag/onboard/details";
		String USerOnActualUrl = driver.getCurrentUrl();
		
		System.out.println(USerOnActualUrl);
		Assert.assertEquals(UserOnExpectedUrl, USerOnActualUrl);
	
	}
	
	@Test(priority=9)
	public void ClickonContactUs() throws InterruptedException 
	{
		Thread.sleep(15000);
		WebElement ContactUs = driver.findElement(By.xpath("//a[@target='_blank']"));
		ContactUs.click();
		
		
		if(ContactUs.isEnabled()) {
			System.out.println("link is Click");
		}else {
			System.out.println("link is not click");
		}
		
	}
	
	@Test(priority=10)
	public void LoginEnterKeys() throws InterruptedException 
	{
		Thread.sleep(15000);
		WebElement Email = driver.findElement(By.xpath("//input[@type='text']"));
		WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		Email.sendKeys("abc@gmail.com");
		password.sendKeys("jcnwkndwnd");
		driver.findElement(By.xpath("//button[@type='submit']")).sendKeys(Keys.ENTER);

		String ActualErrAlert = "Username not found";
		String ExpectErrAlert = driver.findElement(By.xpath("//div[@class='Input_caption__53xdW']")).getText();
		System.out.println(ExpectErrAlert);
		Assert.assertEquals(ExpectErrAlert, ActualErrAlert);
	}
	

	@AfterMethod
	public void teardown() {
		driver.quit();
	}

}


