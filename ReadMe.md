# Graph design
***
Graph design is a software tool for directed and undirected graphs. it gives the user the ability to see the graph
visually, get information about the graph and analyze it. all the information based on many algorithms and calculations
simple and complicated together to give the best complexity possible. Graph design gives Graph theory students a new way
to study and understand intricate graphs faster simpler and get productive.

## Technologies

- Intellij idea 2020.2.3
- Junit 5.7
- Jason reader

## Libraries

org.json.simple.JSONArray; org.json.simple.JSONObject; org.json.simple.parser.JSONParser;
org.json.simple.parser.ParseExcep java.awt.*; java.awt.geom.Point2D; java.io.FileNotFoundException; java.io.FileReader;
java.io.FileWriter; java.io.IOException; java.util.*; java.util.List;

## Structure

Graph design represents each dimension of the graph individually from micro to macro.

#### Geolocation:

this is the microest dimension of the graph and it represented a vertex coordinates on the coordinates system. each
GeoLocation obj contains x,y,z represent the location as(x,y,z);

#### Node:

the class represnt each vertex on the graph. all the Node in the graph hold ther Geolocation, id and, map of pointers to
the Outedges, and other fields for the gui;

#### Edge

Edge as it souned represnt the edges of the graph, evry edge object contains the src and dest node, weight for
algorithems usage.

#### Graph

the macroest dimnsion of the graph is of course the graph itself, it contains map for the vertexex and the edges.
syncing with the gui the Graphe class hold all the functions responsibles to the user desires.

#### AlgoDwg

this is the core class of Graph desighn. it contains multyple algorithems based on well known Graph tehory algoritems
like dirkjstra DFS and many more, the main methood of this class is to disassemble those algorithmes to smaller functuin
in way they can use each other's information and sync with each other,what make Graphe Desighn better quicker and
simpler.the whole class outputs is based on the same results format so each function can help muliple answers for
diffent user requests. the main algorthems are:

-Sageakstra: it based on dirkjstra's algorithem but uses a better DFS to initilize the vertex weight's use a priorty que
to make sure it always takes the shortest way.

-SagiCp:  another greedy based algorithem who use "Tsp" obj to hold a list of node that contains the shortest path
between two nodes,and a distance represnted as doubel, for each Node we create a Priority que of "Tsp" each one for a
differnt "city", so it "actully" create a new Graph only with the cities we want to visit while the other Nodes connects
them are represnted in the weight of the shortest distance between them.

-DFS: the well known classic DFS algorithm as we used to chack connection inside the Graph with deep reqursive entrce.
the DFS algoirthem gave us a simple structure for other calcuatins in the project like how we initialize the weiget for each
Node and in what order of Node we should go first.

## guiAva

Guiava is our gui for this project, it works on another thread so it comunicate with all the other structure withount
makeing the user wait to long, another early thought that made Graph design the best tool for graph theory students.

## investigation

before we started to plan the algorithm the class ant the gui structure we made some internet reserche about the current
ways to calcuate this graph's feature and mathematical proofs the will help us to make simpler calculations with the best
complexety we can.

another thing we did was to modulise the gui and the calculations together, so they will sync with each other and will
shows the results smooth and clean, we made the user interface in a simple good-looking way that every student will fill
comfortable and easy to use. the good communication between those two structure is what makes Graph design so good and
different from other software's who try's to do the same quests.

## usage
***

in order to start works with Graph design create a jason file at the source whice you installed the softwere . in the
jason file you need to fill the vertex and the edges by the follwing format:

```json
{
  "Edges": [
    {
      "src": 0,
      "w": 1.3118716362419698,
      "dest": 16
    }
  ],
  "Nodes": [
    {
      "pos": "35.19589389346247,32.10152879327731,0.0",
      "id": 0
    }
  ]
}
```

once Graph desghin will detect the file it will lode it and the gui will start with futre instructins of how to use, un
any case you get complicated with the gui you can press the "help" butten at the left side of the screen for more
information.

### requierments
***

Graph desghin works on windows,linux and mac. it requierse a java base ide to run the project. the hardwere requiermnts
are very basic and doe not require higely advandce cpu or gpu.

### Time running
***


### UML
***
![alt text](src/UML.png)






