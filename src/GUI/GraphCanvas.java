package GUI;

import Departments.Edge;
import Departments.GeoPosition;
import Departments.Graph;
import Departments.Node;
import api.DirectedWeightedGraph;
import api.EdgeData;
import api.GeoLocation;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.util.Iterator;


public class GraphCanvas extends JPanel implements MouseListener, Runnable {

    private Graph_GUI gui;
    private double minX, maxX, minY, maxY;
    private double phi;
    private int barb;
    //----------------------------- constructor -----------------------------------

    public GraphCanvas(Graph_GUI frame, DirectedWeightedGraph gra) {
        this.gui = frame;
        phi = Math.toRadians(25);
        barb = 25;
        super.setBackground(Color.lightGray);
        super.addMouseListener(this);
    }

    //-------------------------------- paintComponent -------------------------------

    //O(nodeSize*edgeSizeNode)
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        findMinMaxPos();

        double ABSx = Math.abs(minX - maxX);
        double ABSy = Math.abs(minY - maxY);
        double sX = (getWidth() / ABSx) * 0.9;
        double sY = (getHeight() / ABSy) * 0.9;

        Graph graph = (Graph) gui.getAlgo().getGraph();
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        Iterator<NodeData> ItNode = gui.getAlgo().getGraph().nodeIter();

        //O(nodeSize)
        while (ItNode.hasNext()) {
            Node node = (Node) ItNode.next();
            g2.setColor(node.getVertexState());
            double x = (node.getLocation().x() - minX) * sX * 0.97 + 30;
            double y = (node.getLocation().y() - minY) * sY * 0.97 + 30;
            node.setLocation(new GeoPosition(x,y,0));
            node.setVisualVertex(new Ellipse2D.Double(x - 9, y - 9, 20, 20));
            node.setPointDraw(new Point((int) x, (int) y));
            g2.fill(node.getVisualVertex());
        }

        g2.setStroke(new BasicStroke(5));

        ItNode = graph.nodeIter();
        Iterator<EdgeData> ItEdge = gui.getAlgo().getGraph().edgeIter();
        while (ItEdge.hasNext()) {
            Edge ed = (Edge) ItEdge.next();
            ed.setLineDraw(null);
        }
        while (ItNode.hasNext()) {
            Node nodeSrc = (Node) ItNode.next();
            Iterator edgeIter = nodeSrc.getEdgeListOut().iterator();
            //O(edgeSizeNode)
            while (edgeIter.hasNext()) {
                Edge edge = (Edge) edgeIter.next();
                //O(1)
                Point sw = (Point) nodeSrc.getPointDraw();
                Node destNode = (Node) graph.getNode(edge.getDest());
                Point ne = (Point) destNode.getPointDraw();
                Point2D pointDest = edge.getP();
                Line2D line = new Line2D.Double(sw, ne);
                Edge edgeD = ((Edge) nodeSrc.getEdgeMapIn().get(pointDest));
                if (edgeD == null) {
                    edge.setLineDraw(line);
                    g2.setColor(edge.getEdgeColor());
                    g2.draw(edge.getLineDraw());
                    drawArrowHead(g2, ne, sw, Color.BLACK, edge.getWeight());
                    continue;
                }
                if (edge.getLineDraw() == null && nodeSrc.getEdgeMapIn().containsKey(pointDest) && ((Edge) nodeSrc.getEdgeMapIn().get(pointDest)).getLineDraw() != null) {
                    edgeD.setLineDraw(line);
                } else {
                    edge.setLineDraw(line);
                    g2.setColor(edge.getEdgeColor() == edgeD.getEdgeColor() ? Color.blue : Color.CYAN);
                    g2.draw(edge.getLineDraw());
                }

                drawArrowHead(g2, ne, sw, Color.BLACK, edge.getWeight());
            }
        }
    }
    //------------------------------ Functions ---------------------------------

    private void findMinMaxPos() {
        DirectedWeightedGraph graph = gui.getAlgo().getGraph();
        minX = Double.MAX_VALUE;
        maxX = Double.MIN_VALUE;
        minY = Double.MAX_VALUE;
        maxY = Double.MIN_VALUE;
        Iterator<NodeData> iterNode = graph.nodeIter();
        while (iterNode.hasNext()) {
            NodeData node = iterNode.next();
            GeoLocation pos = node.getLocation();
            if (pos.x() < minX)
                minX = pos.x();
            if (pos.x() > maxX)
                maxX = pos.x();
            if (pos.y() < minY)
                minY = pos.y();
            if (pos.y() > maxY)
                maxY = pos.y();
        }
    }

    private void drawArrowHead(Graphics2D g2, Point tip, Point tail, Color color, double w) {
        g2.setPaint(color);
        double dy = tip.y - tail.y;
        double dx = tip.x - tail.x;
        double theta = Math.atan2(dy, dx);
        //System.out.println("theta = " + Math.toDegrees(theta));
        double x, y, rho = theta + phi;
        for (int j = 0; j < 2; j++) {
            x = tip.x - barb * Math.cos(rho);
            y = tip.y - barb * Math.sin(rho);
            g2.draw(new Line2D.Double(tip.x, tip.y, x, y));
            rho = theta - phi;
        }
//        g2.setFont(new Font("Dialog", Font.BOLD, 18));
//        g2.drawString(MessageFormat.format("{0,number,#.##}",+w), (tip.x+tail.x) , ((tip.y+tail.y)));

    }

    //-------------------------------------- Override ------------------------------------
    @Override
    public void mouseClicked(MouseEvent e) {
        double x = e.getX();
        double y = e.getY();

        if (gui.buttonF) {
            System.out.println(x + "," + y);
            gui.getAlgo().getGraph().addNode(new Node(new GeoPosition(x, y, 0), gui.getAlgo().getGraph().nodeSize()));
            gui.repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void run() {

    }
}
