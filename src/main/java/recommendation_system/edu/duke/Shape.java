package recommendation_system.edu.duke;

import java.util.ArrayList;

public class Shape {
    private ArrayList<Point> points = new ArrayList();

    public Shape() {
    }

    public Shape(FileResource file) {
        this();
        for (String line : file.lines()) {
            int commaloc = line.indexOf(",");
            int x = Integer.parseInt(line.substring(0, commaloc).trim());
            int y = Integer.parseInt(line.substring(commaloc + 1).trim());
            this.addPoint(new Point(x, y));
        }
    }

    public void addPoint(Point p) {
        this.points.add(p);
    }

    public Point getLastPoint() {
        return this.points.get(this.points.size() - 1);
    }

    public Iterable<Point> getPoints() {
        return this.points;
    }
}

