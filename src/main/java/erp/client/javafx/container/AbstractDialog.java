package erp.client.javafx.container;

import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Base class for all wizard dialog
 */
public abstract class AbstractDialog {

    protected Stage stage;
    protected Pane rootPane;
    protected ChannelInterface channelInterface;

    public AbstractDialog(ChannelInterface channelInterface) {
        this.channelInterface = channelInterface;
        this.stage = new Stage();
        init();
        designRootGUI(rootPane);
        Scene scene = new Scene(rootPane);
        this.stage.setScene(scene);
        this.stage.initModality(Modality.APPLICATION_MODAL);
        registerListeners();
        checkSecurity();
        this.stage.show();
    }

    protected abstract void init();
    protected abstract void designRootGUI(Pane pane);
    protected abstract void registerListeners();
    protected abstract boolean checkSecurity();

    public Stage getStage() {
        return stage;
    }

    public Pane getRootPane() {
        return rootPane;
    }

    public ChannelInterface getChannelInterface() {
        return channelInterface;
    }

}
