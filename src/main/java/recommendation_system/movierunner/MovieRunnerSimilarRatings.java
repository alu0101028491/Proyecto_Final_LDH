package recommendation_system.movierunner;

import recommendation_system.filters.*;
import recommendation_system.movies.MovieDatabase;
import recommendation_system.raters.RaterDatabase;
import recommendation_system.ratings.FourthRatings;
import recommendation_system.ratings.Rating;

import java.util.ArrayList;
import java.lang.System.Logger;

/**
 * <p>
 *  Class to test the methods on FourthRatings
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Print similar ratings </li>
 *  <li> Print similar ratings by genre </li>
 *  <li> Print similar ratings by directors </li>
 *  <li> Print similar ratings by genre and minutes </li>
 *  <li> Print similar ratings by year and minutes </li>
 *  </ul>
 *  </p>
 *
 *  @since 30/12/22
 *  @version 1.0
 */

public class MovieRunnerSimilarRatings {

	private static final String foundRatingsPrint = "Found ratings for movies : ";

	private static String[] durationInfo = {"---------------Duration = ", "s-------------"};

	private Logger loggerMovieRunnerSimilarRatings = System.getLogger(MovieRunnerSimilarRatings.class.getName());

	private static Logger loggerStaticMovieRunnerSimilarRatings = System.getLogger(MovieRunnerSimilarRatings.class.getName());

    /**
     * Method to print similar ratings
     */
    public void printSimilarRatings() {
        FourthRatings tr = new FourthRatings();
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr.getSimilarRatings("65", 20, 5);
        loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());

        for (int i = 0; i < 3; i++) {
            loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, ratingList.get(i).getValue() + " " +
                    MovieDatabase.getTitle(ratingList.get(i).getItem()));
        }
    }

    /**
     * Method to print similar ratings by genre
     */
    public void printSimilarRatingsByGenre() {
        FourthRatings tr4 = new FourthRatings();
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr4.getSimilarRatingsByFilter("65", 20, 5, new GenreFilter("Action"));
        loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());
        for (int i = 0; i < 3; i++) {
        	loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, ratingList.get(i).getValue() + " " +
                    MovieDatabase.getTitle(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getGenres(ratingList.get(i).getItem()));
        }

    }

    /**
     * Method to print similar ratings by director
     */
    public void printSimilarRatingsByDirector() {
        FourthRatings tr4 = new FourthRatings();
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr4.getSimilarRatingsByFilter("1034", 10, 3, new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
        loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());
        int printNum = ratingList.size();
        if (printNum >= 3) printNum = 3;
        for (int i = 0; i < printNum; i++) {
        	loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, ratingList.get(i).getValue() + " "  +
                    MovieDatabase.getMinutes(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getTitle(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getGenres(ratingList.get(i).getItem()));
        }

    }

    /**
     * Method to print similar ratings by genre and minutes
     */
    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings tr4 = new FourthRatings();
        AllFilters a = new AllFilters();
        a.addFilter(new GenreFilter("Adventure"));
        a.addFilter(new MinutesFilter(100, 200));
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr4.getSimilarRatingsByFilter("65", 10, 5, a);
        loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());
        int printNum = ratingList.size();
        if (printNum >= 3) printNum = 3;
        for (int i = 0; i < printNum; i++) {
            loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, ratingList.get(i).getValue() + " " +
                    MovieDatabase.getMinutes(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getTitle(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getGenres(ratingList.get(i).getItem()));
        }

    }

    /**
     * Method to print similar ratings by year and minutes
     */
    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings tr4 = new FourthRatings();
        AllFilters a = new AllFilters();
        a.addFilter(new YearAfterFilter(2000));
        a.addFilter(new MinutesFilter(80, 100));
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr4.getSimilarRatingsByFilter("65", 10, 5, a);
        loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());

        int printNum = ratingList.size();
        if (printNum >= 3) printNum = 3;
        for (int i = 0; i < printNum; i++) {
            loggerMovieRunnerSimilarRatings.log(Logger.Level.INFO, ratingList.get(i).getValue() + " " +
                    MovieDatabase.getMovie(ratingList.get(i).getItem()).toString());
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mra = new MovieRunnerSimilarRatings();
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "----------Weighted average algorithm optimized by WEI XU DECEMBER 14 2019------------");
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "DUKE:   \"sum of (similar rating(i) *rating of the movie(i))/count of the raters\"");
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "WEI XU: \"sum of (similar rating(i) *rating of the movie(i))/ sum of the similar rating(i)\", will achieve better results.");
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "-----------The FOLLOWING RESULTS are Algorithm by DUKE -------------");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "Movie size (# of movie in list) : " + MovieDatabase.size());
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "Rater size (# of ppl who rates) : " + RaterDatabase.size());
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "---------------Test: printSimilarRatings()----------------");
        double start1 = System.nanoTime();
        mra.printSimilarRatings();
        double duration1 = (System.nanoTime() - start1) / 1000000000;
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, durationInfo[0] + duration1 + durationInfo[1]);
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "---------------Test: printSimilarRatingsByGenre()----------------");
        double start2 = System.nanoTime();
        mra.printSimilarRatingsByGenre();
        double duration2 = (System.nanoTime() - start2) / 1000000000;
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, durationInfo[0] + duration2 + durationInfo[1]);
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "---------------Test: printSimilarRatingsDirector()----------------");
        double start3 = System.nanoTime();
        mra.printSimilarRatingsByDirector();
        double duration3 = (System.nanoTime() - start3) / 1000000000;
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, durationInfo[0] + duration3 + durationInfo[1]);
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "---------------Test: printSimilarRatingsByGenreAndMinutes()----------------");
        double start4 = System.nanoTime();
        mra.printSimilarRatingsByGenreAndMinutes();
        double duration4 = (System.nanoTime() - start4) / 1000000000;

        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, durationInfo[0] + duration4 + durationInfo[1]);
        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, "---------------Test: printSimilarRatingsByYearAfterAndMinutes()----------------");
        double start5 = System.nanoTime();
        mra.printSimilarRatingsByYearAfterAndMinutes();
        double duration5 = (System.nanoTime() - start5) / 1000000000;

        loggerStaticMovieRunnerSimilarRatings.log(Logger.Level.INFO, durationInfo[0] + duration5 + durationInfo[1]);
    }

}
