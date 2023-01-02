package recommendation_system.ratings;

import recommendation_system.movies.Movie;
import recommendation_system.edu.duke.FileResource;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import recommendation_system.raters.EfficientRater;
import recommendation_system.raters.Rater;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.System.Logger;

/**
 * <p>
 *  Class to answer simple questions about movies and ratings. - Phase 1
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Process every record from the CSV file and return an ArrayList of type Movie. </li>
 *  <li> Determine the maximum number of movies by any director. </li>
 *  <li> Load Raters. </li>
 *  <li> Get the number of ratings for a particular rater. </li>
 *  <li> Find the maximum number of ratings by any rater. </li>
 *  <li> Find the number of ratings a particular movie has. </li>
 *  <li> Determine how many movies have been rated. </li>
 *  </ul>
 *  </p>
 *
 *  @since 26/12/22
 *  @version 1.0
 */
public class FirstRatings {

    private Logger logger = System.getLogger(FirstRatings.class.getName());
    private static Logger staticLogger = System.getLogger(FirstRatings.class.getName());

    /**
     * This method process every record from the CSV file
     * and return an ArrayList of type Movie with all of the movie data from the file
     * @param fileName A file of movie information
     * @return ArrayList<Movie> with all of the movie data from the file
     */
    public ArrayList<Movie> loadMovies(String fileName) {
        ArrayList<Movie> movies = new ArrayList<>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {

            Movie m = new Movie(i.get("id"), i.get("title"), i.get("year"),
                    i.get("genre"), i.get("director"), i.get("country"),
                    i.get("poster"), Integer.parseInt(i.get("minutes")));

            movies.add(m);
        }
        return movies;
    }

    /**
     * This method determine the maximum number of movies by any director,
     * and who the directors are that directed that many movies.
     * Remember that some movies may have more than one director.
     */
    public void testLoadMovies() {

        ArrayList<Movie> movies = loadMovies("data/ratedmovies_short.csv");

        logger.log(Logger.Level.INFO, "The size of movie list is = " + movies.size());

        HashMap<String, ArrayList<Movie>> mapDirector = new HashMap<>();
        for (Movie i : movies) {

            String director = i.getDirector();
            String[] directors = director.split(", ");

            for (String j : directors) {
                if (!mapDirector.containsKey(j)) {
                    ArrayList<Movie> a = new ArrayList<>();
                    a.add(i);
                    mapDirector.put(j, a);
                } else {
                    ArrayList<Movie> a = mapDirector.get(j);
                    a.add(i);
                    mapDirector.put(j, a);
                }
            }
        }

        int maxNumMovieByDirector = 0;
        for (String key : mapDirector.keySet()) {
            if (mapDirector.get(key).size() > maxNumMovieByDirector) {
                maxNumMovieByDirector = mapDirector.get(key).size();
            }
        }

        for (String key : mapDirector.keySet()) {
            if (mapDirector.get(key).size() == maxNumMovieByDirector) {
                logger.log(Logger.Level.INFO, "The director who produce the most movie is : "
                        + maxNumMovieByDirector + " : " + key);
            }
        }
    }

    /**
     * Load Raters - Raters is class for storing the data about one rating of an item.
     * @param fileName Ratings file
     * @return ArrayList<Rater> of Raters
     */
    public ArrayList<Rater> loadRaters(String fileName) {
        ArrayList<Rater> raters = new ArrayList<>();
        FileResource fr = new FileResource(fileName);
        CSVParser parser = fr.getCSVParser();
        for (CSVRecord i : parser) {

            double rating = Double.parseDouble(i.get("rating"));

            int count = 0;
            for (Rater j : raters) {
                if (j.getID().contains(i.get("rater_id"))) {
                    j.addRating(i.get("movie_id"), rating);
                    count++;
                    break;
                }
            }
            if (count == 0) {
                EfficientRater m = new EfficientRater(i.get("rater_id"));
                m.addRating(i.get("movie_id"), rating);
                raters.add(m);
            }
        }
        return raters;
    }

    /**
     * This method find the number of ratings by rater, the number of ratings by movie
     * and determine how many movies have been rated
     * @param filename - A String variable representing the filename
     */
    public void testLoadRaters(String filename) {
        ArrayList<Rater> raters = loadRaters(filename);
        logger.log(Logger.Level.INFO, "The size of rater list is = " + raters.size());

        // Number of ratings for a particular rater
        for (Rater i : raters) {
            if (i.getID().equals("2")) {

                logger.log(Logger.Level.INFO, "USER # " + i.getID() + " : "
                        + i.numRatings() + " ratings");

                ArrayList<String> rating = i.getItemsRated();
                for (String j : rating) {
                    logger.log(Logger.Level.INFO, "movie_id: " + j + " ");
                    logger.log(Logger.Level.INFO, i.getRating(j) + " rating");
                }
            }
        }
        // Find the maximum number of ratings by any rater.
        int max = 0;
        for (Rater i : raters) {
            if (i.numRatings() > max) {
                max = i.numRatings();
            }
        }
        for (Rater i : raters) {
            if (i.numRatings() == max) {
                logger.log(Logger.Level.INFO, "The maximum rate is from USER # " +
                        i.getID() + " : " + i.numRatings() + " ratings");
            }
        }
        // Find the number of ratings a particular movie has.
        int count = 0;
        for (Rater i : raters) {
            ArrayList<String> rating = i.getItemsRated();
            if (rating.contains("1798709")) {
                count++;
                logger.log(Logger.Level.INFO, count + " : " + "id = " + i.getID() + rating);
            }
        }
        logger.log(Logger.Level.INFO, "The total # of " + "1798709" + " that been rated is " + count);

        // Determine how many movies have been rated
        ArrayList<String> differentMovie = new ArrayList<>();
        for (Rater i : raters) {
            ArrayList<String> rating = i.getItemsRated();
            for (String j : rating) {
                if (!differentMovie.contains(j)) {
                    differentMovie.add(j);
                }
            }
        }
        logger.log(Logger.Level.INFO, "The total # of movie is " + differentMovie.size());
    }

    public static void main(String[] args) {
        FirstRatings firstRatings = new FirstRatings();
        staticLogger.log(Logger.Level.INFO, "---MOVIES---");
        firstRatings.testLoadMovies();
        staticLogger.log(Logger.Level.INFO, "---RATERS---");
        firstRatings.testLoadRaters("data/ratings_short.csv");
    }
}
