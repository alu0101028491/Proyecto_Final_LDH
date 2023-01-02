package recommendation_system.movies;

import recommendation_system.filters.Filter;
import recommendation_system.ratings.FirstRatings;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * <p>
 *  This class is an efficient way to get information about movies.
 *  It stores movie information in a HashMap for fast lookup of movie information given a movie ID.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on queries </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
 *  @version 1.0
 */
public class MovieDatabase {
    private static HashMap<String, Movie> ourMovies;

    private MovieDatabase() {
        throw new IllegalStateException("MovieDatabase class");
    }

    /**
     * Static builder overload
     * @param moviefile File of movies to load
     */
    public static void initialize(String moviefile) {
        if (ourMovies == null) {
            ourMovies = new HashMap<>();
            loadMovies("data/" + moviefile);
        }
    }

    /**
     * Static default builder - It uses a default moviefile
     */
    private static void initialize() {
        if (ourMovies == null) {
            ourMovies = new HashMap<>();
            loadMovies("data/ratedmoviesfull.csv");
        }
    }

    /**
     * @param filename - A String variable representing the filename
     */
    private static void loadMovies(String filename) {
        FirstRatings fr = new FirstRatings();
        ArrayList<Movie> list = fr.loadMovies(filename);
        for (Movie m : list) {
            ourMovies.put(m.getID(), m);
        }
    }

    /**
     * This method prove if the list contains the ID of the movie
     * @param id - A String variable representing the ID of the movie
     * @return Boolean - True if the list contains the ID - False otherwise
     */
    public static boolean containsID(String id) {
        initialize();
        return ourMovies.containsKey(id);
    }

    /**
     * This method gets the year of the movie
     * @param id - A String variable representing the ID of the movie
     * @return Int - Year of the movie
     */
    public static int getYear(String id) {
        initialize();
        return ourMovies.get(id).getYear();
    }

    /**
     * This method gets the genres of the movie
     * @param id - A String variable representing the ID of the movie
     * @return String - Genres of the movie
     */
    public static String getGenres(String id) {
        initialize();
        return ourMovies.get(id).getGenres();
    }

    /**
     * This method gets the title of the movie
     * @param id - A String variable representing the ID of the movie
     * @return String - Title of the movie
     */
    public static String getTitle(String id) {
        initialize();
        return ourMovies.get(id).getTitle();
    }

    /**
     * @param id - A String variable representing the ID of the movie
     * @return ID of the movie
     */
    public static Movie getMovie(String id) {
        initialize();
        return ourMovies.get(id);
    }

    /**
     * This method gets the poster of the movie
     * @param id - A String variable representing the ID of the movie
     * @return String - Poster of the movie
     */
    public static String getPoster(String id) {
        initialize();
        return ourMovies.get(id).getPoster();
    }

    /**
     * This method gets the minutes of the movie
     * @param id - A String variable representing the ID of the movie
     * @return Int - Minutes of the movie
     */
    public static int getMinutes(String id) {
        initialize();
        return ourMovies.get(id).getMinutes();
    }

    /**
     * This method gets the country of the movie
     * @param id - A String variable representing the ID of the movie
     * @return String - Country of the movie
     */
    public static String getCountry(String id) {
        initialize();
        return ourMovies.get(id).getCountry();
    }

    /**
     * This method gets the director of the movie
     * @param id - A String variable representing the ID of the movie
     * @return String - Directors of the movie
     */
    public static String getDirector(String id) {
        initialize();
        return ourMovies.get(id).getDirector();
    }

    /**
     * This method gets the number of movies stored on the list
     * @return Int - Number of movies stored on the list
     */
    public static int size() {
        return ourMovies.size();
    }

    /**
     * This method adds to the list the movies that follow certain criteria
     * @param f Represents the filter
     * @return Arraylist<String> of movie IDs
     */
    public static ArrayList<String> filterBy(Filter f) {
        initialize();
        ArrayList<String> list = new ArrayList<>();
        for (String id : ourMovies.keySet()) {
            if (f.satisfies(id)) {
                list.add(id);
            }
        }
        return list;
    }
    
}
