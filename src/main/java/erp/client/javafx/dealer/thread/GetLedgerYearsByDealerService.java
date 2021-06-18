package erp.client.javafx.dealer.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.common.ListValue;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetLedgerYearsByDealerService extends Service<ListValue<Integer>> {

    private DealerDTO dealer;

    public GetLedgerYearsByDealerService(DealerDTO dealer) {
        this.dealer = dealer;
    }

    @Override
    protected Task<ListValue<Integer>> createTask() {
        return new GetLedgerYearsByDealerTask();
    }

    class GetLedgerYearsByDealerTask extends Task<ListValue<Integer>> {

        @Override
        protected ListValue<Integer> call() throws Exception {

            String getDealerLedgerYearsUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Dealer.Ledger.GET_LEDGER_YEARS_BY_DEALER_URL;
            ResponseEntity<ListValue<Integer>> responseEntity = HttpModule.postRequest(getDealerLedgerYearsUrl, dealer.getDealerId(), new TypeReference<ListValue<Integer>>() {
            });
            if(responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch dealer ledger years details from database,\nPlease find logs for more details");
            }
            return responseEntity.getEntity();
        }
    }
}
