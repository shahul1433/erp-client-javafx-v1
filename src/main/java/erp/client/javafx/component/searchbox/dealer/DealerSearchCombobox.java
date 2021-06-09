package erp.client.javafx.component.searchbox.dealer;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.dealer.DealerList;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.utility.PopupUtility;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ProgressBar;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

public class DealerSearchCombobox extends ComboBox<DealerDTO> implements EventHandler<KeyEvent> {

    private GetDealersForSearchService service;
    private ProgressBar progressBar;

    public DealerSearchCombobox(ProgressBar bar) {
        this.progressBar = bar;
//        setPrefWidth(Double.MAX_VALUE);
        setEditable(true);
        getEditor().setOnKeyPressed(this);
        getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            moveCaret(getEditor().getText().length());
        });
        setConverter(new DealerConverter());

        service = new GetDealersForSearchService();
        service.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                PopupUtility.showMessage(Alert.AlertType.ERROR, workerStateEvent.getSource().getException().getMessage());
            }
        });
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                DealerList dealerList = service.getValue();
                getItems().clear();
                hide();
                if(dealerList != null) {
                    dealerList.getItems().forEach(d -> getItems().add(d));
                }
                show();
            }
        });
        progressBar.visibleProperty().bind(service.runningProperty());
    }

    private void moveCaret(int textLength) {
        this.getEditor().positionCaret(textLength);
    }

    private ListCell<DealerDTO> createDealerCell() {
        return new ListCell<DealerDTO>() {
            @Override
            protected void updateItem(DealerDTO item, boolean empty) {
                super.updateItem(item, empty);
                if(item == null || empty)
                    setGraphic(null);
                else
                    setText(item.getName() +" - " + item.getShop() + " - " + item.getGstin());
            }
        };
    }

    public DealerDTO getSelectedDealer() {
        return getSelectionModel().getSelectedItem();
    }

    @Override
    public void handle(KeyEvent keyEvent) {
        switch (keyEvent.getCode()) {
            case UP:
            case DOWN:
            case RIGHT:
            case LEFT:
            case TAB:
                return;
            default:
                getDealersForSearch();
                break;
        }
    }

    class DealerConverter extends StringConverter<DealerDTO> {

        @Override
        public String toString(DealerDTO dealer) {
            return dealer == null ? null : dealer.getName() +" - " + dealer.getShop() + " - " + dealer.getGstin();
        }

        @Override
        public DealerDTO fromString(String s) {
            return getSelectionModel().getSelectedItem();
        }
    }

    private void getDealersForSearch() {
        service.restart();
    }

    class GetDealersForSearchService extends Service<DealerList> {

        @Override
        protected Task<DealerList> createTask() {
            return new GetDealersForSearchTask();
        }

        class GetDealersForSearchTask extends Task<DealerList> {

            @Override
            protected DealerList call() throws Exception {
                DealerSearchPOJO searchTerm = new DealerSearchPOJO();
                searchTerm.setSearchTerm(getEditor().getText().trim());

                String getSearchDealerUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.Dealer.GET_ALL_DEALERS_FOR_SEARCH_BOX_URL;
                ResponseEntity<DealerList> responseEntity = HttpModule.postRequest(getSearchDealerUrl, searchTerm, new TypeReference<DealerList>() {
                });
                if(responseEntity == null) {
                    throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetching dealer.\nPlease find log for more info");
                }
                return responseEntity.getEntity();
            }
        }
    }
}
