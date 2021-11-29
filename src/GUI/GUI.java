package GUI;

import GUI.window;
public class GUI {
    public static void main(String[] args) {
        window n = new window();
        Thread thread1 = new Thread(n, "GUI.GUI.window");
        thread1.start();

    }

//    public static void main(String args[]){
//        JFrame frame = new JFrame("My Departments.Graph");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800,800);
//
//        JMenu fileMenu = new JMenu("File");
//        fileMenu.add(new JMenuItem("New"));
//        fileMenu.add(new JMenuItem("Open"));
//        fileMenu.add(new JMenuItem("Close"));
//
//        JMenu editMenu = new JMenu("Edit");
//        editMenu.add(new JMenuItem("Undo"));
//        editMenu.add(new JMenuItem("Redo"));
//        editMenu.add(new JMenuItem("Cut"));
//
//        JMenuBar menubar = new JMenuBar();
//        menubar.add(fileMenu);
//        menubar.add(editMenu);
//
//        frame.setJMenuBar(menubar);
//        frame.setVisible(true);
//
//
//        JButton button1 = new JButton("Button 1");
//        JButton button2 = new JButton("Button 2");
//        frame.getContentPane().add(button1);
//        frame.getContentPane().add(button2);
//        frame.setVisible(true);
//    }
//
//
//    @Override
//    public void actionPerformed(ActionEvent e) {
//        System.out.println(e.getActionCommand());
//    }
}
