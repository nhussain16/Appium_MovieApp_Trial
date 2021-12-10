package com.qa.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utils.Constants;
import com.qa.utils.MobileControlIds;
import com.qa.utils.MobileElementUtils;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class GridPage {

	private AndroidDriver<MobileElement> driver;
	private MobileElementUtils util;
	private Logger log = LogManager.getLogger(GridPage.class);
	
	// Constructor
	public GridPage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		this.util = new MobileElementUtils(driver);
	}
	
	public List<String> MarkMultipleMovies() {
		List<String> movie = new ArrayList<String>();
		
		util.doClick(MobileControlIds.vimeoBtn);
		movie.add(MarkFavMovie(MobileControlIds.MoviesGrid));
		util.NavigateBack();
		util.SwipeLeft(MobileControlIds.MoviesGrid);
		movie.add(MarkFavMovie(MobileControlIds.MoviesGrid));
		
		for(String e : movie) {
			System.out.println(e);
		}
		
		util.NavigateBack();
		return movie;
	}
	
	public String MarkFavMovie(By MoviesGrid) {
		//String MovieName = null;
		driver.findElement(MoviesGrid); 
		util.SwipeLeft(MobileControlIds.MoviesGrid);
		new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		driver.findElements(MoviesGrid).get(2).click();
		
		Actions act = new Actions(driver);
		act.click(driver.findElement(MobileControlIds.heartBtn)).perform();
		
		new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		
		return driver.findElement(MobileControlIds.movieNameIs).getAttribute("text");
	}
	
	public String MarkSingleFavMovie(By MoviesGrid) {
		//SwipeLeft();
		util.SwipeLeft(MobileControlIds.MoviesGrid);
		new WebDriverWait(driver, 10);
		driver.findElements(MobileControlIds.MoviesGrid).get(1).click();
		
		Actions act = new Actions(driver);
		act.click(driver.findElement(MobileControlIds.heartBtn)).perform();
		
		new WebDriverWait(driver, 10);
		
		return driver.findElement(MobileControlIds.movieName).getAttribute("text");
	}
	
	public String MarkFavSingleMovie(String action) {
		String MovieName = null;

		switch(action) {
		case "favourite" :
			util.doClick(MobileControlIds.vimeoBtn);
			SwipeLeft();
			new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
			driver.findElements(MobileControlIds.MoviesGrid).get(2).click();
			util.doClick(MobileControlIds.heartBtn);
			break;
		case "highRate" :
			util.doClick(MobileControlIds.vimeoBtn);
			SwipeLeft();
			new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
			driver.findElements(MobileControlIds.MoviesGrid).get(2).click();
			util.Swipe(driver, 0.4, 0.65, 0.65, 0.65);
			break;
		default:
			System.out.println("Invalid action..." + action);	
		}
		
		MovieName = driver.findElement(MobileControlIds.movieName).getAttribute("text");
		return MovieName;
	}
	
	public void SwipeLeft() {
		WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		wait.until(ExpectedConditions.presenceOfElementLocated(MobileControlIds.MoviesGrid));
		driver.findElements(MobileControlIds.MoviesGrid);

		List<MobileElement> Elelist1 = driver.findElements(MobileControlIds.MoviesGrid);
			for (MobileElement e : Elelist1) {
				System.out.println("Movies Name" + e.getAttribute("text"));
				util.Swipe(driver, 0.9, 0.7, 0.6, 0.7);
			}
	}
	
}
