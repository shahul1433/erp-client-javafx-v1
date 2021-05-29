package erp.client.javafx.entity;

import java.time.Month;
import java.util.List;
import java.util.Objects;

public class TLedger {

    private Long id;
    private Integer year;
    private Month month;
    private Double openBalance;
    private Double closedBalance;
    private List<TLedgerTransaction>transactions;

    public TLedger() {

    }

    public TLedger(Integer year, Month month, Double openBalance, Double closedBalance, List<TLedgerTransaction> transactions) {
        this.year = year;
        this.month = month;
        this.openBalance = openBalance;
        this.closedBalance = closedBalance;
        this.transactions = transactions;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Double getOpenBalance() {
        return openBalance;
    }

    public void setOpenBalance(Double openBalance) {
        this.openBalance = openBalance;
    }

    public Double getClosedBalance() {
        return closedBalance;
    }

    public void setClosedBalance(Double closedBalance) {
        this.closedBalance = closedBalance;
    }

    public List<TLedgerTransaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TLedgerTransaction> transactions) {
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "TLedger{" +
                "id=" + id +
                ", year=" + year +
                ", month=" + month +
                ", openBalance=" + openBalance +
                ", closedBalance=" + closedBalance +
                ", transactions=" + transactions +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TLedger tLedger = (TLedger) o;
        return Objects.equals(id, tLedger.id) && Objects.equals(year, tLedger.year) && month == tLedger.month && Objects.equals(openBalance, tLedger.openBalance) && Objects.equals(closedBalance, tLedger.closedBalance) && Objects.equals(transactions, tLedger.transactions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, year, month, openBalance, closedBalance, transactions);
    }
}
