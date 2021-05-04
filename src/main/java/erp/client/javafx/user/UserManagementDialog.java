package erp.client.javafx.user;

import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.container.tablewithnavigation.TableColumnDataWrapper;
import erp.client.javafx.utility.GuiUtility;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Modality;

import java.util.ArrayList;

public class UserManagementDialog extends AbstractTableWithNavigationDialog<User> {

    public UserManagementDialog() {
        if(checkSecurity()){
            Scene scene = new Scene(this, GuiUtility.maximumSize().getWidth(), GuiUtility.maximumSize().getHeight());
            getStage().setScene(scene);
            getStage().getIcons().add(new Image(getClass().getResourceAsStream("/image/User.png")));
            getStage().setTitle("User Management");
            getStage().initModality(Modality.APPLICATION_MODAL);
            getStage().show();
        }
    }

    @Override
    public void refresh() {

    }

    @Override
    protected void createTableColumns(ArrayList<TableColumnDataWrapper<User, ?>> tableColumns) {
        TableColumn<User, Void> index = new TableColumn<>("#");
        index.setCellFactory(col -> {
            TableCell<User, Void> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.createStringBinding(() -> {
                if (cell.isEmpty()) {
                    return null ;
                } else {
                    return Integer.toString((getBottomBar().getPageNo().getSelectionModel().getSelectedIndex()*getBottomBar().getItemsPerPage().getValue()) + (cell.getIndex()+1));
                }
            }, cell.emptyProperty(), cell.indexProperty()));
            return cell ;
        });
        getCenterPane().getTable().getColumns().add(index);

        tableColumns.add(new TableColumnDataWrapper<>("Name", "name"));
        tableColumns.add(new TableColumnDataWrapper<>("Type", "userType"));
        tableColumns.add(new TableColumnDataWrapper<>("Designation", "designation"));
        tableColumns.add(new TableColumnDataWrapper<>("Email", "email"));
        tableColumns.add(new TableColumnDataWrapper<>("Phone", "phone"));
        tableColumns.add(new TableColumnDataWrapper<>("Username", "username"));
        tableColumns.add(new TableColumnDataWrapper<>("Added On", "addedDate"));
        tableColumns.add(new TableColumnDataWrapper<>("Modified On", "modifiedDate"));

        getCenterPane().getTable().getColumns().addAll(tableColumns);
    }

    @Override
    public boolean checkSecurity() {
        return true;
    }
}
