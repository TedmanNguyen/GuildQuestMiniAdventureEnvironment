public class Realm {
    private int realmId;
    private String name;
    private String description;

    private TimeRule timeRule;

    Realm (int realmId,String name, TimeRule timeRule){
        this.realmId = realmId;
        this.name = name;
        this.timeRule = timeRule;
    }

    public String getLocalTimeDisplay(long worldTotalMinutes){
        int localTotal = timeRule.toLocalTime(Integer.parseInt(String.valueOf(worldTotalMinutes)));
        int days = localTotal / (24 * 60);
        int hours = (localTotal / 60) % 24;
        int minutes = localTotal % 60;
        return String.format("Day %d, %02d:%02d",days,hours,minutes);
    }
    public void getWorldTime(WorldTime worldTime){
        System.out.println("");
    }
    public void addDescription(String description){
        this.description = description;
    }
    public String getDescription(){
        return description;
    }
    public String getName(){
        return name;
    }

}
