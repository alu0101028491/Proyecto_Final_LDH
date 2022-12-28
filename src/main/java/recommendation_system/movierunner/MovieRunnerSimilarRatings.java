package recommendation_system.movierunner;

import recommendation_system.filters.*;
import recommendation_system.movies.MovieDatabase;
import recommendation_system.raters.RaterDatabase;
import recommendation_system.ratings.FourthRatings;
import recommendation_system.ratings.Rating;

import java.util.ArrayList;
import java.lang.System.Logger;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 13-14th, 2019
 *
 *  Description:  -------------------STEP FOUR--------
 *                 similar to the MovieRunnerWithFilters class in step three.
 *                 however, i applied two algorithm for calculating the weighted average ranking.
 *                 that's why my output of WEI XU algorithm is different from the DUKE algorithm.
 *                 mine is more accurate for the movie ranking and recommendations.
 *
 *  * IMPORTANT NOTICE:
 *      * Weighted average algorithm optimized by WEI XU
 *      * Instead of  "sum of (similar rating(i) *rating of the movie(i))/count of the raters"
 *      * !!!!!I use  "sum of (similar rating(i) *rating of the movie(i))/ sum of the similar rating(i)", will achieve better results.
 *
 ****************************************************************/

public class MovieRunnerSimilarRatings {

	private static final String foundRatingsPrint = "Found ratings for movies : ";

	private static String[] durationInfo = {"---------------Duration = ", "s-------------"};

	private Logger logger = System.getLogger(MovieRunnerSimilarRatings.class.getName());

	private static Logger loggerStatic = System.getLogger(MovieRunnerSimilarRatings.class.getName());


    /**
     * top 0-10 similar rater id:
     * id = 512 rating 182.0
     * id = 1040 rating 176.0
     * id = 146 rating 160.0
     * id = 934 rating 143.0
     * id = 735 rating 126.0
     * id = 855 rating 120.0
     * id = 769 rating 116.0
     * id = 431 rating 105.0
     * id = 76 rating 99.0
     * id = 168 rating 99.0
     * <p>
     * top 10-20 rater id
     * id = 463 rating 99.0
     * id = 570 rating 98.0
     * id = 678 rating 97.0
     * id = 882 rating 96.0
     * id = 846 rating 96.0
     * id = 124 rating 91.0
     * id = 555 rating 91.0
     * id = 590 rating 82.0
     * id = 25 rating 80.0
     * id = 88 rating 79.0
     */
    public void printSimilarRatings() {
        FourthRatings tr = new FourthRatings();
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr.getSimilarRatings("65", 20, 5);
        logger.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());

        for (int i = 0; i < 3; i++) {
            logger.log(Logger.Level.INFO, ratingList.get(i).getValue() + " " +
                    MovieDatabase.getTitle(ratingList.get(i).getItem()));
        }
    }

    public void printSimilarRatingsByGenre() {
        FourthRatings tr4 = new FourthRatings();
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr4.getSimilarRatingsByFilter("65", 20, 5, new GenreFilter("Action"));
        logger.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());
        for (int i = 0; i < 3; i++) {
        	logger.log(Logger.Level.INFO, ratingList.get(i).getValue() + " " +
                    MovieDatabase.getTitle(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getGenres(ratingList.get(i).getItem()));
        }

    }

    public void printSimilarRatingsByDirector() {
        FourthRatings tr4 = new FourthRatings();//do i need put filename here?
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr4.getSimilarRatingsByFilter("1034", 10, 3, new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
        logger.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());
        int printNum = ratingList.size();
        if (printNum >= 3) printNum = 3;
        for (int i = 0; i < printNum; i++) {
        	logger.log(Logger.Level.INFO, ratingList.get(i).getValue() + " "  +
                    MovieDatabase.getMinutes(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getTitle(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getGenres(ratingList.get(i).getItem()));
        }

    }

    public void printSimilarRatingsByGenreAndMinutes() {
        FourthRatings tr4 = new FourthRatings();//do i need put filename here?
        AllFilters a = new AllFilters();
        a.addFilter(new GenreFilter("Adventure"));
        a.addFilter(new MinutesFilter(100, 200));
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr4.getSimilarRatingsByFilter("65", 10, 5, a);
        logger.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());
        int printNum = ratingList.size();
        if (printNum >= 3) printNum = 3;
        for (int i = 0; i < printNum; i++) {
            logger.log(Logger.Level.INFO, ratingList.get(i).getValue() + " " +
                    MovieDatabase.getMinutes(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getTitle(ratingList.get(i).getItem()) + " " +
                    MovieDatabase.getGenres(ratingList.get(i).getItem()));
        }

    }

    public void printSimilarRatingsByYearAfterAndMinutes() {
        FourthRatings tr4 = new FourthRatings();
        AllFilters a = new AllFilters();
        a.addFilter(new YearAfterFilter(2000));
        a.addFilter(new MinutesFilter(80, 100));
        ArrayList<Rating> ratingList = (ArrayList<Rating>) tr4.getSimilarRatingsByFilter("65", 10, 5, a);
        logger.log(Logger.Level.INFO, foundRatingsPrint + ratingList.size());

        int printNum = ratingList.size();
        if (printNum >= 3) printNum = 3;
        for (int i = 0; i < printNum; i++) {
            logger.log(Logger.Level.INFO, ratingList.get(i).getValue() + " " +
                    MovieDatabase.getMovie(ratingList.get(i).getItem()).toString());
        }
    }

    public static void main(String[] args) {
        MovieRunnerSimilarRatings mra = new MovieRunnerSimilarRatings();
        loggerStatic.log(Logger.Level.INFO, "----------Weighted average algorithm optimized by WEI XU DECEMBER 14 2019------------");
        loggerStatic.log(Logger.Level.INFO, "DUKE:   \"sum of (similar rating(i) *rating of the movie(i))/count of the raters\"");
        loggerStatic.log(Logger.Level.INFO, "WEI XU: \"sum of (similar rating(i) *rating of the movie(i))/ sum of the similar rating(i)\", will achieve better results.");
        loggerStatic.log(Logger.Level.INFO, "-----------The FOLLOWING RESULTS are Algorithm by DUKE -------------");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        loggerStatic.log(Logger.Level.INFO, "Movie size (# of movie in list) : " + MovieDatabase.size());
        loggerStatic.log(Logger.Level.INFO, "Rater size (# of ppl who rates) : " + RaterDatabase.size());
        loggerStatic.log(Logger.Level.INFO, "---------------Test: printSimilarRatings()----------------");
        double start1 = System.nanoTime();
        mra.printSimilarRatings();
        double duration1 = (System.nanoTime() - start1) / 1000000000;
        loggerStatic.log(Logger.Level.INFO, durationInfo[0] + duration1 + durationInfo[1]);
        loggerStatic.log(Logger.Level.INFO, "---------------Test: printSimilarRatingsByGenre()----------------");
        double start2 = System.nanoTime();
        mra.printSimilarRatingsByGenre();
        double duration2 = (System.nanoTime() - start2) / 1000000000;
        loggerStatic.log(Logger.Level.INFO, durationInfo[0] + duration2 + durationInfo[1]);
        loggerStatic.log(Logger.Level.INFO, "---------------Test: printSimilarRatingsDirector()----------------");
        double start3 = System.nanoTime();
        mra.printSimilarRatingsByDirector();
        double duration3 = (System.nanoTime() - start3) / 1000000000;
        loggerStatic.log(Logger.Level.INFO, durationInfo[0] + duration3 + durationInfo[1]);
        loggerStatic.log(Logger.Level.INFO, "---------------Test: printSimilarRatingsByGenreAndMinutes()----------------");
        double start4 = System.nanoTime();
        mra.printSimilarRatingsByGenreAndMinutes();
        double duration4 = (System.nanoTime() - start4) / 1000000000;

        loggerStatic.log(Logger.Level.INFO, durationInfo[0] + duration4 + durationInfo[1]);
        loggerStatic.log(Logger.Level.INFO, "---------------Test: printSimilarRatingsByYearAfterAndMinutes()----------------");
        double start5 = System.nanoTime();
        mra.printSimilarRatingsByYearAfterAndMinutes();
        double duration5 = (System.nanoTime() - start5) / 1000000000;

        loggerStatic.log(Logger.Level.INFO, durationInfo[0] + duration5 + durationInfo[1]);
    }

}
