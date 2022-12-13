package recommendation_system.filters;

import java.util.ArrayList;

/**
 * <p>
 *  Allfilters class asks about average ratings by genre and films on or after a particular year.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Add different types of filters </li>
 *  </ul>
 *  </p>
 *
 *  @since 12/12/22
 *  @version 1.0
 */

public class AllFilters implements Filter {
    ArrayList<Filter> filters;

    public AllFilters() {
        filters = new ArrayList<>();
    }

    /**
     * builder overload
     * @param f represents the filters
     */
    public void addFilter(Filter f) {
        filters.add(f);
    }

    /**
     * Builder overload
     * @param id a string variable representing the ID of the movie
     * Returns a boolean to prove if the movie meets a specific criteria
     * @return a boolean to prove if the movie meets a specific criteria
     */
    @Override
    public boolean satisfies(String id) {
        for (Filter f : filters) {
            if (!f.satisfies(id)) {
                return false;
            }
        }
        return true;
    }

}
