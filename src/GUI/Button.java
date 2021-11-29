package GUI;

import javax.swing.*;
import java.util.ArrayList;

public class Button extends JButton implements Runnable {
    protected JFrame frame;
    private ArrayList<String> buttonList;


    public Button(JFrame frame) {

        this.frame = frame;
        this.buttonList = new ArrayList<>();
    }

    public void addToButton(String name) {
        if (!this.buttonList.contains(name))
            this.buttonList.add(name);
    }

    public void addToButton(String[] names) {
        for (int i = 0; i < names.length; i++)
            if (!this.buttonList.contains(names[i]))
                this.buttonList.add(names[i]);
    }

    @Override
    public void run() {
        while (true) {


        }

    }
}
