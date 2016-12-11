import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Daltin-Desk on 12/8/2016.
 */
public class Graph {
    public Vertex start;
    public ArrayList<Vertex> vertices;

    public Graph() {

    }

    public Graph(Vertex start) {
        this.start = start;
        vertices = new ArrayList<>();
    }

    public HashMap<Vertex, Double> getNeighbors(Vertex v) {
        HashMap<Vertex, Double> nbrs = new HashMap<>();
        for(Edge e : v.connections) {
            nbrs.put(e.end, e.weight);
        }
        return nbrs;
    }

    public static Graph makeGraphFromXML(String filename, int vertexCount) {
        Graph g = new Graph();
        int edgeCount = vertexCount - 1;
        g.vertices = new ArrayList<>(vertexCount);
        for(int i = 1; i <= vertexCount; i++) {
           g.vertices.add(new Vertex("" + i));
        }
        try {
            File inputFile = new File(filename);
            DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            NodeList edgeList = doc.getElementsByTagName("edge");
            Node n;
            Element e;
            int vertex_num = 0;
            for(int i = 0; i < edgeList.getLength(); i++) {
                if(i != 0 && i % (vertexCount-1) == 0) {
                    vertex_num++;
                }
                e = (Element)edgeList.item(i);
                int vertex_end = Integer.parseInt(e.getTextContent());
                Vertex v = g.vertices.get(vertex_num);
                double weight = 0;
                NamedNodeMap nnm = e.getAttributes();
                if(nnm != null) {
                    for(int j = 0; j < nnm.getLength(); j++) {
                        n = nnm.item(j);
                        weight = Double.parseDouble(n.getNodeValue());
                    }
                }
                v.connections.add(new Edge(v, g.vertices.get(vertex_end), weight));

            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        g.start = g.vertices.get(0);
        return g;
    }
}
