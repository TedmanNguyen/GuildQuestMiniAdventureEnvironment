import java.util.Scanner;

public class CampaignMenu implements Menu{

    private RPGManagerFacade facade;
    private Scanner scanner;

    CampaignMenu(RPGManagerFacade facade, Scanner scanner){
        this.facade = facade;
        this.scanner = scanner;
    }
    public void display(){
        System.out.println("\n\n\n\n\n\n--- Campaign Menu ---");
        facade.printWorldTime();
        System.out.println("1. Add Campaign | 2. Remove Campaign | 3. Update Campaign  | 4. View Campaigns");
    }
}
