import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class problem1 {
    //
    /*Question: A group of k consecutive months is said to be observable if no two months in the group have the same
    stock price. In other word, all the prices in the period are distinct. The sum of stock prices of an observable group
    of months is called the cumulative observable sum. Given the price of Amazon stock for n months, find the maximum
    possible cumulative observable sum among all the observable groups of months. If there is no observable group, return
    -1

    Example:
    stockPrice = [1,2,7,7,4,2,6], k = 3
    return 14
    * */

    //prefix sum +sliding window

    public static int solution(int[] stockPrice, int k) {

        //prefix sum
        int sum = 0;
        Map<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(-1, 0);

        for(int i = 0; i< stockPrice.length; i++){

            sum += stockPrice[i];
            prefixSum.put(i, sum);
        }

        //sliding window, use hashset to check if there are duplicate prices
        Set<Integer> set = new HashSet<>();
        int left = 0, right = 0, maxSum = -1; //no observable group

        while(right < stockPrice.length){

            while(set.contains(stockPrice[right])&& left!=right){ //duplicate price, move left pointer to where the right pointer is

                set.remove(stockPrice[left]);
                left++;
            }
            set.add(stockPrice[right]);

            if(right - left + 1 == k){

                maxSum = Math.max(maxSum, prefixSum.get(right) - prefixSum.get(left-1));
                set.remove(stockPrice[left]);
                left++;
            }
            right++;
        }

        return maxSum;
    }


    public static void main(String[] args) {
        int[] arr1 = new int[]{1,2,7,7,4,3,6};
        int result1 = solution(arr1, 3);
        System.out.println("Test1 result 14");
        System.out.println(result1);

        int[] arr2 = new int[]{7,7,7,7,7,7,7,7};
        int result2 = solution(arr2, 3);
        System.out.println("Test1 result -1");
        System.out.println(result2);
    }
}
