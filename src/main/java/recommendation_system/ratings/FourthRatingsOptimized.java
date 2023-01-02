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
     * This method computes a similarity rating for each rater in the RaterDatabase to see
     * how similar they are to the Rater whose ID is the parameter to getSimilarities.
     * @param raterId - A String variable representing the ID of the rater
     * @return Arraylist<Rating> of the similarities
     */
    private ArrayList<Rating> getSimilarities(String raterId) {
        ArrayList<Rating> simiList = new ArrayList<>();
        ArrayList<Rater> raters = RaterDatabase.getRaters();
        for (Rater r : raters) {
            if (!r.getID().equals(raterId)) {
                double dotProduct = dotProduct(RaterDatabase.getRater(raterId), r);
                if (dotProduct > 0) {
                    simiList.add(new Rating(r.getID(), dotProduct));
                    // System.out.println("rater id = " + r.getID() + " dotProduct " + dotProduct);
                }
            }
        }
        Collections.sort(simiList);
        Collections.reverse(simiList);

        //
        //        for (Rating i : simiList) {
        //            System.out.println("rater id = " + i.getItem() + " dotProduct " + i.getValue());
        //
        //        }
        //
        return simiList;
    }

    // combine getAverageByID and getAverageRatings;
    // HashMap<MovieID, ArrayList<Rater>> from top numSimilarRaters.
    //or
    // Arraylist<movieID> from top numSimilarRaters.

    /**
     * This method calculate a weighted average movie rating
     * @param raterID - A String variable representing the ID of the raters
     * @param numSimilarRaters - An Integer variable representing the number of similar raters
     * @param minimalRaters - An Integer variable representing the minimal number of raters
     * @return Arraylist<Rating> of the movies and their average ratings
     */
    public ArrayList<Rating> getSimilarRatings(String raterID, int numSimilarRaters, int minimalRaters) {
        ArrayList<Rating> ratingList = new ArrayList<>();
        // Get Arraylist<movieID> from top numSimilarRaters.
        ArrayList<String> movidIDByTopSimilar = new ArrayList<>();
        ArrayList<Rating> simiList1 = getSimilarities(raterID);
        //System.out.println("Total number of similar raters : " + simiList1.size());
        int num = simiList1.size();
        if (numSimilarRaters > num) {
            numSimilarRaters = num;
        }
        for (int i = 0; i < numSimilarRaters; i++) {
            String raterID1 = simiList1.get(i).getItem();
            ArrayList<String> movieRated1 = RaterDatabase.getRater(raterID1).getItemsRated();
            for (String movieID : movieRated1) {
                if (!movidIDByTopSimilar.contains(movieID)) {
                    movidIDByTopSimilar.add(movieID);
                    //System.out.println("Movie id = " + movieID + " Rater id = " + raterID1);
                }
            }
        }
        //System.out.println("MoviebyTopSimilar Size = " + movidIDByTopSimilar.size());
        //for (String i:)

        //rating for movies in the movieIDByTopSimilar list;
        //Filter trueFilter = new TrueFilter();
        for (String j : movidIDByTopSimilar) {
            // Rating for one movie
            double ave = 0;
            ArrayList<Rating> simiList = getSimilarities(raterID);
            // List<Rating> topsimiList = simiList.subList(0, numSimilarRaters);
            int count = 0;
            double total = 0;
            int simiweighttotal = 0;
            // System.out.println("total");
            for (int i = 0; i < numSimilarRaters; i++) {
                //   System.out.println("i=" + i);

                double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                //System.out.println(count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);
                if (rating != -1) {
                    count++;
                    total += rating * simiList.get(i).getValue();
                    simiweighttotal += simiList.get(i).getValue();
                    //System.out.println("Movie id = " + j + " count " + count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);

                }
            }
            if (count >= minimalRaters && simiweighttotal > 0)
                ave = total / simiweighttotal;
            //ave = total / count;
            //System.out.println("Movie id = " + j + " count " + count + " : " + " rating " + ave + " total/count " + total / count);
            //(9*31+10*20)/50=279+200=479/50=9.58
            //(9*31+10*20)/2 = 479/2 = 239.5
            // Rating for one movie end
            if (ave > 0)
                ratingList.add(new Rating(j, ave));
            // Rating for all movie end
        }
        Collections.sort(ratingList);
        Collections.reverse(ratingList);

        return ratingList;
    }

    /**
     * This method gets similar ratings following a certain criteria
     * @param raterID - A String variable representing the ID of the raters
     * @param numSimilarRaters - An Integer variable representing the number of similar raters
     * @param minimalRaters minimalRaters - An Integer variable representing the minimal number of raters
     * @param f - Represents the filters
     * @return Arraylist<Rating> of ratings
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
                // List<Rating> topsimiList = simiList.subList(0, numSimilarRaters);
                int count = 0;
                double total = 0;
                double simiweighttotal = 0;
                for (int i = 0; i < numSimilarRaters; i++) {
                    double rating = RaterDatabase.getRater(simiList.get(i).getItem()).getRating(j);
                    if (rating != -1) {
                        count++;
                        total += rating * simiList.get(i).getValue();
                        simiweighttotal += simiList.get(i).getValue();

                        //System.out.println(count + " : " + "id = " + simiList.get(i).getItem() + " rating " + rating + " ave " + total);

                    }
                }
                if (count >= minimalRaters && simiweighttotal > 0)
                    ave = total / simiweighttotal;
                //ave = total / count;

                // Rating for one movie end
                if (ave > 0)
                    ratingList.add(new Rating(j, ave));
            }
            // Rating for all movie end
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
