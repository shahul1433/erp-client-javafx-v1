package erp.client.javafx.common.cellfactory;

import erp.client.javafx.gst.GstStateCodeDTO;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class GstStateCodeCellFactory implements Callback<TableColumn<?, GstStateCodeDTO>, TableCell<?, GstStateCodeDTO>> {

    @Override
    public TableCell<?, GstStateCodeDTO> call(TableColumn<?, GstStateCodeDTO> gstStateCodeDTOTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(GstStateCodeDTO gstStateCodeDTO, boolean b) {
                super.updateItem(gstStateCodeDTO, b);
                setStyle("-fx-alignment: CENTER-LEFT;");
                if (gstStateCodeDTO != null) {
                    setText(gstStateCodeDTO.getCode() + " - " + gstStateCodeDTO.getState());
                } else {
                    setText(null);
                }
            }
        };
    }
}
