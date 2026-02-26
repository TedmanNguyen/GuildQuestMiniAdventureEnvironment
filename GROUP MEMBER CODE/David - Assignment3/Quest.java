import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Quest {
    private int questId;
    private String questName;
//    private int startTime;
    private Integer endTime = 0;
    private QuestInterval interval;
    private List<Realm> allRealms;
    private Realm currentRealm;

    private List<Integer> participatingCharacters;
    private Map<Integer,Integer> lootModifier;

    private Map<Integer,Permission> shareWith;

    public Quest(int questId, String questName,int startTime,Realm newRealm){
        this.questId = questId;
        this.questName = questName;
        this.interval = new QuestInterval(startTime,getEndTime());
        currentRealm = newRealm;
        allRealms = new ArrayList<>();
        allRealms.add(newRealm);
        participatingCharacters = new ArrayList<>();
        lootModifier = new HashMap<>();
        shareWith = new HashMap<>();
    }


    public Realm getCurrentRealm() {
        return currentRealm;
    }

    public int getStartTime() {
        return interval.getStartMinutes();
    }

    public Integer getEndTime() {
        return endTime;
    }

    public String getQuestName() {
        return questName;
    }

    void setQuestName(String name){
        questName = name;
    }
    void setStartTime(int time){
        interval.setStartMinutes(time);
    }
    void setEndTime(int time){
        endTime = time;
    }

    void changeRealm(Realm realm){
        currentRealm = realm;
    }
    void getQuestInfo(){
        System.out.println("(ID: " + getQuestId() + ") " + " Quest: " + questName + " | Realm: " + currentRealm.getName() + "| Start Time: " + getStartTime() + " min from now "+  "| End Time: " + getEndTime() + " min from now");
    }
    void shareQuest(User user, Permission permission){
        int id = user.getUserId();
        shareWith.put(id,permission);
        
    }
    public int getQuestId(){
       return questId;
    }

    public void addRealm(Realm realm){
        allRealms.add(realm);
    }
    public void removeRealm(Realm realm){
        allRealms.remove(realm);
    }

    public void addCharacters(Characters newCharacter){
        int characterId = newCharacter.getId();
        if (!participatingCharacters.contains(characterId)){
            participatingCharacters.add(characterId);
            System.out.println("Character with id: " + characterId + " was added to quest successfully");
        }
        else{
            System.out.println("Character already in Quest");
        }
    }

}
