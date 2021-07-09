package erp.client.javafx.component.objectchooser;

import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.tablewithnavigation.TopBar;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.stock.stockin.StockInDetailsDialog;
import erp.client.javafx.stock.stockin.StockInManagementDialog;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;

public class StockInChooser extends BaseChooser<StockInDTO>{

    /**
     * @param arguments
     * Parameters required in the arguments:
     *  - name
     */
    public StockInChooser(Arguments arguments) {
        super(arguments);
    }

    @Override
    public boolean validateField() {
        var isValid = objectProperty().get() != null;
        if (!isValid) {
            PopupUtility.showMessage(Alert.AlertType.ERROR, "Please select the stock");
        }
        return isValid;
    }

    @Override
    public void clearField() {
        objectProperty().set(null);
    }

    @Override
    public void setReadOnly(boolean isReadOnly) {
        select.setDisable(isReadOnly);
    }

    @Override
    public void registerListeners() {
        super.registerListeners();

        objectProperty().addListener(new ChangeListener<StockInDTO>() {
            @Override
            public void changed(ObservableValue<? extends StockInDTO> observableValue, StockInDTO oldDto, StockInDTO newDto) {
                getTextField().setText(newDto != null ? newDto.getItemBriefInfo() : null);
            }
        });
    }

    @Override
    protected void openChooserWindow() {
        new StockInChooserDialog();
    }

    @Override
    protected void openObjectViewer() {
        Arguments args = new Arguments();
        args.setArgument("stockIn", objectProperty().get());
        new StockInDetailsDialog(null, StageMode.VIEW, args);
    }

    public StockInDTO getStockInDTO() {
        return objectProperty().get();
    }

    @Override
    public boolean checkSecurity() {
        return false;
    }

    class StockInChooserDialog extends StockInManagementDialog {

        public StockInChooserDialog() {
            super();
            getStage().setTitle("Choose Item");
        }

        @Override
        public void init() {
            super.init();
            setTopBar(new TopBar());
        }

        @Override
        public void registerListeners() {
            super.registerListeners();

            this.getCenterPane().getTable().setOnMouseClicked(e -> {
                if(e.getClickCount() >= 2) {
                    StockInDTO stockIn = getCenterPane().getTable().getSelectionModel().getSelectedItem().getStockIn();
                    if(stockIn.getCurrentQuantity().equals(Double.valueOf(0))) {
                        showMessage(Alert.AlertType.WARNING, "There is no stock to return in '" + stockIn.getItemBriefInfo() +"'");
                        return;
                    }
                    objectProperty().set(stockIn);
                    getStage().close();
                }
            });
        }
    }
}
