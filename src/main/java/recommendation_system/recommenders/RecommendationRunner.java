package recommendation_system.recommenders;

import recommendation_system.ratings.FourthRatingsOptimized;
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
import java.io.IOException;

import java.lang.System.Logger;

/**
 * <p>
 *  Class to run the methods on the recommender interface and write the HTML
 *  </p>
 *
 *  <p>
 *  The class contains the necessary methods for:
 *  <ul>
 *  <li> Get the file name </li>
 *  <li> Set the file name </li>
 *  <li> Get the path to file </li>
 *  <li> Set the path to file </li>
 *  <li> Get the movies to rate </li>
 *  <li> Write HTMLHead </li>
 *  <li> Write CSS </li>
 *  <li> Write HTMLBody </li>
 *  <li> Print recommendations </li>
 *  </ul>
 *  </p>
 *
 *  @since 1/1/23
 *  @version 1.0
 */

public class RecommendationRunner implements Recommender {

	
	private Logger logger = System.getLogger(RecommendationRunner.class.getName());

	private Random rand = new SecureRandom();

	private String filename = "index.html";
	private Path pathToFile = Paths.get(filename);
	private String tdEnd = "</td>";
	
	/**
	 * This method gets the file name of the resulting HTML
	 * @return String - filename
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * This method Sets the File name to resulting HTML
	 * @param filename The filename to set
	 */
	public void setFilename(String filename) {
		this.filename = filename;
	}

	/**
	 * This method gets the path to the resulting HTML
	 * @return the pathToFile
	 */
	public Path getPathToFile() {
		return pathToFile;
	}

	/**
	 * This method set the path to write resulting HTML
	 * @param pathToFile set the path To write File
	 */
	public void setPathToFile(Path pathToFile) {
		this.pathToFile = pathToFile;
	}

	/**
	 * This method randomly pick 10 movie to let the user to rate
	 * @return Arraylist<String> of the movies to be rate
	 */

    @Override
    public ArrayList<String> getItemsToRate() {
        ArrayList<String> movieToBeRate = new ArrayList<>();
        ArrayList<String> movieID = MovieDatabase.filterBy(new TrueFilter());
        while(movieToBeRate.iterator().hasNext()) {
        	byte[] bytes = new byte[20];
        	this.rand.nextBytes(bytes);
            int random = rand.nextInt(movieID.size());
            if (!movieToBeRate.contains(movieID.get(random)))
                movieToBeRate.add(movieID.get(random));
        }
        return movieToBeRate;
    }

	/**
	 * This method writes HTML Head
	 */
    public void writeHtmlHead() throws IOException {

    	String textHead = "<!DOCTYPE html>\n"
    			+ "<html lang=\"es\">\n"
    			+ "<head>\n"
    			+ "  <meta charset=\"UTF-8\">\n"
    			+ "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
    			+ "  <title>LPH-Final</title>";


    	Files.writeString(pathToFile, textHead, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);

    }

	/**
	 * This method writes CSS
	 */
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

	/**
	 * This method writes HTML Body
	 * @param outID - Arraylist<Rating> of the movies
	 */
    public void writeBody(ArrayList<Rating> outID) throws IOException {
    	
    	StringBuilder stringBuilderBody = new StringBuilder();
    	stringBuilderBody.append("<body>\n"
    			+ "  <h2>Wei Xu, Samir, Dario y Kevin le recomendamos las mejores peliculas</h2>\n"
    			+ "  <table id = \"rater\">\n"
    			+ "  <tr>\n"
    			+ "  <th>Rank</th>\n"
    			+ "  <th>Poster</th>\n"
    			+ "  <th>Title & Rating</th>\n"
    			+ "  <th>Genre</th>\n"
    			+ "  <th>Country</th>\n"
    			+ "  </tr>\n"
    			+ "  <tr>\n");

    			int rank = 1;
                for (Rating i : outID) {
                	stringBuilderBody.append("      <tr><td>" + rank + tdEnd +
                            "<td><img src = \"" + MovieDatabase.getPoster(i.getItem()).replace("http", "https") + "\" width=\"50\" height=\"70\"></td> " +
                            "<td>" + MovieDatabase.getYear(i.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                            i.getItem() + "\">" + MovieDatabase.getTitle(i.getItem()) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                            + String.format("%.1f", i.getValue()) + "/10</td>" +
                            "<td>" + MovieDatabase.getGenres(i.getItem()) + tdEnd +
                            "<td>" + MovieDatabase.getCountry(i.getItem()) + tdEnd +
                            "</tr>\n");
                    rank++;
                }

    		stringBuilderBody.append("  </table>\n" +
    				"  <h3>*El Rango de las peliculas está basado en otras calificaciones similares. Disfrute.*</h3>");
    		Files.writeString(pathToFile, stringBuilderBody, StandardOpenOption.APPEND);

    }


	/**
	 * This method prints the recommendations
	 * @param webRaterID - A String parameter representing a new rater
	 */
    @Override
    public void printRecommendationsFor(String webRaterID) {
        MovieDatabase.initialize("ratedmoviesfull.csv");
        RaterDatabase.initialize("ratings.csv");
        FourthRatingsOptimized fr = new FourthRatingsOptimized();
        ArrayList<Rating> ratingList = fr.getSimilarRatings(webRaterID, 20, 5);
        if (ratingList.isEmpty()) {
        	try {
        		System.out.println("<h2>Sorry, there are no movie recommend for you based on your rating!</h2>");
            	writeHtmlHead();
            	writeCss();
            	Files.writeString(pathToFile, "<h2>Sorry, there are no movie recommend for you based on your rating!</h2>", StandardOpenOption.APPEND);
    		} catch (IOException e) {
    			logger.log(Logger.Level.ERROR, e);
    		} 
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
            	System.out.println("<tr><td>" + rank + tdEnd +

                        "<td><img src = \"" + MovieDatabase.getPoster(i.getItem()).replace("http", "https") + "\" width=\"50\" height=\"70\"></td> " +
                        "<td>" + MovieDatabase.getYear(i.getItem()) + "&ensp;&ensp; <a href=\"https://www.imdb.com/title/tt" +
                        i.getItem() + "\">" + MovieDatabase.getTitle(i.getItem()) + "</a><br><div class = \"rating\">&starf; &ensp;&ensp;&ensp;"
                        + String.format("%.1f", i.getValue()) + "/10</td>" +
                        "<td>" + MovieDatabase.getGenres(i.getItem()) + tdEnd +
                        "<td>" + MovieDatabase.getCountry(i.getItem()) + tdEnd +
                        "</tr> ");
                rank++;
            }
            try {
            	writeHtmlHead();
            	writeCss();
            	writeBody(outID);
    		} catch (IOException e) {
    			logger.log(Logger.Level.ERROR, e);
    		}
            System.out.println("</table>");
            System.out.println("<h3>*The rank of movies is based on other raters who have the most similar rating to yours. Enjoy!^^</h3>");
        }
        

    }
        public static void main(String[] args) {
            RecommendationRunner a = new RecommendationRunner();
            a.getItemsToRate();
            a.printRecommendationsFor("67");
        }
}
