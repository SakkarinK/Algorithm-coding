import java.util.Scanner;

public class HamiltionGraph {

    public static void main(String[] args) 
    {
        System.out.print("Enter row: ");
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();

        Graph h = new Graph(num);
        System.out.println("Enter matrix : ");
        for(int i=0 ; i< num ; i++){
            Scanner s = new Scanner(System.in);
            String[] line = s.nextLine().split(" ");
            //System.out.println(Arrays.toString(line));
            h.addG(i, line);
            }
        System.out.println("Matrix : ");
        h.printG();
        System.out.print("input u : ");
        Scanner a = new Scanner(System.in);
        String u = a.nextLine();
        System.out.print("input v : ");
        Scanner b = new Scanner(System.in);
        String v = b.nextLine();
        h.path(u,v);
        h.Hpath();
        h.Hcycle();
        }
    }
