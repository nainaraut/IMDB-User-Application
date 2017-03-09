package IMDB_MAIN;

public class FinalResult {
		String title;
		String genre;
		int year;
		String country;
		String locations; 
		double avgCriticsRating;
		double avgCriticsReviews;
		FinalResult(String title,String genre,int year,String country,String locations,double avgCriticsRating,double avgCriticsReviews){
			this.title = title;
			this.genre = genre;
			this.year = year;
			this.country = country;
			this.locations = locations; 
			this.avgCriticsRating = avgCriticsRating;
			this.avgCriticsReviews = avgCriticsReviews;
		}
}
