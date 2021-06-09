package erp.client.javafx.user.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.user.UserDTO;
import erp.client.javafx.user.UserPersistDTO;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class AddUserService extends Service<ResponseEntity<UserDTO>> {

    private UserPersistDTO user;

    public AddUserService(UserPersistDTO user) {
        this.user = user;
    }

    @Override
    protected Task<ResponseEntity<UserDTO>> createTask() {
        return new AddUserTask();
    }

    class AddUserTask extends Task<ResponseEntity<UserDTO>> {

        @Override
        protected ResponseEntity<UserDTO> call() throws Exception {
            String addUserUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.User.ADD_USER_URL;
            ResponseEntity<UserDTO> responseEntity = HttpModule.postRequest(addUserUrl, user, new TypeReference<UserDTO>() {
            });
            if(responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while add/update user to DB.\nPlease find log for more info");
            }
            return responseEntity;
        }
    }
}
