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
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.List;

public class AlgoDWG implements DirectedWeightedGraphAlgorithms {

    private DirectedWeightedGraph graph;
    private ArrayList<NodeData> visited;            // prototrype
    private ArrayList<NodeData> unvisited;          // prototrype
    private Map<NodeData, NodeData> prevNodes;

    // -------------------------- Constructor --------------------------------------
    public AlgoDWG() {
        this.graph = null;
    }


    //--------------------------- Function -----------------------------------------
    public void initWeight(Node n) {
        Map<Point2D, EdgeData> neighbors = n.getEdgeMapOut();
        for (EdgeData e : neighbors.values()) {
            Node neighbor = (Node) graph.getNode(e.getDest());
            if (neighbor.getWeight() > n.getWeight() + e.getWeight()) {
                prevNodes.put(neighbor, n);
                neighbor.setWeight(n.getWeight() + e.getWeight());

            }

        }
    }


    public ResultsFormat dirjkstraAlogorithem(int src, int dest) {
        if(!isConnected()) return null;
        Map<Integer, NodeData> nodesMap = ((Graph)graph).getNodesMap();
        Map<Point2D, EdgeData> edgesMap = ((Graph)graph).getEdgesMap();
        Node start = (Node) nodesMap.get(src);
        Node end = (Node) nodesMap.get(dest);
        start.setWeight(0);
        for (NodeData n : nodesMap.values()) {
            if (n != start) n.setWeight(Integer.MAX_VALUE);        // init the weight of the nodes
        }
        Node temp = start;
        for (NodeData i : ((Graph)graph).getNodesMap().values()) {
            initWeight((Node) i);
        }
        ResultsFormat res = new ResultsFormat(end.getWeight(), null);
        List<NodeData> path = new ArrayList<>();
        boolean button = true;
        path.add(end);
        while (prevNodes.get(end) != null) {
            path.add(prevNodes.get(end));
            end = (Node) prevNodes.get(end);
        }
        res.setPath(path);
//            System.out.println(prevNodes.get(end).getKey());
//            end = (Node) prevNodes.get(end);

        return res;

    }

    private void DFS(DirectedWeightedGraph g, int v, ArrayList<Node> visited) {//==================DFS traversal
        Node n = (Node) g.getNode(v);
        visited.add(n);
        for (EdgeData e : n.getEdgeMapOut().values()) {//------------------- for each edge we chack the dist node
            Node dest = (Node) ((Graph)graph).getNodesMap().get(e.getDest());
            if (!visited.contains(dest)) {        // in case we didn't reach the dest yet
                DFS(g, dest.getKey(), visited);
            }
        }
    }
    //-------------------------------- Override -------------------------------------

    @Override
    public boolean isConnected() {
        for (NodeData n : ((Graph)graph).getNodesMap().values()) {    // move on all the nodes
            ArrayList<Node> visited = new ArrayList<>();   // check which  was allready visited
            DFS(graph, n.getKey(), visited);

            for (NodeData k : ((Graph)graph).getNodesMap().values()) {     // DFS
                if (!visited.contains(k)) {               // if the DFS didnt visit all the nodes then the g is not strongly connected;
                    return false;
                }
            }
        }
        return true;


    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (src==dest)return 0;
        double temp = dirjkstraAlogorithem(src, dest).distance;
        if (temp == Integer.MAX_VALUE) return -1;
        return dirjkstraAlogorithem(src, dest).getDistance();
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        return dirjkstraAlogorithem(src, dest).getPath();
    }

    @Override
    public NodeData center() {
        Map<NodeData, Double> City_Distances = new HashMap<>();
        NodeData best_Node = new Node();
        double best_path = Integer.MAX_VALUE;
        double max_path = Integer.MIN_VALUE;
        for (NodeData src : ((Graph)graph).getNodesMap().values()) {
            for (NodeData dest : ((Graph)graph).getNodesMap().values()) {

                double temp = shortestPathDist(src.getKey(), dest.getKey());
                if (temp > max_path) max_path = temp;
            }
            City_Distances.put(src, max_path);
            if (max_path < best_path) {
                best_Node = src;
            }
        }
        return best_Node;
    }
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
class ResultsFormat {
    public double distance;
    public List<NodeData> path;

    public boolean isStrongConnected() {
        return strongConnected;
    }

    public void setStrongConnected(boolean strongConnected) {
        this.strongConnected = strongConnected;
    }

    public boolean strongConnected;

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public void setPath(List<NodeData> path) {
        this.path = path;
    }

    public double getDistance() {
        return distance;
    }

    public List<NodeData> getPath() {
        return path;
    }

    public ResultsFormat(double distance, LinkedList path) {
        this.distance = distance;
        this.path = path;
    }

}