package erp.client.javafx.stock.stockin.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.stock.stockin.StockInDTO;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class AddStockService extends Service<ResponseEntity<StockInDTO>> {

    private StockInDTO stockIn;

    public AddStockService(StockInDTO stockIn) {
        this.stockIn = stockIn;
    }

    @Override
    protected Task<ResponseEntity<StockInDTO>> createTask() {
        return new AddStockTask();
    }

    class AddStockTask extends Task<ResponseEntity<StockInDTO>> {

        @Override
        protected ResponseEntity<StockInDTO> call() throws Exception {

            String addStockUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.StockIn.ADD_STOCK_URL;
            ResponseEntity<StockInDTO> responseEntity = HttpModule.postRequest(addStockUrl, stockIn, new TypeReference<StockInDTO>() {
            });
            if(responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while try to add stock,\nPlease find the log for more info");
            }
            return responseEntity;
        }
    }
}
