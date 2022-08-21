import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;


/*
leetcode 2268
https://leetcode.com/problems/minimum-number-of-keypresses/
 */

class problem2 {
    public int minimumKeypresses(String s) {
        //use hash map to count the freq
        //put hashmap entry into max heap
        //assign the most frequent one with 1
        
        //step 1: count the frequency
        Map<Character, Integer> map = new HashMap<>();
        
        for(char c: s.toCharArray()){
            
            map.put(c, map.getOrDefault(c,0)+1);
        }
        
        //step2: put into max heap
        Queue<Map.Entry<Character,Integer>> pq = new PriorityQueue<>((a, b)-> (b.getValue() - a.getValue()));
        
        for( Map.Entry<Character,Integer> entry: map.entrySet()){
            
            pq.offer(entry);
        }
        
        
        //step3: pop out the heap, assgin the top 9 to 1, 10 - 18 to 2 and 19 - 26 to 3
        int count = 0, press = 1, totalPress = 0;
        Map.Entry<Character,Integer> temp = null;
        
        while(!pq.isEmpty()){
            
            temp = pq.poll();
            
            if(count/9 < 1){ // 0 - 8, assign to 1
                press = 1;
                totalPress += press* temp.getValue();
            }
            else if(count/9 <2){ //9 - 17
                press = 2;
                totalPress += press* temp.getValue();
            }
            else{
                press = 3;
                totalPress += press* temp.getValue();
            }
            
            count++;
        }
        
        return totalPress;
    }
}