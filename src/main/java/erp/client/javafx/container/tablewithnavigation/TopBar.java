package erp.client.javafx.container.tablewithnavigation;

import erp.client.javafx.icon.FontAwesomeManager;
import erp.client.javafx.layout.AbstractBorderPane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class TopBar extends AbstractBorderPane {

    //Top bar components
    protected Button filter, refresh, clearFilter;

    @Override
    public void init() {
        this.getStylesheets().add(TopBar.class.getResource("tableStyle.css").toExternalForm());
        this.setId("topbar");
        filter = new Button("\uf0b0");
        filter.setFont(FontAwesomeManager.getSolidFontPlain(14));
        filter.setTooltip(new Tooltip("Filter"));

        refresh = new Button("\uf2f1");
        refresh.setFont(FontAwesomeManager.getSolidFontPlain(14));
        refresh.setTooltip(new Tooltip("Refresh"));

        Image image = new Image(getClass().getResourceAsStream("/image/clear_filter.png"));
        clearFilter = new Button("Clear Filter", new ImageView(image));
        clearFilter.setVisible(false);
        clearFilter.setStyle("-fx-base: red");
        clearFilter.setTooltip(new Tooltip("Clear Filter"));
    }

    @Override
    public void designGUI() {
        HBox buttonPane = new HBox(5);
        buttonPane.getChildren().addAll(clearFilter, refresh, filter);
        buttonPane.setPadding(new Insets(10));

        this.setRight(buttonPane);
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public boolean checkSecurity() {
        return true;
    }

    public Button getFilter() {
        return filter;
    }

    public Button getRefresh() {
        return refresh;
    }

    public Button getClearFilter() {
        return clearFilter;
    }
}