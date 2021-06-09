package erp.client.javafx.user.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.common.EntityIDList;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class RemoveUserService extends Service<ResponseEntity<EntityIDList>> {

    private EntityIDList entityIDList;

    public RemoveUserService(EntityIDList entityIDList) {
        this.entityIDList = entityIDList;
    }

    @Override
    protected Task<ResponseEntity<EntityIDList>> createTask() {
        return new RemoveUserTask();
    }

    class RemoveUserTask extends Task<ResponseEntity<EntityIDList>> {

        @Override
        protected ResponseEntity<EntityIDList> call() throws Exception {

            String removeUserUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.User.REMOVE_USER_URL;
            ResponseEntity<EntityIDList> responseEntity = HttpModule.postRequest(removeUserUrl, entityIDList, new TypeReference<EntityIDList>() {
            });
            if(responseEntity == null){
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while try to delete user(s)\nPlease find log for more info");
            }
            return responseEntity;
        }
    }
}
