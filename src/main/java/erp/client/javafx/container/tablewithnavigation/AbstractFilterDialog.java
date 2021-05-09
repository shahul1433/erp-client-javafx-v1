package erp.client.javafx.container.tablewithnavigation;

import erp.client.javafx.component.event.popup.PopupEvent;
import erp.client.javafx.component.event.popup.PopupEventHandler;
import erp.client.javafx.container.ChannelInterface;
import erp.client.javafx.layout.AbstractBorderPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;

/**
 * Base class for filter dialog
 *
 * @param <T>
 */
public abstract class AbstractFilterDialog<T> extends AbstractBorderPane {

    protected Stage stage;

    protected Button apply, clear;

    protected ChannelInterface channelInterface;
    protected AbstractTableWithNavigationDialog parent;

    public AbstractFilterDialog(AbstractTableWithNavigationDialog parent) {
        super();
//        this.channelInterface = channelInterface;
        this.parent = parent;

        Scene scene = new Scene(this);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/image/filter.png")));
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Filter");
    }

    protected abstract Pane designFilterGUI();

    protected abstract void clearFilterFields();

    protected abstract boolean isValidFilter();

    protected abstract void initFilterComponents();

    public abstract T getForm();

    protected void onApply() {
        this.parent.refresh();
    }

    @Override
    public void init() {
        this.stage = new Stage();
        this.stage.addEventHandler(PopupEvent.ANY, new PopupEventHandler(stage)); //This will show popup of message

        apply = new Button("Apply");
        apply.setStyle("-fx-base: green");
        clear = new Button("Clear");
        clear.setStyle("-fx-base: red");

        initFilterComponents();
    }

    @Override
    public void designGUI() {
        this.setCenter(new ScrollPane(designFilterGUI()));

        HBox buttons = new HBox(10);
        buttons.setPadding(new Insets(10));
        buttons.setAlignment(Pos.CENTER);
        buttons.setStyle("-fx-background-color: #333333;");
        buttons.getChildren().addAll(apply, clear);

        this.setBottom(buttons);
    }

    @Override
    public void registerListeners() {
        clear.setOnAction(e -> {
            clearFilterFields();
            onApply();
        });

        apply.setOnAction(e -> {
            if (isValidFilter()) {
                this.parent.topBar.clearFilter.setVisible(isValidFilter());
                onApply();
                this.stage.close();
            }
        });
    }

    private void adjustFilterHeight() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenHeight = screenSize.getHeight();
        double filterHeight = this.getHeight();
        if (filterHeight >= screenHeight) {
            filterHeight = (0.8 * screenHeight);
            this.stage.setHeight(filterHeight);
        }
    }

    public void showFilter() {
        stage.show();
        adjustFilterHeight();
    }

    @Override
    public boolean checkSecurity() {
        return true;
    }

    public Stage getStage() {
        return stage;
    }

    public Button getApply() {
        return apply;
    }

    public Button getClear() {
        return clear;
    }

    public ChannelInterface getChannelInterface() {
        return channelInterface;
    }

    public AbstractTableWithNavigationDialog getParentDialog() {
        return parent;
    }
}
