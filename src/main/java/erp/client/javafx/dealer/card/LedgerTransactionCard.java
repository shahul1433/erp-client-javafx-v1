package erp.client.javafx.dealer.card;

import erp.client.javafx.component.enums.LedgerTransactionType;
import erp.client.javafx.component.label.CLabel;
import erp.client.javafx.dealer.LedgerTransactionDTO;
import erp.client.javafx.layout.AbstractBorderPane;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

import java.text.NumberFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class LedgerTransactionCard extends AbstractBorderPane {

    private CLabel date, description, amount;
    private BorderPane datePanel, descriptionPanel, amountPanel;
    private LedgerTransactionDTO ledgerTransactionDTO;
    private NumberFormat rupeesCurrency = NumberFormat.getCurrencyInstance(new Locale("eng", "IN"));

    public LedgerTransactionCard(LedgerTransactionDTO ledgerTransactionDTO) {
        this.ledgerTransactionDTO = ledgerTransactionDTO;
        populateData();
        if(ledgerTransactionDTO.getTransactionType() == LedgerTransactionType.DEBIT)
            setDebitCard();
        else
            setCreditCard();
    }

    private void populateData() {
        if(ledgerTransactionDTO != null) {
            date.setText(ledgerTransactionDTO.getDate().format(DateTimeFormatter.ofPattern("dd-MMM-yyyy")));
            description.setText(ledgerTransactionDTO.getDescription());
            amount.setText(rupeesCurrency.format(ledgerTransactionDTO.getAmount()));
        }
    }

    @Override
    public void init() {
        this.getStylesheets().add(LedgerTransactionCard.class.getResource("style.css").toExternalForm());
        setId("card");

        datePanel = new BorderPane();
        datePanel.setId("date-panel");

        descriptionPanel = new BorderPane();
        descriptionPanel.setId("description-panel");

        amountPanel = new BorderPane();
        amountPanel.setId("amount-panel");

        date = new CLabel();
        date.setId("date");
        datePanel.setCenter(date);

        description = new CLabel();
        description.setId("description");
        description.setWrapText(true);
        descriptionPanel.setCenter(description);

        amount = new CLabel();
        amount.setId("amount");
        amountPanel.setCenter(amount);
    }

    public void setCreditCard() {
        descriptionPanel.getStyleClass().add("credit");
        amount.getStyleClass().add("credit-amount");
    }

    public void setDebitCard() {
        descriptionPanel.getStyleClass().add("debit");
        amount.getStyleClass().add("debit-amount");
    }

    @Override
    public void designGUI() {
        setLeft(datePanel);
        setCenter(descriptionPanel);
        setRight(amountPanel);
    }

    @Override
    public void registerListeners() {

    }

    @Override
    public boolean checkSecurity() {
        return false;
    }
}
