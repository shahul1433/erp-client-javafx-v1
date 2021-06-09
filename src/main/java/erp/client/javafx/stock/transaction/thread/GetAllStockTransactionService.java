/*
package erp.client.javafx.stock.transaction.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.stock.transaction.StockTransactionFilter;
import erp.client.javafx.stock.transaction.StockTransactionManagementDialog;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetAllStockTransactionService extends Service<Page<TStockTransaction>> {

    private StockTransactionManagementDialog view;
    private SortMap sortMap;

    public GetAllStockTransactionService(StockTransactionManagementDialog view, SortMap sortMap) {
        this.view = view;
        this.sortMap = sortMap;
    }

    @Override
    protected Task<Page<TStockTransaction>> createTask() {
        return new GetAllStockTransactionTask();
    }

    class GetAllStockTransactionTask extends Task<Page<TStockTransaction>> {

        @Override
        protected Page<TStockTransaction> call() throws Exception {
            if (sortMap == null) {
                sortMap = new SortMap("id", "desc");
            }
            int pageNo = view.getBottomBar().getPageNo().getValue() != null ? view.getBottomBar().getPageNo().getValue()-1 : 0;
            int size = view.getBottomBar().getItemsPerPage().getValue() != null ? view.getBottomBar().getItemsPerPage().getValue() : 20;
            StockTransactionFilter filter = (StockTransactionFilter) view.getFilterDialog().getForm();
            filter.setPage(pageNo);
            filter.setSize(size);
            filter.setSortMap(sortMap);

            String getAllStockTransactionUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Stock.GET_ALL_STOCK_TRANSACTIONS;
            ResponseEntity<Page<TStockTransaction>> responseEntity = HttpModule.postRequest(getAllStockTransactionUrl, filter, new TypeReference<Page<TStockTransaction>>() {
            });
            if(responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch stock transactions from DB,\nPlease find log for more info");
            }
            return responseEntity.getEntity();
        }
    }
}
*/
