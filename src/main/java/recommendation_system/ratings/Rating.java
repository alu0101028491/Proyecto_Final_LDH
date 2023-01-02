package recommendation_system.ratings;

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
 *  @since 26/12/22
 *  @version 1.0
 */
public class Rating implements Comparable<Rating> {
    private String item;
    private double value;

    /**
     * Builder overload
     * @param anItem A String variable representing the IMDB ID of the movie
     * @param aValue A double variable representing the rating of the movie
     */
    public Rating(String anItem, double aValue) {
        item = anItem;//IMDB id
        value = aValue;
    }

    /**
     * This method gets the IMDB ID of the movie
     * @return String - IMDB ID of the movie
     */
    public String getItem() {
        return item;
    }

    /**
     * This method gets the value (rating) of the movie
     * @return double - Rating of the movie (it can be used in calculations)
     */
    public double getValue() {
        return value;
    }

    /**
     * This method gets all the rating information
     * @return String - All the rating information
     */
    public String toString() {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    /**
     * This method compare two ratings by their values
     * @param other the object to be compared.
     * @return int - -1 lower, 1 higher, 0 equal
     */
    @Override
    public int compareTo(Rating other) {
        if (this.value < other.value) return -1;
        if (this.value > other.value) return 1;
        
        return 0;
    }
}
