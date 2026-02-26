import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


public class GuildQuest implements Owner {
    private List<User> userList = new ArrayList<User>();
    private Map<String, User> usersByUsername = new HashMap<>();

    private static GuildQuest single_instance = null;
    private GuildQuest()
    {
        //Creating private constructor to prevent instantiation
        //currently no need to init any variables. 
    }
    public static synchronized GuildQuest getInstance()
    {
        if (single_instance == null)
            single_instance = new GuildQuest();
        return single_instance;
    }
    

    @Override
    public void addOwnList(Object item) {
        if (item instanceof User) {
            addUser((User) item);
        }
        else {
            throw new IllegalArgumentException("GuildQuest can only own Users");
        }

    }

    public void addUser(User user) {
        if (usersByUsername.containsKey(user.getUsername())) {
            throw new IllegalArgumentException("Username already exists: " + user.getUsername());
        }
        
        userList.add(user);
        usersByUsername.put(user.getUsername(), user);
        user.setOwner(this);
    }

    @Override
    public boolean removeOwnList(Object item)
    {
        if (item instanceof User) {
            User user = (User) item;
            usersByUsername.remove(user.getUsername());
            return userList.remove(item);
        }
        else {
            throw new IllegalArgumentException("Cannot remove " + item.getClass().getName() + " from GuildQuest");
        }
    }

    @Override
    public <T> List<T> getOwnedItems(Class<T> itemClass)
    {
        List<T> result = new ArrayList<>();

        if (itemClass == User.class) {
            for (User user : userList) {
                result.add(itemClass.cast(user));
            }
        }
        
        return result;      
    }
    /**
     * Find user by username
     * @param username The username to search for
     * @return Optional containing the user if found
     */
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(usersByUsername.get(username));
    }
    
    /**
     * Get all users
     * @return List of all users
     */
    public List<User> getAllUsers() {
        return new ArrayList<>(userList);
    }
    
    /**
     * Check if username exists
     * @param username The username to check
     * @return true if username exists
     */
    public boolean usernameExists(String username) {
        return usersByUsername.containsKey(username);
    }
    
}