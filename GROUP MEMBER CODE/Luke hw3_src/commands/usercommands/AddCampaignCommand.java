package hw2.commands.usercommands;

import hw2.user.UserTextUI;

public class AddCampaignCommand extends UserCommand {
	
	public AddCampaignCommand(UserTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getAddCampaignInput();
	}
	
	public String toString() {
		return "Add Campaign";
	}
}
