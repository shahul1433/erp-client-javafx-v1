package erp.client.javafx.dealer;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.dealer.thread.GetLedgerYearsByDealerService;
import erp.client.javafx.common.ListValue;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.apache.log4j.Logger;

public class DealerDetailsService {

    private final static Logger logger = Logger.getLogger(DealerDetailsService.class);
    private DealerDetailsDialog view;

    public DealerDetailsService(DealerDetailsDialog view) {
        this.view = view;
    }

    public void getLedgerYearsByDealer(DealerDTO dealer) {
        var service = new GetLedgerYearsByDealerService(dealer);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                ListValue<Integer> listValue = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                view.getCenterPanel().topBar.year.getItems().clear();
                view.getCenterPanel().topBar.year.getItems().addAll(listValue.getList());
            }
        });
        service.start();
    }
}
