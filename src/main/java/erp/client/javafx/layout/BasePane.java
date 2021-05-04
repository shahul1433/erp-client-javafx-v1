package erp.client.javafx.layout;

import erp.client.javafx.utility.PopupUtility;
import javafx.scene.control.Alert;

public interface BasePane {
    public void init();
    public void designGUI();
    public void registerListeners();
    public boolean checkSecurity();

//    public default void showMessage(Alert.AlertType alertType, String message) {
//        PopupUtility.showMessage(alertType, message);
//    }
}
