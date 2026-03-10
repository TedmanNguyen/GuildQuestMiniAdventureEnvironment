public class Timer extends Thread {
    private boolean running;
    private int secondsPassed;

    public Timer() {
        secondsPassed = 0;
    }

    public void run() {
        while (running) {
            ++secondsPassed;
            try {
				sleep(1000);    // sleep the thread for 1000 ms (1s)
			}
			catch (InterruptedException e) {
				System.out.println("Error with the clock");
			}
        }
    }

    public int stopTimer() {
        running = false;
        return secondsPassed;
    }
}