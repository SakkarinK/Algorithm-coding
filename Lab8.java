import java.text.DecimalFormat;
import java.io.FileNotFoundException;
import java.io.FileInputStream;
import java.util.Scanner;

public class Lab8 {

    static int buy_day, sell_day;
    static int bday, sday;
    static DecimalFormat f = new DecimalFormat("#0.00");

    public static Double[] read(String filename) throws FileNotFoundException{
        Scanner ss = new Scanner(new FileInputStream(filename));
        int day = Integer.parseInt(ss.nextLine());
        Double[] rate = new Double[day];
        while(ss.hasNextLine()){
            String[] r = ss.nextLine().split(" ");
            for(int i = 0; i < r.length; i++){
                rate[i] = Double.parseDouble(r[i]);
            }
        }
        return rate;
    }

    public static void bruteForce(Double[] rate){
        double maxDiff = 0;
        for(int i = 0; i < rate.length; i++){
            for(int j = i; j < rate.length; j++){
                if(rate[j] > rate[i] && (rate[j] - rate[i] > maxDiff)){
                    maxDiff = rate[j] - rate[i];
                    buy_day = i;
                    sell_day = j;
                }
            }
        }
        //System.out.println("BruteForce :");
        System.out.println(buy_day + 1);
        System.out.println(f.format(rate[buy_day]));
        System.out.println(sell_day + 1);
        System.out.println(f.format(rate[sell_day]));
        System.out.println(f.format(maxDiff));
        System.out.println(sell_day - buy_day);
        //System.out.println("--------------------");
    }

    // public static Double findMaxCs(Double[] A, int low, int mid, int high){
    //     Double leftSum = -9999.00;
    //     Double sumL  = 0.00;
    //     Double maxLeft = -1.00;
    //     for(int i = mid; i > 0; i++){
    //         sumL += A[i];
    //         if(sumL > leftSum){
    //             leftSum = sumL;
    //             maxLeft = i;
    //         }
    //     }

    //     Double rightSum = -9999.00;
    //     Double sumR = 0.00;
    //     Double maxRight = -1.00;
    //     for(int j = mid + 1; j < high; j++){
    //         sumR += A[j];
    //         if(sumR > rightSum){
    //             rightSum = sumR;
    //             maxRight = j;
    //         }
    //     }
    //     return(maxLeft,maxRight,leftSum+rightSum);

    // }

    //i edited to fine minDifference
    public static double maxDifference(Double[] r, int start, int end) {
        if (start >= end) {
            return -1;
        }
        int mid = (start + end) / 2;
        double leftDiff = maxDifference(r, start, mid);
        double rightDiff = maxDifference(r, mid + 1, end);
        double maxLeft = getMax(r, start, mid);
        double minRight = getMin(r, mid, end);
        double centerDiff = minRight - maxLeft;
        if(centerDiff<=leftDiff && centerDiff<=rightDiff){
            getMax(r, mid+1, end);
            getMin(r, start, mid);
            if(bday>=sday){
                getMax(r, start,sday);
            }
            getMin(r, bday, end);
        }
        else if(leftDiff<=centerDiff&& leftDiff<=rightDiff){
            getMax(r, start, mid);
            getMin(r, start, mid);
            if(bday>=sday){
                getMax(r, start,sday);
            }
            getMin(r, bday, end);
        }
        else if(rightDiff<=leftDiff && rightDiff<=centerDiff){
            getMax(r, mid+1,end);
            getMin(r, mid+1,end);
            if(bday>=sday){
                getMax(r, start,sday);
            }
            getMin(r, bday, end);
        }
        return Math.min(centerDiff, Math.min(leftDiff, rightDiff));
    }

    public static double getMin(Double[] r, int i, int j) {
        double min = r[i];
        for (int k = i ; k <= j; k++) {
            if (r[k] < min) {
                min = r[k];
                sday = k;
            }

        }
        return min;
    }

    public static double getMax(Double[] r, int i, int j) {
        double max = r[i];
        for (int k = i ; k <= j; k++) {
            if (r[k] > max) {
                max = r[k];
                bday = k;
            }
        }
        return max;
    }

    public static void main(String[] args) throws Exception {
        Double[] r = read("/Users/InADream/Desktop/Algor/lab 8/cae/8.1.txt");
        bruteForce(r);
        System.out.println("---------------");

        System.out.println("Divide and Conquer :");
        int start = 0;
        int end = r.length - 1;
        double interest = maxDifference(r, start, end);
 
        
        System.out.println(bday + 1);
        System.out.println(r[bday]);
        System.out.println(sday + 1);
        System.out.println(r[sday]);
        System.out.println(f.format(interest));
        System.out.println(sday - bday);}
    
}
