import Departments.Edge;
import Departments.JsonReader;
import GUI.*;
import api.EdgeData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {
//        new Thread(new GraphGUI(), "GUI.GUI-D").start();
        Map<Point2D,String> map =new HashMap<>();
//        System.out.println(map);
//        Point2D p =new Point(1,5);
//        Point2D p1 =new Point(1,5);
//        Point2D p3 =new Point(1,4);
//        map.put(p,"1");
//        map.put(p,"2");
        JsonReader.getJson("src/data/G1.json");
//        System.out.println(p.getX());
//        p.setLocation(2,5);
//        System.out.println(p.getX());
//        System.out.println(Objects.equals(p3,p1));
//        System.out.println(map.get(p1));
        Gson gson = new Gson();

        try {

            System.out.println("Reading JSON from a file");
            System.out.println("----------------------------");

            BufferedReader br = new BufferedReader(
                    new FileReader("src/data/G1.json"));

            //convert the json string back to object
            Edge countryObj = gson.fromJson(br, Edge.class);

            System.out.println("Name Of Country: "+countryObj);




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}







