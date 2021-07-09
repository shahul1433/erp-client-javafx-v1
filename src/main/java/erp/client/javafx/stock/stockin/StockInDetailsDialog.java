package erp.client.javafx.stock.stockin;

import erp.client.javafx.component.enums.ProductScale;
import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.component.textfield.currency.IndianRupeesField;
import erp.client.javafx.component.textfield.percentage.GstPercentageField;
import erp.client.javafx.component.textfield.quantity.QuantityField;
import erp.client.javafx.component.viewerfield.DealerViewerField;
import erp.client.javafx.component.viewerfield.UserViewerField;
import erp.client.javafx.container.AbstractDialog;
import erp.client.javafx.container.Arguments;
import erp.client.javafx.container.StageMode;
import erp.client.javafx.layout.AbstractGridPane;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.InputStream;

public class StockInDetailsDialog extends AbstractDialog {

    private StockInDTO stockInDTO;
    private StockInDetailsPane stockInDetailsPane;

    public StockInDetailsDialog(Stage parentStage, StageMode stageMode, Arguments args) {
        super(parentStage, stageMode, args);
    }

    @Override
    protected void init() {
        stockInDTO = getArgument("stockIn", StockInDTO.class);
        stockInDetailsPane = new StockInDetailsPane();
    }

    @Override
    protected Pane designContentGUI() {
        return stockInDetailsPane;
    }

    @Override
    protected Pane designButtonGUI() {
        return new HBox();
    }

    @Override
    protected void registerListeners() {

    }

    @Override
    protected boolean checkSecurity() {
        return true;
    }

    @Override
    protected InputStream getIcon() {
        return getClass().getResourceAsStream("/image/inventory.png");
    }

    @Override
    protected void adjustViewByStageMode() {
        getStage().setTitle("Stock In Details");
    }

    class StockInDetailsPane extends AbstractGridPane {

        CTextField barcode, name, model, category, company, warranty, guarantee, scale;
        UserViewerField addedBy;
        DealerViewerField dealer;
        CTextArea specifications;
        QuantityField stockInQuantity, reOrderLimit, currentQuantity;
        IndianRupeesField stockPrice, customerPrice, gstAmount, netAmount;
        GstPercentageField gstPercentageField;

        @Override
        public void init() {
            barcode = new CTextField("Barcode", false, -1);
            barcode.setReadOnly(true);
            name = new CTextField("Name", false, -1);
            name.setReadOnly(true);
            model = new CTextField("Model", false, -1);
            model.setReadOnly(true);
            category = new CTextField("Category", false, -1);
            category.setReadOnly(true);
            company = new CTextField("Company", false, -1);
            company.setReadOnly(true);
            warranty = new CTextField("Warranty", false, -1);
            warranty.setReadOnly(true);
            guarantee = new CTextField("Guarantee", false, -1);
            guarantee.setReadOnly(true);
            scale = new CTextField("Scale", false, -1);
            scale.setReadOnly(true);

            dealer = new DealerViewerField("Dealer", null, null);
            addedBy = new UserViewerField("Added By", null, null);

            specifications = new CTextArea("Specifications", false, -1);
            specifications.setReadOnly(true);

            stockInQuantity = new QuantityField("Stock Quantity", false, ProductScale.COUNT);
            stockInQuantity.setReadOnly(true);
            currentQuantity = new QuantityField("Current Quantity", false, ProductScale.COUNT);
            currentQuantity.setReadOnly(true);
            reOrderLimit = new QuantityField("Re-Order Limit", false, ProductScale.COUNT);
            reOrderLimit.setReadOnly(true);

            stockPrice = new IndianRupeesField("Stock Price", false);
            stockPrice.setReadOnly(true);
            customerPrice = new IndianRupeesField("Customer Price", false);
            customerPrice.setReadOnly(true);
            gstAmount = new IndianRupeesField("GST Amount", false);
            gstAmount.setReadOnly(true);
            netAmount = new IndianRupeesField("Net Amount", false);
            netAmount.setReadOnly(true);

            gstPercentageField = new GstPercentageField("GST (%)", false);
            gstPercentageField.setReadOnly(true);

            populateFields();
        }

        @Override
        public void designGUI() {
            setHgap(10);
            setVgap(10);
            setPadding(new Insets(25));
            setAlignment(Pos.CENTER);

            int col = 0, row = 0;

            add(barcode.getLabel(), col++, row);
            add(barcode, col++, row);
            add(stockInQuantity.getLabel(), col++, row);
            add(stockInQuantity, col++, row++);

            col = 0;
            add(name.getLabel(), col++, row);
            add(name, col++, row);
            add(currentQuantity.getLabel(), col++, row);
            add(currentQuantity, col++, row++);

            col = 0;
            add(model.getLabel(), col++, row);
            add(model, col++, row);
            add(reOrderLimit.getLabel(), col++, row);
            add(reOrderLimit, col++, row++);

            col = 0;
            add(category.getLabel(), col++, row);
            add(category, col++, row);
            add(stockPrice.getLabel(), col++, row);
            add(stockPrice, col++, row++);

            col = 0;
            add(company.getLabel(), col++, row);
            add(company, col++, row);
            add(customerPrice.getLabel(), col++, row);
            add(customerPrice, col++, row++);

            col = 0;
            add(warranty.getLabel(), col++, row);
            add(warranty, col++, row);
            add(gstAmount.getLabel(), col++, row);
            add(gstAmount, col++, row++);

            col = 0;
            add(guarantee.getLabel(), col++, row);
            add(guarantee, col++, row);
            add(netAmount.getLabel(), col++, row);
            add(netAmount, col++, row++);

            col = 0;
            add(scale.getLabel(), col++, row);
            add(scale, col++, row);
            add(gstPercentageField.getLabel(), col++, row);
            add(gstPercentageField, col++, row++);

            col = 0;
            add(specifications.getLabel(), col++, row);
            add(specifications, col++, row, 1, 3);
            add(dealer.getLabel(), col++, row);
            add(dealer, col++, row++);

            col = 2;
            add(addedBy.getLabel(), col++, row);
            add(addedBy, col++, row);
        }

        public void populateFields() {
            if (stockInDTO != null) {
                barcode.setText(stockInDTO.getBarcode());
                name.setText(stockInDTO.getName());
                model.setText(stockInDTO.getModel());
                category.setText(stockInDTO.getCategory());
                company.setText(stockInDTO.getCompany());
                warranty.setText(stockInDTO.getWarranty());
                guarantee.setText(stockInDTO.getGuarantee());
                scale.setText(stockInDTO.getScale().getName() + " (" + stockInDTO.getScale().getRepresentation() +")");
                specifications.setText(stockInDTO.getSpecifications());

                stockInQuantity.setQuantity(stockInDTO.getStockQuantity(), stockInDTO.getScale());
                currentQuantity.setQuantity(stockInDTO.getCurrentQuantity(), stockInDTO.getScale());
                reOrderLimit.setQuantity(stockInDTO.getReorderLimit(), stockInDTO.getScale());

                stockPrice.setCashAmount(stockInDTO.getStockPrice());
                customerPrice.setCashAmount(stockInDTO.getCustomerPrice());
                gstAmount.setCashAmount(stockInDTO.getGstAmount());
                netAmount.setCashAmount(stockInDTO.getNetAmount());
                gstPercentageField.setGst(stockInDTO.getGst());

                dealer.setDealerDTO(stockInDTO.getDealer());
                addedBy.setUserDTO(stockInDTO.getAddedBy());
            }
        }

        @Override
        public void registerListeners() {

        }

        @Override
        public boolean checkSecurity() {
            return false;
        }
    }
}
