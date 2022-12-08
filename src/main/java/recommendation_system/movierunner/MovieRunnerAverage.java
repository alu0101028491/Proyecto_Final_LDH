package recommendation_system.movierunner;

import recommendation_system.ratings.Rating;
import recommendation_system.ratings.SecondRatings;

import java.util.ArrayList;
import java.util.Collections;

import java.lang.System.Logger;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 11th, 2019
 *
 *  Description:  -------------------STEP TWO-------------------------
 *                 You will build on this assignment by calculating
 *                 average ratings of movies.
 *                 In this assignment you will modify a new class named SecondRatings,
 *                 which has been started for you, to do many of the calculations
 *                 focusing on computing averages on movie ratings.
 *                 You will also create a second new class named MovieRunnerAverage,
 *                 which you will use to test the methods you created in SecondRatings
 *                 by creating a SecondRatings object in MovieRunnerAverage and
 *                 calling its methods.
 *
 ****************************************************************/
public class MovieRunnerAverage {

	private Logger logger = System.getLogger(MovieRunnerAverage.class.getName());

	private  static Logger loggerStatic = System.getLogger(MovieRunnerAverage.class.getName());


    public void printAverageRatings() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");//do i need put filename here?
        logger.log(Logger.Level.INFO, "Movie size = " + sr.getMovieSize());
        logger.log(Logger.Level.INFO, "Rater size = " + sr.getRaterSize());
        ArrayList<Rating> ratingList = sr.getAverageRatings(2);
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
        	logger.log(Logger.Level.INFO, "%-10.2f%s%n", i.getValue(), sr.getTitle(i.getItem()));
        }
    }

    public void getAverageRatingOneMovie() {
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");//do i need put filename here?
        ArrayList<Rating> ratingList = sr.getAverageRatings(2);
        String movieTitle = "The Godfather";
        for (Rating i : ratingList) {
            if (sr.getTitle(i.getItem()).equals(movieTitle)) {
            	logger.log(Logger.Level.INFO, "%-10.2f%s%n", i.getValue(), sr.getTitle(i.getItem()));
            }
        }
    }

    public static void main(String[] args) {
        MovieRunnerAverage mra = new MovieRunnerAverage();
        loggerStatic.log(Logger.Level.INFO, "---------------print test----------------");
        mra.printAverageRatings();
        loggerStatic.log(Logger.Level.INFO, "---------------get average rating one movie test----------------");
        mra.getAverageRatingOneMovie();
    }
}
