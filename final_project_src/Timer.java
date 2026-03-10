public class Timer extends Thread {
    private boolean running;
    private int secondsPassed;

    public Timer() {
        secondsPassed = 0;
    }

    public void run() {
        while (running) {
            try {
				sleep(1000);    // sleep the thread for 1000 ms (1s)
                ++secondsPassed;
			}
			catch (InterruptedException e) {
				System.out.println("Error with the timer");
                return;
			}
        }
    }

    public int stopTimer() {
        running = false;
        return secondsPassed;
    }

    public void startTimer() {
        running = true;
        start();
    }
}