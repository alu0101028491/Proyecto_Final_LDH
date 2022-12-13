package recommendation_system.filters;

/**
 * <p>
 *  This class can be used to select every movie from MovieDatabase
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Select every movie from the movie database </li>
 *  </ul>
 *  </p>
 *
 *  @since 13/12/22
 *  @version 1.0
 */
public class TrueFilter implements Filter {

    /**
     * @param id A string variable representing the ID of the movie
     * @return Boolean - It’s satisfies method always returns true.
     */
    @Override
    public boolean satisfies(String id) {
        return true;
    }
    
}
