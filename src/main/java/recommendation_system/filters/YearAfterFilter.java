package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/**
 * <p>
 *  This class filters the movies released after or on the specific year that the user wants.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on their released date </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
 *  @version 1.0
 */
public class YearAfterFilter implements Filter {

    private int myYear;

    /**
     * Builder overload
     * @param year An integer variable representing the release year
     */
    public YearAfterFilter(int year) {
        myYear = year;
    }

    /**
     * @param id A string variable representing the ID of the movie
     * @return Boolean - True if movie was released after or on the year that the user wants to filter - False otherwise
     */
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }
}

