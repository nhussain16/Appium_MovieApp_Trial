package com.qa.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.qa.utils.Constants;

public class ListPageTest extends BaseTest {

	String expectedMovie = "\"The Walking Dead\" Season 1 Reel 2010";

	@Test(description= "Scenario1 as per PDF")
	public void ScrollToFindOutSecondLastItem() {

		String actualMovie = null;
		try {
			actualMovie = listPage.ScrollToGetSecondLastElement();
			Assert.assertEquals(actualMovie, expectedMovie);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test(description= "Scenario2 as per PDF")
	public void MarkUnmarkFavMoviesFromList() {
		try {
			List<String> markedFavMovie = listPage.MarkFavMultipleMovies();
			String searchedMovieIs = listPage.SearchFavMovie(markedFavMovie.get(0));
			System.out.println("Searched movie... " + searchedMovieIs);
			listPage.UnmarkFavMovies();
			Assert.assertEquals(listPage.DefaultFavMsg(), Constants.NO_FAV_MSG);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
