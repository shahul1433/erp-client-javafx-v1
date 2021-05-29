package erp.client.javafx.stock.stockin.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.entity.TStockIn;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.stock.stockin.StockInFilter;
import erp.client.javafx.stock.stockin.StockInManagementDialog;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetAllStockInService extends Service<Page<TStockIn>> {

    private StockInManagementDialog view;
    private SortMap sortMap;

    public GetAllStockInService(StockInManagementDialog view, SortMap sortMap) {
        this.view = view;
        this.sortMap = sortMap;
    }

    @Override
    protected Task<Page<TStockIn>> createTask() {
        return new GetAllStockInTask();
    }

    class GetAllStockInTask extends Task<Page<TStockIn>> {

        @Override
        protected Page<TStockIn> call() throws Exception {
            if (sortMap == null) {
                sortMap = new SortMap("id", "desc");
            }
            int pageNo = view.getBottomBar().getPageNo().getValue() != null ? view.getBottomBar().getPageNo().getValue()-1 : 0;
            int size = view.getBottomBar().getItemsPerPage().getValue() != null ? view.getBottomBar().getItemsPerPage().getValue() : 20;
            StockInFilter filter = (StockInFilter) view.getFilterDialog().getForm();
            filter.setPage(pageNo);
            filter.setSize(size);
            filter.setSortMap(sortMap);

            String getAllStockInUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.StockIn.GET_ALL_STOCK_IN;
            ResponseEntity<Page<TStockIn>> responseEntity = HttpModule.postRequest(getAllStockInUrl, filter, new TypeReference<Page<TStockIn>>() {
            });
            if(responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch stock in details from DB,\nPlease fin log for more info");
            }
            return responseEntity.getEntity();
        }
    }
}
