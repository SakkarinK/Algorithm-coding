import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab9 {

    public static ArrayList<String> Naive(String txt, String pat)
    {
        int M = pat.length();
        int N = txt.length();
        ArrayList<String> ans = new ArrayList<>();
 
        /* A loop to slide pat one by one */
        for (int i = 0; i <= N - M; i++) {
 
            int j;
 
            /* For current index i, check for pattern
              match */
            for (j = 0; j < M; j++)
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
 
            if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                ans.add((i+1)+" LR");
        }

        String reverse = new StringBuffer(pat).reverse().toString();

        for (int i = 0; i <= N - M; i++) {
 
            int j;
 
            /* For current index i, check for pattern
              match */
            for (j = 0; j < M; j++)
                if (txt.charAt(i + j) != reverse.charAt(j))
                    break;
 
            if (j == M) // if pat[0...M-1] = txt[i, i+1, ...i+M-1]
                ans.add((i+M)+" RL");
        }

        return ans;
    }

    static ArrayList<String> KMPSearch(String pat, String txt)
    {
        int M = pat.length();
        int N = txt.length();
        ArrayList<String> ans = new ArrayList<>();
  
        // create lps[] that will hold the longest
        // prefix suffix values for pattern
        int lps[] = new int[M];
        int j = 0; // index for pat[]
  
        // Preprocess the pattern (calculate lps[]
        // array)
        computeLPSArray(pat, M, lps);
        String p="";
        for(int a=0;a<M;a++){
            p=p+lps[a]+" ";
        }
        ans.add(p);
  
        int i = 0; // index for txt[]
        while (i < N) {
            if (pat.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                ans.add((i - j + 1)+" "+"LR");
                j = lps[j - 1];
            }
  
            // mismatch after j matches
            else if (i < N && pat.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }

        String reverse = new StringBuffer(pat).reverse().toString();
        //lps = new int[M];
        //computeLPSArray(reverse, M, lps);
        i = 0; // index for txt[]
        j = 0;
        while (i < N) {
            if (reverse.charAt(j) == txt.charAt(i)) {
                j++;
                i++;
            }
            if (j == M) {
                ans.add((i)+" "+"RL");
                //System.out.println(ans);
                j = lps[j - 1];
            }
  
            // mismatch after j matches
            else if (i < N && reverse.charAt(j) != txt.charAt(i)) {
                // Do not match lps[0..lps[j-1]] characters,
                // they will match anyway
                if (j != 0)
                    j = lps[j - 1];
                else
                    i = i + 1;
            }
        }
        return ans;
    }
  
    static void computeLPSArray(String pat, int M, int lps[])
    {
        // length of the previous longest prefix suffix
        int len = 0;
        int i = 1;
        lps[0] = 0; // lps[0] is always 0
  
        // the loop calculates lps[i] for i = 1 to M-1
        while (i < M) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            }
            else // (pat[i] != pat[len])
            {
                // This is tricky. Consider the example.
                // AAACAAAA and i = 7. The idea is similar
                // to search step.
                if (len != 0) {
                    len = lps[len - 1];
  
                    // Also, note that we do not increment
                    // i here
                }
                else // if (len == 0)
                {
                    lps[i] = len;
                    i++;
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner ss = new Scanner(new FileInputStream("/Users/InADream/Desktop/Algor/lab 9/case/test1.txt"));
        String[] sets = ss.nextLine().split(" ");
        String[] n = ss.nextLine().split(" ");
        int n1 = Integer.parseInt(n[0]);
        int n2 = Integer.parseInt(n[1]);
        String pattern = ss.nextLine().replaceAll(" ", "");
        String text = ss.nextLine().replaceAll(" ", "");

        ArrayList<String> setans1 = Naive(text, pattern);
        System.out.println("naive : ");
        System.out.println(setans1.size());
        for(String a:setans1){
            System.out.println(a);
        }

        System.out.println("--------------------");

        ArrayList<String> setans2 = KMPSearch(pattern, text);
        System.out.println("KMP : ");
        System.out.println(setans2.size()-1);
        for(String a:setans2){
            System.out.println(a);
        }
    }
}
