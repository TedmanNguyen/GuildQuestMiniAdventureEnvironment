package hw2;

public class GameTime {

	private int days;	// changed days to integer
	private int hours;
	private int minutes;
	private int totalMinutes;
	
	public GameTime(int days, int hours, int minutes, int totalMinutes) {
		this.days = days;
		this.hours = hours;
		this.minutes = minutes;
		this.totalMinutes = totalMinutes;
	}
	
	public String toString() {
		return days + ":" + hours + ":" + minutes;
	}
	
	
	/**
	 * @param other The GameTime object that this will be compared to
	 * @return True if they are the same, false otherwise.
	 * 
	 */
	public boolean compareTo(GameTime other) {
		return days == other.days &&
				hours == other.hours &&
				minutes == other.minutes &&
				totalMinutes == other.totalMinutes;
	}
	
	public GameTime applyOffset(int offset) {
		return new GameTime(days, hours-offset, minutes, totalMinutes);
	}
	
	// setters
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
