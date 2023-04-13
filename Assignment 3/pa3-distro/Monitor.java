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
		if((states[(i-1) % numberOfPhilosophers ] != status.hungry) && (states[(i+1) % numberOfPhilosophers ] != status.eating)
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
		try
		{
			states[piTID] = status.hungry;
			test(piTID);

			if(states[piTID] != status.eating)
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
		states[piTID] = status.thinking;
		test((piTID - 1)% numberOfPhilosophers); 
		test((piTID + 1)% numberOfPhilosophers);
	}

	/**
	 * Only one philopher at a time is allowed to philosophy
	 * (while she is not eating).
	 */
	public synchronized void requestTalk()
	{
		// ...
	}

	/**
	 * When one philosopher is done talking stuff, others
	 * can feel free to start talking.
	 */
	public synchronized void endTalk()
	{
		// ...
	}
}

// EOF
