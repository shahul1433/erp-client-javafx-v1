package erp.client.javafx.component.date;

import erp.client.javafx.component.FormField;
import erp.client.javafx.utility.PopupUtility;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class DateField extends DatePicker implements FormField {


    @Override
    public boolean validateField() {
        boolean isValid = getValue() != null;
        if(!isValid) {
            PopupUtility.showMessage(Alert.AlertType.ERROR, "Please choose a valid date");
        }
        return true;
    }

    @Override
    public void clearField() {
        setValue(null);
    }

    @Override
    public void setReadOnly(boolean isReadOnly) {

    }
}
