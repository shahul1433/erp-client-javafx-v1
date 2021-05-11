package erp.client.javafx.dealer;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.dealer.thread.GetAllDealerService;
import erp.client.javafx.dealer.thread.RemoveDealerService;
import erp.client.javafx.entity.EntityIDList;
import erp.client.javafx.entity.TDealer;
import erp.client.javafx.exception.TableWithNavigationHandler;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.utility.PopupUtility;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class DealerManagementService {

    private static final Logger LOGGER = LogManager.getLogger(DealerManagementService.class);
    private DealerManagementDialog view;

    public DealerManagementService(DealerManagementDialog view) {
        this.view = view;
    }

    public void getAllDealers(SortMap sortMap) {
        var service = new GetAllDealerService(view, sortMap);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new TableWithNavigationHandler<TDealer, Dealer>(service, view) {
            @Override
            public void setData(Page<TDealer> page, AbstractTableWithNavigationDialog<Dealer> view) {
                page.getContent().forEach(obj -> view.getCenterPane().getTable().getItems().add(new Dealer(obj)));
            }
        });
        service.start();
    }

    public void removeDealer() {
        EntityIDList entityIDList = new EntityIDList();
        ObservableList<Dealer> selectedItems = view.getCenterPane().getTable().getSelectionModel().getSelectedItems();
        selectedItems.forEach(dealer -> entityIDList.addId(dealer.getDealer().getId()));
        view.setStatusBarStatus(StatusBarStatus.WORKING);

        var service = new RemoveDealerService(entityIDList);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                ResponseEntity<EntityIDList> value = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                PopupUtility.showMessage(Alert.AlertType.INFORMATION, value.getMessage());
                view.refresh();
            }
        });
        service.start();
    }
}
