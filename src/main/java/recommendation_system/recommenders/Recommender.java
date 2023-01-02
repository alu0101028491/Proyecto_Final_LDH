package recommendation_system.recommenders;

import java.util.ArrayList;

/**
 * <p>
 *  This interface allows the code to be integrated with the web site
 *  </p>
 *
 *  <p>
 *  The Interface contains:
 *  <ul>
 *  <li> The method getItemsToRate </li>
 *  <li> The method printRecommendationsFor with one String parameter representing a new rater </li>
 *  </ul>
 *  </p>
 *
 *  @since 1/1/23
 *  @version 1.0
 */

public interface Recommender {

    /**
     * This method returns a list of movie IDs that will be used to look up the movies in the MovieDatabase
     * and present them to users to rate
     * @return Arraylist<String> of the movies to rate
     */
    public ArrayList<String> getItemsToRate();

    /**
     * This method prints out an HTML table of the movies recommended for the given rater
     * @param webRaterID - A String parameter representing a new rater
     */
    public void printRecommendationsFor(String webRaterID);
}
