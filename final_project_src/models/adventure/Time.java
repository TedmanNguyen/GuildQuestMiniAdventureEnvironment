package models.adventure;

/**
 * Simple domain class to track elapsed time in seconds for mini-adventures.
 */
public class Time {
    private int secondsPassed;

    public Time() {
        secondsPassed = 0;
    }

    public void increment() { ++secondsPassed; }
    public int secondsPassed() { return secondsPassed; }
}

