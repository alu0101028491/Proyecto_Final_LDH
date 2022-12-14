package recommendation_system.movierunner;

import recommendation_system.filters.*;
import recommendation_system.movies.MovieDatabase;
import recommendation_system.ratings.Rating;
import recommendation_system.ratings.ThirdRatings;
import java.util.ArrayList;
import java.util.Collections;
import java.lang.System.Logger;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP THREE--------
 *                 Create a new class named MovieRunnerWithFilters that
 *                 you will use to find the average rating of movies using
 *                 different filters.
 *                 Copy the printAverageRatings method from the MovieRunnerAverage
 *                 class into this class. You will make several changes to this method:
 *
 ****************************************************************/

public class MovieRunnerWithFilters {

    private Logger logger = System.getLogger(MovieRunnerWithFilters.class.getName());
    private static Logger staticLogger  = System.getLogger(MovieRunnerWithFilters.class.getName());
    static final String MOVIESIZE = "Movie size (# of movie in list) : ";
    static final  String RATERSIZE = "Rater size (# of ppl who rates) : ";
    static final String FOUNDRATINGS = "Found ratings for movies : ";
    static final String RATINGSHORT = "data/ratings_short.csv";
    static final String RATEDMOVIES = "ratedmovies_short.csv";

    public void printAverageRatings() {
        ThirdRatings tr = new ThirdRatings(RATINGSHORT);//do i need put filename here?
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

    public void getAverageRatingOneMovie() {
        ThirdRatings sr = new ThirdRatings(RATINGSHORT);//do i need put filename here?
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

    public void printAverageRatingsByYear() {
        ThirdRatings tr3 = new ThirdRatings(RATINGSHORT);//do i need put filename here?
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

    public void printAverageRatingsByGenre() {
        ThirdRatings tr4 = new ThirdRatings(RATINGSHORT);//do i need put filename here?
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

    public void printAverageRatingsByMinutes() {
        ThirdRatings tr5 = new ThirdRatings(RATINGSHORT);//do i need put filename here?
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

    public void printAverageRatingsByDirectors() {
        ThirdRatings tr5 = new ThirdRatings(RATINGSHORT);//do i need put filename here?
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr5.getRaterSize());
        //what if i use DirectorsFilter d instead of Filter?
        Filter d = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> ratingList = tr5.getAverageRatingsByFilter(1, d);
        logger.log(Logger.Level.INFO, FOUNDRATINGS + ratingList.size());
        Collections.sort(ratingList);
        for (Rating i : ratingList) {
            logger.log(Logger.Level.INFO, i.getValue() + " " +  MovieDatabase.getTitle(i.getItem()) +
                    " " + MovieDatabase.getDirector(i.getItem()));
        }
    }

    public void printAverageRatingsByYearAfterAndGenre() {
        ThirdRatings tr5 = new ThirdRatings(RATINGSHORT);//do i need put filename here?
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr5.getRaterSize());
        //why here must use AllFilters instead of Filter?
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

    public void printAverageRatingsByDirectorsAndMinutes() {
        ThirdRatings tr5 = new ThirdRatings(RATINGSHORT);//do i need put filename here?
        MovieDatabase.initialize(RATEDMOVIES);
        logger.log(Logger.Level.INFO, MOVIESIZE + MovieDatabase.size());
        logger.log(Logger.Level.INFO, RATERSIZE + tr5.getRaterSize());
        //why here must use AllFilters instead of Filter?
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
