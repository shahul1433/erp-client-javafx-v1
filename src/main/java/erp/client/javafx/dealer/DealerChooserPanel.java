package erp.client.javafx.dealer;

import erp.client.javafx.component.searchbox.dealer.DealerSearchBox;
import erp.client.javafx.component.textfield.CTextArea;
import erp.client.javafx.component.textfield.CTextField;
import erp.client.javafx.entity.TDealer;
import erp.client.javafx.layout.AbstractGridPane;
import erp.client.javafx.utility.PopupUtility;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DealerChooserPanel extends AbstractGridPane {

    private DealerSearchBox searchBar;
    private CTextField name, shop, email, phone, gstin, gstStateCode, balance, addedDate, modifiedDate;
    private CTextArea address;
    private TDealer dealer;

    static NumberFormat currencyInstance = NumberFormat.getCurrencyInstance(new Locale("en", "IN"));

    public DealerChooserPanel() {
    }

    public TDealer getSelectedDealer() {
        return searchBar.getSelectedDealer();
    }

    @Override
    public void init() {
        searchBar = new DealerSearchBox();
        name = new CTextField("Name", false, -1);
        shop = new CTextField("Shop",  false, -1);
        email = new CTextField("Email",  false, -1);
        phone = new CTextField("Phone",  false, -1);
        gstin = new CTextField("GSTIN",  false, -1);
        gstStateCode = new CTextField("GST State Code",  false, -1);
        balance = new CTextField("Balance",  false, -1);
        balance.setAlignment(Pos.CENTER_RIGHT);
        addedDate = new CTextField("Added On",  false, -1);
        modifiedDate = new CTextField("Modified On",  false, -1);
        address = new CTextArea("Address", false, -1);

        name.setEditable(false);
        shop.setEditable(false);
        email.setEditable(false);
        phone.setEditable(false);
        gstin.setEditable(false);
        gstStateCode.setEditable(false);
        balance.setEditable(false);
        addedDate.setEditable(false);
        modifiedDate.setEditable(false);
        address.setEditable(false);
    }

    @Override
    public void designGUI() {
        this.setHgap(10);
        this.setVgap(10);
        this.setAlignment(Pos.CENTER);

        ColumnConstraints emptyColumn = new ColumnConstraints();
        ColumnConstraints stretchedColumn = new ColumnConstraints(100, 350, Double.MAX_VALUE);
        stretchedColumn.setHgrow(Priority.ALWAYS);

        this.getColumnConstraints().addAll(emptyColumn, stretchedColumn, emptyColumn, stretchedColumn, emptyColumn, stretchedColumn);

        int col = 0, row = 0;

        add(searchBar, col, row++, 2, 1);

        add(name.getLabel(), col++, row);
        add(name, col--, row++);

        add(shop.getLabel(), col++, row);
        add(shop, col--, row++);

        add(email.getLabel(), col++, row);
        add(email, col--, row++);

        add(phone.getLabel(), col++, row);
        add(phone, col--, row++);

        add(gstin.getLabel(), col++, row);
        add(gstin, col--, row++);

        col = 2;
        row = 0;

        add(gstStateCode.getLabel(), col++, row);
        add(gstStateCode, col--, row++);

        add(balance.getLabel(), col++, row);
        add(balance, col--, row++);

        add(addedDate.getLabel(), col++, row);
        add(addedDate, col--, row++);

        add(modifiedDate.getLabel(), col++, row);
        add(modifiedDate, col--, row++);

        col = 4;
        row = 0;

        add(address.getLabel(), col++, row);
        add(address, col--, row++, 1, 6);
    }

    private void populateFields() {
        TDealer selectedDealer = searchBar.getSelectedDealer();
        if (selectedDealer != null) {
            name.setText(selectedDealer.getName());
            shop.setText(selectedDealer.getShop());
            email.setText(selectedDealer.getEmail());
            phone.setText(selectedDealer.getPhone());
            gstin.setText(selectedDealer.getGstin());
            gstStateCode.setText(selectedDealer.getGstStateCode() == null ? "" :selectedDealer.getGstStateCode().getCode() + " - " + selectedDealer.getGstStateCode().getState());
            balance.setText(currencyInstance.format(selectedDealer.getBalance()));
            addedDate.setText(selectedDealer.getAddedDate() == null ? "" :selectedDealer.getAddedDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
            modifiedDate.setText(selectedDealer.getModifiedDate() == null ? "" : selectedDealer.getModifiedDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
            address.setText(selectedDealer.getAddress());
        }else {
            name.clearField();
            shop.clearField();
            email.clearField();
            phone.clearField();
            gstin.clearField();
            gstStateCode.clearField();
            balance.clearField();
            addedDate.clearField();
            modifiedDate.clearField();
            address.clearField();
        }
    }

    @Override
    public void registerListeners() {
        searchBar.getCombobox().getSelectionModel().selectedIndexProperty().addListener((obs, oldVal, newVal) -> {
            populateFields();
        });
    }

    public boolean validateDealer() {
        if(getSelectedDealer() == null) {
            PopupUtility.showMessage(Alert.AlertType.WARNING, "Please select a dealer");
            searchBar.getCombobox().getEditor().requestFocus();
        }
        return  getSelectedDealer() != null;
    }

    @Override
    public boolean checkSecurity() {
        return true;
    }
}
