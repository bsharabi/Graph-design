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
    private Map<NodeData, NodeData> prevNodes;
    private int connect;
    private int mc;

    // -------------------------- Constructor --------------------------------------
    public AlgoDWG() {
        this.graph = null;
        this.prevNodes = new HashMap<>();
        connect = -1;
        mc = 0;
    }

    // -------------------------- Function --------------------------------------

    public boolean DFS(DirectedWeightedGraph g, NodeData v) {//==================DFS traversal
        Map<Integer, NodeData> Visited = new HashMap<>();
        Stack<NodeData> stack = new Stack<>();
        Node n;
        stack.add(v);
        while (!stack.isEmpty()) {
            n = (Node) stack.pop();
            for (EdgeData e : n.getEdgeListOut())
                if (!Visited.values().contains(g.getNode(e.getDest()))) {
                    stack.add(g.getNode(e.getDest()));
                    Visited.put(e.getDest(), g.getNode(e.getDest()));
                }
        }
        return (Visited.values().size() == g.nodeSize());

    }

    public void traveler(int v, ArrayList<NodeData> visited, PriorityQueue<Node> pq) {
        double newWeight;
        Node n = (Node) graph.getNode(v);
        Map<Point2D, EdgeData> edges = n.getEdgeMapOut();
        for (EdgeData e : edges.values()) {
            Node neighbour = (Node) graph.getNode(e.getDest());
            if (!visited.contains(neighbour)) {
                newWeight = n.getWeight() + e.getWeight();
                if (newWeight < neighbour.getWeight()) {
                    neighbour.setWeight(newWeight);
                    n.setTag(neighbour.getKey());
                    if (prevNodes.get(neighbour.getKey()) != null)
                        prevNodes.replace(neighbour, prevNodes.get(neighbour), n);
                    else
                        prevNodes.put(neighbour, n);
                    pq.add(neighbour);
                }
            }
        }
    }

    public ResultsFormat saGeekyStria(int src, int dest) {
        this.prevNodes.clear();
        if (!isConnected()) return null;
        ArrayList<NodeData> visited = new ArrayList<>();
        Map<Integer, NodeData> nodesMap = ((Graph) graph).getNodesMap();

        Node start = (Node) graph.getNode(src);
        Node end = (Node) graph.getNode(dest);

        for (NodeData n : nodesMap.values()) n.setWeight(Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<Node>(((Graph) graph).getNodesMap().values().size(), new Node());
        pq.add(start);
        start.setWeight(0);
        while (visited.size() != ((Graph) graph).getNodesMap().values().size()) {

            Node u = pq.remove();
            if (!visited.contains(u)) {
                visited.add(u);
            }
            traveler(u.getKey(), visited, pq);
        }
        LinkedList<Node> path = new LinkedList<>();
        Node temp = (Node) graph.getNode(dest);
        path.add(temp);

        while (prevNodes.get(temp) != null) {
            path.addFirst((Node) prevNodes.get(temp));
            temp = (Node) prevNodes.get(temp);
        }
        ResultsFormat res = new ResultsFormat(end.getWeight(), path);

        return res;
    }

    public double saGeekyStria2(int src, int dest) {
        this.prevNodes.clear();
        Map<Integer, NodeData> nodesMap = ((Graph) graph).getNodesMap();

        Node start = (Node) graph.getNode(src);
        Node end = (Node) graph.getNode(dest);

        for (NodeData n : nodesMap.values()) n.setWeight(Integer.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>(((Graph) graph).getNodesMap().values().size(), new Node());

        pq.add(start);
        start.setWeight(0);

        while (!pq.isEmpty()) {
            Node u = pq.remove();
            for (EdgeData e : u.getEdgeListOut()) {
                NodeData neighbour = nodesMap.get(e.getDest());
                if (neighbour.getWeight() > u.getWeight() + e.getWeight()) {
                    neighbour.setWeight(u.getWeight() + e.getWeight());
                    pq.add((Node) neighbour);
                    prevNodes.put(neighbour, u);
                }
            }
        }
        return end.getWeight();
    }

    public double maxDistance(NodeData node) {
        double max = Integer.MIN_VALUE;
        Iterator nodeIter = getGraph().nodeIter();
        while (nodeIter.hasNext()) {
            Node n = (Node) nodeIter.next();
            double temp = shortestPathDist(node.getKey(), n.getKey());
            max = Math.max(temp, max);
        }
        return max;
    }

    public Tsp getMin(Tsp[] arr, int src, ArrayList<Node> visited) {
        Tsp tsp = arr[0];
        double min = Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].getLength() < min && arr[i].getDest() != src && !visited.contains(graph.getNode(arr[i].getDest()))) {
                min = arr[i].getLength();
                tsp = arr[i];
            }
        }


        return tsp;
    }

    public Map<Integer, Tsp[]> tspMapInit(List<NodeData> cities) {

        int amountCities = cities.size();
        Map<Integer, Tsp[]> cityToTsp = new HashMap<>();
        for (NodeData city : cities) {
            Tsp[] temp = new Tsp[amountCities - 1];
            int k = 0;
            PriorityQueue<Tsp> pq = new PriorityQueue<Tsp>(amountCities - 1, new Tsp());
            for (NodeData nextCity : cities) {
                if (city == nextCity) continue;
                else {
                    pq.add(new Tsp(saGeekyStria(city.getKey(), nextCity.getKey()), nextCity.getKey()));
                    temp[k] = new Tsp(saGeekyStria(city.getKey(), nextCity.getKey()), nextCity.getKey());
                    k++;

                }
            }
            cityToTsp.put(city.getKey(), temp);
        }

        return cityToTsp;
    }

    public DirectedWeightedGraph GetOpp(DirectedWeightedGraph g) {
        DirectedWeightedGraph res = new Graph();
        for (NodeData n : ((Graph) g).getNodesMap().values()) {
            Node b = new Node(n.getLocation(), n.getKey());
            res.addNode(b);
        }
        for (EdgeData e : ((Graph) g).getEdgesMap().values()) {
            res.connect(e.getDest(), e.getSrc(), e.getWeight());
        }
        return res;
    }

    public void print() {
        Iterator it = graph.nodeIter();
        while (it.hasNext())
            System.out.println(((Node) it.next()).print());
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
            if (!(graph instanceof Graph) || ((Graph) graph).getNodesMap().isEmpty())
                throw new Exception("There are no nodes in the graph");
            else if (!((Graph) graph).getEdgesMap().isEmpty()) {
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
        if (getGraph().getMC() != mc)
            connect = -1;
        if (connect != -1)
            return connect == 0 ? false : true;
        NodeData src = getGraph().nodeIter().next();
        if (DFS(this.graph, src) == false || DFS(GetOpp(this.graph), src) == false)
            return false;
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if (!isConnected())
            return -1;
        if (src == dest) return 0;
        double temp = saGeekyStria2(src, dest);
        if (temp == Integer.MAX_VALUE) return -1;
        return temp;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if (!isConnected()) {
            System.out.println("there are no path's between those nodes");
            return null;
        }
        if (src == dest) return null;
        saGeekyStria(src, dest);
        return saGeekyStria(src, dest).path;
    }

    @Override
    public NodeData center() {
        if (!isConnected())
            return null;
        NodeData center = null;
        double best_path = Integer.MAX_VALUE;
        double max_path;
        Iterator nodeIter = getGraph().nodeIter();
        while (nodeIter.hasNext()) {
            Node n = (Node) nodeIter.next();
            max_path = maxDistance(n);
            if (max_path < best_path) {
                center = n;
                best_path = max_path;
            }
        }
        return center;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {

        int amountCities = cities.size();
        Map<Integer, Tsp[]> cityToTsp = tspMapInit(cities);
        List<NodeData> res;
        ArrayList<Node> visited = new ArrayList<>();
        Node src = (Node) cities.get(0);
        Node temp;
        res = getMin(cityToTsp.get(src.getKey()), src.getKey(), visited).getPath();
        while (!visited.contains(src)) {
            temp = (Node) res.get(res.size() - 1);
            visited.add(temp);

            Tsp[] tspArr = cityToTsp.get(temp.getKey());
            if (visited.size() == amountCities - 1) {
                for (int i = 0; i < tspArr.length; i++) {
                    if (tspArr[i].getDest() == src.getKey()) {
                        return res;
                    }
                }
            }
            List<NodeData> tempList = getMin(tspArr, src.getKey(), visited).getPath();
            for (int i = 1; i < tempList.size(); i++) {
                res.add(tempList.get(i));
            }
        }
        return res;
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
        connect = -1;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    @Override
    public String toString() {
        return "AlgoDWG{" +
                "graph=" + graph +
                '}';
    }


}




