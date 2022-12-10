package recommendation_system.recommenders;

import recommendation_system.ratings.FourthRatings;
import recommendation_system.ratings.FourthRatingsOptimizedByWeiXu;
import recommendation_system.movies.MovieDatabase;
import recommendation_system.raters.RaterDatabase;
import recommendation_system.ratings.Rating;
import recommendation_system.filters.TrueFilter;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Random;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.io.File;
import java.io.IOException;

/***************************************************************
 *  Name:    Wei Xu
 *
 *  Date: Dec 16th, 2019
 *
 *  Description:  -------------------STEP FIVE--------
 *                 a class RecommendationRunner that implements Recommender.
 *                 The two methods you will need to implement are:
 *                • getItemsToRate()
 *                • printRecommendationsFor()
 *
 *                When the user first visits the recommender site, our code
 *                will call the method getItemsToRate() to get a list of movies
 *                to display on the web page for users to rate.
 *                After the user submits their ratings, our code will call the
 *                method printRecommendationsFor() to get your recommendations
 *                based on the user's ratings and display them.
 *
 *               Style the CSS take a long time to design and debug on CodePen.
 *                https://codepen.io/wei-paradox-xu/pen/XWJjyKV?editors=1100
 *
 *  * IMPORTANT NOTICE:
 *      * Weighted average algorithm optimized by WEI XU
 *      * Instead of  "sum of (similar rating(i) *rating of the movie(i))/count of the raters"
 *      * !!!!!I use  "sum of (similar rating(i) *rating of the movie(i))/ sum of the similar rating(i)", will achieve better results.
 *
 ****************************************************************/

public class RecommendationRunner implements Recommender {

	private Random rand = new SecureRandom();

	private String filename = "index.html";
	private Path pathToFile = Paths.get(filename);

    /**
     * randomly pick 10 movie to let the user to rate.
     * will optimize soon to rate the most popular movie.
     * Because the most movies i met for rating that i'm not familiar with.
     */
    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movieToBeRate = new ArrayList<>();
        ArrayList<String> movieID = MovieDatabase.filterBy(new TrueFilter());
        for (int i = 0; movieToBeRate.size() < 10; i++) {
        	//SecureRandom ran = new SecureRandom();
        	byte bytes[] = new byte[20];
        	this.rand.nextBytes(bytes);
            int random = rand.nextInt(movieID.size());
            if (!movieToBeRate.contains(movieID.get(random)))
                movieToBeRate.add(movieID.get(random));
        }
        return movieToBeRate;
    }

    public void writeHtmlHead() throws IOException {

    	String textHead = "<!DOCTYPE html>\n"
    			+ "<html lang=\"es\">\n"
    			+ "<head>\n"
    			+ "  <meta charset=\"UTF-8\">\n"
    			+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
    			+ "  <title>LPH-Final</title>";


    	Files.writeString(pathToFile, textHead, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }

    public void writeCss() throws IOException {


    	String textCss = "  <style>\n"
    			+ "    h2,h3{\n"
    			+ "      text-align: center;\n"
    			+ "      height: 50px;\n"
    			+ "      line-height: 50px;\n"
    			+ "      font-family: Arial, Helvetica, sans- serif;\n"
    			+ "      background-color: black;\n"
    			+ "       color:  #ff6600 }\n"
    			+ "     table {\n"
    			+ "       border-collapse: collapse;\n"
    			+ "       margin: auto;}\n"
    			+ "    table, th, td {\n"
    			+ "        border: 2px solid white;\n"
    			+ "        font-size: 15px;\n"
    			+ "        padding: 2px 6px 2px 6px; }\n"
    			+ "     td img{\n"
    			+ "        display: block;\n"
    			+ "        margin-left: auto;\n"
    			+ "        margin-right: auto; }\n"
    			+ "    th {\n"
    			+ "        height: 40px;\n"
    			+ "        font-size: 18px;\n"
    			+ "      background-color: black;\n"
    			+ "     color: white;\n"
    			+ "    text-align: center; }\n"
    			+ "     tr:nth-child(even) {\n"
    			+ "         background-color: #f2f2f2; }\n"
    			+ "      tr:nth-child(odd) {\n"
    			+ "    background-color: #cccccc; }\n"
    			+ "     tr:hover {\n"
    			+ "     background-color: #666666; \n"
    			+ "      color:white;}\n"
    			+ "    table td:first-child {\n"
    			+ "     text-align: center; }\n"
    			+ "     tr {\n"
    			+ "     font-family: Arial, Helvetica, sans-serif; }\n"
    			+ "    .rating{\n"
    			+ "        color:#ff6600;\n"
    			+ "        padding: 0px 10px;\n"
    			+ "       font-weight: bold; }\n"
    			+ "    </style>\n"
    			+ "</head>";

    	Files.writeString(pathToFile, textCss, StandardOpenOption.APPEND);
    }

    public void writeBody(ArrayList<Rating> outID) throws IOException {

    	String textBody = "<body>\n"
    			+ "  <h2>Wei Xu Brings Best Movies for You! Enjoy!^^</h2>\n"
    			+ "  <table id = \"rater\">\n"
    			+ "  <tr>\n"
    			+ "  <th>Rank</th>\n"
    			+ "  <th>Poster</th>\n"
    			+ "  <th>Title & Rating</th>\n"
    			+ "  <th>Genre</th>\n"
    			+ "  <th>Country</th>\n"
    			+ "  </tr>\n"
    			+ "  <tr>\n";


    			int rank = 1;
                for (Rating i : outID) {
                	textBody += "      <tr><td>" + rank + "</td>" +
                            "<td><img src = \"" + MovieDatabase.getPoster(i.getItem()).replace("http", "https") + "\" width=\"50\" height=\"70\"></td> " +
                            "<td>" + MovieDatabase.getYear(i.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                            i.getItem() + "\">" + MovieDatabase.getTitle(i.getItem()) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                            + String.format("%.1f", i.getValue()) + "/10</td>" +
                            "<td>" + MovieDatabase.getGenres(i.getItem()) + "</td>" +
                            "<td>" + MovieDatabase.getCountry(i.getItem()) + "</td>" +
                            "</tr>\n";
                    rank++;
                }

    		textBody += "  </table>\n"
    				+ "  <h3>*The rank of movies is based on other raters who have the most similar rating to yours. Enjoy!^^</h3>";
    		Files.writeString(pathToFile, textBody, StandardOpenOption.APPEND);

    }


    @Override
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        //FourthRatingsOptimizedByWeiXu fr = new FourthRatingsOptimizedByWeiXu();
        FourthRatings fr = new FourthRatings();
        ArrayList<Rating> ratingList = fr.getSimilarRatings(webRaterID, 20, 5);
        if (ratingList.size() == 0) {
            System.out.println("<h2>Sorry, there are no movie recommend for you based on your rating!</h2>");
        } else {
            ArrayList<String> movieToBeRate = getItemsToRate();
            ArrayList<Rating> outID = new ArrayList<>();
            int count = 0;
            for (int i = 0; outID.size() + count != ratingList.size() && outID.size() < 10; i++) {
                if (!movieToBeRate.contains(ratingList.get(i).getItem())) {
                    outID.add(ratingList.get(i));
                } else {
                    count++;
                }
            }
            System.out.println("outid size = " + outID.size());


            System.out.println("<style>");
            System.out.println("h2,h3{");
            System.out.println("  text-align: center;");
            System.out.println("  height: 50px;");
            System.out.println("  line-height: 50px;");
            System.out.println("  font-family: Arial, Helvetica, sans- serif;");
            System.out.println("  background-color: black;");
            System.out.println("   color:  #ff6600 }");

            System.out.println(" table {");
            System.out.println("   border-collapse: collapse;");
            System.out.println("   margin: auto;}");
            System.out.println("table, th, td {");
            System.out.println("    border: 2px solid white;");
            System.out.println("    font-size: 15px;");

            System.out.println("    padding: 2px 6px 2px 6px; }");
            System.out.println(" td img{");
            System.out.println("    display: block;");
            System.out.println("    margin-left: auto;");
            System.out.println("    margin-right: auto; }");
            System.out.println("th {");
            System.out.println("    height: 40px;");
            System.out.println("    font-size: 18px;");

            System.out.println("  background-color: black;");
            System.out.println(" color: white;");
            System.out.println("text-align: center; }");

            System.out.println(" tr:nth-child(even) {");
            System.out.println("     background-color: #f2f2f2; }");
            System.out.println("  tr:nth-child(odd) {");
            System.out.println("background-color: #cccccc; }");
            System.out.println(" tr:hover {");
            System.out.println(" background-color: #666666; ");
            System.out.println("  color:white;}");

            System.out.println("table td:first-child {");
            System.out.println(" text-align: center; }");

            System.out.println(" tr {");
            System.out.println(" font-family: Arial, Helvetica, sans-serif; }");
            System.out.println(".rating{");
            System.out.println("    color:#ff6600;");
            System.out.println("    padding: 0px 10px;");
            System.out.println("   font-weight: bold; }");
            System.out.println("</style>");


            System.out.println("<h2>Wei Xu Brings Best Movies for You! Enjoy!^^</h2>");
            System.out.println("<table id = \"rater\">");
            System.out.println("<tr>");
            System.out.println("<th>Rank</th>");

            System.out.println("<th>Poster</th>");
            System.out.println("<th>Title & Rating</th>");
            System.out.println("<th>Genre</th>");
            System.out.println("<th>Country</th>");
            System.out.println("</tr>");

            int rank = 1;
            for (Rating i : outID) {
                System.out.println("<tr><td>" + rank + "</td>" +

                        "<td><img src = \"" + MovieDatabase.getPoster(i.getItem()).replace("http", "https") + "\" width=\"50\" height=\"70\"></td> " +
                        "<td>" + MovieDatabase.getYear(i.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                        i.getItem() + "\">" + MovieDatabase.getTitle(i.getItem()) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                        + String.format("%.1f", i.getValue()) + "/10</td>" +
                        "<td>" + MovieDatabase.getGenres(i.getItem()) + "</td>" +
                        "<td>" + MovieDatabase.getCountry(i.getItem()) + "</td>" +
                        "</tr> ");
                rank++;
            }
            try {
            	writeHtmlHead();
            	writeCss();
            	writeBody(outID);
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
        }
        System.out.println("</table>");
        System.out.println("<h3>*The rank of movies is based on other raters who have the most similar rating to yours. Enjoy!^^</h3>");

    }
        public static void main(String[] args) {
            RecommendationRunner a = new RecommendationRunner();
            a.getItemsToRate();
            a.printRecommendationsFor("30");
        }
}
