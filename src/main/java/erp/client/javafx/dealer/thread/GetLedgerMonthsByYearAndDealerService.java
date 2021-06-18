package erp.client.javafx.dealer.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.common.ListValue;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.dealer.LedgerDTO;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

import java.time.Month;

public class GetLedgerMonthsByYearAndDealerService extends Service<ListValue<Month>> {

    private LedgerDTO ledgerDTO;

    public GetLedgerMonthsByYearAndDealerService(LedgerDTO ledgerDTO) {
        this.ledgerDTO = ledgerDTO;
    }

    @Override
    protected Task<ListValue<Month>> createTask() {
        return new GetLedgerMonthsByYearAndDealerTask();
    }

    class GetLedgerMonthsByYearAndDealerTask extends Task<ListValue<Month>> {

        @Override
        protected ListValue<Month> call() throws Exception {

            String getLegerMonthsByYearAndDealerURL = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Dealer.Ledger.GET_LEDGER_MONTHS_BY_YEAR_AND_DEALER_URL;
            ResponseEntity<ListValue<Month>> responseEntity = HttpModule.postRequest(getLegerMonthsByYearAndDealerURL, ledgerDTO, new TypeReference<ListValue<Month>>() {
            });
            if(responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch dealer ledger months details from database,\nPlease find logs for more details");
            }
            return responseEntity.getEntity();
        }
    }
}
