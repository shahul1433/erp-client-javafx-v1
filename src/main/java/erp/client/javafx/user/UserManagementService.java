package erp.client.javafx.user;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.common.EntityIDList;
import erp.client.javafx.exception.TableWithNavigationHandler;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.user.thread.GetAllUserService;
import erp.client.javafx.user.thread.RemoveUserService;
import erp.client.javafx.utility.PopupUtility;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.Optional;

public class UserManagementService {

    private static final Logger LOGGER = LogManager.getLogger(UserManagementService.class);
    private UserManagementDialog view;

    public UserManagementService(UserManagementDialog view) {
        this.view = view;
    }

    public void removeUser() {
        EntityIDList entityIDList = new EntityIDList();
        ObservableList<User> selectedItems = view.getCenterPane().getTable().getSelectionModel().getSelectedItems();
        Optional<User> loggedUser = selectedItems.stream().filter(u -> u.getUser().getUserId().equals(AppSession.getLoggedUser().getUserId())).findFirst();
        if(loggedUser.isPresent()) {
            PopupUtility.showMessage(Alert.AlertType.WARNING, "Sorry, user can't delete own account - " +AppSession.getLoggedUser().getName());
            return;
        }
        selectedItems.forEach(user -> entityIDList.addId(user.getUser().getUserId()));

        var service = new RemoveUserService(entityIDList);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                view.setStatusBarStatus(StatusBarStatus.WORKING);
                ResponseEntity<EntityIDList> value = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                PopupUtility.showMessage(Alert.AlertType.INFORMATION, value.getMessage());
                view.refresh();
            }
        });
        service.start();
    }

    public void getAllUsers(SortMap sortMap) {
        var service = new GetAllUserService(sortMap, view);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getBottomBar().getStatusBar()));
        service.setOnSucceeded(new TableWithNavigationHandler<UserDTO, User>(service, view) {
            @Override
            public void setData(Page<UserDTO> page, AbstractTableWithNavigationDialog<User> view) {
                page.getContent().forEach(obj -> view.getCenterPane().getTable().getItems().add(new User(obj)));
            }
        });
        service.start();
    }

}
