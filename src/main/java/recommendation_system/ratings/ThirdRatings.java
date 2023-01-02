package recommendation_system.ratings;

import recommendation_system.movies.MovieDatabase;
import recommendation_system.filters.Filter;
import recommendation_system.filters.TrueFilter;
import recommendation_system.raters.Rater;

import java.util.ArrayList;

/**
 * <p>
 *  Class to find the average rating of movies using different filters - Phase 3
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Get a similarity rating for each rater </li>
 *  <li> Get a average ratings </li>
 *  <li> Get a average ratings by filters </li>
 *  </ul>
 *  </p>
 *
 *  @since 1/1/23
 *  @version 1.0
 */

public class ThirdRatings {

    private ArrayList<Rater> myRaters;
    private System.Logger logger = System.getLogger(ThirdRatings.class.getName());
    private static System.Logger staticLogger = System.getLogger(ThirdRatings.class.getName());

    /**
     * Default Builder
     */
    public ThirdRatings() {
        this("ratings.csv");
    }

    /**
     * Builder overload
     * @param ratingFile - A String variable representing the rating file
     */
    public ThirdRatings(String ratingFile) {
        FirstRatings firstRatings = new FirstRatings();
        myRaters = firstRatings.loadRaters(ratingFile);
    }

    /**
     * This method gets the number of the raters
     * @return Integer - Number of raters
     */
    public int getRaterSize() {
        return myRaters.size();
    }

    /**
     * This method gets the average rating by ID
     * @param movieID - A String variable representing the ID of the movie
     * @param minimalRaters - An integer variable representing the minimal number of raters
     * @return Double - Average rating
     */
    private double getAverageByID(String movieID, int minimalRaters) {
        int count = 0;
        double total = 0;
        for (Rater i : myRaters) {
            double rating = i.getRating(movieID);
            if (rating != -1) {
                count++;
                total += rating;
            }
        }
        if(count > 0)
            logger.log(System.Logger.Level.INFO, "Movie ID : Count : Total : Rating = " + movieID +
                    " " + count + " " + total + " " + (total/count));
        if (count >= minimalRaters && count > 0)
            return total / count;

        return 0.0;
    }

    /**
     * This method gets the average ratings
     * @param minimalRaters - An integer variable representing the minimal number of raters
     * @return Arraylist<Rating> of average ratings
     */
    public ArrayList<Rating> getAverageRatings(int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        Filter trueFilter = new TrueFilter();
        for (String i : MovieDatabase.filterBy(trueFilter)) {
            double ave = getAverageByID(i, minimalRaters);
            if (ave > 0)
                ratingList.add(new Rating(i, ave));
        }
        return ratingList;
    }

    /**
     * This method creates a list of all the movies that have at least minimalRaters ratings and satisfies the filter criteria
     * @param minimalRaters - An integer variable representing the minimal number of raters
     * @param f - Represents filters
     * @return Arraylist<Rating> of average ratings
     */
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter f) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        Filter trueFilter = new TrueFilter();
        ArrayList<String> movieID = MovieDatabase.filterBy(trueFilter);

        for (String i : movieID) {
            if (f.satisfies(i)) {
                double ave = getAverageByID(i, minimalRaters);
                if (ave > 0)
                    ratingList.add(new Rating(i, ave));
            }
        }
        return ratingList;
    }

    //
    //    public String getTitle(String movieID) {
    //        for (Movie i : myMovies) {
    //            if (i.getID().equals(movieID)) {
    //                return i.getTitle();
    //            }
    //        }
    //        return "The Movie ID was not found!";
    //    }
    //
    //    public String getID(String title) {
    //        for (Movie i : myMovies) {
    //            if (i.getTitle().equals(title)) {
    //                return i.getID();
    //            }
    //        }
    //        return "NO SUCH TITLE";
    //    }
    //

    public static void main(String[] args) {
        ThirdRatings thirdRatings = new ThirdRatings("data/ratings_short.csv");
        //System.out.println(sr.getAverageByID("0790636", 2));
        staticLogger.log(System.Logger.Level.INFO, "---TEST---");
        staticLogger.log(System.Logger.Level.INFO, thirdRatings.getAverageRatings(2));
    }

}
