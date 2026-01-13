import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Util {
    private static final int DEFAULT_PORT = 1027;

    public static int getPortFromStdin(){
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter port number> ");
        try{
            return Integer.parseInt(bufferedReader.readLine());
        }catch (Exception e){
            System.out.println("Invalid port number entered. Selecting default port.");
            return Util.DEFAULT_PORT;
        }
    }
}
