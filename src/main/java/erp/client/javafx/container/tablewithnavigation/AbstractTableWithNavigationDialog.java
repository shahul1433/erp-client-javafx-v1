package erp.client.javafx.container.tablewithnavigation;

import erp.client.javafx.component.event.popup.PopupEvent;
import erp.client.javafx.component.event.popup.PopupEventHandler;
import erp.client.javafx.component.event.trigger.TriggerEvent;
import erp.client.javafx.container.ChannelInterface;
import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.layout.AbstractBorderPane;
import erp.client.javafx.utility.PopupUtility;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.skin.TableColumnHeader;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.ArrayList;

/**
 * Base class for table with navigation dialog
 *
 * @param <T>
 */
public abstract class AbstractTableWithNavigationDialog<T> extends AbstractBorderPane implements ChannelInterface {

    private static Logger logger = LogManager.getLogger(AbstractTableWithNavigationDialog.class);

    protected Stage stage;

    protected TopBar topBar;
    protected CenterPane<T> centerPane;
    protected BottomBar bottomBar;

    protected AbstractFilterDialog filterDialog;

    public AbstractTableWithNavigationDialog() {
        if (!checkSecurity()) {
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

    protected abstract void sortCall(SortMap sortMap);

    /**
     * Please set "this.filterDialog" inside this method
     */
    protected abstract void setFilterDialog();

    @Override
    public void init() {
        this.stage = new Stage();
        this.stage.addEventHandler(PopupEvent.ANY, new PopupEventHandler(stage)); //This will show popup of message
        this.stage.addEventHandler(TriggerEvent.REFRESH, new EventHandler<TriggerEvent>() {
            @Override
            public void handle(TriggerEvent triggerEvent) {
                refresh();
            }
        });

        topBar = new TopBar();
        centerPane = new CenterPane();
        bottomBar = new BottomBar();

        createTableColumns(centerPane.tableColumns);
        setFilterDialog();
    }

    @Override
    public void designGUI() {
        this.setTop(topBar);
        this.setCenter(centerPane);
        this.setBottom(bottomBar);
    }

    @Override
    public void registerListeners() {

        bottomBar.previous.setOnAction(e -> {
            bottomBar.pageNo.getSelectionModel().selectPrevious();
        });

        bottomBar.next.setOnAction(e -> {
            bottomBar.pageNo.getSelectionModel().selectNext();
        });

        bottomBar.pageNo.setOnAction(e -> {
            refresh();
        });

        bottomBar.itemsPerPage.setOnAction(e -> {
            if (bottomBar.pageNo.getSelectionModel().isSelected(0)) {
                refresh();
            } else {
                bottomBar.pageNo.getSelectionModel().selectFirst();
            }
        });

        centerPane.table.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {
            if (e.getTarget() instanceof TableColumnHeader) {
                final TableColumnHeader columnHeader = (TableColumnHeader) e.getTarget();
                String columnHeaderStr = columnHeader.getTableColumn().getText();

                SortMap sortMap = null;
                for (TableColumnDataWrapper<T, ?> col : centerPane.tableColumns) {
                    if (col.getDisplayName().equals(columnHeaderStr)) {
                        sortMap = new SortMap(col.getDbAttributeName(), col.getSortOrderString());
                        col.changeSortOrder();
                        break;
                    }
                }
                sortCall(sortMap);
            }
        });

        topBar.filter.setOnAction(e -> {
            if(this.filterDialog == null) {
                logger.error("Please provide filter dialog implementation 'this.filterDialog' by override 'setFilterDialog()' on extended/child class");
            }else {
                this.filterDialog.showFilter();
            }
        });

        topBar.refresh.setOnAction(e -> {
            refresh();
        });

        topBar.clearFilter.setOnAction(e -> {
            topBar.clearFilter.setVisible(false);
            this.filterDialog.clearFilterFields();
            refresh();
        });
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

    public AbstractFilterDialog getFilterDialog() {
        return filterDialog;
    }

    public void setStatusBarStatus(StatusBarStatus status) {
        this.bottomBar.getStatusBar().setStatus(status);
    }

    public void setTopBar(TopBar topBar) {
        this.topBar = topBar;
    }

    public void setCenterPane(CenterPane<T> centerPane) {
        this.centerPane = centerPane;
    }

    public void setBottomBar(BottomBar bottomBar) {
        this.bottomBar = bottomBar;
    }

    public void showMessage(Alert.AlertType alertType, String message) {
        stage.fireEvent(new PopupEvent<>(alertType, message));
    }
}
