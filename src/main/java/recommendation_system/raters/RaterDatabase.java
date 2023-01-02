package recommendation_system.raters;

/**
 * <p>
 *  Class to store all information about raters
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Initialize hashmap ourRaters </li>
 *  <li> Add ratings </li>
 *  <li> Add rater ratings </li>
 *  <li> Get a rater </li>
 *  <li> Get the list of the raters </li>
 *  <li> Get the number of the raters </li>
 *  </ul>
 *  </p>
 *
 *  @since 30/12/22
 *  @version 1.0
 */

import recommendation_system.edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.util.ArrayList;
import java.util.HashMap;

public class RaterDatabase {
    private static HashMap<String, Rater> ourRaters;//key:rater id, value: rater object

    private RaterDatabase() {
        throw new IllegalStateException("RaterDatabase class");
    }
    
    /**
     * A private initialize method with no parameters that initializes
     * the HashMap ourRaters if it does not exist.
     **/
    private static void initialize() {
        // This method is only called from addRatings
        if (ourRaters == null) {
            ourRaters = new HashMap<>();
        }
    }

    /**
     * This method initialize the hashmap ourRaters
     * @param filename - A String variable representing the filename
     */
    public static void initialize(String filename) {
        if (ourRaters == null) {
            ourRaters = new HashMap<>();
            addRatings("data/" + filename);
        }
    }

    /**
     * This method add the ratings
     * @param filename - A String variable representing de filename
     */
    public static void addRatings(String filename) {
        initialize();
        FileResource fr = new FileResource(filename);
        CSVParser csvp = fr.getCSVParser();
        for (CSVRecord rec : csvp) {
            String id = rec.get("rater_id");
            String item = rec.get("movie_id");
            String rating = rec.get("rating");
            addRaterRating(id, item, Double.parseDouble(rating));
        }
    }

    /**
     * This method add the raters
     * @param raterID - A String variable representing the ID of the rater
     * @param movieID - A String variable representing the ID of the movie
     * @param rating - A double variable representing the rating
     */
    public static void addRaterRating(String raterID, String movieID, double rating) {
        initialize();
        Rater rater = null;
        if (ourRaters.containsKey(raterID)) {
            rater = ourRaters.get(raterID);
        } else {
            rater = new EfficientRater(raterID);
            ourRaters.put(raterID, rater);
        }
        rater.addRating(movieID, rating);//This is the method in EfficientRater class
    }

    /**
     * This method gets raters ID
     * @param id - A String variable representing the ID of the rater
     * @return ID of the rater
     */
    public static Rater getRater(String id) {
        initialize();
        
        return ourRaters.get(id);
    }

    /**
     * This method get the list of raters
     * @return Arraylist<Rater> of raters
     */
    public static ArrayList<Rater> getRaters() {
        initialize();
        ArrayList<Rater> list = new ArrayList<>(ourRaters.values());
        
        return list;
    }

    /**
     * This method gets the number of raters
     * @return Integer - Number of raters
     */
    public static int size() {
        return ourRaters.size();
    }
    
    
}
