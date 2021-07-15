package erp.client.javafx.stock.stockin;

import erp.client.javafx.component.date.DateSearchPanel;
import erp.client.javafx.component.filter.combobox.ProductScaleCombobox;
import erp.client.javafx.component.filter.textfield.DoubleFieldSearch;
import erp.client.javafx.component.filter.textfield.TextFieldSearch;
import erp.client.javafx.container.tablewithnavigation.AbstractFilterDialog;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.user.UserDTO;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class StockInFilterDialog extends AbstractFilterDialog<StockInFilter> {

    private TextFieldSearch name, model, category, company, warranty, guarantee,  specifications, dealer, addedBy;
    DoubleFieldSearch stockQuantity, currentQuantity, reorderLimit, stockPrice, customerPrice, gst, gstAmount, netAmount;
    ProductScaleCombobox scale;
    DateSearchPanel addedDate;

    public StockInFilterDialog(AbstractTableWithNavigationDialog parent) {
        super(parent);
    }

    @Override
    protected Pane designFilterGUI() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25));

        ColumnConstraints emptyColumn = new ColumnConstraints();
        ColumnConstraints stretchedColumn = new ColumnConstraints(100, 150, Double.MAX_VALUE);
        stretchedColumn.setHgrow(Priority.ALWAYS);

        pane.getColumnConstraints().addAll(emptyColumn, emptyColumn, stretchedColumn);

        int col = 0, row = 0;

        pane.add(name.getLabel(), col++, row);
        pane.add(name.getPattern(), col++, row);
        pane.add(name, col, row++);

        col = 0;
        pane.add(model.getLabel(), col++, row);
        pane.add(model.getPattern(), col++, row);
        pane.add(model, col, row++);

        col = 0;
        pane.add(category.getLabel(), col++, row);
        pane.add(category.getPattern(), col++, row);
        pane.add(category, col, row++);

        col = 0;
        pane.add(company.getLabel(), col++, row);
        pane.add(company.getPattern(), col++, row);
        pane.add(company, col, row++);

        col = 0;
        pane.add(warranty.getLabel(), col++, row);
        pane.add(warranty.getPattern(), col++, row);
        pane.add(warranty, col, row++);

        col = 0;
        pane.add(guarantee.getLabel(), col++, row);
        pane.add(guarantee.getPattern(), col++, row);
        pane.add(guarantee, col, row++);

        col = 0;
        pane.add(specifications.getLabel(), col++, row);
        pane.add(specifications.getPattern(), col++, row);
        pane.add(specifications, col, row++);

        col = 0;
        pane.add(dealer.getLabel(), col++, row);
        pane.add(dealer.getPattern(), col++, row);
        pane.add(dealer, col, row++);

        col = 0;
        pane.add(addedBy.getLabel(), col++, row);
        pane.add(addedBy.getPattern(), col++, row);
        pane.add(addedBy, col, row++);

        col = 3;
        row = 0;
        pane.add(stockQuantity.getLabel(), col++, row);
        pane.add(stockQuantity, col++, row++, 2, 1);

        col = 3;
        pane.add(currentQuantity.getLabel(), col++, row);
        pane.add(currentQuantity, col++, row++, 2, 1);

        col = 3;
        pane.add(reorderLimit.getLabel(), col++, row);
        pane.add(reorderLimit, col++, row++, 2, 1);

        col = 3;
        pane.add(stockPrice.getLabel(), col++, row);
        pane.add(stockPrice, col++, row++, 2, 1);

        col = 3;
        pane.add(customerPrice.getLabel(), col++, row);
        pane.add(customerPrice, col++, row++, 2, 1);

        col = 3;
        pane.add(gst.getLabel(), col++, row);
        pane.add(gst, col++, row++, 2, 1);

        col = 3;
        pane.add(gstAmount.getLabel(), col++, row);
        pane.add(gstAmount, col++, row++, 2, 1);

        col = 3;
        pane.add(netAmount.getLabel(), col++, row);
        pane.add(netAmount, col++, row++, 2, 1);

        col = 3;
        pane.add(scale.getLabel(), col++, row);
        pane.add(scale, col++, row++, 2, 1);

        col = 0;
        row = row+1;
        pane.add(addedDate, col++, row++,3, 1);

        return pane;
    }

    @Override
    protected void clearFilterFields() {
        name.clearSearch();
        model.clearSearch();
        category.clearSearch();
        company.clearSearch();
        warranty.clearSearch();
        guarantee.clearSearch();
        stockQuantity.clearSearch();
        currentQuantity.clearSearch();
        reorderLimit.clearSearch();
        stockPrice.clearSearch();
        customerPrice.clearSearch();
        gst.clearSearch();
        gstAmount.clearSearch();
        netAmount.clearSearch();
        scale.clearSearch();
        specifications.clearSearch();
        dealer.clearSearch();
        addedBy.clearSearch();
        addedDate.clearSearch();
    }

    @Override
    protected boolean isValidFilter() {
        try {
            return (
                    name.isValidFilterField() || model.isValidFilterField() || category.isValidFilterField() || company.isValidFilterField() ||
                            warranty.isValidFilterField() || guarantee.isValidFilterField() || stockQuantity.isValidFilterField() ||
                            currentQuantity.isValidFilterField() || reorderLimit.isValidFilterField() || stockPrice.isValidFilterField() ||
                            customerPrice.isValidFilterField() || gst.isValidFilterField() || gstAmount.isValidFilterField() ||
                            netAmount.isValidFilterField() || scale.isValidFilterField() || specifications.isValidFilterField() || dealer.isValidFilterField() ||
                            addedBy.isValidFilterField() || addedDate.isValidFilterField()
                    );
        } catch (Exception exception) {
            handleException(exception);
            return false;
        }
    }

    @Override
    protected void initFilterComponents() {
        name = new TextFieldSearch("Name");
        model = new TextFieldSearch("Model");
        category = new TextFieldSearch("Category");
        company = new TextFieldSearch("Company");
        warranty = new TextFieldSearch("Warranty");
        guarantee = new TextFieldSearch("Guarantee");
        stockQuantity = new DoubleFieldSearch("Stock Quantity");
        currentQuantity = new DoubleFieldSearch("Current Quantity");
        reorderLimit = new DoubleFieldSearch("Reorder Limit");
        stockPrice = new DoubleFieldSearch("Stock Price");
        customerPrice = new DoubleFieldSearch("Customer Price");
        gst = new DoubleFieldSearch("GST (%)");
        gstAmount = new DoubleFieldSearch("GST Amount");
        netAmount = new DoubleFieldSearch("Net Amount");
        specifications = new TextFieldSearch("Specifications");
        dealer = new TextFieldSearch("Dealer");
        addedBy = new TextFieldSearch("Added By");
        addedDate = new DateSearchPanel("Added Date");

        scale = new ProductScaleCombobox();
    }

    @Override
    public StockInFilter getForm() {

        StockInDTO stockIn = new StockInDTO();
        stockIn.setArchive(false);
        stockIn.setName(name.getSearchString());
        stockIn.setModel(model.getSearchString());
        stockIn.setCategory(category.getSearchString());
        stockIn.setCompany(company.getSearchString());
        stockIn.setWarranty(warranty.getSearchString());
        stockIn.setGuarantee(guarantee.getSearchString());
        stockIn.setStockQuantity(stockQuantity.getSearchValue());
        stockIn.setCurrentQuantity(currentQuantity.getSearchValue());
        stockIn.setReorderLimit(reorderLimit.getSearchValue());
        stockIn.setStockPrice(stockPrice.getSearchValue());
        stockIn.setCustomerPrice(customerPrice.getSearchValue());
        stockIn.setGst(gst.getSearchValue());
        stockIn.setGstAmount(gstAmount.getSearchValue());
        stockIn.setNetAmount(netAmount.getSearchValue());
        stockIn.setSpecifications(specifications.getSearchString());
        stockIn.setScale(scale.getSelectedScale());

        DealerDTO dealerObj = new DealerDTO();
        dealerObj.setArchive(false);
        dealerObj.setName(dealer.getSearchString());
        stockIn.setDealer(dealerObj);

        UserDTO user = new UserDTO();
        user.setArchive(false);
        user.setName(addedBy.getSearchString());
        stockIn.setAddedBy(user);

        StockInFilter filter = new StockInFilter(stockIn,addedDate.getDateSearchable(), 0, 0, null);
        return filter;
    }
}
