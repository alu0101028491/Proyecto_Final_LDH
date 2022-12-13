package recommendation_system.filters;

/**
 * <p>
 *  This class can be used to select every movie from MovieDatabase
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> select every movie from the movie database </li>
 *  </ul>
 *  </p>
 *
 *  @since 12/12/22
 *  @version 1.0
 */

public class TrueFilter implements Filter {

    /**
     * Builder overload
     * @param id a string variable representing the ID of the movie
     * Returns True since it filters every movie from the database
     * @return True since it filters every movie from the database
     */
    @Override
    public boolean satisfies(String id) {
        return true;
    }
    
}
