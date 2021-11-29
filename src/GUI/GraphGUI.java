package GUI;

import java.awt.*;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;

public class GraphGUI extends JFrame implements Runnable {
    private Menu menuBar;
    private Button buttons;
    private Canvas graphic;
    private List<String > d= new ArrayList<>();
    private HashMap<String, List<String>> data;
    private String[] menuName ={"File","Edit","Build","Help"};
    private String[] menuNameItem = {
            "New proj",
            "load",
            "new Departments.Graph",
            "Save"
    };


    public GraphGUI() {
        super("Departments.Graph Design");
        data =new HashMap<>();
        d.add("New proj");
        d.add("load");
        d.add("new Departments.Graph");
        d.add("Save");
        data.put("File",d);
        this.getContentPane().setBackground(Color.DARK_GRAY);
        this.setSize(new Dimension(800, 600));
        this.setBackground(Color.DARK_GRAY);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setIconImage(new ImageIcon("src/GUI.GUI.Icons/icons8-graph-60.png").getImage());
        this.setLocationRelativeTo(null);
        this.menuBar = new Menu(this);
//        this.menuBar.addToMenu(menuName);

        this.menuBar.addItemToMenu(data);
        this.setVisible(true);
//        this.buttons = new Button(this);
//        this.buttons.addToButton(buttonName);
    }



    @Override
    public void run() {

    }
}