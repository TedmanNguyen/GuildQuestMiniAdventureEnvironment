package hw2.user;

import java.util.ArrayList;
import hw2.Campaign;
import hw2.VisabilityStatus;

public class CampaignManager {
	private ArrayList<Campaign> campaigns;
	
	CampaignManager() {
		campaigns = new ArrayList<Campaign>();
	}
	
	public void createCampaign(String name, VisabilityStatus visability) {
		campaigns.add(new Campaign(name, visability));
	}
	
	public void removeCampaign(int index) {
		campaigns.remove(index);
	}
	
	public Campaign getCampaign(int index) {
		return campaigns.get(index);
	}
	
	public void printCampaigns() {
		System.out.println("CAMPAIGNS");
		System.out.println("--------------------");
		for (int i = 0; i < campaigns.size(); ++i)
			System.out.println(i + ": " + campaigns.get(i));
	}
	
	public boolean isEmpty() {
		return campaigns.isEmpty();
	}
	
	public int numCampaigns() {
		return campaigns.size();
	}
}
