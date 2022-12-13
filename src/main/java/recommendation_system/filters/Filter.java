package recommendation_system.filters;

/**
 * <p>
 *  The interface Filter has only one signature for the method satisfies. Any filters that implements
 *  this interface must also have this method.
 *  </p>
 *
 *  <p>
 *  The Interface contains:
 *  <ul>
 *  <li> The method satisfies with one String parameter representing a movie ID </li>
 *  <li> This method returns true if the movie satisfies the criteria in the method and returns false otherwise. </li>
 *  </ul>
 *  </p>
 *
 *  @since 12/12/22
 *  @version 1.0
 */

public interface Filter {

    /**
     * Builder overload
     * @param id a string variable representing the ID of the movie
     * Returns a boolean to prove if the movie meets a specific criteria
     * @return a boolean to prove if the movie meets a specific criteria
     */
    public boolean satisfies(String id);
}
