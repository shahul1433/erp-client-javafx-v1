package erp.client.javafx.common.cellfactory;

import erp.client.javafx.user.UserDTO;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;

public class UserCellFactory implements Callback<TableColumn<?, UserDTO>, TableCell<?, UserDTO>> {
    @Override
    public TableCell<?, UserDTO> call(TableColumn<?, UserDTO> userDTOTableColumn) {
        return new TableCell<>() {
            @Override
            protected void updateItem(UserDTO userDTO, boolean b) {
                super.updateItem(userDTO, b);
                setStyle("-fx-alignment: CENTER-LEFT;");
                if (userDTO != null) {
                    setText(userDTO.getName());
                } else {
                    setText(null);
                }
            }
        };
    }
}
