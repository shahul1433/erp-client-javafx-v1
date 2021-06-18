package erp.client.javafx.dealer;

import erp.client.javafx.component.enums.LedgerTransactionType;

import java.time.LocalDateTime;
import java.util.Objects;

public class LedgerTransactionDTO {

    private Long ledgerTransactionId;
    private String description;
    private LocalDateTime date;
    private Double amount;
    private LedgerTransactionType transactionType;
    private LedgerDTO ledger;

    public LedgerTransactionDTO() {
    }

    public LedgerTransactionDTO(Long ledgerTransactionId, String description, LocalDateTime date, Double amount, LedgerTransactionType transactionType, LedgerDTO ledger) {
        this.ledgerTransactionId = ledgerTransactionId;
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.ledger = ledger;
    }

    public Long getLedgerTransactionId() {
        return ledgerTransactionId;
    }

    public void setLedgerTransactionId(Long ledgerTransactionId) {
        this.ledgerTransactionId = ledgerTransactionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LedgerTransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(LedgerTransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public LedgerDTO getLedger() {
        return ledger;
    }

    public void setLedger(LedgerDTO ledger) {
        this.ledger = ledger;
    }

    @Override
    public String toString() {
        return "LedgerTransactionDTO{" +
                "ledgerTransactionId=" + ledgerTransactionId +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                ", ledger=" + ledger +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LedgerTransactionDTO)) return false;
        LedgerTransactionDTO that = (LedgerTransactionDTO) o;
        return getLedgerTransactionId().equals(that.getLedgerTransactionId()) && getDescription().equals(that.getDescription()) && getDate().equals(that.getDate()) && getAmount().equals(that.getAmount()) && getTransactionType() == that.getTransactionType() && getLedger().equals(that.getLedger());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLedgerTransactionId(), getDescription(), getDate(), getAmount(), getTransactionType(), getLedger());
    }
}
