package GUI;
import Departments.Edge;
import Departments.GeoPosition;
import Departments.Graph;
import Departments.Node;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ButtonListener implements ActionListener{
    private Graph_GUI gui;
    private boolean isEnable;

    public ButtonListener(Graph_GUI gui) {
        this.gui = gui;
        isEnable=false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "IsConnected") {
            boolean isConnect = gui.getAlgo().isConnected();
            JOptionPane.showMessageDialog(gui, isConnect ? "Yes the graph is connected" : "No the graph isn't connected");
        }

        if (e.getActionCommand() == "SPDist") {
            int src = 0, dest = 0;
            src=getPoint("Enter Src ");
            dest=getPoint("Enter Dest ");
            Double SPDist = gui.getAlgo().shortestPathDist(src, dest);
            JOptionPane.showMessageDialog(gui, SPDist);
        }

        if (e.getActionCommand() == "Center") {

            gui.repaint();
        }
        if (e.getActionCommand() == "AC Graph") {

            gui.repaint();
        }


        if (e.getActionCommand() == "Tsp") {
            gui.repaint();
        }
        if (e.getActionCommand() == "Color") {
            Iterator it = gui.getAlgo().getGraph().edgeIter();
            Color color = JColorChooser.showDialog(gui, "Change Button Background",null);
            System.out.println(color);
            while (it.hasNext()){
                ((Edge)it.next()).setEdgeColor(color);
            }
            gui.repaint();
        }
        if (e.getActionCommand() == "Draw Node") {
            isEnable=gui.enableButtons();
        }
        if (e.getActionCommand() == "Draw Edge") {
            isEnable=gui.enableButtons();
        }

        if (e.getActionCommand() == "Add Node") {
            int x = 0, y = 0,z=0,nodeNumber=0;
            //לבדוק שהמיקום לא קיים
            x=getNode("Pos X ");
            y=getNode("Pos Y ");
            z=getNode("Pos Z ");
            gui.getAlgo().getGraph().addNode(new Node(new GeoPosition(x, y, z), gui.getAlgo().getGraph().nodeSize()));
            gui.repaint();
        }

        if (e.getActionCommand() == "Add Edge") {
            //לזרוק שגיאות
            int src = 0, dest = 0;
            src=getPoint("Src ");
            dest=getPoint("Dest ");
            gui.getAlgo().getGraph().connect(src, dest, 66);
            gui.repaint();
        }
    }
    //G1=8,G2=0,G3=40
    private int getPoint(String msg){
        int ans=0;
        String input;
        Map nodeMap = ((Graph) gui.getAlgo().getGraph()).getNodesMap();
        do {
            input = JOptionPane.showInputDialog(msg);
            if (input.matches("^[0-9]*$")) {
                ans = Integer.parseInt(input);
            } else {
                JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                continue;
            }
            if (!nodeMap.containsKey(Integer.parseInt(input))) {
                JOptionPane.showMessageDialog(gui, "Src does not exist");
                input = "y";
            }
        } while (!input.matches("^[0-9]*$"));
        return ans;
    }
    private int getNode(String msg){
        int ans=0;
        String input;
        Map nodeMap = ((Graph) gui.getAlgo().getGraph()).getNodesMap();
        do {
            input = JOptionPane.showInputDialog("Enter "+msg);
            if (input.matches("^[0-9]*$")) {
                ans = Integer.parseInt(input);
            } else {
                JOptionPane.showMessageDialog(gui, "Please enter a valid name containing: numbers");
                continue;
            }
            if (nodeMap.containsKey(Integer.parseInt(input))) {
                JOptionPane.showMessageDialog(gui, msg+"does exist");
                input = "y";
            }
        } while (!input.matches("^[0-9]*$"));
        return ans;
    }
}
