package recommendation_system.filters;

import java.util.ArrayList;

/**
 * <p>
 *  This class allows to combine all filters to pass them consecutively on an element that the
 *  user wants to filter.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Add different types of filters </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
 *  @version 1.0
 */
public class AllFilters implements Filter {
    ArrayList<Filter> filters;

    /**
     * Default builder
     */
    public AllFilters() {
        filters = new ArrayList<>();
    }

    /**
     * This method add a filter to our filter collection
     * @param f Filter to be added
     */
    public void addFilter(Filter f) {
        filters.add(f);
    }

    /**
     * This method proves if the movie satisfies the criteria
     * @param id A string variable representing the ID of the movie
     * @return Boolean - True if the movie satisfies the criteria in the method - False otherwise
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
