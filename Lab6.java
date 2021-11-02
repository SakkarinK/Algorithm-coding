package lab6;

import java.io.*;
import java.util.*;

public class Lab6 {

    public static ArrayList<Graph> read(String filename) throws FileNotFoundException{
        Scanner ss = new Scanner(new FileInputStream(filename));
        String line = ss.nextLine();
        String vertexs = (line.split(" "))[0];
        String edges = (line.split(" "))[1];
        int v = Integer.parseInt(vertexs);
        int e = Integer.parseInt(edges);
        Graph g = new Graph(v);
        ArrayList<Graph> graphs = new ArrayList<Graph>();
        while(v!=0 && e!=0){
        for(int i=0; i<e; i++){
            line = ss.nextLine();
            int v1 = Integer.parseInt((line.split(" "))[0])-1;
            int v2 = Integer.parseInt((line.split(" "))[1])-1;
            int d = Integer.parseInt((line.split(" "))[2]);
            if(d==1){
                g.addEdge(v1, v2);
            }
            if(d==2){
                g.addEdge(v1, v2);
                g.addEdge(v2, v1);
            }
        }
        graphs.add(g);
        line = ss.nextLine();
        vertexs = (line.split(" "))[0];
        edges = (line.split(" "))[1];
        v = Integer.parseInt(vertexs);
        e = Integer.parseInt(edges);
        g = new Graph(v);
    }
        return graphs;
    }
    public static void main(String[] args) throws FileNotFoundException {

        // ArrayList<Graph> graphs = read("/Users/InAdream/Desktop/Algor/Lab6_Finaljingjing/Lab6 Final/case/6.1.txt");
        // ArrayList<Graph> graphs = read("/Users/InAdream/Desktop/Algor/Lab6_Finaljingjing/Lab6 Final/case/6.2.txt");
        // ArrayList<Graph> graphs = read("/Users/InAdream/Desktop/Algor/Lab6_Finaljingjing/Lab6 Final/case/6.3.txt");
        // ArrayList<Graph> graphs = read("/Users/InAdream/Desktop/Algor/Lab6_Finaljingjing/Lab6 Final/case/6.4.txt");
        // ArrayList<Graph> graphs = read("/Users/InAdream/Desktop/Algor/Lab6_Finaljingjing/Lab6 Final/case/Extra6.5.txt");
        ArrayList<Graph> graphs = read("/Users/InAdream/Desktop/Algor/Lab6_Finaljingjing/Lab6 Final/case/Extra6.6.txt");
        for(Graph g : graphs){
            g.printSCCs();}
        }
}

class Graph {

    private int V;   // No. of vertices
    private LinkedList<Integer> adj[]; //Adjacency List
    static int min = 0;//เก็บค่าสูงสุดที่ต้องoutput
    static int m = 0;
    static int line =0;
    static ArrayList<Integer> t = new ArrayList<>();
    static ArrayList<Integer> f = new ArrayList<>();
    static ArrayList<ArrayList<Integer>> forest = new ArrayList<>();

    //Constructor
    Graph(int v) {
        V = v;
        adj = new LinkedList[v];
        f = new ArrayList<>();
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    //Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    // A recursive function to print DFS starting from v
    void DFSUtil(int v, boolean visited[]) {
        // Mark the current node as visited and print it
        visited[v] = true;
        t.add(v);
        f.add(v);
        //System.out.print(v + " ");
        m++;
        if(m > min) {
            min = m;
        }

        int n;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    // Function that returns reverse (or transpose) of this graph
    Graph getTranspose() {
        Graph g = new Graph(V);
        for (int v = 0; v < V; v++) {
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext()) {
                g.adj[i.next()].add(v);
            }
        }
        return g;
    }

    void fillOrder(int v, boolean visited[], Stack stack) {
        // Mark the current node as visited and print it
        visited[v] = true;

        // Recur for all the vertices adjacent to this vertex
        Iterator<Integer> i = adj[v].iterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                fillOrder(n, visited, stack);
            }
        }

        // All vertices reachable from v are processed by now,
        // push v to Stack
        stack.push(new Integer(v));
    }
    void SCC(){
        Stack stack = new Stack();
        line=0;
        forest = new ArrayList<>();

        // Mark all the vertices as not visited (For first DFS)
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        // Fill vertices in stack according to their finishing
        // times
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                fillOrder(i, visited, stack);
            }
        }

        // Create a reversed graph
        Graph gr = getTranspose();

        // Mark all the vertices as not visited (For second DFS)
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }
        // Now process all vertices in order defined by Stack
        while (stack.empty() == false) {
            // Pop a vertex from stack
            int v = (int) stack.pop();
            // Print Strongly connected component of the popped vertex
            if (visited[v] == false) {
                t = new ArrayList<>();
                gr.DFSUtil(v, visited);
                forest.add(t);
                line++;
                m=0;
            }
        }
    }
    

    // The main function that finds and prints all strongly
    // connected components
    void printSCCs() {
        SCC();
        System.out.println("Component = "+line);
        for(int i=0; i<forest.size();i++){
            for(int j=0;j<forest.get(i).size(); j++ ){
                int c= forest.get(i).get(j)+1;
                forest.get(i).set(j, c);
            }
        }
        System.out.println(forest);
        int v,u;
        int num=0;
        while(forest.size()!=1){
                num++;
                v = f.get(f.size()-1); 
                u = f.get(0);
                System.out.println("add line : "+(v+1)+" to "+(u+1));
                addEdge(v,u);
                SCC();
        }
        System.out.println("add line "+num+" times to make SCC");
        forest = new ArrayList<>();
        System.out.println();
    }



}
