package erp.client.javafx.dealer;

import java.time.Month;
import java.util.Objects;

public class LedgerDTO {

    private Long ledgerId;
    private Integer year;
    private Month month;
    private Double openBalance;
    private Double closedBalance;
    private DealerDTO dealer;

    public LedgerDTO() {

    }

    public LedgerDTO(Long ledgerId, Integer year, Month month, Double openBalance, Double closedBalance, DealerDTO dealer) {
        this.ledgerId = ledgerId;
        this.year = year;
        this.month = month;
        this.openBalance = openBalance;
        this.closedBalance = closedBalance;
        this.dealer = dealer;
    }

    public Long getLedgerId() {
        return ledgerId;
    }

    public void setLedgerId(Long ledgerId) {
        this.ledgerId = ledgerId;
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

    public DealerDTO getDealer() {
        return dealer;
    }

    public void setDealer(DealerDTO dealer) {
        this.dealer = dealer;
    }

    @Override
    public String toString() {
        return "LedgerDTO{" +
                "ledgerId=" + ledgerId +
                ", year=" + year +
                ", month=" + month +
                ", openBalance=" + openBalance +
                ", closedBalance=" + closedBalance +
                ", dealer=" + dealer +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof LedgerDTO)) return false;
        LedgerDTO ledgerDTO = (LedgerDTO) o;
        return getLedgerId().equals(ledgerDTO.getLedgerId()) && getYear().equals(ledgerDTO.getYear()) && getMonth() == ledgerDTO.getMonth() && getOpenBalance().equals(ledgerDTO.getOpenBalance()) && getClosedBalance().equals(ledgerDTO.getClosedBalance()) && getDealer().equals(ledgerDTO.getDealer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getLedgerId(), getYear(), getMonth(), getOpenBalance(), getClosedBalance(), getDealer());
    }
}
