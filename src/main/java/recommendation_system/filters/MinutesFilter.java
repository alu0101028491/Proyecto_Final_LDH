package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/**
 * <p>
 *  This class is an efficient way to filter movies.
 *  It filters the movies based on the specific duration that the user wants.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on their duration </li>
 *  </ul>
 *  </p>
 *
 *  @since 12/12/22
 *  @version 1.0
 */
public class MinutesFilter implements Filter {

    private int min;
    private int max;

    /**
     * Builder overload
     * @param min an integer variable representing the Minimum of minutes that the movie should have
     * @param max an integer variable representing the Maximum of minutes that the movie should have
     */
    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Builder overload
     * @param id a string variable representing the ID of the movie
     * Returns a boolean to prove if the criteria (the movie has more of the minimum and less than the maximum of the minutes that the user wants to filter) is fulfilled
     * @return a boolean to prove if the criteria (the movie has more of the minimum and less than the maximum of the minutes that the user wants to filter) is fulfilled
     */
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
}
