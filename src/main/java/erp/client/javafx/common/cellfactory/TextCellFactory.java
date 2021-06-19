package erp.client.javafx.common.cellfactory;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class TextCellFactory implements Callback<TableColumn<?, String>, TableCell<?, String>> {

    @Override
    public TableCell<?, String> call(TableColumn<?, String> stringTableColumn) {
        return new TableCell<>() {
            @Override
            protected void updateItem(String s, boolean b) {
                super.updateItem(s, b);
                setStyle("-fx-alignment: CENTER-LEFT;");
                if (s != null) {
                    setText(s);
                } else {
                    setText(null);
                }
            }
        };
    }
}
