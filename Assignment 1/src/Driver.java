import java.io.PrintStream;
import java.io.FileNotFoundException;

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
    public static void main(String[] args) {
    	
    	 /*******************************************************************************************************************************************
    	  * TODO : implement all the operations of main class   																					*
    	  ******************************************************************************************************************************************/
        
        //Outputs directed to a .txt file (Once for size 10 and once for size 20)
        try
        {
            PrintStream myOutputs = new PrintStream("testBuffer10.txt");
            System.setOut(myOutputs);
        }
        catch(FileNotFoundException exception)
        {
            System.out.println("Failed to find the file!");
            System.exit(0);
        }
        
        
    	Network objNetwork = new Network("network");            /* Activate the network */
        objNetwork.start();
        Server objServer = new Server();
        objServer.start();
        Client sendingClient = new Client("sending");
        sendingClient.start();
        //Initialize Receiving Client
        Client receivingClient = new Client("receiving");
        receivingClient.start();

        /* Complete here the code for the main method ...*/
        
        //After running the main method with two different buffers, we did not notice any significant changes. 
        //The difference of total running time was 3ms. 
    }
}
