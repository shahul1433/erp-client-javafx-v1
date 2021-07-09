package erp.client.javafx.container.tablewithnavigation;

import erp.client.javafx.layout.AbstractBorderPane;
import javafx.geometry.Insets;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;

import java.util.ArrayList;

public class CenterPane<T> extends AbstractBorderPane {

    //Components
    protected TableView<T> table;
    protected ArrayList<TableColumnDataWrapper<T, ?>> tableColumns = new ArrayList<>();

    @Override
    public void init() {
        this.getStylesheets().add(CenterPane.class.getResource("tableStyle.css").toExternalForm());
        table = new TableView<>();
        table.setTableMenuButtonVisible(true);
//        createTableColumns(tableColumns);
        table.setId("table");
        table.setStyle("-fx-selection-bar: #99ebff; -fx-selection-bar-non-focused: #ccf5ff;");
        table.setPadding(new Insets(5));
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
    }

    @Override
    public void designGUI() {
        this.setCenter(table);
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public boolean checkSecurity() {
        return true;
    }

    public TableView<T> getTable() {
        return table;
    }

    public ArrayList<TableColumnDataWrapper<T, ?>> getTableColumns() {
        return tableColumns;
    }
}
