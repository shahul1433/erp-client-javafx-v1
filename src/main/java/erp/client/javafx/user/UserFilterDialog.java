package erp.client.javafx.user;

import erp.client.javafx.component.date.DateSearchPanel;
import erp.client.javafx.component.filter.combobox.UserTypeCombobox;
import erp.client.javafx.component.filter.textfield.TextFieldSearch;
import erp.client.javafx.container.tablewithnavigation.AbstractFilterDialog;
import erp.client.javafx.entity.TUser;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class UserFilterDialog extends AbstractFilterDialog<UserFilter> {

    private TextFieldSearch name, designation, email, phone, username;
    private UserTypeCombobox userTypeCombobox;
    private DateSearchPanel addedDate, modifiedDate;

    public UserFilterDialog(UserManagementDialog parent) {
        super(parent);
    }

    @Override
    protected Pane designFilterGUI() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25));

        ColumnConstraints emptyColumn = new ColumnConstraints();
        ColumnConstraints userTypeConstraint = new ColumnConstraints(100, 150, Double.MAX_VALUE);
        userTypeConstraint.setHgrow(Priority.ALWAYS);

        pane.getColumnConstraints().addAll(emptyColumn, emptyColumn, userTypeConstraint);

        int col = 0, row = 0;

        pane.add(name.getLabel(), col++, row);
        pane.add(name.getPattern(), col++, row);
        pane.add(name, col, row++);

        col = 0;
        pane.add(userTypeCombobox.getLabel(), col++, row);
        pane.add(userTypeCombobox, col, row++, 2, 1);

        col = 0;
        pane.add(designation.getLabel(), col++, row);
        pane.add(designation.getPattern(), col++, row);
        pane.add(designation, col, row++);

        col = 0;
        pane.add(email.getLabel(), col++, row);
        pane.add(email.getPattern(), col++, row);
        pane.add(email, col, row++);

        col = 0;
        pane.add(phone.getLabel(), col++, row);
        pane.add(phone.getPattern(), col++, row);
        pane.add(phone, col, row++);

        col = 0;
        pane.add(username.getLabel(), col++, row);
        pane.add(username.getPattern(), col++, row);
        pane.add(username, col, row++);

        col = 0;
        pane.add(addedDate, col, row++, 3, 1);
        pane.add(modifiedDate, col, row++, 3, 1);

        return pane;
    }

    @Override
    protected void initFilterComponents() {
        this.name = new TextFieldSearch("Name");
        this.designation = new TextFieldSearch("Designation");
        this.email = new TextFieldSearch("Email");
        this.phone = new TextFieldSearch("Phone");
        this.username = new TextFieldSearch("Username");
        this.userTypeCombobox = new UserTypeCombobox();
        this.addedDate = new DateSearchPanel("Added Date");
        this.modifiedDate = new DateSearchPanel("Modified Date");
    }

    @Override
    public UserFilter getForm() {
        TUser user = new TUser();
        user.setArchive(false);
        user.setName(this.name.getSearchString());
        user.setDesignation(this.designation.getSearchString());
        user.setEmail(this.email.getSearchString());
        user.setPhone(this.phone.getSearchString());
        user.setUsername(this.username.getSearchString());
        user.setUserType(this.userTypeCombobox.getSelectedUserType());

        UserFilter filter = new UserFilter(user, this.addedDate.getDateSearchable(), this.modifiedDate.getDateSearchable(), 0, 0, null);
        return filter;
    }

    @Override
    protected void clearFilterFields() {
        name.clearSearch();
        userTypeCombobox.clearSearch();
        designation.clearSearch();
        email.clearSearch();
        phone.clearSearch();
        username.clearSearch();
        addedDate.clearSearch();
        modifiedDate.clearSearch();
    }

    @Override
    protected boolean isValidFilter() {
        try {
            return (
                    name.isValidFilterField() || userTypeCombobox.isValidFilterField() ||
                            designation.isValidFilterField() || email.isValidFilterField() ||
                            email.isValidFilterField() || phone.isValidFilterField() || username.isValidFilterField() ||
                            addedDate.isValidFilterField() || modifiedDate.isValidFilterField()
                    );
        }catch (Exception e) {
            handleException(e);
            return false;
        }
    }

}
