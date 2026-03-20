package models;

public class GameTime {
    private int days;
    private int hours;
    private int minutes;
    private final int totalMinutes;

    public GameTime(int days, int hours, int minutes, int totalMinutes) {
        this.days = days;
        this.hours = hours;
        this.minutes = minutes;
        this.totalMinutes = totalMinutes;
    }

    public String toString() {
        return days + ":" + hours + ":" + minutes;
    }

    public boolean compareTo(GameTime other) {
        return days == other.days && hours == other.hours && minutes == other.minutes && totalMinutes == other.totalMinutes;
    }

    public GameTime applyOffset(int offset) {
        return new GameTime(days, hours - offset, minutes, totalMinutes);
    }

    public void clockTick() {
        ++minutes;
        if (minutes == 60) {
            ++hours;
            minutes = 0;
        }
        if (hours == 24) {
            ++days;
            hours = 0;
        }
    }
}

