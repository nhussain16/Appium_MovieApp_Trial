package com.qa.tests;

import java.io.File;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.pages.GridPage;
import com.qa.pages.ListPage;
import com.qa.utils.ConfigReader;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class BaseTest {

	private AndroidDriver<MobileElement> driver;
	Properties prop;
	ListPage listPage;
	GridPage gridPage;

	@BeforeTest
	public void setUp() throws Exception {
		getProperty();
		DesiredCapabilities caps = new DesiredCapabilities();
		caps.setCapability(MobileCapabilityType.PLATFORM_NAME, prop.getProperty("platformName"));
		caps.setCapability(MobileCapabilityType.DEVICE_NAME, prop.getProperty("deviceName"));
		caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, prop.getProperty("androidAutomationName"));
		caps.setCapability(MobileCapabilityType.NO_RESET, true);

		String androidAppUrl = System.getProperty("user.dir") + File.separator + "src" + File.separator + "test"
				+ File.separator + "resources" + File.separator + "app" + File.separator + "app-debug.apk";

		System.out.println("app url is " + androidAppUrl);
		caps.setCapability(MobileCapabilityType.APP, androidAppUrl);

		URL url = new URL(prop.getProperty("appiumURL"));

		System.out.println("url is = " + url);

		driver = new AndroidDriver<MobileElement>(url, caps);
		System.out.println("driver is = " + driver);
		listPage = new ListPage(driver);
		gridPage = new GridPage(driver);
		

	}

	@AfterTest
	public void TearDown() {
		driver.quit();
	}

	public void getProperty() {
		ConfigReader configReader = new ConfigReader();
		prop = configReader.init_prop();
	}
}
