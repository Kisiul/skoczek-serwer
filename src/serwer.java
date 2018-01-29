
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/**
 *Serwer obslugujacy dowolna ilosc skoczkow.
 */
public class serwer {

    /**
     * Dzialanie serwera w nieskonczonosc, kazdy klient dostaje swoj watek.
     */
    public static void main(String[] args) throws IOException {
        ServerSocket listener = new ServerSocket(9090);
        try {
            while (true) {
                new Polaczenie(listener.accept()).start();
            }
        }
        finally {
            listener.close();
        }
    }

/**
* Watek tworzony dla kazdego nowego klienta
*
*/
private static class Polaczenie extends Thread{
    private Socket socket;
    private Parsowanie pars;

    public Polaczenie(Socket gniazdko){
        this.socket = gniazdko;
        pars = new Parsowanie();

        pars.loadProperties(new File("../conf.properties"));

        System.out.println("Nowe polaczenie");
        
        
    }

    public void run(){
        try{
        BufferedReader in = new BufferedReader(
            new InputStreamReader(socket.getInputStream()));

         PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
         out.println((new Date().toString()) + " Witaj graczu, połączenie ustanowione.");

           while (true) {
                    String input = in.readLine();
                    System.out.println(input);
                    if (input == null || input.equals(".")) {
                        break;
                    }else{
                        if(input.equals("parsuj")){
                            String odpowiedz = pars.parsuj_string(in.readLine());
                            out.println(odpowiedz);
                        }
                    }
                }

        }catch(IOException ex){
            System.out.println(ex);
        }finally{
            try {
                socket.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            System.out.println("zamknieto polaczenie");
        }
    }
}
}