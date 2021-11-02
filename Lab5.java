package al_5;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class Point {
	double x, y;
 
	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point findCentroid(List<Point> points) {
    	int x = 0;
    	int y = 0;
    	for (Point p : points) {
        	x += p.x;
        	y += p.y;
    	}
    	Point center = new Point(0, 0);
    	center.x = x / points.size();
    	center.y = y / points.size();
    	return center;
	}
	// vertices must be clockwise or anti clockwise ordered
	public Point[] sortVerticies(Point[] points) {
    	// get centroid
		List<Point> lp = new ArrayList<>();
		for(int i = 0; i<points.length; i++){
			lp.add(points[i]);
		}
    	Point center = findCentroid(lp);
    	Collections.sort(lp, (a, b) -> {
        	double a1 = (Math.toDegrees(Math.atan2(a.x - center.x, a.y - center.y)) + 360) % 360;
        	double a2 = (Math.toDegrees(Math.atan2(b.x - center.x, b.y - center.y)) + 360) % 360;
        	return (int) (a2 - a1);
    	});
		for(int i = 0; i < points.length; i++){
			points[i] = lp.get(i);
		}
    	return points;
	}
}

public class MCT {
    
    static void printSolution(Point[] points, int[][] s, int i, int j) {
		if(j - i < 2) return;
		printSolution(points, s, i, s[i][j]);
		System.out.println("(" + i + "," + s[i][j] + "," + j + ") ");
		printSolution(points, s, s[i][j], j);
	}
 
	static double dist(Point p1, Point p2) {
		return Math.sqrt((p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y));
	}
 
	static double perimeter(Point[] points, int i, int k, int j) {
		Point p1 = points[i], p2 = points[k], p3 = points[j];
		return dist(p1,p2) + dist(p2,p3) + dist(p3,p1);
	}

	public static double[][] read(String filename) throws FileNotFoundException{
        Scanner ss = new Scanner(new FileInputStream(filename));
        String row = ss.nextLine();
        double testcase[][] = new double[(Integer.parseInt(row)) + 1][2];
		testcase[0][0] = Integer.parseInt(row);
        int j = 1;
        while(ss.hasNext()){
            row = ss.nextLine();
            String[] news = row.split(" ");
            for(int i = 0; i < news.length; i++){
                testcase[j][i] = Double.parseDouble(news[i]);
            }
            j++;
        }
        return testcase;
    }
 
	static double getMCT(Point[] points) {
		int n = points.length;
		if(n < 3){
            System.out.println("Error, not enough vertices.");
		    return 0;
		}    
		double[][] cost = new double[n][n];
		int[][] s = new int[n][n];
 
		for(int L = 0; L < n; L++) {
            for(int i = 0; i < n - L; i++) {
				int j = i + L;
                if(j - i < 2) cost[i][j] = 0;
				else {
                    cost[i][j] = Double.MAX_VALUE; //Min
			    	//cost[i][j] = 0; //MAX
			    	for(int k=i+1;k<j;k++) {
						double q = cost[i][k] + cost[k][j] + perimeter(points,i,k,j);
						if(q < cost[i][j]) {
                            cost[i][j] = q;
	   			    		s[i][j] = k;
						}
			    	}
				}
            }
		}
				// minimum cost table
                // for(int i = 0; i < points.length; i++){
                //     for(int j = 0; j < points.length; j++){
                //         System.out.print(cost[i][j] + " ");
                //     }
                // System.out.println();
                // }               
                // System.out.println();
                
				// minimum k table
                // for(int i = 0; i < points.length; i++){ 
                //     for(int j = 0; j < points.length; j++){
                //         System.out.print(s[i][j] + " ");
                //     }
                // System.out.println();
                // }

		System.out.println("Solution:");
		printSolution(points, s, 0, n-1);
		// for(int a=0;a<points.length;a++){
		// 	System.out.print(a + " : " + "(" + points[a].x + "," + points[a].y + ") " + "  ");
		// }
		System.out.println();
		System.out.print("Output : ");
		return cost[0][n-1];
	}
 
	static void printArray(Point[] arr) {
		for(Point p : arr)
			System.out.print("(" + p.x + "," + p.y + ") ");
		System.out.println();
	}
    public static void main(String[] args) throws FileNotFoundException {
		// input by keyboard
		// System.out.print("Enter vertex : ");
		// Scanner sc = new Scanner(System.in);
		// int n = sc.nextInt();
		// Point[] points = new Point[n];
		// for(int count = 0 ; count<n ; count++){
		// 	System.out.print("vertex "+count+" : ");
		// 	Scanner ss = new Scanner(System.in);
        //     String[] c = ss.nextLine().split(" ");
        //     Point p = new Point(Double.parseDouble(""+c[0]),Double.parseDouble(""+c[1]));
		// 	points[count] = p;

		//input by file
		double[][] r = read("/Users/InADream/Desktop/Algor/lab 5/AL_5/case/1.1.txt");
		//double[][] r = read("/Users/InADream/Desktop/Algor/lab 5/AL_5/case/1.2.txt");
		//double[][] r = read("/Users/InADream/Desktop/Algor/lab 5/AL_5/case/2.1.txt");
		//double[][] r = read("/Users/InADream/Desktop/Algor/lab 5/AL_5/case/2.2.txt");
		//double[][] r = read("/Users/InADream/Desktop/Algor/lab 5/AL_5/case/3.txt");
		//double[][] r = read("/Users/InADream/Desktop/Algor/lab 5/AL_5/case/4.txt"); // wrong case, does not order
		//double[][] r = read("/Users/InADream/Desktop/Algor/lab 5/AL_5/case/5.txt");
		//double[][] r = read("/Users/InADream/Desktop/Algor/lab 5/AL_5/case/6.txt");
		int n = (int)r[0][0];
		System.out.println("\nNumber of Vertices : " + n);
		Point[] points = new Point[n];
		for(int count = 0 ; count < n ; count++){
            Point p = new Point(r[count + 1][0],r[count + 1][1]);
			points[count] = p;
        }
		System.out.println("Vertices : ");
		printArray(points); 
		System.out.println("\n"); 
		System.out.format("%.4f",getMCT(points));
		System.out.println("\n");
    }
}
