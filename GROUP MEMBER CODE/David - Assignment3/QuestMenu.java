import java.util.Scanner;

public class QuestMenu implements Menu{
    private RPGManagerFacade facade;
    private Scanner scanner;
    QuestMenu(RPGManagerFacade facade, Scanner scanner){
        this.facade = facade;
        this.scanner = scanner;
    }

    public void display(){
        System.out.println("\n\n\n\n\n\n--- Quest Menu ---");
        facade.printWorldTime();
        System.out.println("1. Add quest | 2. Remove quest | 3. Update Quest | 4. View Quests");

        facade.getCurrentUser().displayCampaigns();
    }
}
