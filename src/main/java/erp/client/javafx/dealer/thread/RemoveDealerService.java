package erp.client.javafx.dealer.thread;

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

public class RemoveDealerService extends Service<ResponseEntity<EntityIDList>> {

    private EntityIDList entityIDList;

    public RemoveDealerService(EntityIDList entityIDList) {
        this.entityIDList = entityIDList;
    }

    @Override
    protected Task<ResponseEntity<EntityIDList>> createTask() {
        return new RemoveDealerTask();
    }

    class RemoveDealerTask extends Task<ResponseEntity<EntityIDList>> {

        @Override
        protected ResponseEntity<EntityIDList> call() throws Exception {

            String removeDealerUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Dealer.REMOVE_DEALER_URL;
            ResponseEntity<EntityIDList> responseEntity = HttpModule.postRequest(removeDealerUrl, entityIDList, new TypeReference<EntityIDList>() {
            });
            if(responseEntity == null){
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while try to delete dealer(s)\nPlease find log for more info");
            }
            return responseEntity;
        }
    }
}
