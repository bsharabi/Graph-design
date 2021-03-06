package GUI;
import javax.swing.*;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HelpPrompt extends JFrame implements ActionListener {

    private boolean isEnable;
    public HelpPrompt() {
        setSize(700,700);
        setTitle("Help");
        isEnable=false;
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setInformation();
        setVisible(true);
    }

    private void setInformation() {
        setLayout(new GridLayout(1,1));
        JTextPane helpInformation = new JTextPane();
        helpInformation.setEditable(false);
        helpInformation.setFont(new Font("Courier", Font.BOLD, 14));
        helpInformation.setBackground(Color.WHITE);
        StyledDocument doc = helpInformation.getStyledDocument();
        SimpleAttributeSet center = new SimpleAttributeSet();
        StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
        doc.setParagraphAttributes(0, doc.getLength(), center, false);
        helpInformation.setText("\n\n\n\nWelcome to the Graph GUI !\n"
                + "This program will allow you to make your own graph.\n"
                + "Here are some of the following features that this program has.\n\n"
                + "Add Vertex: Adds a Vertex to the screen\n"
                + "(Click anywhere on the canvas to add a vertex)\n\n"
                + "Add Edge: Adds an Edge between two vertices\n"
                + "(In order to add an edge, two vertices must be present. Select one vertex "
                + "[this vertex will turn green]. Then "
                + "select another vertex and it will create an edge)\n\n"
                + "Move Vertex: Moves the selected vertex\n"
                + "(Click on any vertex [this vertex will turn green] and then click anywhere on the screen"
                + " to move the vertex)\n\n"
                + "Shortest Path: Finds the shortest path between 2 vertices\n"
                + "(Click on any two vertices and it will calculate the shortest path between those 2 vertices)\n\n"
                + "Change Weight: Changes the weight of an edge\n"
                + "(In order to change the weight of an edge, there must be an edge between two vertices. To"
                + " change the weight of the edge, enter a number in the TextField."
                + " Then click on the two vertices that have the edge you want to change)\n\n"
                + "Add All Edges: Adds all possible edges with the present vertices (Creates a complete graph)\n\n"
                + "Random Weights: Creates random weights between 1-10 for all edges on the screen\n\n"
                + "Minimal Spanning Tree: Creates a minimal spanning tree for the current graph (NOTE: A connected graph"
                + " must be present and all edges must have a weight before you can click on this)");
        add(helpInformation);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
