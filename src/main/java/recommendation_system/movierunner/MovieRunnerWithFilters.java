package recommendation_system.movierunner;

import recommendation_system.filters.*;
import recommendation_system.movies.MovieDatabase;
import recommendation_system.ratings.Rating;
import recommendation_system.ratings.ThirdRatings;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.System.Logger;

/**
 * <p>
 *  Class to test the methods on ThirdRatings
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Print average ratings </li>
 *  <li> Get average ratings of the movies </li>
 *  <li> Print average ratings by year </li>
 *  <li> Print average ratings by genre </li>
 *  <li> Print average ratings by minutes </li>
 *  <li> Print average ratings by directors </li>
 *  <li> Print average ratings by year and genre </li>
 *  <li> Print average ratings by directors and minutes </li>
 *  </ul>
 *  </p>
 *
 *  @since 30/12/22
 *  @version 1.0
 */


public class MovieRunnerWithFilters {

    private Logger logger = System.getLogger(MovieRunnerWithFilters.class.getName());
    private static Logger staticLogger  = System.getLogger(MovieRunnerWithFilters.class.getName());
    static final String MOVIESIZE = "Movie size (# of movie in list) : ";
    static final  String RATERSIZE = "Rater size (# of ppl who rates) : ";
    static final String FOUNDRATINGS = "Found ratings for movies : ";
    static final String RATINGSHORT = "data/ratings_short.csv";
    static final String RATEDMOVIES = "ratedmovies_short.csv";


    /**
     * This method prints average ratings
     */
    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings(RATINGSHORT);
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr.getRaterSize());
        ArrayList<Rating> ratingList = tr.getAverageRatings(1);
        logger.log(Logger.Level.INFO, FOUNDRATINGS + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            logger.log(Logger.Level.INFO, i.getValue() + " " + MovieDatabase.getTitle(i.getItem()));
        }
    }

    /**
     * This method gets the average rating of one movie
     */
    public void getAverageRatingOneMovie() {
        ThirdRatings sr = new ThirdRatings(RATINGSHORT);
        ArrayList<Rating> ratingList = sr.getAverageRatings(1);
        String movieTitle = "The Godfather";
        boolean exist = false;
        for (Rating i : ratingList) {
            if (MovieDatabase.getTitle(i.getItem()).equals(movieTitle)) {
                logger.log(Logger.Level.INFO, i.getValue() + " " + movieTitle);
                exist = true;
            }
        }
        if (!exist) {
            logger.log(Logger.Level.INFO, "MOVIE TITLE NOT FOUND!");
        }
    }

    /**
     * This method prints average ratings by year
     */
    public void printAverageRatingsByYear() {
        ThirdRatings tr3 = new ThirdRatings(RATINGSHORT);
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr3.getRaterSize());
        ArrayList<Rating> ratingList = tr3.getAverageRatingsByFilter(1, new YearAfterFilter(2000));
        logger.log(Logger.Level.INFO, FOUNDRATINGS + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            logger.log(Logger.Level.INFO, i.getValue() + " " + MovieDatabase.getYear(i.getItem()) +
                    " " + MovieDatabase.getTitle(i.getItem()));
        }
    }

    /**
     * This method prints average ratings by genre
     */
    public void printAverageRatingsByGenre() {
        ThirdRatings tr4 = new ThirdRatings(RATINGSHORT);
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr4.getRaterSize());
        ArrayList<Rating> ratingList = tr4.getAverageRatingsByFilter(1, new GenreFilter("Crime"));
        logger.log(Logger.Level.INFO, FOUNDRATINGS + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            logger.log(Logger.Level.INFO, i.getValue() + " " + MovieDatabase.getTitle(i.getItem()) +
                    " " + MovieDatabase.getGenres(i.getItem()));
        }
    }

    /**
     * This method prints average ratings by minutes
     */
    public void printAverageRatingsByMinutes() {
        ThirdRatings tr5 = new ThirdRatings(RATINGSHORT);
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr5.getRaterSize());
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, new MinutesFilter(110, 170));
        logger.log(Logger.Level.INFO, FOUNDRATINGS + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            logger.log(Logger.Level.INFO, i.getValue() + " " + MovieDatabase.getMinutes(i.getItem()) +
                    " " + MovieDatabase.getTitle(i.getItem()));
        }
    }

    /**
     * This method prints average ratings by directors
     */
    public void printAverageRatingsByDirectors() {
        ThirdRatings tr5 = new ThirdRatings(RATINGSHORT);
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr5.getRaterSize());
        Filter d = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, d);
        logger.log(Logger.Level.INFO, FOUNDRATINGS + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            logger.log(Logger.Level.INFO, i.getValue() + " " +  MovieDatabase.getTitle(i.getItem()) +
                    " " + MovieDatabase.getDirector(i.getItem()));
        }
    }

    /**
     * This method prints average ratings by year and genre
     */
    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr5 = new ThirdRatings(RATINGSHORT);
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr5.getRaterSize());
        AllFilters all = new AllFilters();
        all.addFilter(new GenreFilter("Romance"));
        all.addFilter(new YearAfterFilter(1980));

        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, all);
        logger.log(Logger.Level.INFO, FOUNDRATINGS + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            logger.log(Logger.Level.INFO, i.getValue() + " " + MovieDatabase.getYear(i.getItem()) +
                    " " + MovieDatabase.getTitle(i.getItem()) + " " + MovieDatabase.getGenres(i.getItem()));
        }
    }

    /**
     * This method prints average ratings by directors and minutes
     */
    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr5 = new ThirdRatings(RATINGSHORT);
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr5.getRaterSize());
        AllFilters all = new AllFilters();
        all.addFilter(new MinutesFilter(30, 170));
        all.addFilter(new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola"));

        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, all);
        logger.log(Logger.Level.INFO, FOUNDRATINGS + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            logger.log(Logger.Level.INFO, i.getValue() + " " + MovieDatabase.getMinutes(i.getItem()) +
                    " " + MovieDatabase.getTitle(i.getItem()) + " " + MovieDatabase.getDirector(i.getItem()));
        }
    }

    public static void main(String[] args) {
        MovieRunnerWithFilters mra = new MovieRunnerWithFilters();
        staticLogger.log(Logger.Level.INFO, "++TEST: printAverageRatings()");
        mra.printAverageRatings();
        staticLogger.log(Logger.Level.INFO, "++TEST: getAverageRatingOneMovie()");
        mra.getAverageRatingOneMovie();
        staticLogger.log(Logger.Level.INFO, "++TEST: printAverageRatingsByYear()");
        mra.printAverageRatingsByYear();
        staticLogger.log(Logger.Level.INFO, "++TEST: printAverageRatingsByGenre()");
        mra.printAverageRatingsByGenre();
        staticLogger.log(Logger.Level.INFO, "++TEST: printAverageRatingsByMinutes()");
        mra.printAverageRatingsByMinutes();
        staticLogger.log(Logger.Level.INFO, "++TEST: printAverageRatingsByDirectors()");
        mra.printAverageRatingsByDirectors();
        staticLogger.log(Logger.Level.INFO, "++TEST: printAverageRatingsByYearAfterAndGenre()");
        mra.printAverageRatingsByYearAfterAndGenre();
        staticLogger.log(Logger.Level.INFO, "++TEST: printAverageRatingsByDirectorsAndMinutes()");
        mra.printAverageRatingsByDirectorsAndMinutes();
    }
}
