import java.util.Scanner;

public class SettingsMenu implements Menu{
    private static RPGManagerFacade facade;
    private static Scanner scanner;

    SettingsMenu(RPGManagerFacade facade, Scanner scanner){
        this.facade = facade;
        this.scanner = scanner;
    }
    public void display(){
        System.out.println("\n--- Settings ---");
        System.out.println("1. Change Theme | 2. Set Current Realm | 3. Set Time Display | 4. View Settings | 5. Back");
    }
}
