import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private Map<String,User> users;
    UserManager(){
        users = new HashMap<>();
    }
    User createUser(String name){
        try{

            if (users.containsKey(name)){
                throw new IllegalArgumentException("User " + name + " already in UserManager");
            }
            else{
                User user = new User(name,users.size() + 1);
                users.put(name,user);
                System.out.println("User," + name + " is now in the database");
            }
        }catch (IllegalArgumentException exception){
            System.out.println(exception);
        }
        return users.get(name);
    }
    public void updateUser(String name, User user){
        users.replace(name,user);
    }

    public void deleteUser(String name){
        if(users.containsKey(name)){
            users.remove(name);
        }
        else{
            System.out.println("user not found");
        }
    }
    public User getUser(String name){
        return users.get(name);
    }
}
