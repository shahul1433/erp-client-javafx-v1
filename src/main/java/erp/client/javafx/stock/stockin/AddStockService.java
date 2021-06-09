package erp.client.javafx.stock.stockin;

import erp.client.javafx.component.event.trigger.TriggerEvent;
import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.user.UserDTO;
import erp.client.javafx.utility.PopupUtility;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class AddStockService {

    private static final Logger LOGGER = Logger.getLogger(AddStockService.class);
    private AddStockDialog view;

    public AddStockService(AddStockDialog view) {
        this.view = view;
    }

    public void addStock() throws FormValidationException {
        String msg;
        DealerDTO selectedDealer = view.getDealerChooserPanel().getSelectedDealer();
        UserDTO loggedUser = AppSession.getLoggedUser();

        if(selectedDealer == null) {
            msg = "Please select a dealer";
            LOGGER.warn(msg);
            throw new FormValidationException(Alert.AlertType.WARNING, msg);
        }

        if(loggedUser == null) {
            msg = "Logged user not found, please re-login to continue";
            LOGGER.error(msg);
            throw new FormValidationException(Alert.AlertType.ERROR, msg);
        }

        StockInDTO stockIn = view.getStockDetailsPanel().populateAndGetStockIn();
        LocalDateTime addedDate = LocalDateTime.now();
        stockIn.setAddedDate(addedDate);
        stockIn.setAddedBy(loggedUser);
        stockIn.setDealer(selectedDealer);
        stockIn.setArchive(false);

        view.setStatusBarStatus(StatusBarStatus.WORKING);
        var service = new erp.client.javafx.stock.stockin.thread.AddStockService(stockIn);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                ResponseEntity<StockInDTO> entity = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                PopupUtility.showMessage(Alert.AlertType.INFORMATION, entity.getMessage());
                view.getParentStage().fireEvent(new TriggerEvent(TriggerEvent.REFRESH));
            }
        });
        service.start();
    }
}
