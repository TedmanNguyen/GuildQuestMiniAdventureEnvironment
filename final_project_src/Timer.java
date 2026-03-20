public class Timer extends Thread {
    private boolean running;
    private Time time;      // domain primitive, wrapper around an int

    public Timer() {
        time = new Time();
    }

    public void run() {
        while (running) {
            try {
				sleep(1000);    // sleep the thread for 1000 ms (1s)
                time.increment();
			}
			catch (InterruptedException e) {
				System.out.println("Error with the timer");
                return;
			}
        }
    }

    public Time stopTimer() {
        running = false;
        return time;
    }

    public void startTimer() {
        running = true;
        start();
    }
}