package erp.client.javafx.stock.stockreturn.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.stock.stockreturn.StockReturnDTO;
import erp.client.javafx.stock.stockreturn.StockReturnFilter;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetAllStockReturnService extends Service<Page<StockReturnDTO>> {

    private StockReturnFilter filter;

    public GetAllStockReturnService(StockReturnFilter filter) {
        this.filter = filter;
    }

    @Override
    protected Task<Page<StockReturnDTO>> createTask() {
        return new GetAllStockReturnTask();
    }

    class GetAllStockReturnTask extends Task<Page<StockReturnDTO>> {

        @Override
        protected Page<StockReturnDTO> call() throws Exception {

            String getAllStockReturnURL = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.StockReturn.GET_ALL_STOCK_RETURN;
            ResponseEntity<Page<StockReturnDTO>> responseEntity = HttpModule.postRequest(getAllStockReturnURL, filter, new TypeReference<Page<StockReturnDTO>>() {
            });
            if (responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch stock return details from database,\nPlease find the log for more info.");
            }
            return responseEntity.getEntity();
        }
    }
}
