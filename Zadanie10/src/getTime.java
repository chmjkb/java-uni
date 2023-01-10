import java.io.*;
import java.net.ConnectException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;

public class Connector {

    private static Integer getNumber(String line){
        int colonIndex = line.indexOf(":");
        return Integer.parseInt(line.substring( colonIndex + 1).strip());
    }

    private static void getTime( String host ) {
        final int PORT = 8080;
        Socket so = null;
        BufferedReader br = null;
        BufferedWriter bw = null;
        ArrayList<Integer> numbers = new ArrayList();
        try {
            so = new Socket( host, PORT );
            br = new BufferedReader( new InputStreamReader( so.getInputStream() ) );
            bw = new BufferedWriter( new OutputStreamWriter( so.getOutputStream() ) );
            String line;
            Integer numberOfInterest = null;
            while ( ( line = br.readLine() ) != null ) {
                System.out.println(line);
                if (line.contains("program")){
                    bw.write( "Program\n" );
                    bw.flush();
                    continue;
                }
                if (line.equals("EOD")){
                    System.out.println(numbers);
                    continue;
                }
                if (line.contains("Zaczynamy")){
                    numberOfInterest = getNumber(line);
                }

                if (line.contains("????")){
                    int result = Collections.frequency(numbers, numberOfInterest);
                    System.out.println("WYNIK: " + result);
                    bw.write(result + "\n");
                    bw.flush();
                }
                try {
                    Integer currentNum = Integer.parseInt(line);
                    numbers.add(currentNum);
                } catch (NumberFormatException nfe) {
                    System.out.println("error");
                }

            }
        }
        catch ( UnknownHostException exc ) {
            System.out.println( "Nie znam takiego hosta " + host );
        }
        catch ( ConnectException exc ) {
            System.out.println( "Blad polaczenia z serwerem " + host );
        }
        catch ( Exception e ) {
            e.printStackTrace();
        }
        finally {
            try {
                if ( br != null ) br.close();  // zamykamy strumien
                if ( bw != null ) bw.close();  // zamykamy strumien wyjściowy
                if ( so != null ) so.close();  // zamykamy gniazdo sieciowe
            }
            catch ( IOException e ) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Connector conn = new Connector();
        conn.getTime("172.30.24.101");
    }

}
