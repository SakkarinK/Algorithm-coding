import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Lab7 {
    static ArrayList<ArrayList<Integer>> prob = new ArrayList<>();
    static int pred[][];

    public static int[][] read(String filename) throws FileNotFoundException{
        Scanner ss = new Scanner(new FileInputStream(filename));
        String line = ss.nextLine();
        String vertexs = (line.split(" "))[0];
        String edges = (line.split(" "))[1];
        String pair = (line.split(" "))[2];
        int v = Integer.parseInt(vertexs);
        int e = Integer.parseInt(edges);
        int p = Integer.parseInt(pair);

        //create matrix
        int[][] matrix = new int[v][v];
        while(ss.hasNextLine()){
        for(int i=0; i<e; i++){
            line = ss.nextLine();
            int v1 = Integer.parseInt((line.split(" "))[0])-1;
            int v2 = Integer.parseInt((line.split(" "))[1])-1;
            int dB = Integer.parseInt((line.split(" "))[2]);
            matrix[v1][v2] = matrix[v2][v1] = dB;
        }
        //create list
        prob = new ArrayList<>();
        for(int j=0; j<p; j++){
            line = ss.nextLine();
            int w1 = Integer.parseInt((line.split(" "))[0]);
            int w2 = Integer.parseInt((line.split(" "))[1]);
            ArrayList<Integer>pairs = new ArrayList<Integer>();
            pairs.add(w1);
            pairs.add(w2);
            prob.add(pairs);
            }
        }
        return matrix;
    }    
    public static int[][] FWalgo(int[][] g){
        int V = g.length;
        int weight[][] = new int[V][V];
        int pred[][] = new int[V][V];
        int INF=99999;
        int NIL=0;
        int i, j, k;
        //D-0
        for (i = 0; i < V; i++){
            for (j = 0; j < V; j++){
                if(i==j) {weight[i][j]=0;}
                else if(g[i][j]>0) {weight[i][j] = g[i][j];}
                else {weight[i][j]= INF;}
            }
        }
        //pred-0
        for (i = 0; i < V; i++){
            for (j = 0; j < V; j++){
                if(i!=j & g[i][j]>0) {pred[i][j]= i+1;}
                else {pred[i][j]= NIL;}
            }
        }



        //print D-0
        /*for(int a=0; a<weight.length; a++){
            for(int b=0; b<weight.length; b++){
                System.out.print(weight[a][b]+" ");
            }
            System.out.println();
        }*/
        //print pred-0
        for(int a=0; a<pred.length; a++){
            for(int b=0; b<pred.length; b++){
                System.out.print(pred[a][b]+" ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");

        for(k=0;k<V;k++){
            //create D-k
            for(i=0;i<V;i++){
                for(j=0;j<V;j++){
                    if(weight[i][j]<Math.max(weight[i][k], weight[k][j])){   
                        weight[i][j] = weight[i][j];
                        pred[i][j] = pred[i][j]; 
                    }
                    else if((weight[i][j]>Math.max(weight[i][k], weight[k][j]))){   
                        weight[i][j] = Math.max(weight[i][k], weight[k][j]);
                        pred[i][j] = pred[k][j];
                    }
                }
            }
            //print all D
            /*for(int a=0; a<weight.length; a++){
                for(int b=0; b<weight.length; b++){
                    System.out.print(weight[a][b]+" ");
                }
                System.out.println();
            }
            System.out.println("-------------------------");*/
            for(int a=0; a<weight.length; a++){
                for(int b=0; b<weight.length; b++){
                    System.out.print(pred[a][b]+" ");
                }
                System.out.println();
            }
            System.out.println("-------------------------");
        }
        for(ArrayList<Integer>a : prob){
            int v = a.get(0);
            int u = a.get(1);
            System.out.println(a.get(0) + " -> " + a.get(1));
            System.out.println("Output : " + weight[v-1][u-1]);
            System.out.print("path : " + v + "->");
            printPath(pred, v, u);
            System.out.print(u);
            System.out.println();
            System.out.println("-------------------------");
        }
        return weight;
        
    }
 
    static void printPath(int[][] path, int v, int u)
    {
        if (path[v-1][u-1] == v) {
            return;
        }
 
        printPath(path, v, path[v-1][u-1]);
        System.out.print(path[v-1][u-1] + "->");
    }  

    public static void main(String[] args) throws Exception {
        int[][] matrix = read("/Users/InaDream/Desktop/Algor/lab7/case/7.1.txt");
        // int[][] matrix = read("/Users/InaDream/Desktop/Algor/lab7/case/7.2.txt");
        // int[][] matrix = read("/Users/InaDream/Desktop/Algor/lab7/case/7.3.txt");
        // int[][] matrix = read("/Users/InaDream/Desktop/Algor/lab7/case/7.4.txt");
        // int[][] matrix = read("/Users/InaDream/Desktop/Algor/lab7/case/7_extra1.txt");
        // int[][] matrix = read("/Users/InaDream/Desktop/Algor/lab7/case/7_extra2.txt");
        //print matrix from read
        /*for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix.length; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println("-------------------------");*/
        FWalgo(matrix);

    }
}