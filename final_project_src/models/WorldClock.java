package models;

public class WorldClock extends Thread {
	private static final int minuteTick = 1000;	// how many irl ms in a in-game minute
	private GameTime currentTime;
	boolean isRunning;

	public WorldClock() {
		currentTime = new GameTime(0, 0, 0, 1440); // starts at time 0, 1440 total minutes in day
		isRunning = true;
	}

	public void run() {
		while (isRunning) {
			currentTime.clockTick();
			try {
				sleep(minuteTick);
			}
			catch (InterruptedException e) {
				System.out.println("Error with the clock");
			}
		}
	}

	public void end() {
		isRunning = false;
	}

	public String toString() {
		return currentTime.toString();
	}

	public GameTime getCurrentTime() { return currentTime; }
}

