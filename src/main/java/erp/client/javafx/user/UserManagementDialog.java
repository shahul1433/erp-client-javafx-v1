package erp.client.javafx.user;

import erp.client.javafx.common.AddEditRemoveTopBar;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.container.tablewithnavigation.TableColumnDataWrapper;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.utility.GuiUtility;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.binding.Bindings;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.image.Image;
import javafx.stage.Modality;

import java.util.ArrayList;

public class UserManagementDialog extends AbstractTableWithNavigationDialog<User> {

    private UserManagementService userManagementService = new UserManagementService(this);
    private UserManagementTopBar userManagementTopBar;

    public UserManagementDialog() {
        super();
        if(checkSecurity()){
            Scene scene = new Scene(this, GuiUtility.maximumSize().getWidth(), GuiUtility.maximumSize().getHeight());
            getStage().setScene(scene);
            getStage().getIcons().add(new Image(getClass().getResourceAsStream("/image/User.png")));
            getStage().setTitle("User Management");
            getStage().initModality(Modality.APPLICATION_MODAL);
            getStage().show();

            refresh(); //Initial call.
        }
    }

    @Override
    public void init() {
        super.init();
        this.userManagementTopBar = new UserManagementTopBar();
        setTopBar(userManagementTopBar);
    }

    @Override
    public void refresh() {
        userManagementService.getAllUsers(null);
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
    protected void sortCall(SortMap sortMap) {
        userManagementService.getAllUsers(sortMap);
    }

    @Override
    protected void setFilterDialog() {
        this.filterDialog = new UserFilterDialog(this);
    }

    @Override
    public boolean checkSecurity() {
        return AppSession.hasRole(UserRole.USER_MANAGEMENT);
    }

    @Override
    public void registerListeners() {
        super.registerListeners();

        this.centerPane.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->{
            int size = this.centerPane.getTable().getSelectionModel().getSelectedItems().size();
            userManagementTopBar.getEdit().setDisable(size != 1);
            userManagementTopBar.getRemove().setDisable(size == 0);
        });

        this.centerPane.getTable().setOnMouseClicked(e -> {
            if(e.getClickCount() >= 2) {
                User user = this.centerPane.getTable().getSelectionModel().getSelectedItem();
                Arguments args = new Arguments();
                args.setArgument("user", user.getUser());
                new AddEditViewUserDialog(getStage(), StageMode.VIEW, args);
            }
        });
    }

    class UserManagementTopBar extends AddEditRemoveTopBar {

        @Override
        protected void setOnAction() {
            getAdd().setOnAction(e -> {
                new AddEditViewUserDialog(getStage(), StageMode.ADD, null);
            });

            getEdit().setOnAction(e -> {
                User user = getCenterPane().getTable().getSelectionModel().getSelectedItem();
                Arguments args = new Arguments();
                args.setArgument("user", user.getUser());
                new AddEditViewUserDialog(getStage(), StageMode.EDIT, args);
            });

            getRemove().setOnAction(e -> {
                int size = getCenterPane().getTable().getSelectionModel().getSelectedIndices().size();
                Alert alert = PopupUtility.showMessage(stage,Alert.AlertType.CONFIRMATION, "Are you sure to delete selected "+ (size > 1 ? "users" : "user") + "?");
                alert.showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> userManagementService.removeUser());
            });
        }
    }
}
