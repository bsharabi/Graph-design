package GUI;
import javax.swing.*;
import java.util.*;

public class Menu extends JMenu implements Runnable {

    private HashMap<String, JMenu> menuList;
    private JFrame frame;
    private JMenuBar menubar;

    public Menu(JFrame frame) {
        this.menuList = new HashMap<>();
        this.menubar = new JMenuBar();
        this.frame = frame;
        this.frame.setJMenuBar(this.menubar);
    }

    public void addToMenu(String name) {
        if (!menuList.containsKey(name)) {
            JMenu temp = new JMenu(name);
            menuList.put(name, temp);
            menubar.add(temp);
        }
    }

    public void addToMenu(String[] names) {
        if (names == null)
            return;
        for (int i = 0, n = names.length; i < n; i++)
            if (!menuList.containsKey(names[i])) {
                JMenu temp = new JMenu(names[i]);
                menuList.put(names[i], temp);
                menubar.add(temp);
            }

    }

    public void addItemToMenu(String nameMenu, String nameItem) {
        boolean contain = menuList.containsKey(nameMenu);
        if (contain) {
            JMenu menu = menuList.get(nameMenu);
            for (int i = 0, n = menu.getItemCount(); i < n; i++) {
                if (menu.getItem(i).getText().equals(nameItem))
                    return;
            }
            menu.add(new JMenuItem(nameItem));
        }
    }

    public void addItemToMenu(String nameMenu, String[] nameItem) {
        boolean contain = menuList.containsKey(nameMenu);
        boolean flag;
        if (contain) {
            JMenu menu = menuList.get(nameMenu);
            for (int nameIndex = 0; nameIndex < nameItem.length; nameIndex++) {
                flag = false;
                for (int i = 0, n = menu.getItemCount(); i < n; i++) {
                    if (menu.getItem(i).getText().equals(nameItem[nameIndex])) {
                        flag = true;
                        break;
                    }
                }
                if (!flag)
                    menu.add(new JMenuItem(nameItem[nameIndex]));
            }
        }
    }

    public void addItemToMenu(HashMap<String, List<String> >menu) {
        menu.forEach((k,v)->{
            if(menuList.containsKey(k)) {
                JMenu temp = menuList.get(k);
            }


        });
    }

    @Override
    public void run() {
        while (true) {


        }

    }


}
