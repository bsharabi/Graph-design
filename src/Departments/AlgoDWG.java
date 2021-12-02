package Departments;

import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class AlgoDWG implements DirectedWeightedGraphAlgorithms {

    private DirectedWeightedGraph graph;

    @Override
    public void init(DirectedWeightedGraph g) {

    }

    @Override
    public DirectedWeightedGraph getGraph() {
        return graph != null ? graph : null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        if (this.graph != null && this.graph instanceof Graph) {
            return new Graph(((Graph) this.graph).getNodesMap(), ((Graph) this.graph).getEdgesMap());
        }
        return null;
    }

    @Override
    public boolean isConnected() {
        return false;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        return 0;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return null;
    }

    @Override
    public NodeData center() {
        return null;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        return null;
    }

    @Override
    public boolean save(String file) {
        return false;
    }

    @Override
    public boolean load(String file) {
        try {
            Object obj = new JSONParser().parse(new FileReader(file));
            JSONObject JsonObj = (JSONObject) obj;
            JSONArray nodeArr = (JSONArray) JsonObj.get("Nodes");
            JSONArray edgeArr = (JSONArray) JsonObj.get("Edges");
            Map<Point2D, EdgeData> edgesMap = new HashMap<>();
            Map<Integer, NodeData> nodesMap = new HashMap<>();
            Iterator it = nodeArr.iterator();
            Map map;
            String[] s;
            while (it.hasNext()) {
                map = (Map) it.next();
                int id = Integer.parseInt(Objects.toString(map.get("id")));
                s = ((String) map.get("pos")).split(",");
                nodesMap.put(id, new Node(new GeoPosition(Double.parseDouble(s[0]),
                        Double.parseDouble(s[1]), Double.parseDouble(s[2])), id));
            }
            it = edgeArr.iterator();
            while (it.hasNext()) {
                map = (Map) it.next();
                int src = Integer.parseInt(Objects.toString(map.get("src")));
                double w = Double.parseDouble(Objects.toString(map.get("w")));
                int dest = Integer.parseInt(Objects.toString(map.get("dest")));
                edgesMap.put(new Point(src, dest), new Edge(src, w, dest));
            }
            this.graph = new Graph(nodesMap, edgesMap);
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return false;
    }
}
