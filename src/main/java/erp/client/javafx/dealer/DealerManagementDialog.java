package erp.client.javafx.dealer;

import erp.client.javafx.common.AddEditRemoveTopBar;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.container.tablewithnavigation.TableColumnDataWrapper;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.utility.GuiUtility;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Modality;

import java.util.ArrayList;

public class DealerManagementDialog extends AbstractTableWithNavigationDialog<Dealer> {

    private DealerManagementService dealerManagementService = new DealerManagementService(this);
    private DealerManagementTopBar dealerManagementTopBar;

    public DealerManagementDialog() {
        super();
        if(checkSecurity()) {
            Scene scene = new Scene(this, GuiUtility.maximumSize().getWidth(), GuiUtility.maximumSize().getHeight());
            getStage().setScene(scene);
            getStage().getIcons().add(new Image(getClass().getResourceAsStream("/image/User.png")));
            getStage().setTitle("Dealer Management");
            getStage().initModality(Modality.APPLICATION_MODAL);
            getStage().show();

            refresh(); //Initial call.
        }
    }

    @Override
    public void init() {
        super.init();
        dealerManagementTopBar = new DealerManagementTopBar();
        setTopBar(dealerManagementTopBar);
    }

    @Override
    public void refresh() {
        dealerManagementService.getAllDealers(null);
    }

    @Override
    protected void createTableColumns(ArrayList<TableColumnDataWrapper<Dealer, ?>> tableColumns) {
        TableColumn<Dealer, Void> index = new TableColumn<>("#");
        index.setCellFactory(col -> {
            TableCell<Dealer, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null ;
                } else {
                    return Integer.toString((getBottomBar().getPageNo().getSelectionModel().getSelectedIndex()*getBottomBar().getItemsPerPage().getValue()) + (cell.getIndex()+1));
                }
            }, cell.emptyProperty(), cell.indexProperty()));
            return cell ;
        });
        getCenterPane().getTable().getColumns().add(index);

        tableColumns.add(new TableColumnDataWrapper<>("Name", "name"));
        tableColumns.add(new TableColumnDataWrapper<>("Shop", "shop"));
        tableColumns.add(new TableColumnDataWrapper<>("Email", "email"));
        tableColumns.add(new TableColumnDataWrapper<>("Phone", "phone"));
        tableColumns.add(new TableColumnDataWrapper<>("GSTIN", "gstin"));
        tableColumns.add(new TableColumnDataWrapper<>("GST State Code", "gstStateCode"));
        TableColumnDataWrapper<Dealer, Object> balanceColumn = new TableColumnDataWrapper<>("Balance", "balance");
        balanceColumn.setStyle("-fx-alignment: CENTER-RIGHT;");
        tableColumns.add(balanceColumn);
        tableColumns.add(new TableColumnDataWrapper<>("Added On", "addedDate"));
        tableColumns.add(new TableColumnDataWrapper<>("Modified On", "modifiedDate"));

        getCenterPane().getTable().getColumns().addAll(tableColumns);
    }

    @Override
    public void registerListeners() {
        super.registerListeners();

        this.getCenterPane().getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            int size = this.getCenterPane().getTable().getSelectionModel().getSelectedItems().size();
            dealerManagementTopBar.getEdit().setDisable(size != 1);
            dealerManagementTopBar.getRemove().setDisable(size == 0);
        });

        this.getCenterPane().getTable().setOnMouseClicked(e -> {
            if (e.getClickCount() >= 2) {

            }
        });
    }

    @Override
    protected void sortCall(SortMap sortMap) {
        dealerManagementService.getAllDealers(sortMap);
    }

    @Override
    protected void setFilterDialog() {
        this.filterDialog = new DealerFilterDialog(this);
    }

    @Override
    public boolean checkSecurity() {
        return AppSession.hasRole(UserRole.DEALER);
    }

    class DealerManagementTopBar extends AddEditRemoveTopBar {

        @Override
        protected void setOnAction() {
            getAdd().setOnAction(e -> new AddEditDealerDialog(getStage(), StageMode.ADD, null));

            getEdit().setOnAction(e -> {
                Dealer selectedItem = getCenterPane().getTable().getSelectionModel().getSelectedItem();
                new AddEditDealerDialog(getStage(), StageMode.EDIT, selectedItem.getDealer());
            });

            getRemove().setOnAction(e -> {
                int size = getCenterPane().getTable().getSelectionModel().getSelectedIndices().size();
                Alert alert = PopupUtility.showMessage(getStage(), Alert.AlertType.CONFIRMATION, "Are you sure to delete selected " + (size > 1 ? "Dealers" : "Dealer") + " ?");
                alert.showAndWait()
                    .filter(res -> res == ButtonType.OK)
                        .ifPresent(res -> dealerManagementService.removeDealer());
            });
        }
    }
}
