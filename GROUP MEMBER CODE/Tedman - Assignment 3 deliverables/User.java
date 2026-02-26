/**
 * User class that can make Campaigns / Characters
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class User implements UniqueID, Owner{

    private final String userID;
    private final String username;
    private GuildQuest owner;
    private List<Campaign> campaignList = new ArrayList<Campaign>();
    private List<Character> characterList = new ArrayList<Character>();
    private List<Realm> realmList = new ArrayList<Realm>();

    public User(String username) {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty");
        }
        this.username = username.trim();
        this.userID = "user-" + UUID.randomUUID().toString().substring(0, 8);
    }
    public User() {
        this("GuildQuestUser" + System.currentTimeMillis());
    }
    @Override
    public String generateID() {
        return userID;
    }
    public String getUserID() {
        return userID;
    }
    public String getUsername() {
        return username;
    }
    @Override
    public void addOwnList(Object item){
        if (item instanceof Campaign){
            addCampaign((Campaign) item);
        }
        else if (item instanceof Character){
            addCharacter((Character) item);
        }
        else if (item instanceof Realm){
            addRealm((Realm) item);
        }
        else {
            throw new IllegalArgumentException("Cannot add " + item.getClass().getName() + " to User");
        }
    }
    @Override
    public boolean removeOwnList(Object item){
        if (item instanceof Campaign){
            return campaignList.remove(item);
        }
        else if (item instanceof Character){
            return characterList.remove(item);
        }
        else if (item instanceof Realm){
            return realmList.remove(item);
        }
        else {
            throw new IllegalArgumentException("Cannot remove " + item.getClass().getName() + " from User");
        }
    }
    @Override
    public <T> List<T> getOwnedItems(Class<T> itemClass)  {
        List<T> result = new ArrayList<>();

        if (itemClass == Campaign.class) {
            for (Campaign campaign : campaignList) {
                result.add(itemClass.cast(campaign));
            }
        }
        else if (itemClass == Character.class) {
            for (Character character : characterList) {
                result.add(itemClass.cast(character));
            }
        }
        else if (itemClass == Realm.class) {
            for (Realm realm : realmList) {
                result.add(itemClass.cast(realm));
            }
        }
        return result;        
    }
    
    public void addCampaign(Campaign campaign) {
        if (!campaignList.contains(campaign)) {
            campaignList.add(campaign);
            campaign.setOwner(this);
        }
    }
    public List<Campaign> getCampaign(){
        return new ArrayList<>(campaignList);
    }
    public void viewCampaigns() {
        if (campaignList.isEmpty()) {
            System.out.println("User has no campaigns");
        }
        else {
            for (Campaign cam : campaignList) {
                System.out.print(cam.getCampaignID() + " (" + cam.getTitle() + "), ");
            }
            System.out.println();
        }
    }

    public void addCharacter(Character character) {
        if (!characterList.contains(character)) {
            characterList.add(character);
            character.setOwner(this);
        }
    }
    public void viewCharacters(){
        if (characterList.isEmpty()) {
            System.out.println("User has no characters");
        }
        else {
            for (Character character: characterList){
                System.out.print(character.getName() + ", ");
            }
            System.out.println();
        }
    }

    public void addRealm(Realm realm)
    {
        realmList.add(realm);
        realm.setOwner(this);
    }
    public void viewRealm(){
        if (realmList.isEmpty()) {
            System.out.println("User has no realms");
        }
        else {
            for (Realm realm: realmList){
                System.out.print(realm.getName() + ", ");
            }
            System.out.println();
        }
    }

    public void setOwner(GuildQuest game) {
        owner = game;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username);
    }
    
    @Override
    public String toString() {
        return "User{username='" + username + "', userID='" + userID + "'}";
    }
}




