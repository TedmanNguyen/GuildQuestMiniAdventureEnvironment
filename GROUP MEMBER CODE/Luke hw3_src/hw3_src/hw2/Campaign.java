package hw2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import hw2.ui.InputManager;

public class Campaign {
	private static int totalCampaigns = 0; // used to generate IDs
	
	private int campaignId; // changed design (String -> int)
	private String title;
	private boolean isArchived;
	// every instance variable below this is not in the original design
	private ArrayList<QuestEvent> events;
	private VisabilityStatus visability;
	
	public Campaign(String title, VisabilityStatus visability) {
		this.title = title;
		this.campaignId = totalCampaigns++;
		this.visability = visability;
	}
	
	public void addEvent(QuestEvent e) {
		events.add(e);
	}
	
	// it says remoteEvent in the UML, I'm assuming it meant removeEvent
	public void removeEvent(QuestEvent e) {
		events.remove(e);
	}
	
	// every method after this is not in the original design
	public void toggleArchived() {
		isArchived = !isArchived;
	}
	
	public void toggleVisibility() {
		visability = visability == VisabilityStatus.PUBLIC ? 
					VisabilityStatus.PRIVATE :
					VisabilityStatus.PUBLIC;
	}
	
	public String toString() {
		return "#" + campaignId + ": " + title + " - " + visability;
	}
	
	public int hashCode() {
		return Objects.hash(campaignId, title, visability);
	}
	
	public boolean equals(Object other) {
		if (other instanceof Campaign)
			return toString().equals(other.toString());
		return false;
	}
	
	// getters and setters
	public String getTitle() { return title; }
	public void setTitle(String newTitle) { title = newTitle; }
	public int getId() { return campaignId; }
	
	public void editInput() {
		String newTitle = InputManager.getLineInput("Enter a new title: ",
													InputManager.stringNotEmpty);
		String archive = InputManager.getStringInput("Do you want to archive this campaign? (y or n)",
													InputManager.stringNotContainsNorY);
		setTitle(newTitle);
		isArchived = archive.toLowerCase() == "y" ? true : false;
	}
	
}