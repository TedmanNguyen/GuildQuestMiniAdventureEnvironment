public class TimeRule {
    private int offsetMinutes;
    private int dayMultiplier;

    public TimeRule(int offsetMinutes, int dayMultiplier){
        this.offsetMinutes = offsetMinutes;
        this.dayMultiplier = dayMultiplier;
    }

    public int toLocalTime(int worldTotalMinutes){
        return (worldTotalMinutes + offsetMinutes) * (dayMultiplier > 0 ? dayMultiplier : 1);
    }

    public void setOffsetMinutes(int offset) {
        this.offsetMinutes = offset;
    }

    public void setDayMultiplier(int multiplier){
        this.dayMultiplier = multiplier;
    }
}
