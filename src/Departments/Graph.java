package Departments;

import api.DirectedWeightedGraph;
import api.EdgeData;
import api.NodeData;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Graph implements DirectedWeightedGraph {

    private Map<Point2D, EdgeData> edgesMap;
    private Map<Integer, NodeData> nodesMap;
    private int mCount;

    // -------------------------- Constructor --------------------------------------
    public Graph() {
        this.edgesMap = new HashMap<>();
        this.nodesMap = new HashMap<>();
        this.mCount = 0;
    }

    public Graph(Map<Integer, NodeData> nodeMap, Map<Point2D, EdgeData> edgeMap) {
        super();
        //The constructor makes sure to accept existing objects
        this.edgesMap = edgeMap != null ? edgeMap : new HashMap<Point2D, EdgeData>();
        this.nodesMap = nodeMap != null ? nodeMap : new HashMap<Integer, NodeData>();
        this.mCount = 0;

        //Go over the edges of the graph and add to each node the edges that belong to it,
        //ie both the target edge and the source edge
        //O(edge.size())
        assert edgeMap != null;
        edgesMap.forEach((k, v) -> {
            int src = (int) k.getX();
            int dest = (int) k.getY();
            try {
                if (!nodesMap.containsKey(src) || !nodesMap.containsKey(dest))
                    throw new Exception("The node does not exist, Src or Dest node");
                Node node = (Node) nodesMap.get(src);
                node.getEdgeMapOut().put(k, v);
                node = (Node) nodesMap.get(dest);
                node.getEdgeMapIn().put(k, v);
            } catch (Exception e) {
                System.out.println(e);
            }
        });
    }

    //--------------------------- Getter && Setter --------------------------------

    public void setEdgesMap(Map<Point2D, EdgeData> edgesMap) {
        this.edgesMap = edgesMap;
    }

    public void setNodesMap(Map<Integer, NodeData> nodesMap) {
        this.nodesMap = nodesMap;
    }

    public Map<Point2D, EdgeData> getEdgesMap() {
        try {
            if (edgesMap.isEmpty())
                throw new NullPointerException("(Getter) The Map does not exist or the EdgesMap is not found");
            return edgesMap;
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return new HashMap<Point2D, EdgeData>();
    }

    public Map<Integer, NodeData> getNodesMap() {
        try {
            if (nodesMap.isEmpty())
                throw new NullPointerException("The Map does not exist or the NodesMap is not found");
            return nodesMap;
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return new HashMap<Integer, NodeData>();
    }

    //-------------------------------- Override -------------------------------------
    //O(1)
    @Override
    public NodeData getNode(int key) {
        try {
            //Check if the list exists or if the node exists according to the key ID
            if (nodesMap == null || !nodesMap.containsKey(key))
                throw new NullPointerException("The list does not exist or the node is not found");
            return this.nodesMap.get(key);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return null;
    }

    //O(1)
    @Override
    public EdgeData getEdge(int src, int dest) {
        try {
            //Create a new edge ID
            Point2D p = new Point(src, dest);
            //Check if the list exists or if the edge exists according to the key ID
            if (edgesMap == null || !edgesMap.containsKey(p))
                throw new NullPointerException("The list does not exist or the edge is not found");
            return edgesMap.get(p);
        } catch (NullPointerException e) {
            System.out.println(e);
        }
        return null;
    }

    //O(1)
    @Override
    public void addNode(NodeData n) {
        try {
            //Check whether the resulting node is valid or does not exist in the node list
            if (n == null || nodesMap.containsKey(n.getKey()))
                throw new Exception("It is not possible to add a new node because it exists or the sent node is invalid");
            //Inc mode count
            this.mCount++;
            nodesMap.put(n.getKey(), n);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //O(1)
    @Override
    public void connect(int src, int dest, double w) {
        try {
            //To add an edge you need to make sure that there
            //is indeed a receiving node and a sending node
            if (nodesMap.containsKey(src) && nodesMap.containsKey(dest)) {
                //Create an ID key to add a new edge to the map
                Point2D p = new Point(src, dest);
                //Create a new edge object
                EdgeData newEdge = new Edge(src, w, dest);
                //Check whether such an identifier exists, that is, whether such an end object exists.
                //If it exists, we will override the old, otherwise we will add it as new
                if (edgesMap.containsKey(p)) {
                    EdgeData oldEdge = edgesMap.get(p);
                    //Replace edge only with a different weight from the previous weight
                    if (oldEdge.getWeight() != w)
                        edgesMap.replace(p, oldEdge, newEdge);
                } else {
                    //Add the edge to the list of edges of the graph
                    edgesMap.put(p, newEdge);
                    //When adding a new edge it is necessary to add to the recipient
                    //node and also to the sender node the edge to the lists of each of them
                    Node node = (Node) nodesMap.get(src);
                    node.getEdgeMapOut().put(p, newEdge);
                    node = (Node) nodesMap.get(dest);
                    node.getEdgeMapIn().put(p, newEdge);
                }
                //Inc mode count
                this.mCount++;
            } else
                throw new Exception("The node does not exist");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Override
    public Iterator<NodeData> nodeIter() {
        int oldMC = this.mCount;
        Iterator<NodeData> nodeIter = nodesMap.values().iterator();
        try {
            if (nodesMap.isEmpty())
                throw new Exception("(nodeIter)->the object is not defined or empty.");
            return new Iterator<NodeData>() {
                @Override
                public boolean hasNext() {
                    return nodeIter.hasNext();
                }

                @Override
                public NodeData next() {
                    if (oldMC == mCount)
                        return nodeIter.next();
                    throw new RuntimeException("(nodeIter_next)->There is a change in the graph");
                }
            };
        } catch (RuntimeException r) {
            System.out.println(r);
        } catch (Exception e) {
            System.out.println(e);
        }
        return nodeIter;
    }

    @Override
    public Iterator<EdgeData> edgeIter() {
        int oldMC = this.mCount;
        Iterator<EdgeData> edgeIter = edgesMap.values().iterator();
        try {
            if (edgesMap.isEmpty())
                throw new Exception("(edgeIter)->There is no edge object or the object is not defined or empty.");
            return new Iterator<EdgeData>() {
                @Override
                public boolean hasNext() {
                    return edgeIter.hasNext();
                }

                @Override
                public EdgeData next() {
                    if (oldMC == mCount)
                        return edgeIter.next();
                    throw new RuntimeException("(edgeIter_next)->There is a change in the graph");
                }
            };
        } catch (RuntimeException r) {
            System.out.println(r);
        } catch (Exception e) {
            System.out.println(e);
        }
        return edgeIter;
    }

    @Override
    public Iterator<EdgeData> edgeIter(int node_id) {
        int oldMC = this.mCount;
        Node n = (Node) nodesMap.get(node_id);
        Iterator<EdgeData> edgeIter = n.getEdgeListOut().iterator();
        try {
            if (edgeIter == null)
                throw new Exception("(edgeIter_node_id)->There is no edge object or the object is not defined or empty.");
            return new Iterator<EdgeData>() {
                @Override
                public boolean hasNext() {
                    return edgeIter.hasNext();
                }

                @Override
                public EdgeData next() {
                    if (oldMC == mCount)
                        return edgeIter.next();
                    throw new RuntimeException("(edgeIter_next)->There is a change in the graph");
                }
            };
        } catch (RuntimeException r) {
            System.out.println(r);
        } catch (Exception e) {
            System.out.println(e);
        }
        return new ArrayList<EdgeData>().iterator();
    }

    //O(k)
    @Override
    public NodeData removeNode(int key) {
        try {
            //Checks if the node exists
            if (nodesMap == null || !nodesMap.containsKey(key))
                throw new Exception("(removeNode)->The node does not exist ");
            //Removes the node from the list of nodes in the graph
            Node temp = (Node) nodesMap.remove(key);
            Map<Point2D, EdgeData> mapIn = temp.getEdgeMapIn();
            Map<Point2D, EdgeData> mapOut = temp.getEdgeMapOut();

            //When deleting a node it is necessary to delete all
            //the edges to which it points and also the edges that point to it
            mapIn.forEach((k, v) -> {
                Point2D p = k;
                int src = (int) p.getX();
                //Delete the edge from the list of edges of the graph
                edgesMap.remove(p);
                Node node = (Node) nodesMap.get(src);
                node.getEdgeMapOut().remove(p);
            });
            //When deleting a node it is necessary to delete all
            //the edges to which it points and also the edges that point to it
            mapOut.forEach((k, v) -> {
                Point2D p = k;
                int dest = (int) p.getY();
                //Delete the edge from the list of edges of the graph
                edgesMap.remove(p);
                Node node = (Node) nodesMap.get(dest);
                node.getEdgeMapIn().remove(p);
            });
            this.mCount++;
            return temp;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Deletes and returns an edge if it exists.
     * If there is no edge then throws an error
     *
     * @param src
     * @param dest
     * @return
     */
    //O(1)
    @Override
    public EdgeData removeEdge(int src, int dest) {
        Point2D p = new Point(src, dest);
        try {
            //Checks if the edge exists
            if (edgesMap == null || !edgesMap.containsKey(p))
                throw new Exception("(removeEdge)->The edge does not exist");
            //Removes the edge from the list of edges in the graph
            EdgeData edge = edgesMap.remove(p);
            //Removes the edge from the node.
            Node node = (Node) nodesMap.get(src);
            node.getEdgeMapOut().remove(p);
            //Removes the edge from the node.
            node = (Node) nodesMap.get(dest);
            node.getEdgeMapIn().remove(p);
            //return edge
            this.mCount++;
            return edge;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Returns the number of nodes
     *
     * @return
     */
    //O(1)
    @Override
    public int nodeSize() {
        //Checks if the nodesMap is not empty
        if (!nodesMap.isEmpty())
            //Returns the number of nodes
            return nodesMap.size();
        return 0;
    }

    /**
     * Returns the number of edges
     *
     * @return
     */
    //O(1)
    @Override
    public int edgeSize() {
        //Checks if the edgesMap is not empty
        if (!edgesMap.isEmpty())
            //Returns the number of edges
            return edgesMap.values().size();
        return 0;
    }

    @Override
    public int getMC() {
        return this.mCount;
    }

    @Override
    public String toString() {
        return "\n\"Edges\": " + edgesMap.values().stream().toList() + ", \n\"Nodes\": " + nodesMap.values().stream().toList() + "\n";
    }
}
