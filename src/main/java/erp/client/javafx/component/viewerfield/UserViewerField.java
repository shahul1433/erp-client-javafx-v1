package erp.client.javafx.component.viewerfield;

import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.icon.FontAwsomeManager;
import erp.client.javafx.user.UserDTO;
import javafx.scene.control.Tooltip;

public class UserViewerField extends ViewerField{

    private UserDTO userDTO;

    public UserViewerField(String attributeName, UserDTO userDTO, Arguments arguments) {
        super(arguments);
        label.setText(attributeName);
        this.userDTO = userDTO;
    }

    @Override
    public void init() {
        super.init();
        button.setText("\uf508");
        button.setFont(FontAwsomeManager.getSolidFontPlain(14));
        button.setTooltip(new Tooltip("View User"));
    }

    @Override
    protected void onButtonClick() {
        Arguments args = new Arguments();
        args.setArgument("user", userDTO);
        new UserViewerDialog(null, StageMode.VIEW, args);
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
        textField.setText(userDTO.getName());
    }
}
