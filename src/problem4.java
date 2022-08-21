import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class problem4 {

    /*
    move data locations
     */

    public static int[] solution (int[] locations, int[] moveFrom, int[] moveTo){

        Set<Integer> server = new HashSet<>();

        for(int i: locations){
            server.add(i);
        }

        for(int i = 0; i< moveFrom.length; i++){

            server.remove(moveFrom[i]);
            server.add(moveTo[i]);

        }

        int[] result = new int[server.size()];
        int c = 0;
        for(int i: server){

            result[c++] = i;
        }

        Arrays.sort(result);

        return result;
    }


    public static void main(String[] args) {
        int[] locations = new int[]{1,7,6,8};
        int[] moveFrom = new int[]{1,7,2};
        int[] moveTo = new int[]{2,9,5};

        int[] result = solution(locations, moveFrom, moveTo);
        System.out.println("Test1");
        for( int i: result){
            System.out.print(i);
            System.out.print(" ");
        }
    }
}
