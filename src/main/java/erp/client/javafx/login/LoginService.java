package erp.client.javafx.login;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.entity.TUser;
import erp.client.javafx.exception.WorkerStateEventExceptionHandler;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.home.HomeWindow;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.utility.GuiUtility;
import erp.client.javafx.utility.JacksonService;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class LoginService {

    private static Logger logger = LogManager.getLogger(LoginService.class);

    LoginPane view;

    public LoginService(LoginPane view) {
        this.view = view;
    }

    public void validateFields() throws FormValidationException {
        String username = view.username.getText().trim();
        String password = view.password.getText().trim();

        if(username.isBlank()) {
            view.username.requestFocus();
            throw new FormValidationException(Alert.AlertType.WARNING, "Please enter username");
        }else if(password.isBlank()) {
            view.password.requestFocus();
            throw new FormValidationException(Alert.AlertType.WARNING, "Please enter password");
        }
    }

    public void login() throws FormValidationException {
        validateFields();
        var task = new LoginTask();
        task.setOnFailed(new WorkerStateEventExceptionHandler(view));
        task.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                Stage primaryStage = (Stage) view.login.getScene().getWindow();
                primaryStage.setScene(new Scene(new HomeWindow(((TUser)workerStateEvent.getSource().getValue()).getName()), GuiUtility.getScreenWidth(), GuiUtility.getScreenHeight()));
                primaryStage.setMaximized(true);
            }
        });
        view.progressBar.visibleProperty().bind(task.runningProperty());
        view.progressBar.progressProperty().bind(task.progressProperty());
        new Thread(task).start();
    }

    class LoginTask extends Task<TUser> {

        @Override
        protected TUser call() throws Exception {
            String msg;
            String username = view.username.getText().trim();
            String password = view.password.getText().trim();

            AuthenticationRequest request = new AuthenticationRequest(username,password);

            String loginUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.User.LOGIN_URL;
            String getLoggedUserUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.User.GET_LOGGED_USER;

            CloseableHttpClient httpClient = HttpClients.createDefault();
            HttpPost post = new HttpPost(loginUrl);

            StringEntity input = new StringEntity(request.toString());
            input.setContentType("application/json");
            post.setEntity(input);

            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(post);

                if(response == null) {
                    msg = "Something went wrong while connecting to the server";
                    logger.error(msg);
                    throw new FormValidationException(Alert.AlertType.ERROR, msg);
                }

                if(response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) {
                    msg = "Invalid credentials !";
                    logger.error(msg);
                    throw new FormValidationException(Alert.AlertType.WARNING, msg);
                }
                AuthenticationResponse tokenResponse = JacksonService.jsonToObject(new TypeReference<AuthenticationResponse>() {}, JacksonService.getResponse(response));
                AppSession.setAuthorization(tokenResponse.getToken());

                ResponseEntity<TUser> responseEntity = HttpModule.getRequest(getLoggedUserUrl, new TypeReference<TUser>() {});
                if(responseEntity == null)
                    throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while retrieve logged user");
                AppSession.setLoggedUser(responseEntity.getEntity());
                return responseEntity.getEntity();
            }finally {
                if(response != null){
                    response.close();
                }
            }
        }

    }
}
