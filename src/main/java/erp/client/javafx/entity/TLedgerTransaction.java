package erp.client.javafx.entity;

import erp.client.javafx.component.enums.LedgerTransactionType;

import java.time.LocalDateTime;
import java.util.Objects;

public class TLedgerTransaction {

    private Long id;
    private String description;
    private LocalDateTime date;
    private Double amount;
    private LedgerTransactionType transactionType;

    public TLedgerTransaction() {

    }

    public TLedgerTransaction(String description, LocalDateTime date, Double amount, LedgerTransactionType transactionType) {
        this.description = description;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "TLedgerTransaction{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", amount=" + amount +
                ", transactionType=" + transactionType +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TLedgerTransaction that = (TLedgerTransaction) o;
        return Objects.equals(id, that.id) && Objects.equals(description, that.description) && Objects.equals(date, that.date) && Objects.equals(amount, that.amount) && transactionType == that.transactionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, date, amount, transactionType);
    }
}
