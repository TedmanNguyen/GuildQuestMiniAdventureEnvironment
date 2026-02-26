//AI REFACTOR CLASS introduce paramter object
public class QuestInterval {
    private int startMinutes;
    private Integer endMinutes;

    public QuestInterval(int start, Integer end) {
        if (end != null && end < start) {
            throw new IllegalArgumentException("End time cannot be before start time.");
        }
        this.startMinutes = start;
        this.endMinutes = end;
    }

    public int getStartMinutes() { return startMinutes; }
    public Integer getEndMinutes() { return endMinutes; }
    public void setStartMinutes(int startMinutes){
        this.startMinutes = startMinutes;
    }
    public String getFormattedStart() {
        return formatMinutes(startMinutes);
    }

    public String getFormattedEnd() {
        return (endMinutes == null) ? "Ongoing" : formatMinutes(endMinutes);
    }

    private String formatMinutes(int totalMinutes) {
        int days = totalMinutes / (24 * 60);
        int hours = (totalMinutes / 60) % 24;
        int mins = totalMinutes % 60;
        return "Day " + days + ", " + String.format("%02d:%02d", hours, mins);
    }
}