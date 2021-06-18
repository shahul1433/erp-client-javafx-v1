package erp.client.javafx.container.tablewithnavigation;

import erp.client.javafx.component.combobox.PageNoCombobox;
import erp.client.javafx.container.status.StatusBar;
import erp.client.javafx.icon.FontAwsomeManager;
import erp.client.javafx.layout.AbstractBorderPane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class BottomBar extends AbstractBorderPane {

    //Bottom bar components
    protected Button previous, next;
    protected Text info;
    protected PageNoCombobox pageNo;
    protected ComboBox<Integer> itemsPerPage;
    protected StatusBar statusBar;

    @Override
    public void init() {
        this.getStylesheets().add(getClass().getResource("tableStyle.css").toExternalForm());
        this.setId("bottombar");
        previous = new Button("\uf04a");
        previous.setFont(FontAwsomeManager.getSolidFontPlain(14));

        next = new Button("\uf04e");
        next.setFont(FontAwsomeManager.getSolidFontPlain(14));

        info = new Text("Showing 0 - 0 of 0");
        info.setFill(Color.WHITE);
        pageNo = new PageNoCombobox();
        itemsPerPage = new ComboBox<>();
        itemsPerPage.getItems().addAll(40, 80, 100, 200, 500);
        itemsPerPage.getSelectionModel().select(0);
        statusBar = new StatusBar();
    }

    @Override
    public void designGUI() {
        HBox navPane = new HBox(5);
        navPane.getChildren().addAll(previous, pageNo, next);
        navPane.setPadding(new Insets(10));

        HBox itemsPerPagePane = new HBox(5);
        Label label = new Label("Display");
        itemsPerPagePane.getChildren().addAll(label, itemsPerPage);
        HBox.setMargin(label, new Insets(3));
        itemsPerPagePane.setPadding(new Insets(10));

        this.setLeft(navPane);
        this.setCenter(info);
        this.setRight(itemsPerPagePane);
        this.setBottom(statusBar);
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public boolean checkSecurity() {
        return true;
    }

    public Button getPrevious() {
        return previous;
    }

    public Button getNext() {
        return next;
    }

    public PageNoCombobox getPageNo() {
        return pageNo;
    }

    public ComboBox<Integer> getItemsPerPage() {
        return itemsPerPage;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }

    public Text getInfo() {
        return info;
    }
}
