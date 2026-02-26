package hw2.user;

import hw2.VisabilityStatus;
import hw2.ui.InputManager;

public class CampaignInputHandler {
	private final User user;

    public CampaignInputHandler(User user) {
        this.user = user;
    }

    public void getAddCampaignInput() {
        String title = InputManager.getLineInput("Enter the campaign title: ",
                                                  InputManager.stringNotEmpty);
        int visBuffer = InputManager.getIndexInput(
            "Enter the campaign visibility (0 for public, 1 for private): ", 0, 2);
        user.createCampaign(title,
            visBuffer == 0 ? VisabilityStatus.PUBLIC : VisabilityStatus.PRIVATE);
    }

    public void getEditCampaignInput() {
        if (user.campaignManager.isEmpty()) return;

        user.campaignManager.printCampaigns();
        int i = InputManager.getIndexInput(
            "Enter the number of the campaign you wish to edit: ",
            0, user.campaignManager.numCampaigns());
        user.campaignManager.getCampaign(i).editInput();
    }
}
