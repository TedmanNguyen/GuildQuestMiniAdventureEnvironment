package models;

public class WorldClock extends Thread {
	private static int minuteTick = 1000;	// how many irl ms in a in-game minute
	private GameTime currentTime;
	
	public WorldClock() {
		currentTime = new GameTime(0, 0, 0, 1440); // starts at time 0, 1440 total minutes in day
	}
	
	public void run() {
		while (true) {
			currentTime.clockTick();
			try {
				sleep(minuteTick);
			}
			catch (InterruptedException e) {
				System.out.println("Error with the clock");
			}
		}
	}
	
	public String toString() {
		return currentTime.toString();
	}
	
	public GameTime getCurrentTime() { return currentTime; }
}