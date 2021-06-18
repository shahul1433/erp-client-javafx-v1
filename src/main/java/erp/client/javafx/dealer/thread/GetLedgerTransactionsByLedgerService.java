package erp.client.javafx.dealer.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.dealer.LedgerTransactionDTO;
import erp.client.javafx.dealer.LedgerTransactionFilter;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetLedgerTransactionsByLedgerService extends Service<Page<LedgerTransactionDTO>> {

    private LedgerTransactionFilter filter;

    public GetLedgerTransactionsByLedgerService(LedgerTransactionFilter filter) {
        this.filter = filter;
    }

    @Override
    protected Task<Page<LedgerTransactionDTO>> createTask() {
        return new GetLedgerTransactionByLedgerTask();
    }

    class GetLedgerTransactionByLedgerTask extends Task<Page<LedgerTransactionDTO>> {

        @Override
        protected Page<LedgerTransactionDTO> call() throws Exception {
            String getledgerTransactionByLedgerUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Dealer.Transaction.GET_LEDGER_TRANSACTION_URL;
            ResponseEntity<Page<LedgerTransactionDTO>> responseEntity = HttpModule.postRequest(getledgerTransactionByLedgerUrl, filter, new TypeReference<Page<LedgerTransactionDTO>>() {
            });
            if(responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch dealer ledger transactions from database,\nPlease find logs for more details");
            }
            return responseEntity.getEntity();
        }
    }
}
