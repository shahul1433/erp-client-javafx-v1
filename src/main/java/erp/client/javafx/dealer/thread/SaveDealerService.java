package erp.client.javafx.dealer.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.entity.TDealer;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class SaveDealerService extends Service<ResponseEntity<TDealer>> {

    private TDealer dealer;

    public SaveDealerService(TDealer dealer) {
        this.dealer = dealer;
    }

    @Override
    protected Task<ResponseEntity<TDealer>> createTask() {
        return new SaveDealerTask();
    }

    class SaveDealerTask extends Task<ResponseEntity<TDealer>> {

        @Override
        protected ResponseEntity<TDealer> call() throws Exception {
            String saveDealerUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Dealer.SAVE_DEALER_URL;
            ResponseEntity<TDealer> responseEntity = HttpModule.postRequest(saveDealerUrl, dealer, new TypeReference<TDealer>() {
            });
            if (responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while save dealer,\nPlease find the log for more info");
            }
            return responseEntity;
        }
    }
}
