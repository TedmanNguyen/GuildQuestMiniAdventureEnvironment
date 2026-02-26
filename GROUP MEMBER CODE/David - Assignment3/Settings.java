public class Settings {
    private Theme theme;
    private Realm currentRealm;
    private TimeDisplayPreference timeDisplayPreference;

    public Settings() {
        this.theme = Theme.CLASSIC;
        this.currentRealm = null; // Or some default realm
        this.timeDisplayPreference = TimeDisplayPreference.WORLD;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
        System.out.println("Theme set to: " + theme);
    }

    public Realm getCurrentRealm() {
        return currentRealm;
    }

    public void setCurrentRealm(Realm currentRealm) {
        this.currentRealm = currentRealm;
        System.out.println("Current realm set to: " + currentRealm.getName());
    }

    public TimeDisplayPreference getTimeDisplayPreference() {
        return timeDisplayPreference;
    }

    public void setTimeDisplayPreference(TimeDisplayPreference timeDisplayPreference) {
        this.timeDisplayPreference = timeDisplayPreference;
        System.out.println("Time display preference set to: " + timeDisplayPreference);
    }

//    move method
    public void viewSettings() {
        System.out.println("--- Your Settings ---");
        System.out.println("Theme: " + theme);
        System.out.println("Time Display: " + timeDisplayPreference);
        if (currentRealm != null) {
            System.out.println("Current Realm: " + currentRealm.getName());
        } else {
            System.out.println("Current Realm: Not set");
        }
    }
}

