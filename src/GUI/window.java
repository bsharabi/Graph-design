package GUI;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Canvas;
import java.awt.Image.*;
import java.io.File;

public class window extends Canvas implements ActionListener, MenuListener,Runnable {
    JFrame frame;
    public window() {

        JFrame frame = new JFrame("Departments.Graph");
        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.getGlassPane().setBackground(Color.DARK_GRAY);
        frame.getLayeredPane().setBackground(Color.DARK_GRAY);
        frame.getRootPane().setBackground(Color.DARK_GRAY);
        frame.setFont(new Font("Ariel", Font.ITALIC, 9));
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        ImageIcon img = new ImageIcon("src/GUI.GUI.Icons/icons8-graph-60.png");
        frame.setIconImage(img.getImage());


//        Canvas canvas =new Canvas();
//        canvas.setSize(400, 400);
//        frame.add(canvas);
//        frame.pack();
//        frame.setVisible(true);

        Container contPane = frame.getContentPane();
        contPane.setLayout(null);

        JButton button = new JButton("algo 1");
        button.setLocation(30, 30);
        button.setSize(90, 20);
        contPane.add(button);
        button.setFont(new Font("Ariel", Font.ITALIC, 12));

        JButton button1 = new JButton("algo 2");
        button1.setLocation(30, 60);
        button1.setSize(90, 20);
        button1.setFont(new Font("Ariel", Font.ITALIC, 12));
        contPane.add(button1);

        button.addActionListener(this);
        button1.addActionListener(this);


        JMenu fileMenu = new JMenu("File");
        JMenuItem a =new JMenuItem("New graph");
        fileMenu.add(a);
        a.setIcon(new ImageIcon("src/GUI.GUI.Icons/icons8-folder-24.png"));
        fileMenu.add(new JMenuItem("Open file"));
        fileMenu.add(new JMenuItem("Close"));
        fileMenu.setIcon(new ImageIcon("src/GUI.GUI.Icons/icons8-file-24.png"));


        a.addActionListener(this);
        JMenu editMenu = new JMenu("Edit");
        editMenu.add(new JMenuItem("Undo"));
        editMenu.add(new JMenuItem("Redo"));
        editMenu.add(new JMenuItem("Cut"));

//
        JMenuBar menubar = new JMenuBar();
        menubar.add(fileMenu);
        menubar.add(editMenu);
        frame.setJMenuBar(menubar);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getActionCommand()=="New graph") {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Hello");
//            FileSystemView.getFileSystemView().getSystemIcon(new file )
            chooser.setCurrentDirectory(new java.io.File("./"));
            chooser.setSelectedFile(new File(""));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            // chooser.setAcceptAllFileFilterUsed(false);
            if (chooser.showOpenDialog(this) == JFileChooser.OPEN_DIALOG) {
                File f =chooser.getSelectedFile();
                System.out.println(f);
            } else {
                // do when cancel
            }
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        System.out.println(e);
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println(e);
    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println(e);
    }

    @Override
    public void run() {
        System.out.println("f");
        while(true) {
            if (frame != null){
                frame.getContentPane().setBackground(Color.BLACK);
                System.out.println(frame.getTitle());}
        }
//        SwingUtilities.updateComponentTreeUI(frame);
    }
}
