package erp.client.javafx.component.objectchooser;

import erp.client.javafx.component.FormField;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.icon.FontAwesomeManager;
import erp.client.javafx.layout.AbstractHBoxPane;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public abstract class BaseChooser<T> extends AbstractHBoxPane implements FormField {

    protected Label label;
    protected TextField textField;
    protected Button select, view, clear;

    /**
     * use this attribute where ever you want to listen changes in the value.
     */
    private SimpleObjectProperty<T> object;

    /**
     *
     * @param arguments
     * Parameters required in the arguments:
     *  - name
     */
    public BaseChooser(Arguments arguments) {
        super(arguments);
    }

    @Override
    public void init() {
        object = new SimpleObjectProperty<>();
        label = new Label(arguments.getArgument("name", String.class));
        textField = new TextField();
        textField.setEditable(false);

        select = new Button("......");
        select.setStyle("-fx-base: #00cc00;");
        select.setTooltip(new Tooltip("Select Item"));

        view = new Button("\uf06e");
        view.setFont(FontAwesomeManager.getSolidFontPlain(14));
        view.setTooltip(new Tooltip("View"));
        view.setStyle("-fx-base: #00ccff;");

        clear = new Button("\uf00d");
        clear.setFont(FontAwesomeManager.getSolidFontPlain(14));
        clear.setTooltip(new Tooltip("Clear"));
        clear.setStyle("-fx-base: red;");
    }

    @Override
    public void designGUI() {
        setSpacing(5);
        getChildren().addAll(textField, select, view, clear);
        HBox.setHgrow(textField, Priority.ALWAYS);
    }

    @Override
    public void registerListeners() {
        select.setOnAction(e -> openChooserWindow());

        view.setOnAction(e -> openObjectViewer());
        view.disableProperty().bind(textField.textProperty().isEmpty());

        clear.disableProperty().bind(textField.textProperty().isEmpty());
        clear.setOnAction(e -> {
            objectProperty().set(null);
        });

    }

    public Label getLabel() {
        return label;
    }

    public TextField getTextField() {
        return textField;
    }

    public Button getSelect() {
        return select;
    }

    public Button getClear() {
        return clear;
    }

    public Object getObject() {
        return object.get();
    }

    public SimpleObjectProperty<T> objectProperty() {
        return object;
    }

    protected abstract void openChooserWindow();
    protected abstract void openObjectViewer();
}
