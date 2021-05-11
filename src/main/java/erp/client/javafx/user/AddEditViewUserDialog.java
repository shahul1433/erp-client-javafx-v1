package erp.client.javafx.user;

import erp.client.javafx.component.combobox.UserTypeCombobox;
import erp.client.javafx.component.textfield.CPasswordField;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.component.textfield.email.EmailField;
import erp.client.javafx.component.textfield.phone.Country;
import erp.client.javafx.component.textfield.phone.PhoneField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.entity.TUser;
import erp.client.javafx.entity.UserType;
import erp.client.javafx.icon.FontAwsomeManager;
import erp.client.javafx.layout.AbstractGridPane;
import erp.client.javafx.layout.AbstractHBoxPane;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;

public class AddEditViewUserDialog extends AbstractDialog {

    TabPane tabPane;
    Tab detailsTab, rolesTab;
    Button add, clear;

    DetailsPane detailsPane;
    RolesPane rolesPane;

    AddEditViewUserService userService;
    TUser user;

    public AddEditViewUserDialog(Stage parentStage, StageMode stageMode, TUser user) {
        super(parentStage, stageMode);
        this.user = user;
    }

    @Override
    protected void init() {
        tabPane = new TabPane();
        detailsTab = new Tab("Details");
        detailsTab.setClosable(false);
        rolesTab = new Tab("Roles");
        rolesTab.setClosable(false);

        tabPane.getTabs().addAll(detailsTab, rolesTab);

        detailsPane = new DetailsPane();
        detailsTab.setContent(detailsPane);

        rolesPane = new RolesPane();
        rolesTab.setContent(rolesPane);

        add = new Button("Add");
        add.setStyle("-fx-base: green");
        clear = new Button("Clear");
        clear.setStyle("-fx-base: red");

        userService = new AddEditViewUserService(this);
        userService.getAllRoles();
    }

    @Override
    protected Pane designContentGUI() {
        BorderPane pane = new BorderPane();
        pane.setCenter(tabPane);
        return pane;
    }

    @Override
    protected Pane designButtonGUI() {
        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(10));
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(add, clear);
        return buttonPane;
    }

    @Override
    protected void registerListeners() {
        clear.setOnAction(e -> {
            clearFields();
        });

        add.setOnAction(e -> {
            if(
                detailsPane.name.validateField() && detailsPane.userType.validateField() &&
                        detailsPane.designation.validateField() &&
                        detailsPane.email.validateField() &&
                        detailsPane.phone.validateField() &&
                        detailsPane.username.validateField() &&
                        validateFields()
            ) {
                userService.addUser();
            }
        });
    }

    public void populateFields() {
        if(stageMode == StageMode.EDIT || stageMode == StageMode.VIEW) {
            if(user != null) {
                stage.setTitle(stage.getTitle() + " - " + user.getName());
                detailsPane.populateFields();
                rolesPane.populateFields();
            }
        }
    }

    private void clearFields() {
        detailsPane.name.clearField();
        detailsPane.userType.clearField();
        detailsPane.designation.clearField();
        detailsPane.email.clearField();
        detailsPane.phone.clearField();
        detailsPane.username.clearField();
        detailsPane.password.clearField();
        detailsPane.confirmPassword.clearField();
        rolesPane.availableRoles.getItems().addAll(rolesPane.selectedRoles.getItems());
        rolesPane.selectedRoles.getItems().clear();
    }

    private boolean validateFields() {
        String pass= detailsPane.password.getText().trim();
        String confPassword = detailsPane.confirmPassword.getText().trim();

        if(stageMode == StageMode.ADD || (stageMode == StageMode.EDIT && detailsPane.isChangePassword.isSelected())) {
            if(!detailsPane.password.validateField())
                return false;
            if(!detailsPane.confirmPassword.validateField())
                return false;

            if(!pass.equals(confPassword)) {
                PopupUtility.showErrorMessage(stage, "Password mismatched !");
                detailsPane.confirmPassword.requestFocus();
                return false;
            }
        }

        if(rolesPane.selectedRoles.getRoles().isEmpty()) {
            PopupUtility.showErrorMessage(stage, "User without role is invalid\nSo please select at least one role");
            tabPane.getSelectionModel().select(rolesTab);
            return false;
        }

        return true;
    }

    @Override
    protected boolean checkSecurity() {
        return AppSession.hasRole(erp.client.javafx.component.enums.UserRole.USER_MANAGEMENT);
    }

    @Override
    protected InputStream getIcon() {
        return getClass().getResourceAsStream("/image/User.png");
    }

    @Override
    protected void adjustViewByStageMode() {
        switch (stageMode) {
            case ADD:
                getStage().setTitle("Add User");
                break;
            case EDIT:
                getStage().setTitle("Edit User");
                add.setText("Update");
                clear.setDisable(true);
                break;
            case VIEW:
                getStage().setTitle("View User");
                detailsPane.name.setEditable(false);
                detailsPane.userType.setEditable(false);
                detailsPane.designation.setEditable(false);
                detailsPane.email.setEditable(false);
                detailsPane.phone.setFieldEditable(false);
                detailsPane.username.setEditable(false);
                add.setVisible(false);
                clear.setVisible(false);
                break;
            default:
                break;
        }
    }

    class RolesPane extends AbstractHBoxPane {

        UserRoleListView availableRoles, selectedRoles;
        Button moveRight, moveLeft;

        @Override
        public void init() {
            availableRoles = new UserRoleListView();
            selectedRoles = new UserRoleListView();

            moveLeft = new Button("\uf04a");
            moveLeft.setFont(FontAwsomeManager.getSolidFontPlain(14));
            moveLeft.setTooltip(new Tooltip("Move to available roles"));
            moveLeft.setStyle("-fx-base: orange");
            moveLeft.setDisable(true);

            moveRight = new Button("\uf04e");
            moveRight.setFont(FontAwsomeManager.getSolidFontPlain(14));
            moveRight.setTooltip(new Tooltip("Move to selected roles"));
            moveRight.setStyle("-fx-base: orange");
            moveRight.setDisable(true);
        }

        @Override
        public void designGUI() {
            VBox buttons = new VBox(10);
            buttons.setAlignment(Pos.CENTER);
            buttons.getChildren().addAll(moveLeft, moveRight);

            StackPane buttonPanel = new StackPane();
            buttonPanel.getChildren().add(buttons);

            this.setSpacing(10);
            this.setPadding(new Insets(10));
            this.setAlignment(Pos.CENTER);
            this.getChildren().addAll(availableRoles, buttonPanel, selectedRoles);
        }

        @Override
        public void registerListeners() {

            availableRoles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserRole>() {
                @Override
                public void changed(ObservableValue<? extends UserRole> observable, UserRole oldValue,
                                    UserRole newValue) {
                    int selectedItems = availableRoles.getSelectionModel().getSelectedIndices().size();
                    moveRight.setDisable(selectedItems == 0);
                }
            });

            selectedRoles.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<UserRole>() {
                @Override
                public void changed(ObservableValue<? extends UserRole> observable, UserRole oldValue,
                                    UserRole newValue) {
                    int selectedItems = selectedRoles.getSelectionModel().getSelectedIndices().size();
                    moveLeft.setDisable(selectedItems == 0);
                }
            });

            moveRight.setOnAction(e -> {
                ObservableList<UserRole> selectedItems = availableRoles.getSelectionModel().getSelectedItems();
                selectedRoles.getItems().addAll(selectedItems);
                availableRoles.getItems().removeAll(selectedItems);
                availableRoles.getSelectionModel().clearSelection();
            });

            moveLeft.setOnAction(e -> {
                ObservableList<UserRole> selectedItems = selectedRoles.getSelectionModel().getSelectedItems();
                availableRoles.getItems().addAll(selectedItems);
                selectedRoles.getItems().removeAll(selectedItems);
                selectedRoles.getSelectionModel().clearSelection();
            });
        }

        private void populateFields() {
            if(stageMode == StageMode.EDIT || stageMode == StageMode.VIEW) {
                if(user != null) {
                    selectedRoles.setRoles(user.getRoles());
                    if((availableRoles.getRoles().size() > 0 ) && (selectedRoles.getRoles().size() > 0)) {
                        availableRoles.getItems().removeAll(selectedRoles.getItems());
                    }
                    availableRoles.refresh();
                }
            }
        }

        @Override
        public boolean checkSecurity() {
            return true;
        }
    }

    class DetailsPane extends AbstractGridPane {

        CTextField name, designation, username, confirmPassword;
        PhoneField phone;
        EmailField email;
        UserTypeCombobox userType;
        CPasswordField password;
        CheckBox isChangePassword;
        
        @Override
        public void init() {
            name = new CTextField("Name",  true, 100);
            userType = new UserTypeCombobox("User Type", true, stage);
            userType.setPrefWidth(Double.MAX_VALUE);
            userType.setSelectedUserType(UserType.ADMINISTRATOR);
            designation = new CTextField("Designation",  true, 100);
            email = new EmailField( false, 200);
            phone = new PhoneField("Phone",  true, Country.INDIA);
            username = new CTextField("Username",  true, 100);
            confirmPassword = new CTextField("Confirm Password",  true, -1);
            password = new CPasswordField("Password");
            isChangePassword = new CheckBox("Change Password");

            if(stageMode == StageMode.EDIT) {
                password.getLabel().visibleProperty().bind(isChangePassword.selectedProperty());
                password.visibleProperty().bind(isChangePassword.selectedProperty());

                confirmPassword.getLabel().visibleProperty().bind(isChangePassword.selectedProperty());
                confirmPassword.visibleProperty().bind(isChangePassword.selectedProperty());
            }
        }

        @Override
        public void designGUI() {

            this.setHgap(10);
            this.setVgap(10);
            this.setPadding(new Insets(20));
            this.setAlignment(Pos.CENTER);

            ColumnConstraints empty = new ColumnConstraints();
            ColumnConstraints stretched = new ColumnConstraints(100, 200, Double.MAX_VALUE);
            stretched.setHgrow(Priority.ALWAYS);

            this.getColumnConstraints().addAll(empty, stretched);

            int col = 0, row = 0;

            this.add(name.getLabel(), col++, row);
            this.add(name, col--, row++);

            this.add(userType.getLabel(), col++, row);
            this.add(userType, col--, row++);

            this.add(designation.getLabel(), col++, row);
            this.add(designation, col--, row++);

            this.add(email.getLabel(), col++, row);
            this.add(email, col--, row++);

            this.add(phone.getLabel(), col++, row);
            this.add(phone.getHBoxComboPhoneField(), col--, row++);

            this.add(username.getLabel(), col++, row);
            this.add(username, col--, row++);

            if(stageMode == StageMode.EDIT) {
                this.add(isChangePassword, col, row++, 2, 1);
            }
            if(stageMode != StageMode.VIEW) {
                this.add(password.getLabel(), col++, row);
                this.add(password, col--, row++);

                this.add(confirmPassword.getLabel(), col++, row);
                this.add(confirmPassword, col--, row);
            }
        }

        private void populateFields() {
            if(stageMode == StageMode.EDIT || stageMode == StageMode.VIEW){
                if(user != null) {
                    name.setText(user.getName());
                    userType.setSelectedUserType(user.getUserType());
                    designation.setText(user.getDesignation());
                    email.setText(user.getEmail());
                    phone.setPhoneNo(user.getPhone());
                    username.setText(user.getUsername());
                }
            }
        }

        @Override
        public void registerListeners() {
            name.setOnAction(e -> {
                if(name.validateField())
                    userType.requestFocus();
            });
            designation.setOnAction(e -> {
                if(designation.validateField())
                    email.requestFocus();
            });
            email.setOnAction(e -> {
                if(email.validateField())
                    phone.requestFocus();
            });
            phone.setOnAction(e -> {
                if(phone.validateField())
                    username.requestFocus();
            });
            username.setOnAction(e -> {
                if(username.validateField())
                    password.requestFocus();
            });
            password.setOnAction(e -> {
                if(password.validateField())
                    confirmPassword.requestFocus();
            });
            confirmPassword.setOnAction(e -> {
                if(confirmPassword.validateField())
                    add.requestFocus();
            });
        }

        @Override
        public boolean checkSecurity() {
            return true;
        }
    }
}
