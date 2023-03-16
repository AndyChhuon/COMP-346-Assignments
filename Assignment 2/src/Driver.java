
import java.io.FileNotFoundException;
import java.io.PrintStream;

//Written by Andy Chhuon (40199798) and Bayazeed Rahman (40190096)

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kerly Titus
 */
public class Driver {

    /** 
     * main class
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
    	
    	 /*******************************************************************************************************************************************
    	  * TODO : implement all the operations of main class   																					*
    	  ******************************************************************************************************************************************/
        //Outputs directed to a .txt file (Once for size 10 and once for size 20)


        try
        {
            PrintStream myOutputs = new PrintStream("3threads.txt");
            System.setOut(myOutputs);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("Failed to find the file!");
            System.exit(0);
        }
        
    	Network objNetwork = new Network( );            /* Activate the network */
        objNetwork.start();
        
        //We have 3 servers now as Thread1, Thread2, Thread3. 3 Server threads effectively performs better than 2 servers (Maximums of 501 milliseconds vs 354 milliseconds).
        Server objServer1 = new Server("Thread1");
        Server objServer2 = new Server("Thread2");
        Server objServer3 = new Server("Thread3");

        objServer1.start();
        objServer2.start();
        objServer3.start();

        Client objClient1 = new Client("sending");          /* Start the sending client thread */
        objClient1.start();
        Client objClient2 = new Client("receiving");        /* Start the receiving client thread */
        objClient2.start();

        /* Complete here the code for the main method ...*/

        //After running the main method with two and three servers, we can see that the total running time of the program is faster with three servers.
        //The difference of total running time was 147 milliseconds.
    }
}
//With two servers, we had the following runtimes: 
//  Client thread: 336ms
//  Server1 thread: 358ms
//  Server2 thread: 358ms
//  Network thread: 501ms

//With three servers, we had the following runtimes:
//  Client thread: 227ms
//  Server1 thread: 241ms
//  Server2 thread: 241ms
//  Server3 thread: 240ms
//  Network thread: 354ms

