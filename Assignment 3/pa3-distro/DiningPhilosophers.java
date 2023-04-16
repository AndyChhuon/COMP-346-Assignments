import java.io.FileNotFoundException;
import java.io.PrintStream; 

/**
 * Class DiningPhilosophers
 * The main starter.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class DiningPhilosophers
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	/**
	 * This default may be overridden from the command line
	 */
	public static final int DEFAULT_NUMBER_OF_PHILOSOPHERS = 4;

	/**
	 * Dining "iterations" per philosopher thread
	 * while they are socializing there
	 */
	public static final int DINING_STEPS = 10;

	/**
	 * Our shared monitor for the philosphers to consult
	 */
	public static Monitor soMonitor = null;

	/*
	 * -------
	 * Methods
	 * -------
	 */

	/**
	 * Main system starts up right here
	 */
	public static void main(String[] args)
	{
		try
		{
			
			/*
			 * TODO:
			 * Should be settable from the command line
			 * or the default if no arguments supplied.
			 */
			int iPhilosophers = DEFAULT_NUMBER_OF_PHILOSOPHERS;
			
			//Get nb of philosophers from command line
			if(args.length>0) {
				try {
					iPhilosophers = Integer.parseInt(args[0]);
					if(iPhilosophers<1) {
			            throw new IllegalArgumentException("Age cannot be negative.");
					}

				}catch(NumberFormatException e)
		        {
		            System.out.println(args[0]+" is not a number.");
		            System.exit(1);
		        }catch(IllegalArgumentException e) {
		            System.out.println(args[0]+" is a negative number.");
		            System.exit(1);
		        }
			}
			System.out.print("Running with "+iPhilosophers+" philosophers.");
			//Set output to stream into txt file
	        try
	        {
	            PrintStream myOutputs = new PrintStream(iPhilosophers+"Philosophers.txt");
	            System.setOut(myOutputs);
	        }
	        catch(FileNotFoundException exception)
	        {
	            System.out.println("Failed to find the file!");
	            System.exit(0);
	        }

			// Make the monitor aware of how many philosophers there are
			soMonitor = new Monitor(iPhilosophers);

			// Space for all the philosophers
			Philosopher aoPhilosophers[] = new Philosopher[iPhilosophers];

			// Let 'em sit down
			for(int j = 0; j < iPhilosophers; j++)
			{
				aoPhilosophers[j] = new Philosopher();
				aoPhilosophers[j].start();
			}

			System.out.println
			(
				iPhilosophers +
				" philosopher(s) came in for a dinner."
			);

			// Main waits for all its children to die...
			// I mean, philosophers to finish their dinner.
			for(int j = 0; j < iPhilosophers; j++)
				aoPhilosophers[j].join();

			System.out.println("All philosophers have left. System terminates normally.");
		}
		catch(InterruptedException e)
		{
			System.err.println("main():");
			reportException(e);
			System.exit(1);
		}
	} // main()

	/**
	 * Outputs exception information to STDERR
	 * @param poException Exception object to dump to STDERR
	 */
	public static void reportException(Exception poException)
	{
		System.err.println("Caught exception : " + poException.getClass().getName());
		System.err.println("Message          : " + poException.getMessage());
		System.err.println("Stack Trace      : ");
		poException.printStackTrace(System.err);
	}
}

// EOF
