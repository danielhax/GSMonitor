package mainPkg;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * Created by DANIEL on 20/05/2016.
 */
/*
    This class is where the main process of pinging the given address is done
 */

public class Ping extends Thread{

    //STATIC GLOBAL VARIABLES
    private final static Runtime rt = Runtime.getRuntime();
    private final Process p;
    private final String ip;
    private final static String pingToken = "time="; //this is the prefix to the actual integer result, which is the ping

    //NON-STATIC GLOBAL VARIABLES
    private int overallLineCount;
    private int rtoCount;                           //request time out counter
    private Integer[] last20Pings;

    private int iterator;

    public Ping(String ip) throws IOException {
        this.ip = ip;                               //use the inputted ip as a local string to be used in this class
        this.p = rt.exec("ping " + ip + " /t");          //runtime command: ping *ip address* -t

        this.rtoCount = 0;
        this.overallLineCount = -1;          //-1 to start count with 1; first 2 fetched lines are not needed
        this.iterator = 0;                 //iterator for 'last20Pings' array
    }


    public void run() {

        BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()));   //get output from executed runtime command
        String fetchedLine;

        try {
            last20Pings = new Integer[20];
            Arrays.fill(last20Pings, null);                 //fill array with null values to identify if an index has been used

            while ((fetchedLine = input.readLine()) != null) {
                if(Thread.interrupted()) {                  //stopped pinging
                    System.out.println("Inner interrupt");
                    break;
                }

                System.out.println(pingAddress(fetchedLine));
                overallLineCount++;

                for (int i = 0; i < 3; ++i) System.out.println("\r \n");    //clear screen
            }
        } catch (IOException  e) {
            e.printStackTrace();
        }
    }                         //run thread and keep pinging an address until stopped

    private final String pingAddress(String lineToProcess){
        String ping;
        int averagePing = 0;

        if(lineToProcess.contains("Request timed out."))
            rtoCount++;
        else{
            for (String word : lineToProcess.split(" ")){

                if(word.contains(pingToken)) {        // if 'time=' is found
                    ping = word.substring(word.indexOf("=") + 1, word.indexOf("ms"));   //actual ping is after the character '=' and before 'ms'

                    if(iterator == 20)
                        iterator = 0;           //iterator resets, thus overwriting the first index

                    last20Pings[iterator++] = Integer.parseInt(ping);

                    averagePing = averagePing(last20Pings);

                }
                else
                    continue;
            }
        }

        float averageRTO = (float)rtoCount / (float)overallLineCount;        //get average packet loss

        //////////////OUTPUT SECTION///////////////////////
        /*
            To be deleted once GUI is in development
         */
        return  "\n\nPinging " + ip
                + "\nTotal Lines: " + overallLineCount
                + "\nPing: " + averagePing
                + "\nTime Out Count: " + rtoCount
                + "\nPacket Loss: " + Math.round(averageRTO * 100) + "%";
    }

    private final int averagePing(Integer[] pingsArray){

        int i = 0; //while iterator
        int totalPing = 0;

        //show array content
        System.out.println("Last 20 ping results: ");
        for(Integer j : pingsArray)
            System.out.print(j + " ");

        while(i < pingsArray.length){
            if(pingsArray[i] != null)
                totalPing += pingsArray[i++];
            else
                break;
        }

        return totalPing / i;
    }
}
