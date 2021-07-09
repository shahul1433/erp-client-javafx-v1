package erp.client.javafx.component.searchbox.category;

import com.fasterxml.jackson.core.type.TypeReference;
import erp.client.javafx.component.FormField;
import erp.client.javafx.config.ConfigurationManager;
import erp.client.javafx.config.Constants;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.http.HttpModule;
import erp.client.javafx.http.ResponseEntity;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class CategorySearchCombobox extends ComboBox<String> implements FormField, EventHandler<KeyEvent> {

    private Label label;
    private final IntegerProperty maxLength;
    private boolean isMandatoryField;
    private GetCategoryService service;

    public CategorySearchCombobox(boolean isMandatoryField, int characterLimit_setMinusOneForNoLimit) {
        this.isMandatoryField = isMandatoryField;
        this.maxLength = new SimpleIntegerProperty(characterLimit_setMinusOneForNoLimit);
        label = new Label("Category" + (isMandatoryField ? " *" : ""));

        this.setPrefWidth(Double.MAX_VALUE);
        this.setEditable(true);
        this.getEditor().setOnKeyPressed(this);
        this.getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            moveCaret(getEditor().getText().length());
        });
        if(maxLength.intValue() >= 0) {
            this.getEditor().textProperty().addListener((obs, oldVal, newVal) -> {
                int currentLength = newVal.length();
                if(currentLength > maxLength.intValue()) {
                    getEditor().setText(getEditor().getText().trim().substring(0, maxLength.intValue()));
                }
            });
        }

        service = new GetCategoryService();
        service.setOnFailed(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                PopupUtility.showMessage(Alert.AlertType.ERROR, workerStateEvent.getSource().getException().getMessage());
            }
        });
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                CategoryList categoryList = service.getValue();
                getItems().clear();
                hide();
                if(categoryList != null) {
                    categoryList.getCategoryList().forEach(c -> getItems().add(c));
                }
                show();
            }
        });

    }

    public Label getLabel() {
        return label;
    }

    private void moveCaret(int textLength) {
        this.getEditor().positionCaret(textLength);
    }

    public String getSelectedCategory() {
        return getSelectionModel().getSelectedItem();
    }

    public void setSelectedCategory(String category) {
        this.getSelectionModel().select(category);
    }

    @Override
    public boolean validateField() {
        if(isMandatoryField) {
            String text = getEditor().getText().trim();
            if(text.isEmpty()) {
                getEditor().requestFocus();
                PopupUtility.showMessage(Alert.AlertType.ERROR, "Please enter Category");
                return false;
            }
        }
        return true;
    }

    @Override
    public void clearField() {
        getSelectionModel().select(null);
    }

    @Override
    public void setReadOnly(boolean isReadOnly) {
        setEditable(!isReadOnly);
    }

    @Override
    public void handle(KeyEvent event) {
        switch (event.getCode()) {
            case UP:
            case DOWN:
            case RIGHT:
            case LEFT:
            case HOME:
            case END:
            case ENTER:
            case TAB:
                return;
            default:
                getCategoryFromDB();
                break;
        }
    }

    private void getCategoryFromDB() {
        service.restart();
    }

    class GetCategoryService extends Service<CategoryList> {

        @Override
        protected Task<CategoryList> createTask() {
            return new GetCategoryTask();
        }

        class GetCategoryTask extends Task<CategoryList> {

            @Override
            protected CategoryList call() throws Exception {

                CategoryPOJO pojo = new CategoryPOJO(getEditor().getText().trim());
                String getCategoryUrl = ConfigurationManager.getConfiguration().getServer().getServerUrl() + Constants.StockIn.GET_CATEGORY_URL;
                ResponseEntity<CategoryList> responseEntity = HttpModule.postRequest(getCategoryUrl, pojo, new TypeReference<CategoryList>() {
                });
                if(responseEntity == null) {
                    throw new FormValidationException(Alert.AlertType.ERROR, "Something went wrong while fetch category from DB,\nPlease find the log for more info");
                }
                return responseEntity.getEntity();
            }
        }
    }
}
