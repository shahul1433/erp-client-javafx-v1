package erp.client.javafx.dealer.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.dealer.LedgerDTO;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetLedgerByMonthAndYearAndDealerService extends Service<LedgerDTO> {

    private LedgerDTO dto;

    public GetLedgerByMonthAndYearAndDealerService(LedgerDTO dto) {
        this.dto = dto;
    }

    @Override
    protected Task<LedgerDTO> createTask() {
        return new GetLedgerByMonthAndYearAndDealerTask();
    }

    class GetLedgerByMonthAndYearAndDealerTask extends Task<LedgerDTO> {

        @Override
        protected LedgerDTO call() throws Exception {

            String getLedgerByMonthAndYearAndDealerUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Dealer.Ledger.GET_LEDGER_BY_MONTH_AND_YEAR_AND_DEALER_URL;
            ResponseEntity<LedgerDTO> responseEntity = HttpModule.postRequest(getLedgerByMonthAndYearAndDealerUrl, dto, new TypeReference<LedgerDTO>() {
            });
            if(responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch dealer ledger details from database,\nPlease find logs for more details");
            }
            return responseEntity.getEntity();
        }
    }
}
