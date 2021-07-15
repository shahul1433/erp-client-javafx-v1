package erp.client.javafx.component.date;

import erp.client.javafx.component.FormField;
import erp.client.javafx.component.font.CustomFontManager;
import erp.client.javafx.component.label.CLabel;
import erp.client.javafx.utility.PopupUtility;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;

public class DateField extends DatePicker implements FormField {

    private CLabel label;
    private boolean isMandatoryField;

    public DateField(String attributeName, boolean isMandatoryField) {
        this.isMandatoryField = isMandatoryField;
        label = new CLabel();
        label.setText(attributeName + (isMandatoryField ? " *" : ""));
        this.getEditor().setFont(new CustomFontManager().getRobotoFont(12));
    }

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
        setEditable(!isReadOnly);
        if (isReadOnly) {
            this.getEditor().setStyle("-fx-background-color: #ccf5ff; -fx-border-color: #00b8e6; -fx-border-radius: 3px 0px 0px 3px;");
        } else {
        }
    }

    public CLabel getLabel() {
        return label;
    }
}
