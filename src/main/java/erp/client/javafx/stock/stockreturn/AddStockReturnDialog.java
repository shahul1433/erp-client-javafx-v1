package erp.client.javafx.stock.stockreturn;

import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.component.event.popup.PopupEvent;
import erp.client.javafx.component.objectchooser.StockInChooser;
import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.currency.IndianRupeesField;
import erp.client.javafx.component.textfield.percentage.GstPercentageField;
import erp.client.javafx.component.textfield.quantity.QuantityField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.session.AppSession;
import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.utility.PopupUtility;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;
import java.math.BigDecimal;

public class AddStockReturnDialog extends AbstractDialog {

    private StockInChooser stockInChooser;
    private CTextArea reason;
    private QuantityField stockInQty, currentQty, returnQty;
    private IndianRupeesField refundAmount, stockPrice;
    private GstPercentageField gstPercentageField;
    private Button add, clear;
    private AddStockReturnService addStockReturnService = new AddStockReturnService(this);

    public AddStockReturnDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
    }

    @Override
    protected void init() {

        Arguments arguments = new Arguments();
        arguments.setArgument("name", "Item");
        stockInChooser = new StockInChooser(arguments);

        reason = new CTextArea("Reason for return", true, 500);

        stockInQty = new QuantityField("Stock Quantity", false, ProductScale.COUNT);
        stockInQty.setReadOnly(true);
        currentQty = new QuantityField("Current Quantity", false, ProductScale.COUNT);
        currentQty.setReadOnly(true);
        returnQty = new QuantityField("Return Quantity", true, ProductScale.COUNT);

        refundAmount = new IndianRupeesField("Refund Amount", false);
        refundAmount.setReadOnly(true);

        stockPrice = new IndianRupeesField("Stock Price", false);
        stockPrice.setReadOnly(true);

        gstPercentageField = new GstPercentageField("GST(%)", false);
        gstPercentageField.setReadOnly(true);

        add = new Button("Add");
        add.setStyle("-fx-base: red;");
        clear = new Button("Clear");
        clear.setStyle("-fx-base: green;");
    }

    @Override
    protected Pane designContentGUI() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25));
        pane.setAlignment(Pos.CENTER);

        int col = 0, row = 0;

        pane.add(stockInChooser.getLabel(), col++, row);
        pane.add(stockInChooser, col--, row++);

        pane.add(stockPrice.getLabel(), col++, row);
        pane.add(stockPrice, col--, row++);

        pane.add(gstPercentageField.getLabel(), col++, row);
        pane.add(gstPercentageField, col--, row++);

        pane.add(stockInQty.getLabel(), col++, row);
        pane.add(stockInQty, col--, row++);

        pane.add(currentQty.getLabel(), col++, row);
        pane.add(currentQty, col--, row++);

        pane.add(returnQty.getLabel(), col++, row);
        pane.add(returnQty, col--, row++);

        pane.add(refundAmount.getLabel(), col++, row);
        pane.add(refundAmount, col--, row++);

        pane.add(reason.getLabel(), col++, row);
        pane.add(reason.getTextAreaWithProgressBar(), col, row++);

        return pane;
    }

    @Override
    protected Pane designButtonGUI() {
        HBox buttonPane = new HBox(10);
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.setPadding(new Insets(10));
        buttonPane.getChildren().addAll(add, clear);
        return buttonPane;
    }

    @Override
    protected void registerListeners() {
        stockPrice.disableProperty().bind(stockInChooser.objectProperty().isNull());
        gstPercentageField.disableProperty().bind(stockInChooser.objectProperty().isNull());
        stockInQty.disableProperty().bind(stockInChooser.objectProperty().isNull());
        currentQty.disableProperty().bind(stockInChooser.objectProperty().isNull());
        returnQty.disableProperty().bind(stockInChooser.objectProperty().isNull());
        refundAmount.disableProperty().bind(stockInChooser.objectProperty().isNull());
        reason.disableProperty().bind(stockInChooser.objectProperty().isNull());

        stockInChooser.objectProperty().addListener(new ChangeListener<StockInDTO>() {
            @Override
            public void changed(ObservableValue<? extends StockInDTO> observableValue, StockInDTO oldVal, StockInDTO newVal) {
                if (newVal != null) {
                    stockPrice.setCashAmount(newVal.getStockPrice());
                    gstPercentageField.setGst(newVal.getGst());
                    stockInQty.setQuantity(newVal.getStockQuantity(), newVal.getScale());
                    currentQty.setQuantity(newVal.getCurrentQuantity(), newVal.getScale());
                    returnQty.setScale(newVal.getScale());
                } else {
                    stockPrice.clearField();
                    gstPercentageField.clearField();
                    stockInQty.clearField();
                    currentQty.clearField();
                }
                returnQty.clearField();
                refundAmount.clearField();
                reason.clearField();
            }
        });

        clear.setOnAction(e -> clearFields());

        returnQty.getField().setOnKeyTyped(e -> {
            if (!checkReturnValue()) {
                PopupUtility.showMessage(Alert.AlertType.WARNING, "Return quantity should be less than or equal to current quantity");
                returnQty.clearField();
                return;
            } else {
                calculateRefundAmount();
            }
        });

        add.setOnAction(e -> {
            if (stockInChooser.validateField() && returnQty.validateField() && reason.validateField()) {
                try {
                    addStockReturnService.addStockReturn();
                } catch (FormValidationException formValidationException) {
                    stage.fireEvent(new PopupEvent<FormValidationException>(Alert.AlertType.ERROR, formValidationException));
                }
            }
        });
    }

    private void calculateRefundAmount() {
        BigDecimal returnQtyQuantity = returnQty.getQuantityBigDecimal();
        BigDecimal stockPriceAmt = stockPrice.getCashAmountBigDecimal();
        BigDecimal gstPercentage = gstPercentageField.getGstBigDecimal();

        BigDecimal totAmount = returnQtyQuantity.multiply(stockPriceAmt);
        BigDecimal gst = totAmount.multiply(gstPercentage);
        refundAmount.setCashAmount(totAmount.add(gst).doubleValue());
    }

    private boolean checkReturnValue() {
        if (currentQty.getQuantity() != null && returnQty.getQuantity() != null) {
            Double current = currentQty.getQuantity();
            Double retrn = returnQty.getQuantity();
            int compareTo = retrn.compareTo(current);
            switch (compareTo) {
                case -1:
                case 0:
                    return true;
                default:
                    return false;
            }
        }
        return true;
    }

    private void clearFields() {
        stockInChooser.clearField();
    }

    @Override
    protected boolean checkSecurity() {
        return AppSession.hasRole(UserRole.STOCK_RETURN);
    }

    @Override
    protected InputStream getIcon() {
        return getClass().getResourceAsStream("/image/inventory.png");
    }

    @Override
    protected void adjustViewByStageMode() {
        if (stageMode == StageMode.ADD) {
            stage.setTitle("Add Stock Return");
        } else if (stageMode == StageMode.VIEW) {
            stage.setTitle("View Stock Return");
        }
    }

    public StockInChooser getStockInChooser() {
        return stockInChooser;
    }

    public CTextArea getReason() {
        return reason;
    }

    public QuantityField getStockInQty() {
        return stockInQty;
    }

    public QuantityField getCurrentQty() {
        return currentQty;
    }

    public QuantityField getReturnQty() {
        return returnQty;
    }

    public IndianRupeesField getRefundAmount() {
        return refundAmount;
    }

    public IndianRupeesField getStockPrice() {
        return stockPrice;
    }

    public GstPercentageField getGstPercentageField() {
        return gstPercentageField;
    }
}
