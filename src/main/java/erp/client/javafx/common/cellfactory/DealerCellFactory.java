package erp.client.javafx.common.cellfactory;

import erp.client.javafx.dealer.DealerDTO;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class DealerCellFactory implements Callback<TableColumn<?, DealerDTO>, TableCell<?, DealerDTO>> {
    @Override
    public TableCell<?, DealerDTO> call(TableColumn<?, DealerDTO> dealerDTOTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(DealerDTO dealerDTO, boolean b) {
                super.updateItem(dealerDTO, b);
                setStyle("-fx-alignment: CENTER-LEFT;");
                if (dealerDTO != null) {
                    setText(dealerDTO.getName());
                } else {
                    setText(null);
                }
            }
        };
    }
}
