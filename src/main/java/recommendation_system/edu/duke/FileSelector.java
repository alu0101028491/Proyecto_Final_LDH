package recommendation_system.edu.duke;

import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

class FileSelector {
    private static File[] ourFiles;
    private static JFileChooser ourChooser;

    static {
        ourChooser = new JFileChooser();
        ourChooser.setFileSelectionMode(0);
        ourChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
    }

    FileSelector() {
    }

    public static File selectFile() {
        try {
            return FileSelector.selectFiles(null, false, true)[0];
        } catch( NullPointerException e)
        {
            throw e;
        }
    }

    public static File selectFile(String[] extensionAccepted) {
        try {
            return FileSelector.selectFiles(extensionAccepted, false, true)[0];
        } catch( NullPointerException e)
        {
            throw e;
        }
    }

    public static File[] selectFiles() {
        try {
            return FileSelector.selectFiles(null, true, true);
        } catch( NullPointerException e)
        {
            throw e;
        }
    }

    public static File[] selectFiles(String[] extensionAccepted) {
        try {
            return FileSelector.selectFiles(extensionAccepted, true, true);
        } catch( NullPointerException e)
        {
            throw e;
        }
    }

    public static File saveFile() {
        return FileSelector.selectFiles(null, false, false)[0];
    }

    public static File saveFile(String[] extensionAccepted) {
        return FileSelector.selectFiles(extensionAccepted, false, false)[0];
    }

    private static File[] selectFiles(String[] extensionAccepted, final boolean allowMultiple, final boolean openForRead) {
        ourChooser.setMultiSelectionEnabled(allowMultiple);
        ourChooser.setFileFilter(new ChooserFilter(extensionAccepted));
        try {
            ourFiles = null;
            SwingUtilities.invokeAndWait(new Runnable(){

                @Override
                public void run() {
                    int result = 0;
                    result = openForRead ? ourChooser.showOpenDialog(null) : ourChooser.showSaveDialog(null);
                    if (result == 1) {
                        ourFiles = new File[1];
                    } else {
                        try {
                            if (allowMultiple) {
                                ourFiles = ourChooser.getSelectedFiles();
                            } else {
                                ourFiles = new File[]{ourChooser.getSelectedFile()};
                            }
                        }
                        catch (Exception e) {
                            JOptionPane.showMessageDialog(null, e.toString());
                        }
                    }
                }
            });
            return ourFiles;
        }
        catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
        return ourFiles;
    }

    static class ChooserFilter
    extends FileFilter {
        private String myExtensions;

        public ChooserFilter(String[] extensionsAccepted) {
            if (extensionsAccepted != null) {
                this.myExtensions = String.format("(?i).*\\.(%s)", String.join((CharSequence)"|", extensionsAccepted));
            }
        }

        @Override
        public boolean accept(File f) {
            if (this.myExtensions != null) {
                return f.getName().matches(this.myExtensions) || f.isDirectory();
            }
            return true;
        }

        @Override
        public String getDescription() {
            return "Files";
        }
    }
}

