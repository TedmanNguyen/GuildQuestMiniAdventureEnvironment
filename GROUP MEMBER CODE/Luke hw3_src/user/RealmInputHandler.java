package hw2.user;

import hw2.Realm;
import hw2.ui.InputManager;

public class RealmInputHandler {
	private final User user;

    public RealmInputHandler(User user) {
        this.user = user;
    }

    public void getEnterRealmInput() {
        Realm.printRealms();
        int realm = InputManager.getIndexInput("Enter the number of the Realm: ",
                                                0, Realm.getNumRealms());
        user.enterRealm(Realm.realms.get(realm));
    }
}
