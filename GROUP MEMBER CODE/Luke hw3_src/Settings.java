package hw2;

public class Settings {
	
//	public static GameTime worldTime
	private UITheme uiTheme;
	// new instance variables
	private Realm currentRealm;
	private TimeDisplayPreference timeDisplay;
	
	public Settings() {
		uiTheme = UITheme.CONSOLE_TEXT;
		currentRealm = Realm.realms.get(0);
		timeDisplay = TimeDisplayPreference.SHOW_BOTH;
	}
	
	public void updateTheme(UITheme newTheme) {
		uiTheme = newTheme;
	}
	
	// getters and setters
	public Realm getCurrentRealm() { return currentRealm; }
	public void setCurrentRealm(Realm r) { currentRealm = r; }
	public TimeDisplayPreference getTimeDisplayPreference() { return timeDisplay; }
	public void setTimeDisplayPreference(TimeDisplayPreference t) { timeDisplay = t; }
	
	
}
