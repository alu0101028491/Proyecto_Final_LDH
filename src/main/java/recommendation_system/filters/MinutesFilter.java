package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/**
 * <p>
 *  This class filters the movies based on the specific duration that the user wants.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on their duration </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
 *  @version 1.0
 */
public class MinutesFilter implements Filter {

    private int min;
    private int max;

    /**
     * Builder overload
     * @param min An integer variable representing the Minimum of minutes that the movie should have
     * @param max An integer variable representing the Maximum of minutes that the movie should have
     */
    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * This method proves if the movie is within the minutes that the user wants
     * @param id A string variable representing the ID of the movie
     * @return Boolean - True if movie is within the minutes that the user wants to filter - False otherwise
     */
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
}
