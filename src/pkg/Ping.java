package pkg;

import com.sun.org.apache.xml.internal.serializer.utils.SystemIDResolver;
import com.sun.org.apache.xpath.internal.SourceTree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Created by DANIEL on 20/05/2016.
 */
/*
    This class is where the main process of pinging the given address is done
 */

public class Ping extends Thread{

    private final static Runtime rt = Runtime.getRuntime();
    private final Process p;
    private int lineCount;
    private int rtoCount;
    private float averageRTO;
    private String ip;
    private Integer[] last20Pings;
    private int iterator;

    public Ping(String ip) throws IOException {
        this.ip = ip;                               //use the inputted ip as a local string to be used in this class
        lineCount = rtoCount = 0;                   //reset total lines and packet loss average every object creation
        p = rt.exec("ping " + ip + " /t");          //runtime command: ping *ip address* -t

        last20Pings = new Integer[20];   //each ping is stored here
        Arrays.fill(last20Pings, null);  //fill array with null values
        iterator = 0;                 //iterator for 'last20Pings' array
    }


    public void run() {

        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));   //get output from executed runtime command
        String line = null;

        try {
            while ((line = input.readLine()) != null) {
                System.out.println(printResult(line));
                lineCount++;

                for (int i = 0; i < 3; ++i) System.out.println("\r \n");    //clear screen
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }                         //run thread and keep pinging an address until stopped

    private static final String pingToken = "time="; //this is the prefix to the actual integer result, which is the ping
    private String ping;
    private int averagePing;

    private final String printResult(String lineToProcess){

        if(lineToProcess.contains("Request timed out."))
            rtoCount++;
        else{
            for (String word : lineToProcess.split(" ")){

                if(word.contains(pingToken)) {        // if 'time=' is found
                    ping = word.substring(word.indexOf("=") + 1, word.indexOf("ms"));   //actual ping is after the character '=' and before 'ms'

                    if(iterator != 20)
                        last20Pings[iterator++] = Integer.parseInt(ping);
                    else
                        iterator = 0;
                    averagePing = averagePing(last20Pings);

                }
                else
                    continue;
            }
        }

        averageRTO = (float)rtoCount / (float)lineCount;        //get average packet loss

        //////////////OUTPUT SECTION///////////////////////
        /*
            To be deleted once GUI is in development
         */
        return  "Pinging " + ip
                + "\nTotal Lines: " + lineCount
                + "\nPing: " + averagePing
                + "\nTime Out Count: " + rtoCount
                + "\nPacket Loss: " + ((float)Math.round(averageRTO * 100)) + "%";
    }

    private final int averagePing(Integer[] pingsArray){

        int i = 0; //while iterator
        int totalPing = 0;

        //show pings

        for(Integer j : pingsArray)
            System.out.print(j + " ");

        System.out.println(i);

        while(i < pingsArray.length){
            if(pingsArray[i] != null)
                totalPing += pingsArray[i++];
            else
                break;
        }


        System.out.println(i);

        return totalPing / i;
    }
}
