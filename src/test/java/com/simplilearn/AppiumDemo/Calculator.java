package com.simplilearn.AppiumDemo;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class Calculator {
	
	AndroidDriver<MobileElement> driver;
	
	@BeforeTest
	public void launchApplication() throws MalformedURLException {
		DesiredCapabilities cap = new DesiredCapabilities();
		cap.setCapability(MobileCapabilityType.DEVICE_NAME, "adb-1A191FDF600B2S-lUmTYS._adb-tls-connect._tcp device");
		cap.setCapability("platformName", "ANDROID");
		cap.setCapability("appPackage", "com.miui.calculator");
		cap.setCapability("appActivity", "com.miui.calculator.cal.CalculatorActivity");
		cap.setCapability("noReset", true); //do not rest the application every time it is launched
		driver = new AndroidDriver<MobileElement>(new URL("http://0.0.0.0:4723/wd/hub"), cap);
	}
	
	@Test(priority=0)
	public void verifyAdditionProcess() {
		//Press digit 9
		driver.findElement(By.id("com.miui.calculator:id/digit_9")).click();
		
		//Press + symbol
		driver.findElementByAccessibilityId("plus").click();
		
		//Press digit 6
		driver.findElement(By.id("com.miui.calculator:id/digit_6")).click();
		
		//Press '=' button
		driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"equals\"]")).click();
		
		String expectedResult = "15";
		String actualResult = driver.findElement(By.id("com.miui.calculator:id/result")).getText().substring(2, 4);
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	@Test(priority=1)
	public void verifyMultiplicationProcess() {
		//Press 5
		driver.findElement(By.id("com.miui.calculator:id/digit_5")).click();
		
		//Press *
		driver.findElement(By.id("com.miui.calculator:id/op_mul")).click();
		
		//Press 4
		driver.findElement(By.id("com.miui.calculator:id/digit_4")).click();
		
		//Press =
		driver.findElement(By.id("com.miui.calculator:id/btn_equal_s")).click();
		
		String expectedResult = "20";
		String actualResult = driver.findElement(By.id("com.miui.calculator:id/result")).getText().substring(2, 4);
		Assert.assertEquals(actualResult, expectedResult);
	}
	
	@Test(priority=2)
	public void verifyDelBtnIsPresent() {
		boolean clearBtnDisplayed = driver.findElement(By.id("com.miui.calculator:id/btn_del_s")).isDisplayed();
		Assert.assertTrue(clearBtnDisplayed);
	}
	
	@AfterTest
	public void closeApp() {
		driver.quit();
	}
}
