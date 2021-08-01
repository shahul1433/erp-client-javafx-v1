package erp.client.javafx.stock.transaction;

import erp.client.javafx.common.cellfactory.DateCellFactory;
import erp.client.javafx.common.cellfactory.RupeesCellFactory;
import erp.client.javafx.common.cellfactory.StockInCellFactory;
import erp.client.javafx.common.cellfactory.StockTransactionTypeCellFactory;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.container.tablewithnavigation.TableColumnDataWrapper;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.utility.GuiUtility;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Modality;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class StockTransactionManagementDialog extends AbstractTableWithNavigationDialog<StockTransaction> {

    private StockTransactionManagementService transactionManagementService = new StockTransactionManagementService(this);

    public StockTransactionManagementDialog() {
        if (checkSecurity()) {
            Scene scene = new Scene(this, GuiUtility.maximumSize().getWidth(), GuiUtility.maximumSize().getHeight());
            getStage().setScene(scene);
            getStage().getIcons().add(new Image(getClass().getResourceAsStream("/image/User.png")));
            getStage().setTitle("Stock Transactions History");
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
        index.setStyle("-fx-alignment: CENTER;");
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

        TableColumnDataWrapper<StockTransaction, StockTransactionType> transactionTypeColumn = new TableColumnDataWrapper<>("Transaction Type", "stockTransactionType", new StockTransactionTypeCellFactory());
        TableColumnDataWrapper<StockTransaction, StockInDTO> stockInColumn = new TableColumnDataWrapper<>("Product", "product", new StockInCellFactory());
        TableColumnDataWrapper<StockTransaction, Double> amountColumn = new TableColumnDataWrapper<>("Amount", "amount", new RupeesCellFactory());
        TableColumnDataWrapper<StockTransaction, LocalDateTime> addedDateColumn = new TableColumnDataWrapper<>("Added On", "addedDate", new DateCellFactory());

        tableColumns.add(transactionTypeColumn);
        tableColumns.add(stockInColumn);
        tableColumns.add(amountColumn);
        tableColumns.add(addedDateColumn);

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
