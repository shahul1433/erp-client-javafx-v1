package erp.client.javafx.dealer;

import erp.client.javafx.http.DatabaseTableFilter;
import erp.client.javafx.http.SortMap;

public class LedgerTransactionFilter extends DatabaseTableFilter {

    private LedgerTransactionDTO ledgerTransactionDTO;

    public LedgerTransactionFilter(LedgerTransactionDTO dto, int page, int size, SortMap sortMap) {
        super(page, size, sortMap);
        this.ledgerTransactionDTO = dto;
    }

    public LedgerTransactionDTO getLedgerTransactionDTO() {
        return ledgerTransactionDTO;
    }

    public void setLedgerTransactionDTO(LedgerTransactionDTO ledgerTransactionDTO) {
        this.ledgerTransactionDTO = ledgerTransactionDTO;
    }
}
