package com.mindtree.SmartExecutionTest;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;

public class AppTest {

	public static ChromeDriver driver;
	public static ExtentReports extent;
	public static ExtentTest test;

	@BeforeSuite
	public void beforeSuit() {
		System.out.println("print from before suit");
		Date currentDate = new Date();
		String reportFilename = currentDate.toString().replace(" ", "-").replace(":", "-");
		extent = new ExtentReports(".//Reports//"+reportFilename+".html", true);
	}

	@BeforeMethod
	public void beforeMethod(Method method) {
		System.out.println("print from before method");
		test = extent.startTest((this.getClass().getSimpleName() + "::" + method.getName()), method.getName());
	}

	@BeforeTest
	public void setTest() {
		WebDriverManager.getInstance(DriverManagerType.CHROME).proxy("10.252.0.180:8085").setup();
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test
	public void login() {

		System.out.println("print from test");
		driver.get("https://www.google.com/");
		driver.manage().window().maximize();
		test.log(LogStatus.PASS, "open google");
		driver.findElement(By.name("q")).sendKeys("mindtree");
		driver.findElement(By.name("q")).sendKeys(Keys.ENTER);
		test.log(LogStatus.PASS, "search mindtree");
//		driver.findElement(By.xpath("//*[@id=\"tsf\"]/div[1]/div[1]/div[2]/div/div[2]/input")).sendKeys("mindtree");
//		Actions action = new Actions(driver);
//		action.click(element).perform();
//		WebElement element = driver.findElement(By.xpath("//a[@id = 'nav-link-accountList']"));
//		Actions action = new Actions(driver);
//		action.click(element).perform();
//		driver.findElement(By.xpath("//input[@id = 'ap_email']")).sendKeys("vaishalijindal99@gmail.com");
//		driver.findElement(By.xpath("//input[@id = 'continue']")).click();
//		driver.findElement(By.xpath("//input[@id = 'ap_password']")).sendKeys("Tomphiker@25");
//		test.log(LogStatus.PASS, "Entering the credentaial");
//		driver.findElement(By.xpath("//input[@id = 'signInSubmit']")).click();
//		test.log(LogStatus.PASS, "Logged in");
	}

	@AfterMethod
	public void afterMethod() {
		extent.endTest(test);
	}

	@AfterTest
	public void downTest() {
		driver.close();
		driver.quit();
		test.log(LogStatus.PASS, "quit the driver");
	}

	@AfterSuite
	public void afterSuite() {
		extent.flush();
		extent.close();
	}
}
