package erp.client.javafx.dealer;

import erp.client.javafx.component.event.trigger.TriggerEvent;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.dealer.thread.SaveDealerService;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.gst.GstStateCodeDTO;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.utility.PopupUtility;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class AddEditDealerService {

    private static final Logger LOGGER = LogManager.getLogger(AddEditDealerService.class);
    AddEditDealerDialog view;

    public AddEditDealerService(AddEditDealerDialog view) {
        this.view = view;
    }

    public void saveDealer() {
        String name = view.getName().getText().trim();
        String shop = view.getShop().getText().trim();
        String email = view.getEmail().getText().trim();
        String phoneNo = view.getPhone().getPhoneNo();
        String gstin = view.getGstin().getText().trim();
        GstStateCodeDTO selectedGstStateCode = view.getGstStateCodeCombobox().getSelectedGstStateCode();
        String address = view.getAddress().getText().trim();

        DealerDTO dealer;
        if (view.getStageMode() == StageMode.EDIT) {
            dealer = view.getDealer();
        }else {
            dealer = new DealerDTO();
        }
        dealer.setName(name);
        dealer.setShop(shop);
        dealer.setAddress(address);
        dealer.setEmail(email);
        dealer.setPhone(phoneNo);
        dealer.setGstin(gstin);
        dealer.setGstStateCode(selectedGstStateCode);
        dealer.setAddress(address);
        dealer.setArchive(false);

        view.setStatusBarStatus(StatusBarStatus.WORKING);
        var service = new SaveDealerService(dealer);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                ResponseEntity<DealerDTO> responseEntity = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                PopupUtility.showMessage(Alert.AlertType.INFORMATION, responseEntity.getMessage());
                view.getStage().close();
                view.getParentStage().fireEvent(new TriggerEvent(TriggerEvent.REFRESH));
            }
        });
        service.start();
    }
}
