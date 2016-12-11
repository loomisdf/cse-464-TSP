import java.util.*;

/**
 * Created by Daltin-Desk on 12/7/2016.
 */

public class TSPMain {
    static Tour best_tour;

    public static Tour solve_TSP(Graph g) {
        ArrayList<Vertex> visited = new ArrayList<>();
        visited.add(g.start);
        best_tour = new Tour(g.start);
        best_tour.length = Double.POSITIVE_INFINITY;
        Tour curr_tour = new Tour(g.start);
//        solveTSP_bf(curr_tour, g, visited);
        solveTSP_greedy(curr_tour, g, visited);
        return curr_tour;
    }

    public static void solveTSP_greedy(Tour t, Graph g, ArrayList<Vertex> visited) {
        Vertex prev_v = t.getLastVertex();
        HashMap<Vertex, Double> adj_vertices = g.getNeighbors(prev_v);
        if(visited.size() < g.vertices.size()) {
            Vertex closest_vert = null;
            double dist = Double.POSITIVE_INFINITY;
            // Find the closest neighbor
            for(Vertex v : adj_vertices.keySet()) {
                if(!visited.contains(v)) {
                    if (adj_vertices.get(v) < dist) {
                        closest_vert = v;
                        dist = adj_vertices.get(v);
                    }
                }
            }
            t.path.add(closest_vert);
            t.length += dist;
            visited.add(closest_vert);
            solveTSP_greedy(t, g, visited);
        }
        else {
//            t.path.add(g.start);
//            t.length += adj_vertices.get(g.start);
        }

    }

    // Finds the best tour using brute force
    // This method will be very slow on graphs larger than 10 vertices
    // May never finish on graphs larger than 20 vertices
    public static void solveTSP_bf(Tour t, Graph g, ArrayList<Vertex> visited) {
        Vertex prev_v = t.getLastVertex();
        HashMap<Vertex, Double> adj_vertices = g.getNeighbors(prev_v);
        if(visited.size() < g.vertices.size()) {
            for(Vertex nbr : adj_vertices.keySet()) {
                if(!visited.contains(nbr)) {
                    Tour t_copy = t.copy();
                    t_copy.path.add(nbr);
                    t_copy.length += adj_vertices.get(nbr);
                    ArrayList<Vertex> visited_copy = new ArrayList<>();
                    for(Vertex n : visited) {
                        visited_copy.add(n);
                    }
                    visited_copy.add(nbr);
                    solveTSP_bf(t_copy, g, visited_copy);
                }
            }
        }
        else {
            // Calculate the length back to the start node
            t.path.add(g.start);
            t.length += adj_vertices.get(g.start);
            if(t.length < best_tour.length) {
                best_tour = t;
            }
        }
    }

    public static void main(String[] args) {

        double percent_within_optimal = 0;
        long beforetime = 0;
        long aftertime = 0;

        Graph fri26_g = Graph.makeGraphFromXML("fri26.xml", 26);
        beforetime = System.currentTimeMillis();
        Tour fri26_tour = solve_TSP(fri26_g);
        aftertime = System.currentTimeMillis();
        int fri26_opt_len = 937;
        percent_within_optimal = ((fri26_tour.length - fri26_opt_len) / fri26_opt_len) * 100;
        System.out.println("fri26");
        System.out.printf("Greedy %s\n", fri26_tour);
        System.out.printf("Elapsed time: %dms\n", (aftertime - beforetime));
        System.out.printf("Optimal tour: %d\n", fri26_opt_len);
        System.out.printf("Percentage within optimal: %.1f%%\n", percent_within_optimal);

        System.out.println();

        Graph ch150_g = Graph.makeGraphFromXML("ch150.xml", 150);
        beforetime = System.currentTimeMillis();
        Tour ch150_tour = solve_TSP(ch150_g);
        aftertime = System.currentTimeMillis();
        int ch150_opt_len = 6528;
        percent_within_optimal = ((ch150_tour.length - ch150_opt_len) / ch150_opt_len) * 100;
        System.out.println("ch150");
        System.out.printf("Greedy %s\n", ch150_tour);
        System.out.printf("Elapsed time: %dms\n", (aftertime - beforetime));
        System.out.printf("Optimal tour: %d\n", ch150_opt_len);
        System.out.printf("Percentage within optimal: %.1f%%\n", percent_within_optimal);

        System.out.println();

        Graph d1655_g = Graph.makeGraphFromXML("d1655.xml", 1655);
        beforetime = System.currentTimeMillis();
        Tour d1655_tour = solve_TSP(d1655_g);
        aftertime = System.currentTimeMillis();
        int d1655_opt_len = 62128;
        percent_within_optimal = ((d1655_tour.length - d1655_opt_len) / d1655_opt_len) * 100;
        System.out.println("d1655");
        System.out.printf("Greedy %s\n", d1655_tour);
        System.out.printf("Elapsed time: %dms\n", (aftertime - beforetime));
        System.out.printf("Optimal tour: %d\n", d1655_opt_len);
        System.out.printf("Percentage within optimal: %.1f%%\n", percent_within_optimal);
    }
}
