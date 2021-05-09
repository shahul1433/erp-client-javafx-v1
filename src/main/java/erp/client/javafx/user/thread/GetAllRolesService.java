package erp.client.javafx.user.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.user.UserRolesList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetAllRolesService extends Service<UserRolesList> {

    @Override
    protected Task<UserRolesList> createTask() {
        return new GetAllRolesTask();
    }

    class GetAllRolesTask extends Task<UserRolesList> {

        @Override
        protected UserRolesList call() throws Exception {
            String getAllRolesUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.User.GET_ALL_USER_ROLES_URL;
            ResponseEntity<UserRolesList> responseEntity = HttpModule.getRequest(getAllRolesUrl, new TypeReference<UserRolesList>() {
            });
            if (responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch user roles from DB,\nPlease find log for more info");
            }
            return responseEntity.getEntity();
        }
    }
}
