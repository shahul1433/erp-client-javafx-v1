package erp.client.javafx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Logger logger = LogManager.getLogger(App.class);

    @Override
    public void start(Stage stage) {
        loadLog4jConfiguration();
        var javaVersion = SystemInfo.javaVersion();
        var javafxVersion = SystemInfo.javafxVersion();

        var label = new Label("Hello, JavaFX " + javafxVersion + ", running on Java " + javaVersion + ".");
        var scene = new Scene(new StackPane(label), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        logger.error("This is an error");
        launch();
    }

    private void loadLog4jConfiguration() {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
    }
}