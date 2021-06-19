package erp.client.javafx.common.cellfactory;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.NumberFormat;
import java.util.Locale;

public class RupeesCellFactory implements Callback<TableColumn<?, Double>, TableCell<?, Double>> {

    private NumberFormat rupeesFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    @Override
    public TableCell<?, Double> call(TableColumn<?, Double> doubleTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(Double aDouble, boolean b) {
                super.updateItem(aDouble, b);
                setStyle("-fx-alignment: CENTER-RIGHT;");
                if (aDouble != null) {
                    setText(rupeesFormat.format(aDouble));
                } else {
                    setText(null);
                }
            }
        };
    }
}
