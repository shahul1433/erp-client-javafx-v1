package erp.client.javafx.stock.stockin;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.exception.TableWithNavigationHandler;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.stock.stockin.thread.GetAllStockInService;
import org.apache.log4j.Logger;

public class StockInManagementService {

    private static final Logger LOGGER = Logger.getLogger(StockInManagementService.class);
    private StockInManagementDialog view;

    public StockInManagementService(StockInManagementDialog view) {
        this.view = view;
    }

    public void  getAllStockIn(SortMap sortMap) {
        var service = new GetAllStockInService(view, sortMap);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new TableWithNavigationHandler<StockInDTO, StockIn>(service, view) {
            @Override
            public void setData(Page<StockInDTO> page, AbstractTableWithNavigationDialog<StockIn> view) {
                page.getContent().forEach(obj -> view.getCenterPane().getTable().getItems().add(new StockIn(obj)));
            }
        });
        service.start();
    }
}
