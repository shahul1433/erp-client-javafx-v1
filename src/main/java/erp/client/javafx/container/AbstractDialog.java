package erp.client.javafx.container;

import erp.client.javafx.component.event.popup.PopupEvent;
import erp.client.javafx.component.event.popup.PopupEventHandler;
import erp.client.javafx.container.status.StatusBar;
import erp.client.javafx.utility.PopupUtility;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.InputStream;

/**
 * Base class for all wizard dialog
 */
public abstract class AbstractDialog extends BorderPane{

    protected Stage stage;
//    protected Pane contentPane, buttonPane;
    protected Stage parentStage;
    protected StageMode stageMode;
    protected StatusBar statusBar;

    public AbstractDialog(Stage parentStage, StageMode stageMode) {
        this.parentStage = parentStage;
        this.stageMode = stageMode;
        this.stage = new Stage();
        this.statusBar = new StatusBar();

        init();
        adjustViewByStageMode();

        this.setCenter(designContentGUI());
        VBox vBox = new VBox(5);
        vBox.setStyle("-fx-background-color: #333333;");
        vBox.getChildren().addAll(designButtonGUI(), statusBar);
        this.setBottom(vBox);

        Scene scene = new Scene(this);
        this.stage.setScene(scene);
        this.stage.addEventHandler(PopupEvent.ANY, new PopupEventHandler(stage)); //This will show popup of message
        this.stage.initModality(Modality.APPLICATION_MODAL);
        this.stage.getIcons().add(new Image(getIcon()));
        registerListeners();
        if(!checkSecurity()){
            PopupUtility.showMessage(Alert.AlertType.ERROR, "Sorry, you don't have permission to access this module");
        }else{
            this.stage.show();
        }
    }

    protected abstract void init();
    protected abstract Pane designContentGUI();
    protected abstract Pane designButtonGUI();
    protected abstract void registerListeners();
    protected abstract boolean checkSecurity();
    protected abstract InputStream getIcon();
    protected abstract void adjustViewByStageMode();

    public Stage getStage() {
        return stage;
    }

//    public Pane getContentPane() {
//        return contentPane;
//    }

//    public Pane getButtonPane() {
//        return buttonPane;
//    }

    public Stage getParentStage() {
        return parentStage;
    }

    public StageMode getStageMode() {
        return stageMode;
    }

    public StatusBar getStatusBar() {
        return statusBar;
    }
}
