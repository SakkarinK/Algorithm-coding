import java.util.ArrayList;

public class Graph {
    int n;
    int[][] matrix;
    public static ArrayList<String> ans = new ArrayList<>();

    public Graph(int v){
        n = v;
        matrix = new int[n][n];
    }

    public void addG(int l,String[] data){ ///O(n)
        for(int i=0; i<n; i++){
            int o = Integer.parseInt(data[i]);
            matrix[l][i] =  o;
        }
    }

    public boolean haveVertex(String v){

        if (Integer.parseInt(v) > n)
        {
            return false;
        }
        return true;
    }
    public void printG(){ ///O(n^2)
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println(); 
        }
    }



    public void Permutation(String pre, String s) { ///O(nlogn) or O(n)
        int n = s.length();
        if (n == 0) {
            ans.add(pre);
        } else {
            for (int i = 0; i < n; i++) {
                Permutation(pre + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n));
            }

        }
    }

    public void PrePer(String s) {
        Permutation("", s);
    }

    public static String subPath(String u, String v, String s) {
        int p1, p2;
        p1 = s.indexOf(u);
        p2 = s.indexOf(v);
        if (p1 < p2) {
            return s.substring(p1, p2 + 1);
        } else {
            return "";
        }
    }
    public boolean checkPath(String s){
        if (s.length() == 0) {
            return false;
        }
        String walk[] = s.split("");
        int walk2[] = new int[s.length()];
        int v1, v2;
        for (int i = 0; i < walk.length; i++) {
            walk2[i] = Integer.parseInt(walk[i]) - 1;
        }
        for (int i = 0; i < walk2.length - 1; i++) {
            v1 = walk2[i];
            v2 = walk2[i + 1];
            if (matrix[v1][v2] == 0) {
                return false;
            }
        }
        return true;
    }

    public void path(String u,String v){
        if(this.haveVertex(u) && this.haveVertex(v)){
            String pt = "";
            //Send String to permutate
            for (int i = 0; i < n; i++) {
                pt += i + 1;
            }
            PrePer(pt);
            ArrayList<String> solp = new ArrayList<>();
            for(int i = 0; i < ans.size(); i++){
            String str1 = subPath(u, v, ans.get(i));
            if (checkPath(str1)) {
                boolean redundant = false;
                for (int j = 0; j < solp.size(); j++) {
                    if (solp.get(j).equals(str1)) {
                        redundant = true;
                    }
                }
                if (!redundant) {
                    solp.add(str1);
                }
            }
            }
            System.out.println("Path : "+solp.toString());
            System.out.println("Number of paths :  "+solp.size());
        }
        else {System.out.println("No path");}
    }


    public void Hpath(){
        ArrayList<String> solhp = new ArrayList<>();
        for (int i = 0; i < ans.size(); i++) {
            String str2 = ans.get(i);
            if (checkPath(str2)) {
                solhp.add(str2);
            }
        }
        System.out.println("Hamiltonian path : "+solhp.toString());
        System.out.println("Number of Hamiltonian paths :  "+solhp.size());
    }


    public void Hcycle(){
        ArrayList<String> solc = new ArrayList<>();
        for (int i = 0; i < ans.size(); i++) {
            String str3 = ans.get(i);
            if (checkPath(str3)) {
                if(checkPath(str3 + str3.charAt(0)))
                    solc.add(str3 + str3.charAt(0));
            }
        }
        System.out.println("Hamiltonian cycles :" + solc);
        System.out.println("Number of Hamiltonian cycle :  "+solc.size());
    }

}