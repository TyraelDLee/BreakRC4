import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Decrypt {
    static String pas = "PASSWORD MISMATCH                                                                                                               ";
    static String vai = "WELCOME INGRID                                                                                                                  ";
    public static int toInt(byte b){
        if(b < 0){
            return b + 128;
        }
        else return (int)b;
    }

    public static int XOR(int a, int b){
        return a ^ b;
    }
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArrayList<Integer> xorCSM = new ArrayList<>();
        ArrayList<Integer> dataInt = new ArrayList<>();
        ArrayList<Integer> key = new ArrayList<>();
        ArrayList<Integer> decFri18 = new ArrayList<>();
        ArrayList<Integer> dataCli = new ArrayList<>();
        ArrayList<Integer> decSec18 = new ArrayList<>();
        for (int i = 0; i < 256; i++)
            xorCSM.add(i);
       byte []data = ReadEnc.readEnc("src/ServerLogEnc.dat");
       byte[] clien = ReadEnc.readEnc("src/ClientLogEnc.dat");
       int index = 1;
       for(byte b : data){
           dataInt.add(toInt(b));
       }
       for(byte b : clien){
           dataCli.add(toInt(b));
       }

       index = 0;
       int line = 0;
       //Names
        try{
            BufferedReader reader = new BufferedReader(new FileReader("src/us.txt"));
            String out = "";
            int t = 0;
            while ((out = reader.readLine()) != null){
                if(out.length()<8){
                    for (int i = out.length(); i < 8; i++) {
                        out += " ";
                    }
                }
                out = out.toUpperCase();
                out = "WELCOME "+out;
                for (int i = out.length(); i < 128; i++) {
                    out+=" ";
                }

                for(line = 0; line < 30; line++){
                    decFri18 = new ArrayList<>();
                    decSec18 = new ArrayList<>();
                    index = 0;
                    for(int i : dataInt){
                        if(index >= 128*line && index < 128*(line+1)){
                            decFri18.add(i);
                        }

                        index++;
                    }
                    index = 0;
                    for(int i : dataCli){
                        if(index >= 128*line && index < 128*(line+1)){
                            decSec18.add(i);
                        }

                        index++;
                    }
                    key = new ArrayList<>();
                    for (int i = 0; i < 128; i++) {
                        int c = (int)out.charAt(i);
                        for(int j : xorCSM){
                            if(XOR(j,decFri18.get(i))==c){
                                key.add(j);
                            }
                        }
                    }


                    index = 0;
                    String dec = "";
                    String cle = "";
                    for(int i : key){
                        char c = (char) XOR(decSec18.get(index),i);
                        dec += c;
                        index++;
                    }


                    index = 0;
                    for(int i : key){
                        char c = (char) XOR(decFri18.get(index), i);
                        cle += c;
                        index++;
                    }

                    String n = cle.replace("WELCOME ", "");
                    n = n.replace(" ","");
                    String pass = dec.replace("LOGIN", "");
                    pass = pass.replace(" ","");
                    pass = pass.replace(n,"");
                    if(dec.contains("LOGIN") && dec.contains(n) && pass.length() == 8){
                        System.out.println("Answer found at line: "+line);
                        System.out.println(dec);
                        System.out.println(cle+"\n");
                    }

                }
                t++;
            }
            System.out.println(t+" entries tested");
        }catch(Exception e){}
        System.out.println("\nPASSWORD MISMATCH");
        //PASSWORD MISMATCH
        for(line = 0; line < 30; line++){
            decFri18 = new ArrayList<>();
            decSec18 = new ArrayList<>();
            index = 0;
            for(int i : dataInt){
                if(index >= 128*line && index < 128*(line+1)){
                    decFri18.add(i);
                }

                index++;
            }
            index = 0;
            for(int i : dataCli){
                if(index >= 128*line && index < 128*(line+1)){
                    decSec18.add(i);
                }

                index++;
            }
            key = new ArrayList<>();
            for (int i = 0; i < 128; i++) {
                int c = (int)pas.charAt(i);
                for(int j : xorCSM){
                    if(XOR(j,decFri18.get(i))==c){
                        key.add(j);
                    }
                }
            }


            index = 0;
            String dec = "";
            String cle = "";
            for(int i : key){
                char c = (char) XOR(decSec18.get(index),i);
                dec += c;

                index++;
            }


            index = 0;
            for(int i : key){
                char c = (char) XOR(decFri18.get(index), i);
                cle += c;
                index++;
            }
            if(dec.contains("LOGIN")){
                System.out.println("Answer found at line: "+line);
                System.out.println(dec);
                System.out.println(cle+"\n");
            }

        }

        long endTime = System.currentTimeMillis();
        System.out.println(Timer.timer(startTime,endTime));

    }
}
