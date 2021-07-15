package erp.client.javafx.stock.stockin;

import erp.client.javafx.component.border.BorderedTitledPane;
import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.component.enums.UserRole;
import erp.client.javafx.component.event.popup.PopupEvent;
import erp.client.javafx.component.font.CustomFontManager;
import erp.client.javafx.component.objectchooser.DealerChooser;
import erp.client.javafx.component.scale.ProductScaleCombobox;
import erp.client.javafx.component.searchbox.category.CategorySearchCombobox;
import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.component.textfield.currency.IndianRupeesField;
import erp.client.javafx.component.textfield.percentage.GstPercentageField;
import erp.client.javafx.component.textfield.quantity.QuantityField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.dealer.DealerChooserPanel;
import erp.client.javafx.exception.FormValidationException;
import erp.client.javafx.layout.AbstractGridPane;
import erp.client.javafx.session.AppSession;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.io.InputStream;
import java.math.BigDecimal;

public class AddStockDialog extends AbstractDialog {

    private StockInDTO stockIn;
    private Button add, clear;

    private StockDetailsPanel stockDetailsPanel;
    private AddStockService stockService;

    public AddStockDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
    }

    @Override
    protected void init() {
        this.stockIn = getArgument("stockIn", StockInDTO.class);
        stockService = new AddStockService(this);
        add = new Button("Add");
        add.setStyle("-fx-base: green");
        clear = new Button("Clear");
        clear.setStyle("-fx-base: red");

        stockDetailsPanel = new StockDetailsPanel();

        stage.setWidth(1400);
    }

    public StockDetailsPanel getStockDetailsPanel() {
        return stockDetailsPanel;
    }

    @Override
    protected Pane designContentGUI() {

        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(5));

        ColumnConstraints stretchedColumn = new ColumnConstraints(100, 350, Double.MAX_VALUE);
        stretchedColumn.setHgrow(Priority.ALWAYS);

        pane.getColumnConstraints().add(stretchedColumn);

        int col = 0, row = 0;
        pane.add(stockDetailsPanel, col, row);

        return pane;
    }

    @Override
    protected Pane designButtonGUI() {
        HBox buttonPane = new HBox(10);
        buttonPane.setPadding(new Insets(10));
        buttonPane.setAlignment(Pos.CENTER);
        buttonPane.getChildren().addAll(add, clear);
        return buttonPane;
    }

    @Override
    protected void registerListeners() {
        clear.setOnAction(e ->{
            stockDetailsPanel.clearForm();
        });

        add.setOnAction(e -> {
            if( stockDetailsPanel.validateFields()) {
                try {
                    stockService.addStock();
                } catch (FormValidationException formValidationException) {
                    stage.fireEvent(new PopupEvent<FormValidationException>(Alert.AlertType.ERROR, formValidationException));
                }
            }
        });
    }

    @Override
    protected boolean checkSecurity() {
        return AppSession.hasRole(UserRole.STOCK_IN);
    }

    @Override
    protected InputStream getIcon() {
        return getClass().getResourceAsStream("/image/inventory.png");
    }

    @Override
    protected void adjustViewByStageMode() {
        switch (stageMode) {
            case ADD:
                getStage().setTitle("Add Stock");
                break;
            case EDIT:
//                getStage().setTitle("Edit Stock");
//                add.setText("Update");
//                clear.setDisable(true);
//                break;
            default:
                break;
        }
    }

    class StockDetailsPanel extends AbstractGridPane {

        DealerChooser dealerChooser;
        CheckBox hasMoreItem;
        CTextField barcode, name, model, company, warranty, guarantee;
        CategorySearchCombobox category;
        ProductScaleCombobox scale;
        QuantityField stockQuantity, reorderLimit;
        IndianRupeesField stockPrice, customerPrice, gstAmount, netAmount;
        CTextArea specifications;
        GstPercentageField gst;

        @Override
        public void init() {

            Arguments args = new Arguments();
            args.setArgument("name", "Dealer");
            dealerChooser = new DealerChooser(args);

            hasMoreItem = new CheckBox("Add more items to the selected dealer");
            hasMoreItem.setFont(new CustomFontManager().getRobotoFont(12));
            hasMoreItem.setTooltip(new Tooltip("Check this if you have more items to add to the selected dealer"));

            barcode = new CTextField("Barcode",  false, 100);
            name = new CTextField("Name",  true, 100);
            model = new CTextField("Model",  true, 50);
            category = new CategorySearchCombobox(true, 50);
            company = new CTextField("Company",  true, 100);
            warranty = new CTextField("Warranty",  false, 100);
            guarantee = new CTextField("Guarantee",  false, 100);

            specifications = new CTextArea("Specifications", false, 500);

            scale = new ProductScaleCombobox();
            stockQuantity = new QuantityField("Stock Quantity", true, scale.getSelectedScale());
            reorderLimit = new QuantityField("Re-order Limit", false, scale.getSelectedScale());

            stockPrice = new IndianRupeesField("Stock Price", true);
            customerPrice = new IndianRupeesField("Customer Price", true);
            gstAmount = new IndianRupeesField("GST Amount", false);
            gstAmount.setEditable(false);
            netAmount = new IndianRupeesField("Net Amount", false);
            netAmount.setEditable(false);

            gst = new GstPercentageField("GST", false);
        }

        public boolean validateFields() {
            return (dealerChooser.validateField() && name.validateField() && model.validateField() && category.validateField() && company.validateField() && warranty.validateField() &&
                    guarantee.validateField() && specifications.validateField() && stockQuantity.validateField() && reorderLimit.validateField() &&
                    stockPrice.validateField() && customerPrice.validateField() && gstAmount.validateField() && netAmount.validateField() && gst.validateField());
        }

        @Override
        public void designGUI() {
            this.setHgap(10);
            this.setVgap(10);
            this.setPadding(new Insets(25));

            ColumnConstraints emptyColumn = new ColumnConstraints();
            ColumnConstraints stretchedColumn = new ColumnConstraints(100, 350, Double.MAX_VALUE);
            stretchedColumn.setHgrow(Priority.ALWAYS);

            this.getColumnConstraints().addAll(emptyColumn, stretchedColumn, emptyColumn, stretchedColumn);

            int col = 0, row = 0;

            this.add(dealerChooser.getLabel(), col++, row);
            this.add(dealerChooser, col, row++);

            this.add(hasMoreItem, col--, row++);

            this.add(barcode.getLabel(), col++, row);
            this.add(barcode, col--, row++);

            this.add(name.getLabel(), col++, row);
            this.add(name, col--, row++);

            this.add(model.getLabel(), col++, row);
            this.add(model, col--, row++);

            this.add(category.getLabel(), col++, row);
            this.add(category, col--, row++);

            this.add(company.getLabel(), col++, row);
            this.add(company, col--, row++);

            this.add(warranty.getLabel(), col++, row);
            this.add(warranty, col--, row++);

            this.add(guarantee.getLabel(), col++, row);
            this.add(guarantee, col--, row++);

            this.add(specifications.getLabel(), col++, row);
            this.add(specifications.getTextAreaWithProgressBar(), col--, row++, 1, 2);

            col = 2;
            row = 0;

            this.add(scale.getLabel(), col++, row);
            scale.setPrefWidth(Double.MAX_VALUE);
            this.add(scale, col--, row++);

            this.add(stockQuantity.getField().getLabel(), col++, row);
            this.add(stockQuantity, col--, row++);

            this.add(reorderLimit.getField().getLabel(), col++, row);
            this.add(reorderLimit, col--, row++);

            this.add(stockPrice.getLabel(), col++, row);
            this.add(stockPrice, col--, row++);

            this.add(customerPrice.getLabel(), col++, row);
            this.add(customerPrice, col--, row++);

            this.add(gst.getLabel(), col++, row);
            this.add(gst, col--, row++);

            this.add(gstAmount.getLabel(), col++, row);
            this.add(gstAmount, col--, row++);

            this.add(netAmount.getLabel(), col++, row);
            this.add(netAmount, col--, row++);

        }

        public StockInDTO populateAndGetStockIn() {

            String barcodeVal = barcode.getText().trim();
            String nameVal = name.getText().trim();
            String modelVal = model.getText().trim();
            String categoryVal = category.getSelectedCategory();
            String companyVal = company.getText().trim();
            String warrantyVal = warranty.getText().trim();
            String guaranteeVal = guarantee.getText().trim();
            String specificationsVal = specifications.getText().trim();
            ProductScale selectedScale = scale.getSelectedScale();
            Double stockQuantityVal = stockQuantity.getQuantity();
            Double reorderLimitVal = reorderLimit.getQuantity();
            Double stockPriceVal = stockPrice.getCashAmount();
            Double customerPriceVal = customerPrice.getCashAmount();
            Double gstAmountVal = gstAmount.getCashAmount();
            Double netAmountVal = netAmount.getCashAmount();
            Double gstVal = gst.getGst();

            stockIn.setBarcode(barcodeVal);
            stockIn.setName(nameVal);
            stockIn.setModel(modelVal);
            stockIn.setCategory(categoryVal);
            stockIn.setCompany(companyVal);
            stockIn.setWarranty(warrantyVal);
            stockIn.setGuarantee(guaranteeVal);
            stockIn.setSpecifications(specificationsVal);
            stockIn.setScale(selectedScale);
            stockIn.setStockQuantity(stockQuantityVal);
            stockIn.setCurrentQuantity(stockQuantityVal);
            stockIn.setReorderLimit(reorderLimitVal);
            stockIn.setStockPrice(stockPriceVal);
            stockIn.setCustomerPrice(customerPriceVal);
            stockIn.setGstAmount(gstAmountVal);
            stockIn.setNetAmount(netAmountVal);
            stockIn.setGst(gstVal);

            return stockIn;
        }

        public void setStockIn(StockInDTO stockIn) {
            if(stockIn != null) {
                barcode.setText(stockIn.getBarcode());
                name.setText(stockIn.getName());
                model.setText(stockIn.getModel());
                category.setSelectedCategory(stockIn.getCategory());
                company.setText(stockIn.getCompany());
                warranty.setText(stockIn.getWarranty());
                guarantee.setText(stockIn.getGuarantee());
                specifications.setText(stockIn.getSpecifications());
                scale.setScale(stockIn.getScale());
                stockQuantity.setQuantity(stockIn.getStockQuantity(), stockIn.getScale());
                reorderLimit.setQuantity(stockIn.getReorderLimit(), stockIn.getScale());
                stockPrice.setCashAmount(stockIn.getStockPrice());
                customerPrice.setCashAmount(stockIn.getCustomerPrice());
                gstAmount.setCashAmount(stockIn.getGstAmount());
                netAmount.setCashAmount(stockIn.getNetAmount());
                gst.setGst(stockIn.getGst());
            }
        }

        public void clearForm() {
            stockIn = new StockInDTO();

            if(!hasMoreItem.isSelected()){
                dealerChooser.clearField();
            }

            barcode.clearField();
            name.clearField();
            model.clearField();
            category.clearField();
            company.clearField();
            warranty.clearField();
            guarantee.clearField();
            specifications.clearField();
            stockQuantity.clearField();
            reorderLimit.clearField();
            stockPrice.clearField();
            customerPrice.clearField();
            gstAmount.clearField();
            netAmount.clearField();
            gst.clearField();
        }

        private void calculateGstAmount() {
            BigDecimal stockQty = stockQuantity.getQuantityBigDecimal();
            BigDecimal price = stockPrice.getCashAmountBigDecimal();
            BigDecimal percentage = gst.getGstBigDecimal();
            BigDecimal gstAmount = percentage.multiply(stockQty.multiply(price));
            this.gstAmount.setCashAmount(gstAmount.doubleValue());
        }

        private void calculateNetAmount() {
            BigDecimal stockQty = stockQuantity.getQuantityBigDecimal();
            BigDecimal stockPrc = stockPrice.getCashAmountBigDecimal();
            BigDecimal gstAmnt = gstAmount.getCashAmountBigDecimal();
            BigDecimal netAmnt = stockQty.multiply(stockPrc).add(gstAmnt);
            this.netAmount.setCashAmount(netAmnt.doubleValue());
        }

        @Override
        public void registerListeners() {
            scale.valueProperty().addListener((obs, oldVal, newVal) -> {
                stockQuantity.getField().clearField();
                reorderLimit.getField().clearField();
                stockQuantity.setScale(newVal);
                reorderLimit.setScale(newVal);

                gstAmount.clearField();
                netAmount.clearField();
            });

            this.stockQuantity.getField().textProperty().addListener((obs, oldVal, newVal) -> {
                calculateGstAmount();
                calculateNetAmount();
            });

            stockPrice.textProperty().addListener((obs, oldVal, newVal) -> {
                calculateGstAmount();
                calculateNetAmount();
            });

            gst.textProperty().addListener((obs, oldVal, newVal) -> {
                calculateGstAmount();
            });

            gstAmount.textProperty().addListener((obs, oldVal, newVal) -> {
                calculateNetAmount();
            });
        }

        @Override
        public boolean checkSecurity() {
            return true;
        }
    }
}
