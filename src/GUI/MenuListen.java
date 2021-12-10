package GUI;

import api.DirectedWeightedGraph;

import javax.swing.*;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class MenuListen implements ActionListener, MenuListener {

    private Graph_GUI gui;

    public MenuListen(Graph_GUI gui) {
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getActionCommand() == "Help Prompt")
            new HelpPrompt();
        if (e.getActionCommand() == "Readme github")
            if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE))
                try {
                    Desktop.getDesktop().browse(new URI("http://www.github.com/bsharabi"));
                } catch (IOException ex) {
                    ex.printStackTrace();
                } catch (URISyntaxException ex) {
                    ex.printStackTrace();
                }

        if (e.getActionCommand() == "Close") {
            gui.dispose();
        }
        if (e.getActionCommand() == "Load graph") {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Hello");
            chooser.setCurrentDirectory(new java.io.File("./src/data"));
            chooser.setSelectedFile(new File(""));
            chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
            if (chooser.showOpenDialog(gui) == JFileChooser.OPEN_DIALOG) {
                File f = chooser.getSelectedFile();
                gui.getAlgo().load(f.getPath());
                gui.repaint();

            } else {
                JOptionPane.showMessageDialog(gui, "Sorry ");
            }
        }
        if (e.getActionCommand() == "save graph") {
            //לבדוק ששם הקובץ לא קיים
            String input = JOptionPane.showInputDialog("Enter name ");
            gui.getAlgo().save("src/data/" + input + ".json");
        }
    }

    @Override
    public void menuSelected(MenuEvent e) {
        System.out.println("menuSelected");
    }

    @Override
    public void menuDeselected(MenuEvent e) {
        System.out.println("menuDeselected");

    }

    @Override
    public void menuCanceled(MenuEvent e) {
        System.out.println("menuCanceled");
    }
}
