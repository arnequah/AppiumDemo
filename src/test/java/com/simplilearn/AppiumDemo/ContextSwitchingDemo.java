package com.simplilearn.AppiumDemo;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class ContextSwitchingDemo {
	
	AndroidDriver<AndroidElement> driver; //initializing the driver as Android driver
	
	@BeforeTest
	public void launchBrowser() throws MalformedURLException {
		// 1) Launch the Chrome browser
		DesiredCapabilities dc = new DesiredCapabilities();
		dc.setCapability("deviceName", "emulator-5554");
		dc.setCapability("platformName", "ANDROID");
		dc.setCapability("browserName", "Chrome");
		dc.setCapability("noReset", "true");
		driver = new AndroidDriver<>(new URL("http://0.0.0.0:4723/wd/hub"), dc);
		
		// 2) Navigate to https://ebay.com
		driver.get("https://ebay.com");
	}
	
	@Test
	public void addShortcutToHomeScreen() {
		//Default Context
		Set<String> contexts = driver.getContextHandles();
		for (String t: contexts) {
			System.out.println(t);
		}
		
		System.out.println("Current Context = " + driver.getContext());
		
		// Switch to NATIVE_APP Context
		driver.context("NATIVE_APP");
		
		// 3) Click on 3 dots at top right corner
		driver.findElement(By.id("com.android.chrome:id/menu_button")).click();
		
		// 4) Click on 'Add to Home Screen' in the context menu
		driver.findElement(By.xpath("android.widget.TextView[@text='Add to Home screen']")).click();
		
		// 5) Wait for the pop-up to appear
		WebDriverWait wait = new WebDriverWait(driver,60);
		wait.until(ExpectedConditions.elementToBeClickable(By.id("com.android.chrome:id/positive_button")));
		
		// 6) Click on 'Add' button on the pop-up
		driver.findElement(By.id("com.android.chrome:id/positive_button")).click();
		
		// 7) Click on 'Add to Home screen' button
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath("android.widget.TextView[@text='Add to Home screen']")));
		driver.findElement(By.xpath("android.widget.Button[@text='Add to Home screen']")).click();
	}
	
	@AfterTest
	public void closeApp() {
		driver.quit();
	}
}
