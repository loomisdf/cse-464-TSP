import java.util.ArrayList;

/**
 * Created by Daltin-Desk on 12/8/2016.
 */
public class Vertex {
    public String name;
    public ArrayList<Edge> connections;

    public Vertex() {
        this.name = "";
        this.connections = new ArrayList<>();
    }
    public Vertex(String name) {
        this.name = name;
        this.connections = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        Vertex v = (Vertex)o;
        if(this.name.equals(v.name)) {
            return true;
        }
        else
            return false;
    }

    @Override
    public String toString() {
        return name;
    }
}
