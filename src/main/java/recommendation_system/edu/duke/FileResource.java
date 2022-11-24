package recommendation_system.edu.duke;

import recommendation_system.edu.duke.FileSelector;
import recommendation_system.edu.duke.ResourceException;
import recommendation_system.edu.duke.StorageResource;
import recommendation_system.edu.duke.TextIterable;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;

public class FileResource {
    private String myPath;
    private String mySource;
    private File mySaveFile;

    public FileResource() {
        this.initRead();
    }

    public FileResource(File file) {
        this.initRead(file);
    }

    public FileResource(String filename) {
        this.initRead(filename);
    }

    public FileResource(boolean writable) {
        if (writable) {
            this.initWrite();
        } else {
            this.initRead();
        }
    }

    public FileResource(File file, boolean writable) {
        if (writable) {
            this.initWrite(file);
        } else {
            this.initRead(file);
        }
    }

    public FileResource(String filename, boolean writable) {
        if (writable) {
            this.initWrite(filename);
        } else {
            this.initRead(filename);
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
            throw new ResourceException("FileResource: CSV delimiter must be a single character: " + delimiter);
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
            throw new ResourceException("FileResource: cannot read " + this.myPath + " as a CSV file.");
        }
    }

    public Iterable<String> getCSVHeaders(CSVParser parser) {
        return parser.getHeaderMap().keySet();
    }

    public void write(String s) {
        ArrayList<String> list = new ArrayList<String>();
        list.add(s);
        this.write(list);
    }

    public void write(StorageResource list) {
        this.write((ArrayList)list.data());
    }

    public void write(String[] list) {
        this.write(new ArrayList<String>(Arrays.asList(list)));
    }

    public void write(ArrayList<String> list) {
        if (this.mySaveFile != null) {
            StringBuilder sb = new StringBuilder();
            for (String s : list) {
                sb.append(s);
                sb.append("\n");
            }
            this.mySource = String.valueOf(this.mySource) + sb.toString();
            PrintWriter writer = null;
            try {
                try {
                    writer = new PrintWriter(new FileWriter(this.mySaveFile, true));
                    writer.println(sb.toString());
                }
                catch (Exception e) {
                    throw new ResourceException("FileResource: cannot change " + this.mySaveFile);
                }
            }
            finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                }
                catch (Exception exception) {}
            }
        }
    }

    private void initRead() {
        File f = FileSelector.selectFile();
        if (f == null) {
            throw new ResourceException("FileResource: no file choosen for reading");
        }
        this.initRead(f);
    }

    private void initRead(File f) {
        try {
            this.initRead(f.getCanonicalPath());
        }
        catch (Exception e) {
            throw new ResourceException("FileResource: cannot access " + f);
        }
    }

    private void initRead(String fname) {
        try {
            this.myPath = fname;
            InputStream is = this.getClass().getClassLoader().getResourceAsStream(fname);
            if (is == null) {
                is = new FileInputStream(fname);
            }
            this.mySource = this.initFromStream(is);
        }
        catch (Exception e) {
            throw new ResourceException("FileResource: cannot access " + fname);
        }
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
            throw new ResourceException("FileResource: error encountered reading " + this.myPath, e);
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

    private void initWrite() {
        File f = FileSelector.saveFile();
        if (f == null) {
            throw new ResourceException("FileResource: no file choosen for writing");
        }
        this.initWrite(f);
    }

    private void initWrite(File f) {
        try {
            this.mySaveFile = f;
            if (f.exists() && f.canWrite()) {
                this.initRead(f);
            } else {
                this.mySource = "";
                this.myPath = f.getCanonicalPath();
            }
        }
        catch (Exception e) {
            throw new ResourceException("FileResource: cannot access " + f, e);
        }
    }

    private void initWrite(String fname) {
        try {
            URL loc = this.getClass().getClassLoader().getResource(fname);
            if (loc != null) {
                fname = loc.toString();
            }
            this.initWrite(new File(fname));
        }
        catch (Exception e) {
            throw new ResourceException("FileResource: cannot access " + fname);
        }
    }
}

