package erp.client.javafx.stock.stockreturn;

import erp.client.javafx.component.event.trigger.TriggerEvent;
import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.user.UserDTO;
import erp.client.javafx.utility.PopupUtility;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import org.apache.log4j.Logger;

import java.time.LocalDateTime;

public class AddStockReturnService {

    private static final Logger logger = Logger.getLogger(AddStockReturnService.class);
    private AddStockReturnDialog view;

    public AddStockReturnService(AddStockReturnDialog view) {
        this.view = view;
    }

    public void addStockReturn() throws FormValidationException {
        String msg;

        UserDTO loggedUser = AppSession.getLoggedUser();

        if (loggedUser == null) {
            msg = "Logged user not found, please re-login to continue";
            throw new FormValidationException(Alert.AlertType.ERROR, msg);
        }

        StockInDTO stockInDTO = view.getStockInChooser().getStockInDTO();
        String reason = view.getReason().getText().trim();
        Double returnQty = view.getReturnQty().getQuantity();
        Double refundAmount = view.getRefundAmount().getCashAmount();

        StockReturnDTO dto = new StockReturnDTO();
        dto.setStockIn(stockInDTO);
        dto.setReason(reason);
        dto.setReturnQuantity(returnQty);
        dto.setRefundAmount(refundAmount);
        dto.setAddedDate(LocalDateTime.now());
        dto.setAddedBy(loggedUser);
        dto.setArchive(false);

        view.setStatusBarStatus(StatusBarStatus.WORKING);
        var service = new erp.client.javafx.stock.stockreturn.thread.AddStockReturnService(dto);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                ResponseEntity<StockReturnDTO> entity = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                PopupUtility.showMessage(Alert.AlertType.INFORMATION, entity.getMessage());
                view.getStage().close();
                view.getParentStage().fireEvent(new TriggerEvent(TriggerEvent.REFRESH));
            }
        });
        service.start();
    }
}
