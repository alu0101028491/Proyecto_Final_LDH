package recommendation_system.raters;

import recommendation_system.ratings.Rating;
import java.util.ArrayList;
import java.util.HashMap;

/***************************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP Three----------------------------
 *                Create a new class named EfficientRater, and copy the
 *                PlainRater class into this class. You will make several
 *                changes to this class, including:
 *
 *                Change the ArrayList of type Rating private variable to a
 *                HashMap<String,Rating>. The key in the HashMap is a movie ID,
 *                and its value is a rating associated with this movie.
 *                You will need to change addRating to instead add a new Rating
 *                to the HashMap with the value associated with the movie ID
 *                String item as the key in the HashMap. The method hasRating
 *                should now be much shorter; it no longer needs a loop.
 *                More method need change.......
 *
 ***************************************************************************/

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
    /* Change the ArrayList of type Rating private variable to a HashMap<String,Rating>.
       The key in the HashMap is a movie ID, and
        its value is a rating associated with this movie.*/
    //private HashMap<String, ArrayList<Rating>> myRatings;//why not this one?
    private HashMap<String, Rating> myRatings;

    /**
     *
     * @param id - A String variable representing the ID
     */
    public EfficientRater(String id) {
        myID = id;
        myRatings = new HashMap<>();
    }

    /**
     *
     * @param item - A String variable
     * @param rating - A variable representing the rating
     */
    public void addRating(String item, double rating) {
        myRatings.put(item, new Rating(item, rating));//item is string id?
    }

    /**
     *
     * @param item
     * @return
     */
    public boolean hasRating(String item) {
        return myRatings.containsKey(item);
    }

    /**
     *
     * @return
     */
    public String getID() {
        return myID;
    }

    /**
     *
     * @param item
     * @return
     */
    public double getRating(String item) {
        if (myRatings.containsKey(item)) {
            return myRatings.get(item).getValue();
        }
        return -1;
    }

    /**
     *
     * @return
     */
    public int numRatings() {
        return myRatings.size();
    }

    /**
     *
     * @return
     */
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<>(myRatings.keySet());

        return list;
    }
}
