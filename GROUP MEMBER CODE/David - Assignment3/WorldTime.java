public class WorldTime {
    private static WorldTime instance;
    private int totalMinutes;

    private WorldTime(){
        this.totalMinutes = 1000;
    }

    public static WorldTime getInstance(){
        if (instance == null){
            instance = new WorldTime();
        }
        return instance;
    }
//    removed to QuestInterval class
//    public int getDays(){
//        return totalMinutes / (24 * 60);
//    }
//    public int getHours(){
//        return (totalMinutes / 60) % 24;
//    }
//    public int getMinutes(){
//        return totalMinutes % 60;
//    }
//    public void addMinutes(int mins){
//        this.totalMinutes += mins;
//    }
    public int getTotalMinutes(){
        return totalMinutes;
    }
//    public String getFormattedTime() {
//        return "Day " + getDays() + ", " + String.format("%02d:%02d", getHours(), getMinutes());
//    }
//
//    public void displayTime() {
//        System.out.println("World Time: Day " + getDays() + ", " + String.format("%02d:%02d", getHours(), getMinutes()));
//    }
}
