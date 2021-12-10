package GUI;

import Departments.Graph;
import api.DirectedWeightedGraph;
import api.DirectedWeightedGraphAlgorithms;
import api.EdgeData;
import api.NodeData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Point2D;
import java.util.Map;

public class Graph_GUI extends JFrame implements ActionListener, Runnable {

    private JPanel sideMenu;
    private DirectedWeightedGraphAlgorithms algo;
    private menuFrame menuframe;
    public boolean buttonF;
    protected GraphCanvas canvas;
    private MenuListen ml;
    private ButtonListener bl;
    private JButton buttons[];
    private List undoEdgesMap;
    private List undoNodesMap;
    private int mCount;

    public Graph_GUI(DirectedWeightedGraphAlgorithms dwa) {
        setTitle("Graph Design");
//        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setSize(1000, 800);
        setMinimumSize(new Dimension(500,500));
        buttonF = false;
        setResizable(true);
        setLocationRelativeTo(null);
        ml = new MenuListen(this);
        bl = new ButtonListener(this);
        this.algo = dwa;
        this.menuframe = new menuFrame(this, algo, ml);
        this.buttons = new JButton[10];
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.pack();
        setComponents();
        setVisible(true);
    }

    public void setComponents() {
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
        sideMenu = new JPanel();
        sideMenu.setAlignmentX(LEFT_ALIGNMENT);
        sideMenu.setMaximumSize(new Dimension(200, 700));
        setSideMenu();
        add(sideMenu);
        this.canvas = new GraphCanvas(this, algo.getGraph());
        this.canvas.setAlignmentX(RIGHT_ALIGNMENT);
        add(canvas);
    }

    private void setSideMenu() {

        sideMenu.setLayout(new GridLayout(10, 1));
        //-------Title button ----------
        buttons[0] = new JButton("IsConnected");
        buttons[1] = new JButton("SPDist");
        buttons[2] = new JButton("SPath");
        buttons[3] = new JButton("Center");
        buttons[4] = new JButton("Tsp");
        buttons[5] = new JButton("Draw Node");
        buttons[6] = new JButton("Draw Edge");
        buttons[7] = new JButton("Add Node");
        buttons[8] = new JButton("Add Edge");
        buttons[9] = new JButton("Color");
        buttons[9] = new JButton("AC Graph");

        //----------Add side menu --------
        for (int i = 0; i < buttons.length; i++) {
            sideMenu.add(buttons[i]);
        }
        //------- addListener to buttons -----
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(bl);
        }
    }

    public DirectedWeightedGraphAlgorithms getAlgo() {
        return algo;
    }

    public boolean enableButtons() {
        for (int i = 0; i < buttons.length; i++) {
            //לסדר את הסגירת כפתורים
            if (buttons[i].getText() == "Draw Node")
                continue;
            if(buttons[i].getText() == "Draw Edge")
                continue;
            buttons[i].setEnabled(buttonF);
        }
        this.buttonF = !this.buttonF;
        return this.buttonF;

    }

    @Override
    public void run() {

    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

}
