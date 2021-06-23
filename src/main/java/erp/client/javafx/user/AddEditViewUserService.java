package erp.client.javafx.user;

import erp.client.javafx.component.event.trigger.TriggerEvent;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.user.thread.AddUserService;
import erp.client.javafx.user.thread.GetAllRolesService;
import erp.client.javafx.utility.PopupUtility;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class AddEditViewUserService {

    private static final Logger LOGGER = LogManager.getLogger(AddEditViewUserService.class);
    AddEditViewUserDialog view;

    public AddEditViewUserService(AddEditViewUserDialog view) {
        this.view = view;
    }

    public void addUser() {
        String name = view.detailsPane.name.getText().trim();
        String designation = view.detailsPane.designation.getText().trim();
        String email = view.detailsPane.email.getText().trim();
        String phone = view.detailsPane.phone.getPhoneNo();
        String username = view.detailsPane.username.getText().trim();
        String password = view.detailsPane.password.getText().trim();
        HashSet<UserRole> userRoles = view.rolesPane.selectedRoles.getRoles();

        UserPersistDTO user;
        if(view.getStageMode() == StageMode.EDIT) {
            user = view.user;
        }else {
            user = new UserPersistDTO();
        }
        user.setName(name);
        user.setDesignation(designation);
        user.setEmail(email);
        user.setPhone(phone);
        user.setUsername(username);
        user.setPassword(password);

        Set<UserRoleDTO> roles = new HashSet<>();
        userRoles.forEach(ur -> roles.add(ur.getUserRole()));

        user.setRoles(roles);
        user.setArchive(false);

        var service = new AddUserService(user);
        view.getStatusBar().setStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                ResponseEntity<UserDTO> entity = service.getValue();
                view.getStatusBar().setStatus(StatusBarStatus.READY);
                AppSession.updateLoggedUserIfApplicable(entity.getEntity());
                PopupUtility.showMessage(Alert.AlertType.INFORMATION, entity.getMessage());
                view.getStage().close();
                view.getParentStage().fireEvent(new TriggerEvent(TriggerEvent.REFRESH));
            }
        });
        service.start();
    }

    public void getAllRoles() {
        var service = new GetAllRolesService();
        view.getStatusBar().setStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                UserRolesList userRolesList = service.getValue();
                for (UserRoleDTO role : userRolesList.getRoles()) {
                    view.rolesPane.availableRoles.addUserRole(new UserRole(role));
                }
                view.getStatusBar().setStatus(StatusBarStatus.READY);
                view.populateFields();
            }
        });
        service.start();
    }

}
