import java.util.concurrent.*;

/**
 * Class Monitor
 * To synchronize dining philosophers.
 *
 * @author Serguei A. Mokhov, mokhov@cs.concordia.ca
 */
public class Monitor
{
	/*
	 * ------------
	 * Data members
	 * ------------
	 */

	private int numberOfPhilosophers;
	private enum status {eating, hungry, thinking}; 
	private status[] states;  
	private static Semaphore talkMutex = new Semaphore(1);




	/**
	 * Constructor
	 */
	public Monitor(int piNumberOfPhilosophers)
	{
		// TODO: set appropriate number of chopsticks based on the # of philosophers
		numberOfPhilosophers = piNumberOfPhilosophers;
		states = new status[piNumberOfPhilosophers];
		
		
		
		//To complete
	}

	/*
	 * -------------------------------
	 * User-defined monitor procedures
	 * -------------------------------
	 */

	//
	public void test(int i)
	{
		//Check left (Nb added in the case where i-1 is negative) then right
		if((states[(i-1+numberOfPhilosophers) % numberOfPhilosophers ] != status.hungry) && (states[(i+1) % numberOfPhilosophers ] != status.eating)
			&& (states[i] == status.hungry))
		{
			states[i] = status.eating;
			notifyAll();
		}
	}

	/**
	 * Grants request (returns) to eat when both chopsticks/forks are available.
	 * Else forces the philosopher to wait()
	 */
	public synchronized void pickUp(final int piTID)
	{
		int index = piTID-1; //index of the philosopher in the array since the philosopher's id starts at 1  
		try
		{
			states[index] = status.hungry;
			test(index);

			if(states[index] != status.eating)
			{
				wait();
			}
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * When a given philosopher's done eating, they put the chopstiks/forks down
	 * and let others know they are available.
	 */
	public synchronized void putDown(final int piTID)
	{
		int index = piTID-1; //index of the philosopher in the array since the philosopher's id starts at 1  

		states[index] = status.thinking;
		test((index - 1 +numberOfPhilosophers)% numberOfPhilosophers); 
		test((index + 1)% numberOfPhilosophers);
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		// ...
		//check mutex, wait if cant take
		try
		{
			talkMutex.acquire();
		}
		catch(Exception e)
		{
			System.out.println("INTERRUPTED"); 
		}

	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		// ...
		//release mutex
        talkMutex.release();
        notifyAll();
	}
}

// EOF
