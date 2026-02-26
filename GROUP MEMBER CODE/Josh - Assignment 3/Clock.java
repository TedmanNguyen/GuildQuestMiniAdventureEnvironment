
package GuildQuest;

public class Clock {
    public int time; // raw minutes since start
    public DateRule globalDateRule;

    public Clock(DateRule globalDateRule) {
        this.globalDateRule = globalDateRule;
        this.time = 0;
    }

    public void tick() { time++; }

    public int getDays() { return globalDateRule.getDayConversion(time); }
    public int getHours() { return globalDateRule.getHourConversion(time); }
    public int getMinutes() { return globalDateRule.getMinuteConversion(time); }
}
