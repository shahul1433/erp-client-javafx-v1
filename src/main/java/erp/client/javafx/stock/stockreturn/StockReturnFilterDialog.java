package erp.client.javafx.stock.stockreturn;

import erp.client.javafx.component.date.DateSearchPanel;
import erp.client.javafx.component.filter.textfield.DoubleFieldSearch;
import erp.client.javafx.component.filter.textfield.TextFieldSearch;
import erp.client.javafx.container.tablewithnavigation.AbstractFilterDialog;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.dealer.DealerDTO;
import erp.client.javafx.stock.stockin.StockInDTO;
import erp.client.javafx.user.UserDTO;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class StockReturnFilterDialog extends AbstractFilterDialog<StockReturnFilter> {

    private TextFieldSearch name, model, category, company, warranty, guarantee,  specifications, dealer, stockInAddedBy, stockReturnAddedBy, reason;
    private DoubleFieldSearch returnQuantity, refundAmount;
    private DateSearchPanel addedDate;

    public StockReturnFilterDialog(AbstractTableWithNavigationDialog parent) {
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
        pane.add(stockInAddedBy.getLabel(), col++, row);
        pane.add(stockInAddedBy.getPattern(), col++, row);
        pane.add(stockInAddedBy, col, row++);

        col = 0;
        pane.add(stockReturnAddedBy.getLabel(), col++, row);
        pane.add(stockReturnAddedBy.getPattern(), col++, row);
        pane.add(stockReturnAddedBy, col, row++);

        col = 0;
        pane.add(reason.getLabel(), col++, row);
        pane.add(reason.getPattern(), col++, row);
        pane.add(reason, col, row++);

        col = 0;
        pane.add(returnQuantity.getLabel(), col++, row);
        pane.add(returnQuantity, col++, row++, 2, 1);

        col = 0;
        pane.add(refundAmount.getLabel(), col++, row);
        pane.add(refundAmount, col++, row++, 2, 1);

        col = 0;
        pane.add(addedDate, col++, row++, 3, 1);

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
        specifications.clearSearch();
        dealer.clearSearch();
        stockInAddedBy.clearSearch();
        stockReturnAddedBy.clearSearch();
        reason.clearSearch();
        returnQuantity.clearSearch();
        refundAmount.clearSearch();
        addedDate.clearSearch();
    }

    @Override
    protected boolean isValidFilter() {
        try {
            return (
                    name.isValidFilterField() || model.isValidFilterField() || category.isValidFilterField() || company.isValidFilterField() ||
                            warranty.isValidFilterField() || guarantee.isValidFilterField() || specifications.isValidFilterField() ||
                            dealer.isValidFilterField() || stockInAddedBy.isValidFilterField() || stockReturnAddedBy.isValidFilterField() ||
                            reason.isValidFilterField() || returnQuantity.isValidFilterField() || refundAmount.isValidFilterField() ||
                            addedDate.isValidFilterField()
                    );
        }catch (Exception e) {
            handleException(e);
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
        specifications = new TextFieldSearch("Specifications");
        dealer = new TextFieldSearch("Dealer");
        stockInAddedBy = new TextFieldSearch("Stock In Added By");
        stockReturnAddedBy = new TextFieldSearch("Stock Return Added By");
        reason = new TextFieldSearch("Reason");

        returnQuantity = new DoubleFieldSearch("Return Quantity");
        refundAmount = new DoubleFieldSearch("Refund Amount");

        addedDate = new DateSearchPanel("Added Date");
    }

    @Override
    public StockReturnFilter getForm() {
        StockReturnDTO stockreturn = new StockReturnDTO();
        stockreturn.setArchive(false);

        StockInDTO stockIn = new StockInDTO();
        stockIn.setArchive(false);
        stockIn.setName(name.getSearchString());
        stockIn.setModel(model.getSearchString());
        stockIn.setCategory(category.getSearchString());
        stockIn.setCompany(company.getSearchString());
        stockIn.setWarranty(warranty.getSearchString());
        stockIn.setGuarantee(guarantee.getSearchString());
        stockIn.setSpecifications(specifications.getSearchString());

        DealerDTO dealerObj = new DealerDTO();
        dealerObj.setArchive(false);
        dealerObj.setName(dealer.getSearchString());
        stockIn.setDealer(dealerObj);

        UserDTO stockInAddedByUser = new UserDTO();
        stockInAddedByUser.setArchive(false);
        stockInAddedByUser.setName(stockInAddedBy.getSearchString());
        stockIn.setAddedBy(stockInAddedByUser);

        stockreturn.setStockIn(stockIn);
        UserDTO stockReturnAddedByUser = new UserDTO();
        stockReturnAddedByUser.setArchive(false);
        stockReturnAddedByUser.setName(stockReturnAddedBy.getSearchString());
        stockreturn.setAddedBy(stockReturnAddedByUser);


        StockReturnFilter filter = new StockReturnFilter(stockreturn, addedDate.getDateSearchable(), 0, 0, null);
        return filter;
    }
}
