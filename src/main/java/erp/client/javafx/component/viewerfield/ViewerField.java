package erp.client.javafx.component.viewerfield;

import erp.client.javafx.component.FormField;
import erp.client.javafx.component.font.CustomFontManager;
import erp.client.javafx.component.label.CLabel;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.layout.AbstractHBoxPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

abstract public class ViewerField extends AbstractHBoxPane {

    protected CLabel label;
    protected TextField textField;
    protected Button button;

    public ViewerField(Arguments arguments) {
        super(arguments);
    }

    @Override
    public void init() {
        this.getStylesheets().add(FormField.class.getResource("style.css").toExternalForm());
        label = new CLabel();
        textField = new TextField();
        textField.setEditable(false);
        textField.getStyleClass().add("read-only");
        textField.setFont(new CustomFontManager().getRobotoFont(12));
        button = new Button("view");
    }

    @Override
    public void designGUI() {
        setSpacing(5);
        getChildren().addAll(textField, button);
        HBox.setHgrow(textField, Priority.ALWAYS);
    }

    @Override
    public void registerListeners() {
        button.setOnAction(e -> onButtonClick());
    }

    @Override
    public boolean checkSecurity() {
        return false;
    }

    public Label getLabel() {
        return label;
    }

    protected abstract void onButtonClick();
}
