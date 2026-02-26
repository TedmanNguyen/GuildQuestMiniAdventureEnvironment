
package GuildQuest;

public class DateRule {
    private final int offsetMinutes;
    private final int minutesPerHour;
    private final int minutesPerDay;
    private final int minutesPerWeek;
    private final int minutesPerMonth;
    private final int minutesPerYear;

    public DateRule() { this(0); }

    public DateRule(int offsetMinutes) {
        this.offsetMinutes = offsetMinutes;
        this.minutesPerHour = 60;
        this.minutesPerDay = 24 * minutesPerHour;
        this.minutesPerWeek = 7 * minutesPerDay;
        this.minutesPerMonth = 30 * minutesPerDay;
        this.minutesPerYear = 365 * minutesPerDay;
    }

    private int applyOffset(int rawMinutes) {
        return rawMinutes + offsetMinutes;
    }

    public int getDayConversion(int rawMinutes) {
        int m = Math.max(0, applyOffset(rawMinutes));
        return m / minutesPerDay;
    }

    public int getHourConversion(int rawMinutes) {
        int m = Math.max(0, applyOffset(rawMinutes));
        return (m % minutesPerDay) / minutesPerHour;
    }

    public int getMinuteConversion(int rawMinutes) {
        int m = Math.max(0, applyOffset(rawMinutes));
        return m % minutesPerHour;
    }

// Future change and red in the UML but provided simple implemenation
    public int getWeekConversion(int rawMinutes) {
        int m = Math.max(0, applyOffset(rawMinutes));
        return m / minutesPerWeek;
    }

    public int getMonthConversion(int rawMinutes) {
        int m = Math.max(0, applyOffset(rawMinutes));
        return m / minutesPerMonth;
    }

    public int getYearConversion(int rawMinutes) {
        int m = Math.max(0, applyOffset(rawMinutes));
        return m / minutesPerYear;
    }
}
