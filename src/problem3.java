public class problem3 {

    public static int searchWordResultWord(String search, String result){
        //two pointers
        int p1 = 0, p2 = 0; //p1-search, p2-result

        while(p1 < search.length()){

            if(search.charAt(p1) == result.charAt(p2)){
                p1++;
                p2++;
            }
            else{
                p1++;
            }
        }

        return result.length() - p2;
    }

    public static void main(String[] args) {
        String search = "armaze", result = "amazon";
        int result1 = searchWordResultWord(search, result);
        System.out.println("Test1 result 2");
        System.out.println(result1);

    }
}
