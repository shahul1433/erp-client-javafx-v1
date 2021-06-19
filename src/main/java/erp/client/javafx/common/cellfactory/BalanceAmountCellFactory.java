package erp.client.javafx.common.cellfactory;

import erp.client.javafx.dealer.Dealer;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.text.NumberFormat;
import java.util.Locale;

public class BalanceAmountCellFactory implements Callback<TableColumn<?, Double>, TableCell<?, Double>> {

    private NumberFormat rupeesFormat = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    @Override
    public TableCell<?, Double> call(TableColumn<?, Double> doubleTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(Double value, boolean b) {
                getStylesheets().add(BalanceAmountCellFactory.class.getResource("style.css").toExternalForm());
                super.updateItem(value, b);
                setStyle("-fx-alignment: center-right");
                if(value != null) {
                    setText(rupeesFormat.format(value));
                    if(value < 0) {
                        getStyleClass().add("debit-balance");
                        getStyleClass().removeAll("credit-balance");
                        getStyleClass().removeAll("zero-balance");
                    }else if(value > 0) {
                        getStyleClass().add("credit-balance");
                        getStyleClass().removeAll("debit-balance");
                        getStyleClass().removeAll("zero-balance");
                    }else {
                        getStyleClass().add("zero-balance");
                        getStyleClass().removeAll("debit-balance");
                        getStyleClass().removeAll("credit-balance");
                    }
                } else
                    setText(null);
            }
        };
    }
}
