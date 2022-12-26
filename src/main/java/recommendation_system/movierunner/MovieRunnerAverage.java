package recommendation_system.movierunner;

import recommendation_system.ratings.Rating;
import recommendation_system.ratings.SecondRatings;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.System.Logger;

/**
 * <p>
 *  Class to test the methods created in SecondRatings. - Phase 2
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Print average ratings. </li>
 *  <li> Get average rating of one movie. </li>
 *  </ul>
 *  </p>
 *
 *  @since 26/12/22
 *  @version 1.0
 */
public class MovieRunnerAverage {

	private Logger logger = System.getLogger(MovieRunnerAverage.class.getName());

	private  static Logger loggerStatic = System.getLogger(MovieRunnerAverage.class.getName());


    public void printAverageRatings(String ratedMovies, String ratings) {
        SecondRatings sr = new SecondRatings(ratedMovies, ratings);
        logger.log(Logger.Level.INFO, "Movie size = " + sr.getMovieSize());
        logger.log(Logger.Level.INFO, "Rater size = " + sr.getRaterSize());
        ArrayList<Rating> ratingList = sr.getAverageRatings(2);
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
        	logger.log(Logger.Level.INFO, i.getValue() + " " + sr.getTitle(i.getItem()));
        }
    }

    public void getAverageRatingOneMovie(String movieTitle, String ratedMovies, String ratings) {
        SecondRatings sr = new SecondRatings(ratedMovies, ratings);
        ArrayList<Rating> ratingList = sr.getAverageRatings(2);
        for (Rating i : ratingList) {
            if (sr.getTitle(i.getItem()).equals(movieTitle)) {
            	logger.log(Logger.Level.INFO, i.getValue() + " " + sr.getTitle(i.getItem()));
            }
        }
    }

    public static void main(String[] args) {
        MovieRunnerAverage mra = new MovieRunnerAverage();
        loggerStatic.log(Logger.Level.INFO, "---printAverageRatings---");
        mra.printAverageRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        loggerStatic.log(Logger.Level.INFO, "---getAverageRatingOneMovie---");
        mra.getAverageRatingOneMovie("The Godfather", "data/ratedmovies_short.csv",
                "data/ratings_short.csv");
    }
}
