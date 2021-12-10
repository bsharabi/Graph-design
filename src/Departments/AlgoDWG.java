package Departments;
import api.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.*;
import java.awt.geom.Point2D;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class AlgoDWG implements DirectedWeightedGraphAlgorithms {

    private DirectedWeightedGraph graph;

    // -------------------------- Constructor --------------------------------------
    public AlgoDWG() {
        this.graph = null;
    }

    //-------------------------------- Override -------------------------------------
    @Override
    public void init(DirectedWeightedGraph g) {
        this.graph = g;
    }

    @Override
    public DirectedWeightedGraph getGraph() {
        try {
            if (graph == null)
                throw new Exception("The graph dose not exists");
            return graph;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    @Override
    public DirectedWeightedGraph copy() {
        Map<Point2D, EdgeData> edgesMap = new HashMap<>();
        Map<Integer, NodeData> nodesMap = new HashMap<>();
        try {
            if (!(graph instanceof Graph) || ((Graph) graph).getNodesMap() == null)
                throw new Exception("There are no nodes in the graph");
            else if (((Graph) graph).getEdgesMap() != null) {
                ((Graph) graph).getEdgesMap().forEach((K, V) -> {
                    int src = (int) K.getX();
                    int dest = (int) K.getY();
                    edgesMap.put(new Point(src, dest), new Edge(src, V.getWeight(), dest));
                });
            }
            ((Graph) graph).getNodesMap().forEach((K, V) -> {
                double x = V.getLocation().x();
                double y = V.getLocation().y();
                double z = V.getLocation().z();
                nodesMap.put(K, new Node(new GeoPosition(x, y, z), V.getKey()));
            });
            return new Graph(nodesMap, edgesMap);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new Graph();
    }

    @Override
    public boolean isConnected() {
        return true;
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

    /**
     * @param file - the file name (may include a relative path).
     * @return
     */
    @Override
    public boolean save(String file) {
        try {
            if (this.graph != null) {
                Iterator It = this.graph.edgeIter();
                List<Object> list = new ArrayList<Object>();
                JSONObject jsonObject = new JSONObject();
                while (It.hasNext())
                    list.add(It.next());
                jsonObject.put("Edges", list);
                It = this.graph.nodeIter();
                list = new ArrayList();
                while (It.hasNext())
                    list.add(It.next());
                jsonObject.put("Nodes", list);
                FileWriter fileToJson = new FileWriter(file);
                fileToJson.write(jsonObject.toJSONString());
                fileToJson.close();
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    /**
     * @param file - file name of JSON file
     * @return
     */
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
                if (nodesMap.containsKey(src) && nodesMap.containsKey(dest))
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
        }catch (Exception e){
            e.printStackTrace();
        }
        return false;
    }

    public void print() {
        Iterator it = graph.nodeIter();
        while (it.hasNext())
            System.out.println(((Node) it.next()).print());
    }

    @Override
    public String toString() {
        return "AlgoDWG{" +
                "graph=" + graph +
                '}';
    }
}
