package erp.client.javafx.common.cellfactory;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateCellFactory implements Callback<TableColumn<?, LocalDateTime>, TableCell<?, LocalDateTime>> {
    @Override
    public TableCell<?, LocalDateTime> call(TableColumn<?, LocalDateTime> localDateTimeTableColumn) {
        return new TableCell<>(){
            @Override
            protected void updateItem(LocalDateTime dateTime, boolean b) {
                super.updateItem(dateTime, b);
                setStyle("-fx-alignment: center;");
                if(dateTime != null) {
                    setText(dateTime.format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
                }else {
                    setText(null);
                }
            }
        };
    }
}
