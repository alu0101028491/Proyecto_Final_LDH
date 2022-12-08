package recommendation_system.filters;

import recommendation_system.movies.MovieDatabase;

public class YearAfterFilter implements Filter {
    /**
     * Implement Year after filter.
     * This filter will be used to filter the movies released after or on the specific year that the user wants.
     */
    private int myYear;
    
    public YearAfterFilter(int year) {
        myYear = year;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }
    /**
     * Returns True if the movie was released after or on the specific year that the user wants to filter.
     * Returns false if the movie was released before the specific year that the user want.
     */
}

