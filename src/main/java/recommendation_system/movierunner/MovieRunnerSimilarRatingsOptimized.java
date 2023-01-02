package recommendation_system.movierunner;

import recommendation_system.movies.MovieDatabase;
import recommendation_system.raters.RaterDatabase;
import recommendation_system.ratings.FourthRatingsOptimized;
import recommendation_system.ratings.Rating;

import java.lang.System.Logger;
import java.util.ArrayList;

/**
 * <p>
 *  Class to test the methods on FourthRatings
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Find and print similar ratings </li>
 *  </ul>
 *  </p>
 *
 *  @since 30/12/22
 *  @version 1.0
 */

public class MovieRunnerSimilarRatingsOptimized {
	
	private Logger logger = System.getLogger(MovieRunnerSimilarRatingsOptimized.class.getName());
	
	private static Logger loggerStatic = System.getLogger(MovieRunnerSimilarRatingsOptimized.class.getName());

    /**
     * This method finds and prints similar ratings
     */
    public void printSimilarRatings() {
        FourthRatingsOptimized tr = new FourthRatingsOptimized();
        ArrayList<Rating> ratingList = tr.getSimilarRatings("65", 20, 5);
        logger.log(Logger.Level.INFO, "Found ratings for movies : " + ratingList.size());
        
        for (int i = 0; i < 3; i++) {
        	logger.log(Logger.Level.INFO, "%-10.2f%s%n", ratingList.get(i).getValue(), MovieDatabase.getTitle(ratingList.get(i).getItem()));
        }
    }

    //    public void printSimilarRatingsByGenre() {
    //        FourthRatingsOptimizedByWeiXu tr4 = new FourthRatingsOptimizedByWeiXu();//do i need put filename here?
    //        ArrayList<Rating> ratingList = tr4.getSimilarRatingsByFilter("65", 20, 5, new GenreFilter("Action"));
    //        System.out.println("Found ratings for movies : " + ratingList.size());
    //        //Collections.sort(ratingList); already sort in FourthRating;
    //        for (int i = 0; i < 3; i++) {
    //            System.out.printf("%-10.2f%-16s%-5s%n", ratingList.get(i).getValue(), MovieDatabase.getTitle(ratingList.get(i).getItem()), MovieDatabase.getGenres(ratingList.get(i).getItem()));
    //        }
    //
    //    }
    //
    //    public void printSimilarRatingsByDirector() {
    //        FourthRatingsOptimizedByWeiXu tr4 = new FourthRatingsOptimizedByWeiXu();//do i need put filename here?
    //        ArrayList<Rating> ratingList = tr4.getSimilarRatingsByFilter("1034", 10, 3, new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone"));
    //        System.out.println("Found ratings for movies : " + ratingList.size());
    //        //Collections.sort(ratingList);
    //        int printNum = ratingList.size();
    //        if (printNum >= 3) printNum = 3;
    //        for (int i = 0; i < printNum; i++) {
    //
    //            System.out.printf("%-10.2f%-16s%-5s%n", ratingList.get(i).getValue(), MovieDatabase.getTitle(ratingList.get(i).getItem()), MovieDatabase.getDirector(ratingList.get(i).getItem()));
    //        }
    //
    //    }
    //
    //    public void printSimilarRatingsByGenreAndMinutes() {
    //        FourthRatingsOptimizedByWeiXu tr4 = new FourthRatingsOptimizedByWeiXu();//do i need put filename here?
    //        AllFilters a = new AllFilters();
    //        a.addFilter(new GenreFilter("Adventure"));
    //        a.addFilter(new MinutesFilter(100, 200));
    //        ArrayList<Rating> ratingList = tr4.getSimilarRatingsByFilter("65", 10, 5, a);
    //        System.out.println("Found ratings for movies : " + ratingList.size());
    //        //Collections.sort(ratingList);
    //        int printNum = ratingList.size();
    //        if (printNum >= 3) printNum = 3;
    //        for (int i = 0; i < printNum; i++) {
    //            System.out.printf("%-10.2f%-5d%-16s%-5s%n", ratingList.get(i).getValue(), MovieDatabase.getMinutes(ratingList.get(i).getItem()), MovieDatabase.getTitle(ratingList.get(i).getItem()), MovieDatabase.getGenres(ratingList.get(i).getItem()));
    //        }
    //
    //    }
    //
    //    public void printSimilarRatingsByYearAfterAndMinutes() {
    //        FourthRatingsOptimizedByWeiXu tr4 = new FourthRatingsOptimizedByWeiXu();//do i need put filename here?
    //        AllFilters a = new AllFilters();
    //        a.addFilter(new YearAfterFilter(2000));
    //        a.addFilter(new MinutesFilter(80, 100));
    //        ArrayList<Rating> ratingList = tr4.getSimilarRatingsByFilter("65", 10, 5, a);
    //        System.out.println("Found ratings for movies : " + ratingList.size());
    //        //Collections.sort(ratingList);
    //        int printNum = ratingList.size();
    //        if (printNum >= 3) printNum = 3;
    //        for (int i = 0; i < printNum; i++) {
    //            //System.out.printf("%-10.2f%-5d%-5d%-16s%-5s%n", ratingList.get(i).getValue(), MovieDatabase.getYear(ratingList.get(i).getItem()), MovieDatabase.getMinutes(ratingList.get(i).getItem()), MovieDatabase.getTitle(ratingList.get(i).getItem()), MovieDatabase.getGenres(ratingList.get(i).getItem()));
    //            System.out.printf("%-10.2f%-5s%n", ratingList.get(i).getValue(), MovieDatabase.getMovie(ratingList.get(i).getItem()).toString());
    //
    //        }
    //    }
    //

    public static void main(String[] args) {
        MovieRunnerSimilarRatingsOptimized mra = new MovieRunnerSimilarRatingsOptimized();

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
        loggerStatic.log(Logger.Level.INFO, "---------------Duration = " + duration1 + "s-------------");

        //        System.out.println("---------------Test: printSimilarRatingsByGenre()----------------");
        //        double start2 = System.nanoTime();
        //        mra.printSimilarRatingsByGenre();
        //        double duration2 = (System.nanoTime() - start2) / 1000000000;
        //        System.out.println("---------------Duration = " + duration2 + "s-------------");
        //        // MovieRunnerSimilarRatings duration                  : 12.3
        //        // MovieRunnerSimilarRatingsOptimizedByWeiXu: duration : 4.61
        //
        //        System.out.println("---------------Test: printSimilarRatingsDirector()----------------");
        //        double start3 = System.nanoTime();
        //        mra.printSimilarRatingsByDirector();
        //        double duration3 = (System.nanoTime() - start3) / 1000000000;
        //        System.out.println("---------------Duration = " + duration3 + "s-------------");
        //        // MovieRunnerSimilarRatings duration                  : 0.511
        //        // MovieRunnerSimilarRatingsOptimizedByWeiXu: duration : 0.16
        //
        //        System.out.println("---------------Test: printSimilarRatingsByGenreAndMinutes()----------------");
        //        double start4 = System.nanoTime();
        //        mra.printSimilarRatingsByGenreAndMinutes();
        //        double duration4 = (System.nanoTime() - start4) / 1000000000;
        //        System.out.println("---------------Duration = " + duration4 + "s-------------");
        //        // MovieRunnerSimilarRatings duration                  : 6.3876
        //        // MovieRunnerSimilarRatingsOptimizedByWeiXu: duration : 1.96
        //
        //        System.out.println("---------------Test: printSimilarRatingsByYearAfterAndMinutes()----------------");
        //        double start5 = System.nanoTime();
        //        mra.printSimilarRatingsByYearAfterAndMinutes();
        //        double duration5 = (System.nanoTime() - start5) / 1000000000;
        //        System.out.println("---------------Duration = " + duration5 + "s-------------");
        //        // MovieRunnerSimilarRatings duration                  : 18.49
        //        // MovieRunnerSimilarRatingsOptimizedByWeiXu: duration : 3.58
        //
    }
}
