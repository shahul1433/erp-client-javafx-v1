package erp.client.javafx.common.cellfactory;

import erp.client.javafx.stock.stockin.StockInDTO;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class StockItemCellFactory implements Callback<TableColumn<?, StockInDTO>, TableCell<?, StockInDTO>> {
    @Override
    public TableCell<?, StockInDTO> call(TableColumn<?, StockInDTO> stockInDTOTableColumn) {
        return new TableCell<>() {
            @Override
            protected void updateItem(StockInDTO stockInDTO, boolean b) {
                super.updateItem(stockInDTO, b);
                setStyle("-fx-alignment: CENTER-LEFT;");
                if (stockInDTO != null) {
                    setText(stockInDTO.getItemBriefInfo());
                } else {
                    setText(null);
                }
            }
        };
    }
}
