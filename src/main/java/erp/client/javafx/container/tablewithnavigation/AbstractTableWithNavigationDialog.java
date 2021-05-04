package erp.client.javafx.container.tablewithnavigation;

import erp.client.javafx.component.event.PopupEvent;
import erp.client.javafx.component.event.PopupEventHandler;
import erp.client.javafx.container.ChannelInterface;
import erp.client.javafx.layout.AbstractBorderPane;
import erp.client.javafx.utility.PopupUtility;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.stage.Stage;

import java.util.ArrayList;

/**
 * Base class for table with navigation dialog
 * @param <T>
 */
public abstract class AbstractTableWithNavigationDialog<T> extends AbstractBorderPane implements ChannelInterface {

    protected Stage stage;

    protected TopBar topBar;
    protected CenterPane<T> centerPane;
    protected BottomBar bottomBar;

    public AbstractTableWithNavigationDialog() {
        if(!checkSecurity()) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    PopupUtility.showMessage(Alert.AlertType.WARNING, "Sorry, you don't have permission to access this module");
                }
            });
            return;
        }
    }

    protected abstract void createTableColumns(ArrayList<TableColumnDataWrapper<T, ?>> tableColumns);

    @Override
    public void init() {
        this.stage = new Stage();
        this.stage.addEventHandler(PopupEvent.ANY, new PopupEventHandler(stage)); //This will show popup of message

        topBar = new TopBar();
        centerPane = new CenterPane();
        bottomBar = new BottomBar();

        createTableColumns(centerPane.tableColumns);
    }

    @Override
    public void designGUI() {
        this.setTop(topBar);
        this.setCenter(centerPane);
        this.setBottom(bottomBar);
    }

    @Override
    public void registerListeners() {

    }

    public Stage getStage() {
        return stage;
    }

    public TopBar getTopBar() {
        return topBar;
    }

    public CenterPane<T> getCenterPane() {
        return centerPane;
    }

    public BottomBar getBottomBar() {
        return bottomBar;
    }
}
