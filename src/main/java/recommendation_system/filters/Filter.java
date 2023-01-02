package recommendation_system.filters;

/**
 * <p>
 *  This interface has only one signature for the method satisfies. Any filters that implements
 *  this interface must also have this method.
 *  </p>
 *
 *  <p>
 *  The Interface contains:
 *  <ul>
 *  <li> The method satisfies with one String parameter representing a movie ID </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
 *  @version 1.0
 */
public interface Filter {

    /**
     * This method prove if the movie satisfies certain criteria
     * @param id A string variable representing the ID of the movie
     * @return Boolean - True if the movie satisfies the criteria in the method - False otherwise
     */
    public boolean satisfies(String id);
}
