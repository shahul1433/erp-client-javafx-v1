package erp.client.javafx.dealer;

import erp.client.javafx.component.date.DateSearchPanel;
import erp.client.javafx.component.filter.combobox.GSTStateCodeCombobox;
import erp.client.javafx.component.filter.textfield.TextFieldSearch;
import erp.client.javafx.container.tablewithnavigation.AbstractFilterDialog;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class DealerFilterDialog extends AbstractFilterDialog<DealerFilter> {

    private TextFieldSearch name, shop, email, phone, gstin;
    private GSTStateCodeCombobox gstStateCodeCombobox;
    private DateSearchPanel addedDate, modifiedDate;

    public DealerFilterDialog(DealerManagementDialog parent) {
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
        pane.add(shop.getLabel(), col++, row);
        pane.add(shop.getPattern(), col++, row);
        pane.add(shop, col, row++);

        col = 0;
        pane.add(email.getLabel(), col++, row);
        pane.add(email.getPattern(), col++, row);
        pane.add(email, col, row++);

        col = 0;
        pane.add(phone.getLabel(), col++, row);
        pane.add(phone.getPattern(), col++, row);
        pane.add(phone, col, row++);

        col = 0;
        pane.add(gstin.getLabel(), col++, row);
        pane.add(gstin.getPattern(), col++, row);
        pane.add(gstin, col, row++);

        col = 0;
        pane.add(gstStateCodeCombobox.getLabel(), col++, row);
        pane.add(gstStateCodeCombobox, col, row++, 2, 1);

        col = 0;
        pane.add(addedDate, col, row++, 3, 1);
        pane.add(modifiedDate, col, row++, 3, 1);

       return pane;
    }

    @Override
    protected void clearFilterFields() {
        name.clearSearch();
        shop.clearSearch();
        email.clearSearch();
        phone.clearSearch();
        gstin.clearSearch();
        gstStateCodeCombobox.clearSearch();
        addedDate.clearSearch();
        modifiedDate.clearSearch();
    }

    @Override
    protected boolean isValidFilter() {
        try {
            return (
                    name.isValidFilterField() || shop.isValidFilterField() || email.isValidFilterField() || phone.isValidFilterField() ||
                    gstin.isValidFilterField() || gstStateCodeCombobox.isValidFilterField() || addedDate.isValidFilterField() || modifiedDate.isValidFilterField()
                    );
        }catch (Exception e){
            handleException(e);
            return false;
        }
    }

    @Override
    protected void initFilterComponents() {
        name = new TextFieldSearch("Name");
        shop = new TextFieldSearch("Shop");
        email = new TextFieldSearch("Email");
        phone = new TextFieldSearch("Phone");
        gstin = new TextFieldSearch("GSTIN");
        gstStateCodeCombobox = new GSTStateCodeCombobox();
        addedDate = new DateSearchPanel("Added Date");
        modifiedDate = new DateSearchPanel("Modified Date");
    }

    @Override
    public DealerFilter getForm() {
        DealerDTO dealer = new DealerDTO();
        dealer.setArchive(false);
        dealer.setName(this.name.getSearchString());
        dealer.setShop(this.shop.getSearchString());
        dealer.setEmail(this.email.getSearchString());
        dealer.setPhone(this.phone.getSearchString());
        dealer.setGstin(this.gstin.getSearchString());
        dealer.setGstStateCode(this.gstStateCodeCombobox.getSelectedGstStateCode());

        DealerFilter filter = new DealerFilter(dealer, addedDate.getDateSearchable(), modifiedDate.getDateSearchable(), 0, 0, null);
        return filter;
    }
}
