package erp.client.javafx.user.thread;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.http.SortMap;
import erp.client.javafx.user.UserDTO;
import erp.client.javafx.user.UserFilter;
import erp.client.javafx.user.UserManagementDialog;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

public class GetAllUserService extends Service<Page<UserDTO>> {

    private UserManagementDialog view;
    private SortMap sortMap;

    public GetAllUserService(SortMap sortMap, UserManagementDialog view) {
        this.view = view;
        this.sortMap = sortMap;
    }

    @Override
    protected Task<Page<UserDTO>> createTask() {
        return new GetAllUserTask();
    }

    class GetAllUserTask extends Task<Page<UserDTO>> {

        @Override
        protected Page<UserDTO> call() throws Exception {
            synchronized (view) {
                if (sortMap == null)
                    sortMap = new SortMap("userId", "desc");
                int pageNo = view.getBottomBar().getPageNo().getValue() != null ? view.getBottomBar().getPageNo().getValue() - 1 : 0;
                int size = view.getBottomBar().getItemsPerPage().getValue() != null ? view.getBottomBar().getItemsPerPage().getValue() : 20;
                UserFilter filter = (UserFilter) view.getFilterDialog().getForm();
                filter.setPage(pageNo);
                filter.setSize(size);
                filter.setSortMap(sortMap);

                String getAllUserUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.User.GET_ALL_USER_URL;
                ResponseEntity<Page<UserDTO>> responseEntity = HttpModule.postRequest(getAllUserUrl, filter, new TypeReference<Page<UserDTO>>() {
                });
                if (responseEntity == null) {
                    throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch users from DB,\nPlease find log for more info.");
                }
                return responseEntity.getEntity();
            }
        }
    }
}
