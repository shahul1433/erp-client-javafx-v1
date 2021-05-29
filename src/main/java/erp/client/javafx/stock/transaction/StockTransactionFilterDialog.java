package erp.client.javafx.stock.transaction;

import erp.client.javafx.component.date.DateSearchPanel;
import erp.client.javafx.component.filter.combobox.StockTransactionTypeCombobox;
import erp.client.javafx.component.filter.textfield.TextFieldSearch;
import erp.client.javafx.container.tablewithnavigation.AbstractFilterDialog;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.entity.TStockIn;
import erp.client.javafx.entity.TStockTransaction;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class StockTransactionFilterDialog extends AbstractFilterDialog<StockTransactionFilter> {

    TextFieldSearch name, model, category, company, specifications;
    StockTransactionTypeCombobox transactionType;
    DateSearchPanel addedDate;

    public StockTransactionFilterDialog(AbstractTableWithNavigationDialog parent) {
        super(parent);
    }

    @Override
    protected Pane designFilterGUI() {
        GridPane pane = new GridPane();
        pane.setHgap(10);
        pane.setVgap(10);
        pane.setPadding(new Insets(25));

        ColumnConstraints emptyColumn = new ColumnConstraints();
        ColumnConstraints userTypeConstraint = new ColumnConstraints(100, 150, Double.MAX_VALUE);
        userTypeConstraint.setHgrow(Priority.ALWAYS);

        pane.getColumnConstraints().addAll(emptyColumn, emptyColumn, userTypeConstraint);

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
        pane.add(specifications.getLabel(), col++, row);
        pane.add(specifications.getPattern(), col++, row);
        pane.add(specifications, col, row++);

        col = 0;
        pane.add(transactionType.getLabel(), col++, row);
        pane.add(transactionType, col, row++, 2, 1);

        col = 0;
        pane.add(addedDate, col++, row, 3, 2);
        return pane;
    }

    @Override
    protected void clearFilterFields() {
        name.clearSearch();
        model.clearSearch();
        category.clearSearch();
        company.clearSearch();
        specifications.clearSearch();
        transactionType.clearSearch();
        addedDate.clearSearch();
    }

    @Override
    protected boolean isValidFilter() {
        try {
            return (
                    name.isValidFilterField() || model.isValidFilterField() || category.isValidFilterField() || company.isValidFilterField() ||
                    specifications.isValidFilterField() || transactionType.isValidFilterField() || addedDate.isValidFilterField()
            );
        }catch (Exception e) {
            handleException(e);
            return false;
        }
    }

    @Override
    protected void initFilterComponents() {
        this.name = new TextFieldSearch("Name");
        this.model = new TextFieldSearch("Model");
        this.category = new TextFieldSearch("Category");
        this.company = new TextFieldSearch("Company");
        this.specifications = new TextFieldSearch("Specifications");
        this.transactionType = new StockTransactionTypeCombobox();
        this.addedDate = new DateSearchPanel("Added Date");
    }

    @Override
    public StockTransactionFilter getForm() {
        TStockTransaction transaction = new TStockTransaction();
        transaction.setStockTransactionType(transactionType.getSelectedStockTransactionType());
        TStockIn stockIn = new TStockIn();
        stockIn.setArchive(false);
        stockIn.setName(name.getSearchString());
        stockIn.setModel(model.getSearchString());
        stockIn.setCategory(category.getSearchString());
        stockIn.setCompany(company.getSearchString());
        stockIn.setSpecifications(specifications.getSearchString());
        transaction.setStockIn(stockIn);

        StockTransactionFilter filter = new StockTransactionFilter(transaction, addedDate.getDateSearchable(),0, 0, null);
        return filter;
    }
}
