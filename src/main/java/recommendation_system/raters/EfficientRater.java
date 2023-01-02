package recommendation_system.raters;

import recommendation_system.ratings.Rating;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  This class uses a HashMap to store all the information about the ratings associated with a rater
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Add ratings </li>
 *  <li> Get ID of the movies </li>
 *  <li> Get ratings of the movies </li>
 *  <li> Prove if the movie has a rating </li>
 *  <li> Get the number of the rated movies </li>
 *  <li> Get rated movies </li>
 *  </ul>
 *  </p>
 *
 *  @since 30/12/22
 *  @version 1.0
 */

public class EfficientRater implements Rater {
    private String myID;
    private HashMap<String, Rating> myRatings;

    /**
     *
     * @param id - A String variable representing the IMDB ID of the movie
     */
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    /**
     * This method adds ratings to the list
     * @param item - A String variable representing IMDB ID of the movie
     * @param rating - A variable representing the rating
     */
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));
    }

    /**
     * This method proves if the list contains the ID of the movie
     * @param item - A String variable representing IMDB ID of the movie
     * @return Boolean - True if the list contains the ID of the movie - False otherwise
     */
    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    /**
     * This method gets the ID of the movie
     * @return Integer - ID of the movie
     */
    public String getID() {
        return myID;
    }

    /**
     * This method gets the rating of the movie
     * @param item - A String variable representing ID
     * @return Double - Rating of the movie
     */
    public double getRating(String item) {
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    /**
     * This method gets the number of ratings on the list
     * @return Integer - Number of ratings on the list
     */
    public int numRatings() {
        return myRatings.size();
    }

    /**
     * This method gets the list of the rated movies
     * @return Arraylist<String> of ratings
     */
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>(myRatings.keySet());

        return list;
    }
}
