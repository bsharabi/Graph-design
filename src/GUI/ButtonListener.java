package GUI;

import Departments.Edge;
import Departments.GeoPosition;
import Departments.Graph;
import Departments.Node;
import api.NodeData;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class ButtonListener implements ActionListener {
    private Graph_GUI gui;
    private boolean isEnable;

    public ButtonListener(Graph_GUI gui) {
        this.gui = gui;
        isEnable = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graph g = (Graph) gui.getAlgo().getGraph();
        if (e.getActionCommand() == "IsConnected") {
            boolean isConnect = gui.getAlgo().isConnected();
            JOptionPane.showMessageDialog(gui, isConnect ? "Yes the graph is connected" : "No the graph isn't connected");
        }

        if (e.getActionCommand() == "SPDist") {
            int src = 0, dest = 0;
            boolean srcFlag = true, destFlag = true;
            String inputSrc = "", inputDest = "";
            do {
                if (srcFlag)
                    inputSrc = JOptionPane.showInputDialog("Enter node source ");
                if (!inputSrc.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                srcFlag = false;
                if (destFlag)
                    inputDest = JOptionPane.showInputDialog("Enter node destination ");
                if (!inputDest.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                destFlag = false;
            } while (srcFlag || destFlag);
            src = Integer.parseInt(inputSrc);
            dest = Integer.parseInt(inputDest);
            Map mapNode = ((Graph) gui.getAlgo().getGraph()).getNodesMap();
            boolean nodeSrcExist = mapNode.containsKey(src);
            boolean nodeDestExist = mapNode.containsKey(dest);
            if (nodeSrcExist && nodeDestExist) {
                Double SPDist = gui.getAlgo().shortestPathDist(src, dest);
                JOptionPane.showMessageDialog(gui, SPDist == -1 ? "no" : SPDist);
            } else
                JOptionPane.showMessageDialog(gui, "Sagi Homo");

        }

        if (e.getActionCommand() == "SPath") {
            int src = 0, dest = 0;
            gui.arrowEnable = false;
            Map nodeMap = ((Graph) gui.getAlgo().getGraph()).getNodesMap();
            nodeMap.forEach((K, V) -> {
                ((Node) V).initDrawColor();
            });
            gui.repaint();
            boolean srcFlag = true, destFlag = true;
            String inputSrc = "", inputDest = "";
            do {
                if (srcFlag)
                    inputSrc = JOptionPane.showInputDialog("Enter node source ");
                if (!inputSrc.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                srcFlag = false;
                if (destFlag)
                    inputDest = JOptionPane.showInputDialog("Enter node destination ");
                if (!inputDest.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                destFlag = false;
            } while (srcFlag || destFlag);
            src = Integer.parseInt(inputSrc);
            dest = Integer.parseInt(inputDest);

            try {
                if (g.getNode(src) == null || g.getNode(dest) == null)
                    throw new Exception("src or dest does not exist");
                List<NodeData> SP = gui.getAlgo().shortestPath(src, dest);
                if (SP == null)
                    throw new Exception("There is no route");
                gui.arrowEnable = true;
                for (int i = 0, n = SP.size(); i < n; i++) {
                    if (i + 1 < n) {
                        int currentNodeNumber = SP.get(i).getKey();
                        int nextNodeNumber = SP.get(i + 1).getKey();
                        ((Node) nodeMap.get(currentNodeNumber)).setVertexState(i == 0 ? Color.pink : Color.CYAN);
                        ((Node) nodeMap.get(nextNodeNumber)).setVertexState(i + 1 == n - 1 ? Color.pink : Color.CYAN);
                        Edge e0 = ((Edge) gui.getAlgo().getGraph().getEdge(currentNodeNumber, nextNodeNumber));
                        Edge e1 = ((Edge) gui.getAlgo().getGraph().getEdge(nextNodeNumber, currentNodeNumber));
                        if (e0 != null) {
                            e0.setEdgeColor(Color.GREEN);
                            e0.setArrowColor(Color.DARK_GRAY);
                        }
                        if (e1 != null)
                            e1.setEdgeColor(Color.GREEN);
                    }

                }
                gui.repaint();
            } catch (Exception ex) {
                System.out.println(ex);
                JOptionPane.showMessageDialog(gui, ex.getMessage());
            }
        }

        if (e.getActionCommand() == "Minimal Tree Spanning") {}

        if (e.getActionCommand() == "Center") {
            Node node = (Node) gui.getAlgo().center();
            if (node != null) {
                node.setVertexState(Color.magenta);
                JOptionPane.showMessageDialog(gui, "center is: "+node);
            }
            gui.repaint();
        }

        if (e.getActionCommand() == "Tsp") {
            Map nodeMap = ((Graph) gui.getAlgo().getGraph()).getNodesMap();
            gui.arrowEnable = false;
            nodeMap.forEach((K, V) -> {
                ((Node) V).initDrawColor();
            });
            gui.repaint();
            List<NodeData> l = new ArrayList();
            String[] input;
            boolean keyFlag = true;
            input = JOptionPane.showInputDialog("Enter node x pos ").split(",");
            int key;
            for (int i = 0; i < input.length; i++) {
                input[i] = input[i].trim();
                if (input[i].matches("^[0-9]*$")) {
                    key = Integer.parseInt(input[i]);
                    if (key >= 0 && !l.contains(key))
                        l.add((NodeData) nodeMap.get(key));
                } else {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid cities numbers " + input[i]);
                    keyFlag = false;
                    break;
                }
            }
            if (keyFlag)
                try {
                    List<NodeData> SP = gui.getAlgo().tsp(l);
                    JOptionPane.showMessageDialog(gui, SP);
                    if (SP == null)
                        throw new Exception("There is no route");
                    gui.arrowEnable = true;
                    for (int i = 0, n = SP.size(); i < n; i++) {
                        if (i + 1 < n) {
                            int currentNodeNumber = SP.get(i).getKey();
                            int nextNodeNumber = SP.get(i + 1).getKey();
                            ((Node) nodeMap.get(currentNodeNumber)).setVertexState(i == 0 ? Color.pink : Color.CYAN);
                            ((Node) nodeMap.get(nextNodeNumber)).setVertexState(i + 1 == n - 1 ? Color.pink : Color.CYAN);
                            Edge e0 = ((Edge) gui.getAlgo().getGraph().getEdge(currentNodeNumber, nextNodeNumber));
                            Edge e1 = ((Edge) gui.getAlgo().getGraph().getEdge(nextNodeNumber, currentNodeNumber));
                            if (e0 != null) {
                                e0.setEdgeColor(Color.GREEN);
                                e0.setArrowColor(Color.DARK_GRAY);
                            }
                            if (e1 != null)
                                e1.setEdgeColor(Color.GREEN);
                        }

                    }
                    gui.repaint();
                } catch (Exception ex) {
                    System.out.println(ex);
                    JOptionPane.showMessageDialog(gui, ex.getMessage());
                }
        }

        if (e.getActionCommand() == "Draw Node") {
            isEnable = gui.enableButtons();
        }

        if (e.getActionCommand() == "Add Node") {
            double x = 0, y = 0, z = 0,num;
            boolean xFlag = true, yFlag = true,numFlag=true;
            String inputX = "", inputY = "",inputNum="";
            do {
                if (xFlag)
                    inputX = JOptionPane.showInputDialog("Enter node x pos ");
                if (!inputX.matches("^[0-9]\\d*(\\.\\d+)?$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                xFlag = false;
                if (yFlag)
                    inputY = JOptionPane.showInputDialog("Enter node y pos ");
                if (!inputY.matches("^[0-9]\\d*(\\.\\d+)?$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                yFlag = false;
                if (numFlag)
                    inputNum = JOptionPane.showInputDialog("Enter node Number ");
                if (!inputNum.matches("^[0-9]\\d*(\\.\\d+)?$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                numFlag = false;
            } while (xFlag || yFlag||numFlag);
            x = Double.parseDouble(inputX);
            y = Double.parseDouble(inputY);
            gui.getAlgo().getGraph().addNode(new Node(new GeoPosition(x, y, 0), gui.getAlgo().getGraph().nodeSize()));
            JOptionPane.showMessageDialog(gui, "successful ");
            gui.repaint();
        }

        if (e.getActionCommand() == "Remove Node") {
            int x;
            String input;
            boolean flag;
            do {
                input = JOptionPane.showInputDialog("Enter node");
                flag = input.matches("^[0-9]*$");
                if (!flag) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                }
            } while (!flag);
            x = Integer.parseInt(input);
            Node nodeRm = (Node) gui.getAlgo().getGraph().removeNode(x);
            if (nodeRm != null) {
                JOptionPane.showMessageDialog(gui, "The node " + nodeRm + "\ndeleted");
                gui.repaint();
            } else {
                JOptionPane.showMessageDialog(gui, "The node does not deleted\nThe node does not exist");
            }
        }

        if (e.getActionCommand() == "Add Edge") {
            int src = 0, dest = 0;
            double w;
            boolean srcFlag = true, destFlag = true, wFlag = true;
            String inputSrc = "", inputDest = "", inputW = "";
            do {
                if (srcFlag)
                    inputSrc = JOptionPane.showInputDialog("Enter node source ");
                if (!inputSrc.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                srcFlag = false;
                if (destFlag)
                    inputDest = JOptionPane.showInputDialog("Enter node destination ");
                if (!inputDest.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                destFlag = false;
                if (wFlag)
                    inputW = JOptionPane.showInputDialog("Enter edge Weight ");
                if (!inputW.matches("^[0-9]\\d*(\\.\\d+)?$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                wFlag = false;
            } while (srcFlag || destFlag || wFlag);
            src = Integer.parseInt(inputSrc);
            dest = Integer.parseInt(inputDest);
            w = Double.parseDouble(inputW);
            boolean success = ((Graph) gui.getAlgo().getGraph()).getNodesMap().containsKey(src) &&
                    ((Graph) gui.getAlgo().getGraph()).getNodesMap().containsKey(dest);
            if (success) {
                gui.getAlgo().getGraph().connect(src, dest, w);
                JOptionPane.showMessageDialog(gui, "successful ");
                gui.repaint();
            } else {
                JOptionPane.showMessageDialog(gui, "The node src or dest does not exist");
            }
        }

        if (e.getActionCommand() == "Remove Edge") {
            int src = 0, dest = 0;
            boolean srcFlag = true, destFlag = true;
            String inputSrc = "", inputDest = "";
            do {
                if (srcFlag)
                    inputSrc = JOptionPane.showInputDialog("Enter node source ");
                if (!inputSrc.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                srcFlag = false;
                if (destFlag)
                    inputDest = JOptionPane.showInputDialog("Enter node destination ");
                if (!inputDest.matches("^[0-9]*$")) {
                    JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                    continue;
                }
                destFlag = false;
            } while (srcFlag || destFlag);
            src = Integer.parseInt(inputSrc);
            dest = Integer.parseInt(inputDest);
            Edge edgeRm = (Edge) gui.getAlgo().getGraph().removeEdge(src, dest);
            if (edgeRm != null) {
                JOptionPane.showMessageDialog(gui, "The edge " + edgeRm + "\ndeleted");
                gui.repaint();
            } else {
                JOptionPane.showMessageDialog(gui, "The edge does not deleted\nThe edge does not exist");
            }
        }

        if (e.getActionCommand() == "Color") {
            Iterator it = gui.getAlgo().getGraph().edgeIter();
            Color color;
            do {
                color = JColorChooser.showDialog(gui, "Change Button Background", null);
            } while (gui.arrowEnable && color != null && color.equals(Color.GREEN));
            while (it.hasNext()) {
                Edge ed = ((Edge) it.next());
                if (ed.getEdgeColor() != Color.GREEN && color != null)
                    ed.setEdgeColor(color);

            }
            gui.repaint();
        }

        if (e.getActionCommand() == "AC Graph") {
            gui.getAlgo().init(new Graph());
            gui.repaint();
        }

    }
}
