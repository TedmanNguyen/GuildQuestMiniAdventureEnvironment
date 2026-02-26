import java.util.Scanner;

public class MainMenu implements Menu{

    private static RPGManagerFacade facade;
    private static Scanner scanner;
    MainMenu(RPGManagerFacade facade, Scanner scanner){
        this.facade = facade;
        this.scanner = scanner;
    }
    public void display(){
        System.out.println("\n--- Main Menu ---");
        if (facade.getCurrentUser().getUserSettings().getTimeDisplayPreference() == TimeDisplayPreference.WORLD){
            facade.printWorldTime();
        }
        else{
            facade.printRealmTime();
        }
        System.out.println("1. Campaigns | 2. Quests | 3. Settings | 4. Log Out");
    }
}
