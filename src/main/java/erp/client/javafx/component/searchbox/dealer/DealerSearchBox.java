package erp.client.javafx.component.searchbox.dealer;

import erp.client.javafx.entity.TDealer;
import erp.client.javafx.icon.FontAwsomeManager;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.Region;

public class DealerSearchBox extends Region {

    private ProgressBar progressBar;
    private DealerSearchCombobox combobox;
    private Label clearButton;

    public DealerSearchBox() {
        this.getStylesheets().add(DealerSearchBox.class.getResource("style.css").toExternalForm());
        progressBar = new ProgressBar();
        progressBar.setVisible(false);
        combobox = new DealerSearchCombobox(progressBar);
        combobox.getEditor().setPadding(new Insets(15,25,5,5));
        combobox.setPrefWidth(300);

        clearButton = new Label("\uf057");
        clearButton.setId("clear-button");
        clearButton.setFont(FontAwsomeManager.getSolidFontPlain(18));
        clearButton.setTooltip(new Tooltip("Clear Dealer"));
        clearButton.setVisible(false);

        getChildren().addAll(combobox, progressBar, clearButton);

        registerListeners();
    }

    @Override
    protected void layoutChildren() {
        super.layoutChildren();
        combobox.setPrefWidth(getWidth());
        progressBar.resizeRelocate(1, 1, combobox.getWidth() - 26, progressBar.getHeight() - 8);
        clearButton.resizeRelocate(combobox.getWidth() - 45, 4, clearButton.getWidth(), combobox.getHeight());
    }

    private void registerListeners() {
        clearButton.visibleProperty().bind(combobox.getEditor().textProperty().isNotEmpty());

        clearButton.setOnMouseClicked(e ->{
            combobox.getEditor().clear();
            combobox.getSelectionModel().select(null);
        });
    }

    public DealerSearchCombobox getCombobox() {
        return combobox;
    }

    public TDealer getSelectedDealer() {
        return combobox.getSelectedDealer();
    }
}
