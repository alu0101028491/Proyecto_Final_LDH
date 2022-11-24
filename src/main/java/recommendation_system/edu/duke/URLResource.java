package recommendation_system.edu.duke;

import recommendation_system.edu.duke.ResourceException;
import recommendation_system.edu.duke.TextIterable;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class URLResource {
    private String myPath;
    private String mySource;

    public URLResource(String name) {
        if (name.startsWith("http://") || name.startsWith("https://")) {
            try {
                this.mySource = this.initFromStream(new URL(name).openStream());
                this.myPath = name;
            }
            catch (Exception e) {
                throw new ResourceException("URLResource: unable to load URL with name " + name, e);
            }
        } else {
            throw new ResourceException("URLResource: name must start with http:// or https://" + name);
        }
    }

    public Iterable<String> lines() {
        return new TextIterable(this.mySource, "\\n");
    }

    public Iterable<String> words() {
        return new TextIterable(this.mySource, "\\s+");
    }

    public String asString() {
        return this.mySource;
    }

    public CSVParser getCSVParser() {
        return this.getCSVParser(true);
    }

    public CSVParser getCSVParser(boolean withHeader) {
        return this.getCSVParser(withHeader, ",");
    }

    public CSVParser getCSVParser(boolean withHeader, String delimiter) {
        if (delimiter == null || delimiter.length() != 1) {
            throw new ResourceException("URLResource: CSV delimiter must be a single character: " + delimiter);
        }
        try {
            char delim = delimiter.charAt(0);
            StringReader input = new StringReader(this.mySource);
            if (withHeader) {
                return new CSVParser((Reader)input, CSVFormat.EXCEL.withHeader(new String[0]).withDelimiter(delim));
            }
            return new CSVParser((Reader)input, CSVFormat.EXCEL.withDelimiter(delim));
        }
        catch (Exception e) {
            throw new ResourceException("URLResource: cannot read " + this.myPath + " as a CSV file.");
        }
    }

    public Iterable<String> getCSVHeaders(CSVParser parser) {
        return parser.getHeaderMap().keySet();
    }

    private String initFromStream(InputStream stream) {
        BufferedReader buff = null;
        try {
            buff = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
            StringBuilder contents = new StringBuilder();
            String line = null;
            while ((line = buff.readLine()) != null) {
                contents.append(String.valueOf(line) + "\n");
            }
            String string = contents.toString();
            return string;
        }
        catch (Exception e) {
            throw new ResourceException("URLResource: error encountered reading " + this.myPath, e);
        }
        finally {
            try {
                if (buff != null) {
                    buff.close();
                }
            }
            catch (Exception exception) {}
        }
    }
}

