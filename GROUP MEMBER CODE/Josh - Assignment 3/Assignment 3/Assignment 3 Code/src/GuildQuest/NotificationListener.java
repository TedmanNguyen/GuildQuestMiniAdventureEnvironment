package GuildQuest;

// === A3 CHANGES START: AI-Assisted Design Pattern (Observer Pattern) ===
// Observer interface: "listeners" can receive notifications from CampaignManager (the Subject).
public interface NotificationListener {
    void notify(String message);
}
// === A3 CHANGES END: AI-Assisted Design Pattern (Observer Pattern) ===