package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/**
 * <p>
 *  This class is an efficient way to filter movies.
 *  It filters the movies released after or on the specific year that the user wants.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on their released date </li>
 *  </ul>
 *  </p>
 *
 *  @since 12/12/22
 *  @version 1.0
 */

public class YearAfterFilter implements Filter {

    private int myYear;

    /**
     * Builder overload
     * @param year an integer variable representing the release year
     */
    public YearAfterFilter(int year) {
        myYear = year;
    }

    /**
     * Builder overload
     * @param id an string variable representing the ID of the movie
     * Returns a boolean to prove if the criteria (The movie was released after or on the specific year that the user wants to filter) is fulfilled
     * @return a boolean to prove if the criteria (The movie was released after or on the specific year that the user wants to filter) is fulfilled
     */
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }
}

