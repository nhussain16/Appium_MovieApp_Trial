package com.qa.utils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;

public class MobileElementUtils {

	private AndroidDriver<MobileElement> driver;

	public MobileElementUtils(AndroidDriver<MobileElement> driver2) {
		this.driver = driver2;
	}


	public void NavigateBack() {
		driver.navigate().back();
	}

	public void doClick(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		MobileElement MediaType = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		MediaType.click();
	}

	public void clear(By locator) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		MobileElement searchField = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		searchField.clear();
	}

	public void sendKeys(By locator, String txt) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		MobileElement searchField = (MobileElement) wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		searchField.sendKeys(txt);
	}

	public List<String> scrollToLastElement(List<MobileElement> Elelist, By movieList) {
		List<String> movie = new ArrayList<String>();
		System.out.println("Elelist.size "+Elelist.size());

		int a = 0;
		while (a < Elelist.size()) {

			scrollForward();
			
			List<MobileElement> Elelist1 = driver.findElements(movieList);
			for (int i = 0; i < Elelist1.size(); i++) {
				movie.add(Elelist1.get(i).getAttribute("text"));
			}
			a++;
			
			
		}
		return movie;
	}
	
	public void scrollForward() {
		
		driver.findElement(MobileBy
				.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).scrollForward()"));

	}
	
	
	public void Swipe(AndroidDriver<MobileElement> driver, double start_x, double start_y, double end_x, double end_y) {
		Dimension dimension = driver.manage().window().getSize();
		int start_xi = (int) (dimension.width * start_x);
		int start_yi = (int) (dimension.width * start_y);

		int end_xi = (int) (dimension.width * end_x);
		int end_yi = (int) (dimension.width * end_x);

		TouchAction touch = new TouchAction(driver);
		touch.press(PointOption.point(start_xi, start_yi)).waitAction(WaitOptions.waitOptions(Duration.ofSeconds(1)))
				.moveTo(PointOption.point(end_xi, end_yi)).release().perform();
	}
	
	public void SwipeLeft(By MoviesGrid) {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(MoviesGrid));
		driver.findElements(MoviesGrid);

			List<MobileElement> Elelist1 = driver.findElements(MoviesGrid);
			for (MobileElement e : Elelist1) {
				Swipe(driver, 0.9, 0.7, 0.6, 0.7);
			}
	}
	
}
