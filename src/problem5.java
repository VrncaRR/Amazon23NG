import java.util.*;

public class problem5 {
    /*
    1.regiser login and logout （hashmap处理，分清情况讨论，
    注意logout的时候输入没有psw的边界情况，容易出现指针越界）
写一个简单的api，有三个功能 register，log in，log out。
register的时候要输入name和password，如果这个用户已经register过了要
返回username already exists，没有的话返回registered successfully；
log in时也要name和password，如果该name并没有register或者已经logged in，
或者password错误，要返回log in unsuccessful，如果都满足就返回logged in successfully；
最后是log out，也是很直观的逻辑，正常的话返回成功，
没有register或者没有log in的name要返回log out失败。这道题不是要你去写class，
而是一个函数，通过输入a list of strings然后你自己去parse出以上三个指令。
     */

    public  List<String> implementAPI(List<String> logs){

        List<String> output = new ArrayList<>();
        Map<String, String > register = new HashMap<>();
        Set<String> login = new HashSet<>();

        for(String log: logs){

            String[] input = log.split(" ");
            helper(input,register,login,output);

        }
        return output;
    }
    private void helper(String[] input, Map<String, String> register, Set<String> login, List<String> output){

        final String USER_ALREADY_EXISTS = "Username already exists";
        final String REGISTERED_SUCCESSFULLY = "Registered Successfully";
        final String LOGIN_SUCCESSFULLY = "Logged In Successfully";
        final String LOGIN_UNSUCCESSFULLY = "Logged In Unsuccessful";
        final String LOGOUT_UNSUCCESSFULLY = "Logged Out Unsuccessful";
        final String LOGOUT_SUCCESSFULLY = "Logged Out successfully";

        String operation = input[0], username = input[1];
        String password = input.length == 3? input[2]: null;
        //register
        if(operation.equals("register")){
            //user already exist
            if(register.containsKey(username)) { output.add(USER_ALREADY_EXISTS); }

            //user register Successfully
            else{
                register.put(username, password);
                output.add(REGISTERED_SUCCESSFULLY);
            }
        }
        else if(operation.equals("login")){
            //check if the user already logged in
            if(login.contains(username)) {output.add(LOGIN_UNSUCCESSFULLY );}
            else {

                String savedPassword = register.getOrDefault(username, null);
                if(savedPassword == null){ //user doesn't exsit
                    output.add(LOGIN_UNSUCCESSFULLY);
                }
                else if(savedPassword.equals(password)) {
                    output.add(LOGIN_SUCCESSFULLY);
                    login.add(username);
                }
                else {output.add(LOGIN_UNSUCCESSFULLY);}
            }
        }
        else{
            if(login.contains(username)){
                login.remove(username);
                output.add(LOGOUT_SUCCESSFULLY);
            }
            else{
                output.add(LOGOUT_UNSUCCESSFULLY);
            }

        }

    }



    public static void main(String[] args) {

        problem5 p5 = new problem5();

        List<String> logs = new ArrayList<>();
        logs.add("register david david123");
        logs.add("register Shane password");
        logs.add("login david david123");
        logs.add("login adam 2342we");
        logs.add("login Shane wrongpassword");
        logs.add("login david david123");
        logs.add("logout david");


        List<String> res = p5.implementAPI(logs);
        for(String out: res){
            System.out.println(out);
        }
    }

}

