package erp.client.javafx.user;

import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.container.tablewithnavigation.TableColumnDataWrapper;
import erp.client.javafx.container.tablewithnavigation.TopBar;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.icon.FontAwsomeManager;
import erp.client.javafx.utility.GuiUtility;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.binding.Bindings;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

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
        return true;
    }

    @Override
    public void registerListeners() {
        super.registerListeners();

        this.centerPane.getTable().getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) ->{
            int size = this.centerPane.getTable().getSelectionModel().getSelectedItems().size();
            userManagementTopBar.edit.setDisable(size != 1);
            userManagementTopBar.remove.setDisable(size == 0);
        });

        this.centerPane.getTable().setOnMouseClicked(e -> {
            if(e.getClickCount() >= 2) {
                User user = this.centerPane.getTable().getSelectionModel().getSelectedItem();
                new AddEditViewUserDialog(getStage(), StageMode.VIEW, user.getUser());
            }
        });
    }

    class UserManagementTopBar extends TopBar {

        private Button add, edit, remove;

        public UserManagementTopBar() {
            super();
        }

        @Override
        public void init() {
            super.init();

            add = new Button("\uf234");
            add.setFont(FontAwsomeManager.getSolidFontPlain(14));
            add.setTooltip(new Tooltip("Add User"));

            edit = new Button("\uf4ff");
            edit.setFont(FontAwsomeManager.getSolidFontPlain(14));
            edit.setTooltip(new Tooltip("Edit User"));
            edit.setDisable(true);

            remove = new Button("\uf1f8");
            remove.setFont(FontAwsomeManager.getSolidFontPlain(14));
            remove.setTooltip(new Tooltip("Remove User"));
            remove.setDisable(true);
        }

        @Override
        public void designGUI() {
            super.designGUI();

            HBox buttonPane = new HBox(5);
            buttonPane.getChildren().addAll(add, edit, remove);
            buttonPane.setPadding(new Insets(10));

            this.setLeft(buttonPane);
        }

        @Override
        public void registerListeners() {
            super.registerListeners();

            this.add.setOnAction(e -> {
                new AddEditViewUserDialog(getStage(), StageMode.ADD, null);
            });

            this.edit.setOnAction(e -> {
                User user = getCenterPane().getTable().getSelectionModel().getSelectedItem();
                new AddEditViewUserDialog(getStage(), StageMode.EDIT, user.getUser());
            });

            this.remove.setOnAction(e -> {
                int size = getCenterPane().getTable().getSelectionModel().getSelectedIndices().size();
                Alert alert = PopupUtility.showMessage(stage,Alert.AlertType.CONFIRMATION, "Are you sure to delete selected "+ (size > 1 ? "users" : "user") + "?");
                alert.showAndWait()
                        .filter(response -> response == ButtonType.OK)
                        .ifPresent(response -> userManagementService.removeUser());
            });
        }

        public Button getAdd() {
            return add;
        }

        public Button getEdit() {
            return edit;
        }

        public Button getRemove() {
            return remove;
        }
    }
}
