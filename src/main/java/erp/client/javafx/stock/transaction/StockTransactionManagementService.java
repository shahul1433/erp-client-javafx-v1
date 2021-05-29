package erp.client.javafx.stock.transaction;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.entity.TStockTransaction;
import erp.client.javafx.exception.TableWithNavigationHandler;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.stock.transaction.thread.GetAllStockTransactionService;
import org.apache.log4j.Logger;

public class StockTransactionManagementService {

    private static final Logger LOGGER = Logger.getLogger(StockTransactionManagementService.class);
    private StockTransactionManagementDialog view;

    public StockTransactionManagementService(StockTransactionManagementDialog view) {
        this.view = view;
    }

    public void getAllStockTransactions(SortMap sortMap) {
        var service = new GetAllStockTransactionService(view, sortMap);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view,view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new TableWithNavigationHandler<TStockTransaction, StockTransaction>(service, view) {
            @Override
            public void setData(Page<TStockTransaction> page, AbstractTableWithNavigationDialog<StockTransaction> view) {
                page.getContent().forEach(obj -> view.getCenterPane().getTable().getItems().add(new StockTransaction(obj)));
            }
        });
        service.start();
    }
}
