package GUI;

import api.DirectedWeightedGraphAlgorithms;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class menuFrame{

    private Graph_GUI frame;
    private JMenuBar menubar;
    private DirectedWeightedGraphAlgorithms algo;
    private MenuListen ml;

    public menuFrame(Graph_GUI frame, DirectedWeightedGraphAlgorithms dwa, MenuListen menulis) {
        this.frame = frame;
        this.ml=menulis;
        menubar = new JMenuBar();
        algo=dwa;
        setComponents();
    }

    public void setComponents() {


        JMenu fileMenu = new JMenu("File");
        //------------------------ File -------------------------------
        JMenuItem file = new JMenuItem("Load graph");
        fileMenu.add(file);
        file.addActionListener(ml);

        JMenuItem open = new JMenuItem("save graph");
        fileMenu.add(open);
        open.addActionListener(ml);

        JMenuItem close = new JMenuItem("Close");
        fileMenu.add(close);
        close.addActionListener(ml);

        //-------------------------------------------------------------


        JMenu editMenu = new JMenu("Edit");
        //------------------------ Edit -------------------------------
        editMenu.add(new JMenuItem("Undo"));
        editMenu.add(new JMenuItem("Redo"));
        editMenu.add(new JMenuItem("Cut"));
        //-------------------------------------------------------------


        JMenu helpMenu = new JMenu("Help");
        //------------------------ Help -------------------------------
        JMenuItem help = new JMenuItem("Help Prompt");
        helpMenu.add(help);
        help.addActionListener(ml);

        JMenuItem readme = new JMenuItem("Readme github");
        readme.addActionListener(ml);
        helpMenu.add(readme);
        //-------------------------------------------------------------

        menubar.add(fileMenu);
        menubar.add(editMenu);
        menubar.add(helpMenu);
        frame.setJMenuBar(menubar);
    }


}
