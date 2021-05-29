package erp.client.javafx.stock.transaction;

import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.container.tablewithnavigation.TableColumnDataWrapper;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.utility.GuiUtility;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Modality;

import java.util.ArrayList;

public class StockTransactionManagementDialog extends AbstractTableWithNavigationDialog<StockTransaction> {

    private StockTransactionManagementService transactionManagementService = new StockTransactionManagementService(this);

    public StockTransactionManagementDialog() {
        if (checkSecurity()) {
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
    public void refresh() {
        transactionManagementService.getAllStockTransactions(null);
    }

    @Override
    protected void createTableColumns(ArrayList<TableColumnDataWrapper<StockTransaction, ?>> tableColumns) {
        TableColumn<StockTransaction, Void> index = new TableColumn<>("#");
        index.setCellFactory(col -> {
            TableCell<StockTransaction, Void> cell = new TableCell<>();
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

        tableColumns.add(new TableColumnDataWrapper<>("Transaction Type", "stockTransactionType"));
        tableColumns.add(new TableColumnDataWrapper<>("Product", "product"));
        tableColumns.add(new TableColumnDataWrapper<>("Amount", "amount"));
        tableColumns.add(new TableColumnDataWrapper<>("Added On", "addedDate"));

        getCenterPane().getTable().getColumns().addAll(tableColumns);
    }

    @Override
    protected void sortCall(SortMap sortMap) {
        transactionManagementService.getAllStockTransactions(sortMap);
    }

    @Override
    protected void setFilterDialog() {
        this.filterDialog = new StockTransactionFilterDialog(this);
    }

    @Override
    public boolean checkSecurity() {
        return AppSession.hasRole(UserRole.STOCK_TRANSACTION);
    }
}
