package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 12th, 2019
 *
 *  Description:  -------------------STEP THREE-------------------------
 *                 Create a new class named MinutesFilter that implements Filter.
 *                 Its satisfies method should return true if a movie’s running time
 *                 is at least min minutes and no more than max minutes.
 *
 ****************************************************************/

/**
 * Implement minutes filter.
 * This filter will be used to filter the movies of a specific duration that the user wants to watch.
 */
public class MinutesFilter implements Filter {

    private int min;
    private int max;

    /**
     * Minimum and maximum of minutes that the movie should have
     */
    public MinutesFilter(int min, int max) {
        this.min = min;
        this.max = max;
    }

    /**
     * Returns True if the movie has more of the minimum and less than the maximum of the minutes that the user wants to filter.
     * Returns false if the movie does not meet the criteria of minutes that the user wants.
     */
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getMinutes(id) >= min && MovieDatabase.getMinutes(id) <= max;
    }
}
