package com.qa.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class GridPageTest extends BaseTest{
	
	String validationMsg = "You have no favourites yet!";
	
	@Test(description= "Scenario3 as per PDF")
	public void MarkFavMovieFromGridVerifyFromList() {
		
	String favMovie = gridPage.MarkFavSingleMovie("favourite");
	System.out.println("Fave movie is = " + favMovie);
	String movieName = listPage.SearchFavMovieNameFromList(favMovie);
	System.out.println("Fave movie in list is = " + movieName);
	Assert.assertEquals(favMovie, movieName, "Actual favourite movie is equal to... " + favMovie + "expected movie... " + movieName);

	}
	
	@Test(description= "Scenario4 as per PDF")
	public void RateMovieFromGridVerifyFromList() {
	String highRateMovie = gridPage.MarkFavSingleMovie("highRate");
	System.out.println("Rated movie is = " + highRateMovie);
	String movieName = listPage.SearchFavMovieNameFromList(highRateMovie);
	System.out.println("Rate movie in list is = " + movieName);
	String lowRate = listPage.SetLowRateFromList("highRateMovie");
	System.out.println("Rated movie is = " + lowRate);
	Assert.assertEquals(highRateMovie, lowRate, "Actual rated movie is equal to... " + highRateMovie + "expected movie... " + lowRate);

	}
	
}
