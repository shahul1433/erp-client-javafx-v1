package erp.client.javafx.stock.stockreturn.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.stock.stockreturn.StockReturnDTO;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class AddStockReturnService extends Service<ResponseEntity<StockReturnDTO>> {

    private StockReturnDTO stockReturn;

    public AddStockReturnService(StockReturnDTO stockReturn) {
        this.stockReturn = stockReturn;
    }

    @Override
    protected Task<ResponseEntity<StockReturnDTO>> createTask() {
        return new AddStockReturnTask();
    }

    class AddStockReturnTask extends Task<ResponseEntity<StockReturnDTO>> {

        @Override
        protected ResponseEntity<StockReturnDTO> call() throws Exception {

            String addStockReturnUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.StockReturn.SAVE_STOCK_RETURN;
            ResponseEntity<StockReturnDTO> responseEntity = HttpModule.postRequest(addStockReturnUrl, stockReturn, new TypeReference<StockReturnDTO>() {
            });
            if (responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while try to add stock return,\nPlease find the log for more info");
            }
            return responseEntity;
        }
    }
}
