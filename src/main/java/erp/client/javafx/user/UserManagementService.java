package erp.client.javafx.user;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.entity.EntityIDList;
import erp.client.javafx.entity.TUser;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.user.thread.GetAllUserService;
import erp.client.javafx.user.thread.RemoveUserService;
import erp.client.javafx.utility.PopupUtility;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class UserManagementService {

    private static final Logger LOGGER = LogManager.getLogger(UserManagementService.class);
    private UserManagementDialog view;

    public UserManagementService(UserManagementDialog view) {
        this.view = view;
    }

    public void removeUser() {
        EntityIDList entityIDList = new EntityIDList();
        ObservableList<User> selectedItems = view.getCenterPane().getTable().getSelectionModel().getSelectedItems();
        selectedItems.forEach(user -> entityIDList.addId(user.getUser().getId()));

        var service = new RemoveUserService(entityIDList);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                view.setStatusBar(StatusBarStatus.WORKING);
                ResponseEntity<EntityIDList> value = service.getValue();
                view.setStatusBar(StatusBarStatus.READY);
                PopupUtility.showMessage(Alert.AlertType.INFORMATION, value.getMessage());
                view.refresh();
            }
        });
        service.start();
    }

    public void getAllUsers(SortMap sortMap) {
        var service = new GetAllUserService(sortMap, view);
        view.setStatusBar(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                Page<TUser> page = service.getValue();
                view.getBottomBar().getPrevious().setDisable(page.isFirst());
                view.getBottomBar().getNext().setDisable(page.isLast());

                long totalItems = page.getTotalElements();

                //Setting items info label
                long pageNumber = page.getPageable().getPageNumber();
                long pageSize = page.getPageable().getPageSize();
                long numberOfElements = page.getNumberOfElements();
                long startItem = (pageNumber * pageSize) + 1;
                long endItem = (pageNumber * pageSize) + numberOfElements;
                if(endItem == 0) startItem = 0;
                String info = "Showing "+startItem+" - "+endItem+" of "+totalItems;
                view.getBottomBar().getInfo().setText(info);

                //Setting no of pages
                long pages = page.getTotalPages();

                ObservableList<Integer> items = FXCollections.observableArrayList();
                for(int i=0 ; i<pages; i++)
                {
                    items.add(i+1);
                }
                if(items.isEmpty())
                    items.add(1);
                view.getBottomBar().getPageNo().setPageNoItems(items);

                //Populate data to the table.
                view.getCenterPane().getTable().getItems().clear();
                page.getContent().forEach(userObj -> view.getCenterPane().getTable().getItems().add(new User(userObj)));
                view.setStatusBar(StatusBarStatus.READY);
            }
        });
        service.start();
    }

}
