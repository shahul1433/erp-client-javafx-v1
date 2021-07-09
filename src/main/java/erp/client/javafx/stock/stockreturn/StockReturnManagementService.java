package erp.client.javafx.stock.stockreturn;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.exception.TableWithNavigationHandler;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.stock.stockreturn.thread.GetAllStockReturnService;
import org.apache.log4j.Logger;

public class StockReturnManagementService {

    private static final Logger logger = Logger.getLogger(StockReturnManagementService.class);
    private StockReturnManagementDialog view;

    public StockReturnManagementService(StockReturnManagementDialog view) {
        this.view = view;
    }

    public void getAllStockReturn(SortMap sortMap) {
        if (sortMap == null) {
            sortMap = new SortMap("stockReturnId", "desc");
        }
        int pageNo = view.getBottomBar().getPageNo().getValue() != null ? view.getBottomBar().getPageNo().getValue()-1 : 0;
        int size = view.getBottomBar().getItemsPerPage().getValue() != null ? view.getBottomBar().getItemsPerPage().getValue() : 20;
        StockReturnFilter filter = (StockReturnFilter) view.getFilterDialog().getForm();
        filter.setPage(pageNo);
        filter.setSize(size);
        filter.setSortMap(sortMap);

        var service = new GetAllStockReturnService(filter);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new TableWithNavigationHandler<StockReturnDTO, StockReturn>(service, view) {
            @Override
            public void setData(Page<StockReturnDTO> page, AbstractTableWithNavigationDialog<StockReturn> view) {
                page.getContent().forEach(obj -> view.getCenterPane().getTable().getItems().add(new StockReturn(obj)));
            }
        });
        service.start();
    }
}
