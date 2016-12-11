/**
 * Created by Daltin-Desk on 12/8/2016.
 */
public class Edge {
    public Vertex start;
    public Vertex end;
    public double weight;

    public Edge(Vertex start, Vertex end, double weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }
}
