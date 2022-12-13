package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/**
 * <p>
 *  This class is an efficient way to filter movies.
 *  It filters the movies directed by the specific directors that the user wants to watch.
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Filtering movies based on the directors who directed them </li>
 *  </ul>
 *  </p>
 *
 *  @since 12/12/22
 *  @version 1.0
 */

public class DirectorsFilter implements Filter {

    private String directors;

    /**
     * Builder overload
     * @param directors A string variable representing the directors of the movie
     */
    public DirectorsFilter(String directors) {
        this.directors = directors;
    }

    /**
     * Builder overload
     * @param id A string variable representing the ID
     * Returns a boolean to prove if the criteria (The movie has at least one of the directors that the user wants to filter) is fulfilled
     * @return a boolean to prove if the criteria (The movie has at least one of the directors that the user wants to filter) is fulfilled
     */
    @Override
    public boolean satisfies(String id) {
        String[] dir = directors.split(",");
        //boolean exist = false;
        for (String i : dir) {
            //System.out.println(id + " : " + MovieDatabase.getDirector(id) + " : " + MovieDatabase.getTitle(id));
            if (MovieDatabase.getDirector(id).contains(i)) {
                return true;
                //break;
            }
        }
        return false;
    }

}
