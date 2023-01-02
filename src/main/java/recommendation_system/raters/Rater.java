package recommendation_system.raters;

import java.util.ArrayList;

/**
 * <p>
 *  This interface allows to implement different versions of raters.
 *  </p>
 *
 *  <p>
 *  The Interface contains:
 *  <ul>
 *  <li> The method addRating with one String parameter representing an ID and a Double parameter representing the rating </li>
 *  <li> The method hasRating with one String parameter representing an ID </li>
 *  <li> The method getID </li>
 *  <li> The method getRating with one String parameter representing an ID </li>
 *  <li> The method numRating </li>
 *  <li> The method getItemsRated </li>
 *  </ul>
 *  </p>
 *
 *  @since 1/1/23
 *  @version 1.0
 */
public interface Rater {

    /**
     * This method adds ratings to the list
     * @param item - A String variable representing the IMDB ID of the movies
     * @param rating - A Double variable representing the rating
     */
    public void addRating(String item, double rating);

    /**
     * This method proves if the list contains the ID of the movie
     * @param item - A String variable representing the IMDB ID of the movies
     * @return Boolean - True if the list contains the ID of the movie- False otherwise
     */
    public boolean hasRating(String item);

    /**
     * This method gets the ID of the movie
     * @return String - ID of the movies
     */
    public String getID();

    /**
     * This method gets the rating of the movie
     * @param item - A String variable representing ID
     * @return Double - Rating
     */
    public double getRating(String item);

    /**
     * This method gets the number of ratings on the list
     * @return Integer - Number of ratings
     */
    public int numRatings();

    /**
     * This method gets the list of the rated movies
     * @return Arraylist<String> of the rated movies
     */
    public ArrayList<String> getItemsRated();
}
