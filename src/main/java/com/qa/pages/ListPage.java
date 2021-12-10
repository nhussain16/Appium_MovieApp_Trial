package com.qa.pages;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.utils.Constants;
import com.qa.utils.MobileControlIds;
import com.qa.utils.MobileElementUtils;

import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;

public class ListPage {

	private AndroidDriver<MobileElement> driver;
	private MobileElementUtils util;
	private Logger log = LogManager.getLogger(ListPage.class);

	// Constructor
	public ListPage(AndroidDriver<MobileElement> driver) {
		this.driver = driver;
		this.util = new MobileElementUtils(driver);
	}

	// functions
	public String ScrollToGetSecondLastElement() {
		String secondLastMovie = null;
		List<String> movie = new ArrayList<String>();
		try {
			util.doClick(MobileControlIds.vimeoBtn);
			util.doClick(MobileControlIds.listTab);

			List<MobileElement> Elelist = driver.findElements(MobileControlIds.movieList);
			for (MobileElement e : Elelist)
				movie.add(e.getAttribute("text"));
			System.out.println("Movie... " + movie);

			movie = util.scrollToLastElement(Elelist, MobileControlIds.movieList);
			System.out.println("movie list... " + movie);

			log.info("Scroll to last element... " + movie);

			secondLastMovie = GetSecondLastItemFromList(movie);
			log.info("Second last movie name is... " + secondLastMovie);

		} catch (InvalidSelectorException e) {
			log.error("Invalid selector exception...", e.fillInStackTrace());
		} catch (Exception e1) {
			log.error("Something went wrong...", e1.fillInStackTrace());
		}

		return secondLastMovie;
	}

	public List<String> MarkFavMultipleMovies() {
		List<String> favMovie = new ArrayList<String>();

		try {

			util.doClick(MobileControlIds.vimeoBtn);
			util.doClick(MobileControlIds.listTab);

			favMovie.add(MarkFavMovieFromList());
			util.NavigateBack();
			util.scrollForward();
			favMovie.add(MarkFavMovieFromList());
			for (String e : favMovie) {
				System.out.println(e);
			}

			driver.navigate().back();
			System.out.println("favMovie is..." + favMovie);
		} catch (InvalidSelectorException e) {
			log.error("Invalid selector exception...", e.fillInStackTrace());
		} catch (Exception e1) {
			log.error("Something went wrong...", e1.fillInStackTrace());
		}
		return favMovie;
	}

	public String MarkFavMovieFromList() {
		try {
			List<String> movie = new ArrayList<String>();
			util.scrollForward();
			new WebDriverWait(driver, 10);

			List<MobileElement> Elelist = driver.findElements(MobileControlIds.movieList);
			for (MobileElement e : Elelist)
				movie.add(e.getAttribute("text"));

			util.doClick(MobileControlIds.selectFavMovie);

			util.doClick(MobileControlIds.heartBtn);
			return driver.findElement(MobileControlIds.movieNameIs).getAttribute("text");

		} catch (InvalidSelectorException e) {
			log.error("Invalid selector exception ...", e.fillInStackTrace());
		} catch (Exception e1) {
			log.error("Something went wrong...", e1.fillInStackTrace());
		}
		return driver.findElement(MobileControlIds.movieNameIs).getAttribute("text");
	}

	public String GetSecondLastItemFromList(List<String> movie) {
		String secondLastMovieName = null;
		try {
			List<String> listDistinct = movie.stream().distinct().collect(Collectors.toList());
			System.out.println("distinct list " + listDistinct);

			int b = 0;
			while (b < listDistinct.size())
				b++;
			log.info("Unique items list is ... " + listDistinct);
			System.out.println("second last movie " + listDistinct.get(b - 2));
			secondLastMovieName = listDistinct.get(b - 2);
		} catch (InvalidSelectorException e) {
			log.error("Invalid selector exception ...", e.fillInStackTrace());
		} catch (Exception e1) {
			log.error("Something went wrong...", e1.fillInStackTrace());
		}
		return secondLastMovieName;
	}

	public String SearchFavMovie(String MovieName) {
		try {
			util.doClick(MobileControlIds.favMovieBtn);

			WebDriverWait wait = new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
			wait.until(ExpectedConditions.presenceOfElementLocated(MobileControlIds.searchField));
			driver.findElement(MobileControlIds.searchField).sendKeys(MovieName);

			new WebDriverWait(driver, Constants.DEFAULT_TIME_OUT);
		} catch (InvalidSelectorException e) {
			log.error("Invalid selector exception ...", e.fillInStackTrace());
		} catch (Exception e1) {
			log.error("Something went wrong...", e1.fillInStackTrace());
		}
		return driver.findElement(MobileControlIds.movieList).getAttribute("text");
	}

	public void UnmarkFavMovies() {
		try {
			util.doClick(MobileControlIds.movieListImage);

			util.doClick(MobileControlIds.heartBtn);
			util.NavigateBack();
			util.clear(MobileControlIds.searchField);
			util.doClick(MobileControlIds.movieListImage);

			util.doClick(MobileControlIds.heartBtn);

			util.NavigateBack();
		} catch (InvalidSelectorException e) {
			log.error("Invalid selector exception ...", e.fillInStackTrace());
		} catch (Exception e1) {
			log.error("Something went wrong...", e1.fillInStackTrace());
		}
	}

	public String DefaultFavMsg() {
		String defaultMsg = null;
		try {
			defaultMsg = driver.findElement(MobileControlIds.defaultFavMsg).getAttribute("text");
		} catch (InvalidSelectorException e) {
			log.error("Invalid selector exception ...", e.fillInStackTrace());
		} catch (Exception e1) {
			log.error("Something went wrong...", e1.fillInStackTrace());
		}
		return defaultMsg;
	}
	
	
	public String SearchFavMovieNameFromList(String movieName) {
		List<String> movie = new ArrayList<String>();
		util.NavigateBack();
		util.doClick(MobileControlIds.listTab);
		
		List<MobileElement> Elelist = driver.findElements(MobileControlIds.movieList);
		for (MobileElement e : Elelist) {
			 Elelist = driver.findElements(MobileControlIds.movieList);
			movie.add(e.getAttribute("text"));
			}
			if(movie.contains(movieName))
				return movie.get(0);
			else {
				util.scrollForward();
				List<MobileElement> Elelist1 = driver.findElements(MobileControlIds.movieList);
				System.out.println("Elelist1 size... " + Elelist1.size());
				movie.clear();
				for (int i=0; i<Elelist1.size(); i++) {
					movie.add(Elelist1.get(i).getAttribute("text"));
					if(movie.contains(movieName))
						return movieName;
			
			}
				
		}
		return Constants.MOVIE_NOT_FOUND;
	}
	
	public String SetLowRateFromList(String highRateMovie) {
		List<String> movie = new ArrayList<String>();
		util.doClick(MobileControlIds.listTab);
		util.scrollForward();
		
		List<MobileElement> Elelist = driver.findElements(MobileControlIds.movieList);
		System.out.println("Elelist size... " + Elelist.size());
		for (int i=0; i<Elelist.size(); i++) {
			movie.add(Elelist.get(i).getAttribute("text"));
			if(movie.contains(highRateMovie))
				Elelist.get(i).click();
			List<MobileElement> Elelist1 = driver.findElements(MobileControlIds.movieList);
			movie.clear();
			for(int a=0; a<Elelist.size(); a++) {
				movie.add(Elelist1.get(a).getAttribute("text"));
			if(movie.contains(highRateMovie))
				Elelist1.get(a).click();
			}
				util.Swipe(driver, 0.65, 0.65, 0.4, 0.65);
	}
		return movie.get(2);
	}
	
}
