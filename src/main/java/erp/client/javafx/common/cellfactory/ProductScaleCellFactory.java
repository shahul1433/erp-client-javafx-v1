package erp.client.javafx.common.cellfactory;

import erp.client.javafx.component.enums.ProductScale;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class ProductScaleCellFactory implements Callback<TableColumn<?, ProductScale>, TableCell<?, ProductScale>> {

    @Override
    public TableCell<?, ProductScale> call(TableColumn<?, ProductScale> productScaleTableColumn) {
        return new TableCell<>() {
            @Override
            protected void updateItem(ProductScale productScale, boolean b) {
                super.updateItem(productScale, b);
                setStyle("-fx-alignment: CENTER;");
                if (productScale != null) {
                    setText(productScale.getRepresentation());
                } else {
                    setText(null);
                }
            }
        };
    }
}
