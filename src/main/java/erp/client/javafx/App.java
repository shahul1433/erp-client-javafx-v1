package erp.client.javafx;

import erp.client.javafx.component.event.PopupEvent;
import erp.client.javafx.component.event.PopupEventHandler;
import erp.client.javafx.login.LoginPane;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Logger logger = LogManager.getLogger(App.class);

    LoginPane loginPane;
    Scene loginScene;
    Stage stage;

    private void initComponents() {
        loginPane = new LoginPane();
        loginScene = new Scene(loginPane);
        stage.addEventHandler(PopupEvent.ANY, new PopupEventHandler(stage)); //This will show popup of message
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        loadLog4jConfiguration();
        initComponents();

        stage.setScene(loginScene);
        stage.setTitle("ERP-Client-V1.0");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/dolphin.png")));
        stage.setMaximized(true);
        stage.show();
    }

    public static void main(String[] args) {
        logger.info("***** Loading FontAwsome fonts *****");
        Font.loadFont(App.class.getResourceAsStream("/font/fa-solid-900.ttf"), 12);
        Font.loadFont(App.class.getResourceAsStream("/font/fa-regular-400.ttf"), 12);
        Font.loadFont(App.class.getResourceAsStream("/font/fa-brands-400.ttf"), 12);
        logger.info("Starting App...");
        launch();
    }

    private void loadLog4jConfiguration() {
        PropertyConfigurator.configure(getClass().getResourceAsStream("/log4j.properties"));
    }
}