import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadEnc {

    public static byte[] readEnc(String filepath){
        Path path = Paths.get(filepath);
        byte[] out = new byte[128];
        try{
            out = Files.readAllBytes(path);
        }catch (Exception e){}

        return out;
    }

    public static void main(String[] args) {
        byte[] out = new byte[128];
        byte[] une = new byte[128];
        try{
            Path path = Paths.get("src/ClientLogEnc.dat");
            Path un = Paths.get("ClientLog.txt");
            out = Files.readAllBytes(path);
            une = Files.readAllBytes(un);
        }catch(Exception e){}
        int index = 1;
        for(byte b : out){
            if (index%128==0) System.out.print("\n");
            System.out.print(b+" ");
            index++;
        }
        System.out.println("size: " + out.length + "/128 = " +out.length/128);
        index = 0;
        for(byte b : une){
            if (index%128==0) System.out.print("\n");
            System.out.print(b+" ");
            index++;
        }

    }
}
