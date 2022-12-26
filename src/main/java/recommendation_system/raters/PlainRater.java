package recommendation_system.raters;

import recommendation_system.ratings.Rating;
import java.util.ArrayList;

/**
 * <p>
 *  Keeps track of one rater and all their ratings
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> A constructor with one parameter of the ID for the rater. </li>
 *  <li> A method addRating that has two parameters, a String named item and a double
 * named rating </li>
 *  <li> A method getID with no parameters to get the ID of the rater. </li>
 *  <li> A method getRating that has one parameter item. </li>
 *  <li> A method numRatings that returns the number of ratings this rater has. </li>
 *  <li> A method getItemsRated that has no parameters. </li>
 *  </ul>
 *  </p>
 *
 *  @since 26/12/22
 *  @version 1.0
 */
public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;

    /**
     * Builder overload
     * @param id ID for the rater
     */
    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<>();
    }

    /**
     * Adds a rating to the ArrayList of ratings associated with a user
     * @param item A String variable representing the ID of the movie
     * @param rating A double variable representing the rating of the movie
     */
    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item, rating));
    }

    /**
     * Verify if a user has rated a particular movie
     * @param item A String variable representing the ID of the movie
     * @return Boolean - True if the user has rated the movie - False otherwise
     */
    public boolean hasRating(String item) {
        for (Rating myRating : myRatings) {
            if (myRating.getItem().equals(item)) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return String - ID of the rater
     */
    public String getID() {
        return myID;
    }

    /**
     * @param item A String variable representing the ID of the movie
     * @return double - Value associated with a specific movie rating - If there is no rating -1
     */
    public double getRating(String item) {
        for (Rating myRating : myRatings) {
            if (myRating.getItem().equals(item)) {
                return myRating.getValue();
            }
        }
        
        return -1;
    }

    /**
     * @return int - Size of the ratings ArrayList
     */
    public int numRatings() {
        return myRatings.size();
    }

    /**
     * @return ArrayList<String> - ID of all movies rated by a user
     */
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>();

        for (Rating myRating : myRatings) {
            list.add(myRating.getItem());
        }
        
        return list;
    }
}
