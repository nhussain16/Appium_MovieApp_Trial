package com.qa.utils;

import org.openqa.selenium.By;

import io.appium.java_client.MobileBy;

public class MobileControlIds {

	public static By vimeoBtn = MobileBy.id("de.tdsoftware.moviesharing:id/activity_loading_vimeo_button");
	public static By youTubeBtn = MobileBy.id("de.tdsoftware.moviesharing:id/activity_loading_youtube_button");
	
	public static  By listTab = MobileBy.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc=\"List\"]");

	public static By gridTab = MobileBy.xpath("//androidx.appcompat.app.ActionBar.Tab[@content-desc='Grid']");

	public static By gridElements = MobileBy
			.id("de.tdsoftware.moviesharing:id/recycler_item_movies_grid_thumbnail_image_view");

	public static By movieList = MobileBy.xpath(
			"//android.widget.TextView[@resource-id='de.tdsoftware.moviesharing:id/recycler_item_movies_list_title_text_view']");
	public static By movieListImage = MobileBy.xpath("//android.widget.ImageView[@resource-id='de.tdsoftware.moviesharing:id/recycler_item_movies_list_thumbnail_image_view']");
	
	public static By selectFavMovie = MobileBy.xpath("(//android.widget.ImageView[@resource-id='de.tdsoftware.moviesharing:id/recycler_item_movies_list_thumbnail_image_view'])[3]");
	
	public static By heartBtn = MobileBy
			.xpath("//android.widget.TextView[@resource-id='de.tdsoftware.moviesharing:id/favorite_item']");
	
	public static By movieNameIs = MobileBy.xpath("//android.widget.TextView[@index=1]");
	
	public static By MoviesGrid = MobileBy.id("de.tdsoftware.moviesharing:id/recycler_item_movies_grid_card_view");

	public static By MovieGridThirdElement = MobileBy.xpath("//android.widget.ImageView[@resource-id='de.tdsoftware.moviesharing:id/recycler_item_movies_grid_thumbnail_image_view'])[3]");
	public static By gridMovieName = MobileBy
			.xpath("//android.widget.TextView[@resource-id='de.tdsoftware.moviesharing:id/recycler_item_movies_grid_title_text_view']");
	public static By favMovieBtn = MobileBy.xpath("(//android.widget.ImageView[@resource-id='de.tdsoftware.moviesharing:id/icon'])[2]");
	public static By searchField = MobileBy.className("android.widget.EditText");
	public static By movieName = MobileBy.xpath("//android.widget.TextView[@index=1]");
	public static By defaultFavMsg = MobileBy.id("de.tdsoftware.moviesharing:id/fragment_favorites_empty_state_text_view");
	public static By gridMovieImg = MobileBy.id("de.tdsoftware.moviesharing:id/recycler_item_movies_list_thumbnail_image_view");
	
	public static By starRating = MobileBy.id("de.tdsoftware.moviesharing:id/fragment_movie_details_rating_bar");
}