import java.util.ArrayList;

/**
 * Created by Daltin-Desk on 12/8/2016.
 */
public class Tour {
    public ArrayList<Vertex> path;
    public double length;

    public Tour(ArrayList<Vertex> path, double length) {
        this.path = path;
        this.length = length;
    }

    public Tour(Vertex start) {
        this.path = new ArrayList<>();
        path.add(start);
        this.length = 0;
    }

    public Tour copy() {
        ArrayList<Vertex> newPath = new ArrayList<>();
        for(Vertex n : this.path) {
            newPath.add(n);
        }
        return new Tour(newPath, this.length);
    }

    public Vertex getLastVertex() {
        return path.get(path.size() - 1);
    }

    @Override
    public String toString() {
        String str = "";
        str += "length: " + this.length;
        str += " path: [";
        for(Vertex v : path) {
            str += " " + v;
        }
        str += " ]";
        return str;
    }
}
