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

    // -------------------------- Constructor --------------------------------------
    public AlgoDWG() {
        this.graph = null;
        this.prevNodes = new HashMap<>();
    }

    // -------------------------- Function --------------------------------------

    private void DFS(DirectedWeightedGraph g, int v, ArrayList<Node> visited) {
        Node n = (Node) g.getNode(v);
        visited.add(n);
        for (EdgeData e : n.getEdgeMapOut().values()) {
            Node dest = (Node) ((Graph) graph).getNodesMap().get(e.getDest());
            if (!visited.contains(dest))
                DFS(g, dest.getKey(), visited);
        }
    }

    public boolean availablePath(int src,int dest) {
        ArrayList<Node> visited = new ArrayList<>();
        DFS(graph, src, visited);
        if (!visited.contains(this.graph.getNode(dest)))
            return false;
        return true;
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

    public ResultsFormat sageakstra(int src, int dest) {
        this.prevNodes.clear();
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

            visited.add(u);
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

    public double maxDistance(NodeData node){// maxDistance is a help func who returns the max distance of each node so at the center func all we need to do is to chack what node gives us the min output
        double max =Integer.MIN_VALUE;
        Map<Integer, NodeData> nodesMap = ((Graph) graph).getNodesMap();// list of all the nodes
        for(NodeData e:((Graph)graph).getNodesMap().values()) { // for each node we check the distance from our source node
            double temp = shortestPathDist(node.getKey(), e.getKey());//  if you don't understand this row you might be thinking again of what are yoe doing
            max = Math.max(temp, max);
        }
        return max;


    }

    public Tsp getMin(Tsp[] arr ,int src,ArrayList<Node> visited){
        Tsp tsp = arr[0];
        double min =Integer.MAX_VALUE;
        for(int i=0;i<arr.length;i++){
            if (arr[i].getLength()<min&&arr[i].getDest()!=src&&!visited.contains(graph.getNode(arr[i].getDest()))){
                min=arr[i].getLength();
                tsp= arr[i];
            }
        }



        return tsp;
    }

    public Map<Integer,Tsp[]> tspMapInitl(List<NodeData> cities){// להחליף את הריזולט פורמט בקלאס החדשTCP ולהוסיף לTCP שדה של יעד סופי כך שיהיה לנו את היכולת לדעת לאיזה עיר בדיוק אנחנו מגיעים בסוף המסלול ולדעת מאיפה לחפש מסלול חדש.

        int amountCities = cities.size();
        Map<Integer, Tsp[]> cityTOtsp = new HashMap<>();
        for (NodeData city : cities) {
            Tsp[] temp = new Tsp[amountCities - 1];
            int k = 0;
            PriorityQueue<Tsp> pq= new PriorityQueue<Tsp>(amountCities-1,new Tsp());
            for (NodeData nextCity : cities) {
                if (city == nextCity) continue;
                else {
                    pq.add(new Tsp(sageakstra(city.getKey(),nextCity.getKey()),nextCity.getKey()));
                    temp[k]= new Tsp(sageakstra(city.getKey(),nextCity.getKey()),nextCity.getKey());
                    k++;

                }
            }
            cityTOtsp.put(city.getKey(), temp);
        }

        return cityTOtsp;
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
        for (NodeData n : ((Graph) graph).getNodesMap().values()) {
            ArrayList<Node> visited = new ArrayList<>();
            DFS(graph, n.getKey(), visited);
            for (NodeData k : ((Graph) graph).getNodesMap().values())
                if (!visited.contains(k))
                    return false;
        }
        return true;
    }

    @Override
    public double shortestPathDist(int src, int dest) {
        if(!availablePath(src,dest))
            return -1;
        if (src == dest) return 0;
        double temp = sageakstra(src, dest).distance;
        if (temp == Integer.MAX_VALUE) return -1;
        return sageakstra(src, dest).distance;
    }

    @Override
    public List<NodeData> shortestPath(int src, int dest) {
        if(!availablePath(src,dest)){
            return null;
        }
        if (src == dest) return null;
        sageakstra(src, dest);
        return sageakstra(src, dest).path;
    }

    @Override
    public NodeData center() {
        if (!isConnected()) {
            return null;
        }
        NodeData center = new Node();
        double best_path = Integer.MAX_VALUE;      // initialize all the parameters we need
        double max_path = Integer.MIN_VALUE;
        Node[] dests = new Node[graph.nodeSize()]; // since we want control of knowing what nodes we are working we used the for each loop to insert all the nodes to the arr and now we can use the arr index as anew id to have the control back
        int k=0;
        for(NodeData e:((Graph)graph).getNodesMap().values()) {
            dests[k] = (Node) e;
            k++;
        }
        for(int i=0;i< dests.length;i++){       // and again here we'r just comparing the results to take the lowest dstance and get the center node
            max_path=maxDistance(dests[i]);
            if (max_path<best_path){
                center=dests[i];
                best_path =max_path;
            }
        }
        return center;
    }

    @Override
    public List<NodeData> tsp(List<NodeData> cities) {
        isConnected();
        boolean end= false;
        int amountCities = cities.size();
        Map<Integer, Tsp[]> cityTOtsp = tspMapInitl(cities);
        List<NodeData> res;
        ArrayList<Node> visited= new ArrayList<>();

        Node src = (Node) cities.get(0);
        Node temp ;
        res=getMin(cityTOtsp.get(src.getKey()),src.getKey(),visited).getPath();
        while(!visited.contains(src)){
            temp=(Node) res.get(res.size()-1);
            visited.add(temp);
            Tsp[] tspArr = cityTOtsp.get(temp.getKey());
            if(visited.size()==amountCities-1){
                for(int i=0;i<tspArr.length;i++) {
                    if (tspArr[i].getDest() == src.getKey()) {
                        List<NodeData> homecoming = tspArr[i].getPath();

                        for (int k = 1; k < homecoming.size(); k++) {
                            res.add(homecoming.get(k));

                        }
                        return res;
                    }
                }
            }
            List<NodeData> tempList=getMin(tspArr,src.getKey(),visited).getPath();
            for(int i=1;i<tempList.size();i++){
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
