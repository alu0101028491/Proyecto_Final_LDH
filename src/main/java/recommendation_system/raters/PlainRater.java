package recommendation_system.raters;

import recommendation_system.Rating;
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
 *  @since 30/11/22
 *  @version 1.0
 */
public class PlainRater implements Rater {
    private String myID;
    private ArrayList<Rating> myRatings;
    
    public PlainRater(String id) {
        myID = id;
        myRatings = new ArrayList<Rating>();
    }
    
    public void addRating(String item, double rating) {
        myRatings.add(new Rating(item, rating));//item is string id?
    }
    
    public boolean hasRating(String item) {
        for (int k = 0; k < myRatings.size(); k++) {
            if (myRatings.get(k).getItem().equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    public String getID() {
        return myID;
    }
    
    public double getRating(String item) {
        for (int k = 0; k < myRatings.size(); k++) {
            if (myRatings.get(k).getItem().equals(item)) {
                return myRatings.get(k).getValue();
            }
        }
        
        return -1;
    }
    
    public int numRatings() {
        return myRatings.size();
    }
    
    public ArrayList<String> getItemsRated() {
        ArrayList<String> list = new ArrayList<String>();
        for (int k = 0; k < myRatings.size(); k++) {
            list.add(myRatings.get(k).getItem());
        }
        
        return list;//arrayList of item;
    }
}
