package hw2.commands.usercommands;

import hw2.user.UserTextUI;

public class EditCampaignCommand extends UserCommand {
	
	public EditCampaignCommand(UserTextUI ui) {
		super(ui);
	}
	
	public void process() {
		ui.getEditCampaignInput();
	}
	
	public String toString() {
		return "Edit Campaign";
	}
}
