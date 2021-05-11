package erp.client.javafx.dealer.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.dealer.DealerFilter;
import erp.client.javafx.dealer.DealerManagementDialog;
import erp.client.javafx.entity.TDealer;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.http.SortMap;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetAllDealerService extends Service<Page<TDealer>> {

    private DealerManagementDialog view;
    private SortMap sortMap;

    public GetAllDealerService(DealerManagementDialog view, SortMap sortMap) {
        this.view = view;
        this.sortMap = sortMap;
    }

    @Override
    protected Task<Page<TDealer>> createTask() {
        return new GetAllDealerTask();
    }

    class GetAllDealerTask extends Task<Page<TDealer>> {

        @Override
        protected Page<TDealer> call() throws Exception {
            if (sortMap == null) {
                sortMap = new SortMap("id", "desc");
            }
            int pageNo = view.getBottomBar().getPageNo().getValue() != null ? view.getBottomBar().getPageNo().getValue()-1 : 0;
            int size = view.getBottomBar().getItemsPerPage().getValue() != null ? view.getBottomBar().getItemsPerPage().getValue() : 20;
            DealerFilter filter = (DealerFilter) view.getFilterDialog().getForm();
            filter.setPage(pageNo);
            filter.setSize(size);
            filter.setSortMap(sortMap);

            String getAllDealerUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Dealer.GET_ALL_DEALERS_URL;
            ResponseEntity<Page<TDealer>> responseEntity = HttpModule.postRequest(getAllDealerUrl, filter, new TypeReference<Page<TDealer>>() {
            });
            if (responseEntity == null) {
                throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch dealers from DB,\nPlease find log for more info");
            }
            return responseEntity.getEntity();
        }
    }
}
