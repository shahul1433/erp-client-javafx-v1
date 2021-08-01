package erp.client.javafx.common.cellfactory;

import erp.client.javafx.stock.transaction.StockTransactionType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class StockTransactionTypeCellFactory implements Callback<TableColumn<?, StockTransactionType>, TableCell<?, StockTransactionType>> {
    @Override
    public TableCell<?, StockTransactionType> call(TableColumn<?, StockTransactionType> stockTransactionTypeTableColumn) {
        return new TableCell<>() {
            @Override
            protected void updateItem(StockTransactionType stockTransactionType, boolean b) {
                super.updateItem(stockTransactionType, b);
                if (stockTransactionType != null) {
                    setText(stockTransactionType.getType());
                } else {
                    setText(null);
                }
            }
        };
    }
}
