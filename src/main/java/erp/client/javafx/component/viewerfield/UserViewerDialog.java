package erp.client.javafx.component.viewerfield;

import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.user.UserDTO;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;

public class UserViewerDialog extends AbstractDialog {

    private CTextField name, designation, email, phone, username;
    private UserDTO userDTO;

    public UserViewerDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
    }

    @Override
    protected void init() {
        userDTO = args.getArgument("user", UserDTO.class);
        name = new CTextField("Name", false, -1);
        name.setEditable(false);
        designation = new CTextField("Designation", false, -1);
        designation.setEditable(false);
        email = new CTextField("Email", false, -1);
        email.setEditable(false);
        phone = new CTextField("Phone", false, -1);
        phone.setEditable(false);
        username = new CTextField("Username", false, -1);
        username.setEditable(false);

        populateFields();
    }

    private void populateFields() {
        if (userDTO != null) {
            name.setText(userDTO.getName());
            designation.setText(userDTO.getDesignation());
            email.setText(userDTO.getEmail());
            phone.setText(userDTO.getPhone());
            username.setText(userDTO.getUsername());
        }
    }

    @Override
    protected Pane designContentGUI() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setAlignment(Pos.CENTER);
        pane.setPadding(new Insets(25));

        int col = 0, row = 0;

        pane.add(name.getLabel(), col++, row);
        pane.add(name, col--, row++);

        pane.add(designation.getLabel(), col++, row);
        pane.add(designation, col--, row++);

        pane.add(email.getLabel(), col++, row);
        pane.add(email, col--, row++);

        pane.add(phone.getLabel(), col++, row);
        pane.add(phone, col--, row++);

        pane.add(username.getLabel(), col++, row);
        pane.add(username, col--, row++);

        return pane;
    }

    @Override
    protected Pane designButtonGUI() {
        return new HBox();
    }

    @Override
    protected void registerListeners() {

    }

    @Override
    protected boolean checkSecurity() {
        return true;
    }

    @Override
    protected InputStream getIcon() {
        return getClass().getResourceAsStream("/image/User.png");
    }

    @Override
    protected void adjustViewByStageMode() {
        stage.setTitle("View User");
    }
}
