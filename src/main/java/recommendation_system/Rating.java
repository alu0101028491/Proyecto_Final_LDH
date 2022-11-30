package recommendation_system;

/**
 * <p>
 *  Plain Old Java Object (POJO) class for storing the data about one rating of an item
 *  An immutable passive data object (PDO) to represent the rating data
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> A constructor with two parameters to initialize the private variables. </li>
 *  <li> Two getter methods getItem and getValue. </li>
 *  <li> A toString method to represent rating information as a String. </li>
 *  <li> A compareTo method to compare this rating with another rating. </li>
 *  </ul>
 *  </p>
 *
 *  @since 30/11/22
 *  @version 1.0
 */
public class Rating implements Comparable<Rating> {
    private String item;
    private double value;
    
    public Rating(String anItem, double aValue) {
        item = anItem;//IMDB id
        value = aValue;
    }
    
    // Returns item being rated
    public String getItem() {
        return item;
    }
    
    // Returns the value of this rating (as a number so it can be used in calculations)
    public double getValue() {
        return value;
    }
    
    // Returns a string of all the rating information
    public String toString() {
        return "[" + getItem() + ", " + getValue() + "]";
    }
    
    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;
        
        return 0;
    }
}
