package erp.client.javafx.component.objectchooser;

import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.container.tablewithnavigation.TopBar;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.dealer.DealerDetailsDialog;
import erp.client.javafx.dealer.DealerManagementDialog;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Alert;

public class DealerChooser extends BaseChooser<DealerDTO> {
    /**
     * @param arguments Parameters required in the arguments:
     *                  - name
     */
    public DealerChooser(Arguments arguments) {
        super(arguments);
    }

    @Override
    public boolean validateField() {
        var isValid = objectProperty().get() != null;
        if (!isValid) {
            PopupUtility.showMessage(Alert.AlertType.ERROR, "Please select the dealer");
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
    protected void openChooserWindow() {
        new DealerChooserDialog();
    }

    @Override
    public void registerListeners() {
        super.registerListeners();

        objectProperty().addListener(new ChangeListener<DealerDTO>() {
            @Override
            public void changed(ObservableValue<? extends DealerDTO> observableValue, DealerDTO oldDto, DealerDTO newDto) {
                getTextField().setText(newDto != null ? newDto.getBriefInfo() : null);
            }
        });
    }

    @Override
    protected void openObjectViewer() {
        Arguments args = new Arguments();
        args.setArgument("dealer", objectProperty().get());
        new DealerDetailsDialog(null, StageMode.VIEW, args);
    }

    @Override
    public boolean checkSecurity() {
        return false;
    }

    public DealerDTO getDealerDTO() {
        return objectProperty().get();
    }

    class DealerChooserDialog extends DealerManagementDialog {
        public DealerChooserDialog() {
            super();
            getStage().setTitle("Choose Dealer");
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
                if (e.getClickCount() >= 2) {
                    DealerDTO dealerDTO = getCenterPane().getTable().getSelectionModel().getSelectedItem().getDealer();
                    objectProperty().set(dealerDTO);
                    getStage().close();
                }
            });
        }
    }
}
