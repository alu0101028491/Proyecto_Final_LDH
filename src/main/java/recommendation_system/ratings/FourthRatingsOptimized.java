package recommendation_system.ratings;

import recommendation_system.movies.MovieDatabase;
import recommendation_system.raters.RaterDatabase;
import recommendation_system.filters.Filter;
import recommendation_system.raters.Rater;
import java.lang.System.Logger;
import java.util.ArrayList;
import java.util.Collections;

/**
 * <p>
 *  Class to get similar ratings - Phase 4 (Optimized)
 *  Instead of iterate all movie in the MovieDataBase, we use the movie list
 *  that only the topSimilarRater's movie. Greatly reduce the time.
 *  </p>
 *  <p>
 *  DUKE: "sum of (similar rating(i) *rating of the movie(i))/count of the raters"
 *  </p>
 *  <p>
 *  WEIXU: "sum of (similar rating(i) *rating of the movie(i))/ sum of the similar rating(i)"
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Get a similarity rating for each rater </li>
 *  <li> Get a similar ratings </li>
 *  <li> Get a similar ratings by filter </li>
 *  </ul>
 *  </p>
 *
 *  @since 1/1/23
 *  @version 1.0
 */
public class FourthRatingsOptimized {
	
	private static Logger loggerStaticFourthRatingsOptimized = System.getLogger(FourthRatingsOptimized.class.getName());

    /**
     * @param me Rater being analyzed
     * @param r Rater used as external reference (from RaterDatabase)
     * @return double - Scalar product of ratings between two users
     */
    private double dotProduct(Rater me, Rater r) {
        double dp = 0;
        ArrayList<String> memovieid = me.getItemsRated();
        for (String id : memovieid) {
            if (r.getItemsRated().contains(id)) {
                dp += (me.getRating(id) - 5) * (r.getRating(id) - 5);
            }
        }
        return dp;
    }

    /**
     * Computes a similarity rating for each rater in the RaterDatabase
     * to see how similar they are to the Rater. It shows us the affinity
     * of a user with other raters.
     * @param raterId Rater to analyze (not included into the analysis)
     * @return ArrayList - Ratings sorted from highest to lowest
     */
    private ArrayList<Rating> getSimilarities(String raterId) {
        ArrayList<Rating> simiList = new ArrayList<>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater r : raters) {
            if (!r.getID().equals(raterId)) {
                double dotProduct = dotProduct(RaterDatabase.getRater(raterId), r);
                if (dotProduct > 0) {
                    simiList.add(new Rating(r.getID(), dotProduct));
                }
            }
        }
        Collections.sort(simiList);
        Collections.reverse(simiList);

        return simiList;
    }

    /**
     * This method obtains the movies and their weighted average ratings using
     * only the top numSimilarRaters with positive ratings and including only
     * those movies that have at least minimalRaters ratings from those most
     * similar raters (not just minimalRaters ratings overall).
     * @param raterID Rater to analyze (not included into the analysis)
     * @param numSimilarRaters Raters to be analyzed within the set recommended to the user
     * @param minimalRaters Minimum number of Raters to have recommended a movie
     * @return ArrayList - Movies and their weighted average ratings
     */
    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        // get Arraylist<movieID> from top numSimilarRaters.
        ArrayList<String> movidIDByTopSimilar = new ArrayList<>();
        ArrayList<Rating> simiList1 = getSimilarities(raterID);
        // Verified number of Raters related to the user
        int num = simiList1.size();
        if (numSimilarRaters > num) {
            numSimilarRaters = num;
        }
        // Get IDs of movies recommended by simiList1 top Raters
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID1 = simiList1.get(i).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(raterID1).getItemsRated();
            for (String movieID : movieRated1) {
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                }
            }
        }

        // Rating for movies in the movieIDByTopSimilar list;
        for (String j : movidIDByTopSimilar) {
            // Rating for one movie
            double ave = 0;
            ArrayList<Rating> simiList = getSimilarities(raterID);
            int count = 0;
            double total = 0;
            int simiweighttotal = 0;

            // Based on the ratings of our top Raters we obtain an affinity for each film.
            for (int i = 0; i < numSimilarRaters; i++) {

                double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);

                if (rating != -1) {
                    count++;
                    total += rating * simiList.get(i).getValue();
                    simiweighttotal += simiList.get(i).getValue();
                }
            }
            if (count >= minimalRaters && simiweighttotal > 0)
                ave = total / simiweighttotal;
            if (ave > 0)
                ratingList.add(new Rating(j, ave));
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);

        return ratingList;
    }

    /**
     * This method gets similar ratings following a certain criteria
     * @param raterID Rater to analyze (not included into the analysis)
     * @param numSimilarRaters Raters to be analyzed within the set recommended to the user
     * @param minimalRaters Minimum number of Raters to have recommended a movie
     * @param f - Represents the filters
     * @return ArrayList - Movies and their weighted average ratings
     */
    public ArrayList<Rating> getSimilarRatingsByFilter(String raterID, int numSimilarRaters, int minimalRaters, Filter f) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        //Rating for all movie
        ArrayList<String> movidIDByTopSimilar = new ArrayList<>();
        ArrayList<Rating> simiList1 = getSimilarities(raterID);
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID1 = simiList1.get(i).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(raterID1).getItemsRated();
            for (String movieID : movieRated1) {
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                }
            }
        }

        for (String j : movidIDByTopSimilar) {
            if (f.satisfies(j)) {
                // Rating for one movie
                double ave = 0;
                ArrayList<Rating> simiList = getSimilarities(raterID);
                int count = 0;
                double total = 0;
                double simiweighttotal = 0;

                for (int i = 0; i < numSimilarRaters; i++) {
                    double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                    if (rating != -1) {
                        count++;
                        total += rating * simiList.get(i).getValue();
                        simiweighttotal += simiList.get(i).getValue();
                    }
                }
                if (count >= minimalRaters && simiweighttotal > 0)
                    ave = total / simiweighttotal;
                if (ave > 0)
                    ratingList.add(new Rating(j, ave));
            }
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);

        return ratingList;
    }

    public static void main(String[] args) {
        MovieDatabase.initialize("ratedmovies_short.csv");
        RaterDatabase.initialize("ratings_short.csv");
        FourthRatingsOptimized sr = new FourthRatingsOptimized();
        loggerStaticFourthRatingsOptimized.log(Logger.Level.INFO, "---------------test-------------");
        loggerStaticFourthRatingsOptimized.log(Logger.Level.INFO, sr.getSimilarRatings("2", 3, 0));
    }
}
