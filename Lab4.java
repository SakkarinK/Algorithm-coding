import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class Lab4 {   
    int amount;
    int[] c;
    int[][] sol;
    public Lab4(int amount,int[] c){
        this.amount = amount;
        this.c = c;
        sol = new int[c.length + 1][amount + 1];
    }
    List <List<Integer>> possibleWays = new ArrayList<>();
    List <Integer> currentWay = new ArrayList<>();
    List <Integer> tempmin = new ArrayList<>();
    int min = amount+1;

    public int dynamic(int[] c, int amount) {
        for (int i = 0; i <= c.length; i++) {
            sol[i][0] = 1; 
	}
	for (int i = 1; i <= amount; i++) {
            sol[0][i] = 0; 
	}

	for (int i = 1; i <= c.length; i++) {
            for (int j = 1; j <= amount; j++) {
		if (c[i - 1] <= j) {
                    sol[i][j] = sol[i - 1][j]+ sol[i][j - c[i - 1]];
		} 
                else {
                    sol[i][j] = sol[i - 1][j];
		}
            }
	}
    // for (int i = 0; i < sol.length; i++) {
    //     for (int j = 0; j < sol[i].length; j++) {
    //         System.out.print(sol[i][j] + " ");
    //     }
    //     System.out.println();
    // }
	return sol[c.length][amount];
}

    public int WayToChange(int amount, int startCoinIdx, List<Integer> coinsSoFar) {
        if(startCoinIdx == c.length){
            if(amount == 0){
                possibleWays.add(coinsSoFar);
                System.out.print(coinsSoFar);
                if(coinsSoFar.size() < min){
                    min = coinsSoFar.size();
                    tempmin = coinsSoFar;
                }
            }
            return 0;
        }
        for(int count = 0; (count * c[startCoinIdx]) <= amount; count++){
            List <Integer> temp = new ArrayList<>();
            for(int i = 0; i < coinsSoFar.size(); i++) temp.add(coinsSoFar.get(i));
            for(int i = 0; i < count; i++) temp.add(c[startCoinIdx]);
            WayToChange(amount - (count * c[startCoinIdx]), startCoinIdx + 1, temp);
        }
        return 0;
    }

    public List<Integer> getMin(int amount, int startCoinIdx, List<Integer> coinsSoFar){
        if(sol[c.length][amount] != 0){
            min = 0;
            return Collections.min(possibleWays, Comparator.comparingInt(List::size));
        }
        else{
            return currentWay;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Amount = ");
        int amount = sc.nextInt();
        System.out.print("int c[] = ");
        Scanner sc2 = new Scanner(System.in);
        String[] coins = sc2.nextLine().split(" ");
        int[] c = new int[coins.length];
        for(int i = 0;i < coins.length;i++){
            c[i] = Integer.parseInt(coins[i]);
        }

        Lab4 cc = new Lab4(amount,c);
	    System.out.println("\nWays to make change = " + cc.dynamic(c, amount) + "\n");
        
        List<Integer> countOfCoins = new ArrayList<>(); 
        cc.WayToChange(amount, 0, countOfCoins);
        System.out.println("\n\nMinimum of coin is " + cc.getMin(amount, 0, countOfCoins).size());
        System.out.println(cc.getMin(amount, 0, countOfCoins));
    }
}
