package hw2;

public class QuestEvent {
	
	private String eventId;
	private String title;
	// new variables start here
	private GameTime start;
	private GameTime end;
	private Realm realm;
	
	public QuestEvent(String title, GameTime start, GameTime end, Realm realm) {
		// ID is generated?
		this.title = title;
		this.start = start;
		this.end = end;
		this.realm = realm;
	}
	
	public void updateTime(GameTime start, GameTime end) {
		this.start = start;
		this.end = end;
	}
	
	// getters and setters
	public GameTime getStart() { return start; }
	public GameTime getEnd() { return end; }
	public GameTime getStartRealmTime() { return start; }
	public GameTime getEndRealmTime() { return end; }
	public Realm getRealm() { return realm; }
	public void setRealm(Realm r) { realm = r; }
	public void setTitle(String newTitle) { title = newTitle; }
	public String getTitle() { return title; }
	
	public String toString() {
		return "#" + eventId + ": " + "\"" + title + "\"" + "\nStart time (World Clock): "
				+ start + "\nEnd time (World Clock): " + end
				+ "\nStart time (Realm Clock): " + getStartRealmTime() +
				"\nEnd time (Realm Clock): " + getEndRealmTime() + "\n";
	}
}