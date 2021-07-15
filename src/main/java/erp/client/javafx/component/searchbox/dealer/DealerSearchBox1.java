package erp.client.javafx.component.searchbox.dealer;

import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.icon.FontAwesomeManager;
import erp.client.javafx.layout.AbstractBorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

public class DealerSearchBox1 extends AbstractBorderPane {

    private DealerSearchCombobox combobox;
    private Button clear;
    private ProgressBar progressBar;

    public DealerSearchBox1() {

    }

    @Override
    public void init() {
        this.getStylesheets().add(DealerSearchBox.class.getResource("style.css").toExternalForm());
        progressBar = new ProgressBar();
        combobox = new DealerSearchCombobox(progressBar);

        clear = new Button("\uf057");
        clear.setId("clear-button");
        clear.setFont(FontAwesomeManager.getSolidFontPlain(13));
        clear.setTooltip(new Tooltip("Clear Dealer"));
    }

    @Override
    public void designGUI() {
        this.setTop(progressBar);

        HBox box = new HBox();
        box.getChildren().addAll(combobox, clear);
        HBox.setHgrow(combobox, Priority.ALWAYS);

        this.setCenter(box);
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public boolean checkSecurity() {
        return false;
    }

    public DealerDTO getSelectedDealer() {
        return combobox.getSelectedDealer();
    }

    public DealerSearchCombobox getCombobox() {
        return combobox;
    }

    public Button getClear() {
        return clear;
    }

    public ProgressBar getProgressBar() {
        return progressBar;
    }
}
