package erp.client.javafx.dealer;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.dealer.card.LedgerTransactionCard;
import erp.client.javafx.dealer.thread.GetLedgerByMonthAndYearAndDealerService;
import erp.client.javafx.dealer.thread.GetLedgerMonthsByYearAndDealerService;
import erp.client.javafx.dealer.thread.GetLedgerTransactionsByLedgerService;
import erp.client.javafx.dealer.thread.GetLedgerYearsByDealerService;
import erp.client.javafx.common.ListValue;
import erp.client.javafx.exception.WorkerStateEventStatusBarExceptionHandler;
import erp.client.javafx.http.Page;
import erp.client.javafx.http.SortMap;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import org.apache.log4j.Logger;

import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DealerDetailsService {

    private final static Logger logger = Logger.getLogger(DealerDetailsService.class);
    private DealerDetailsDialog view;

    public DealerDetailsService(DealerDetailsDialog view) {
        this.view = view;
    }

    public void getLedgerYearsByDealer(DealerDTO dealer) {
        var service = new GetLedgerYearsByDealerService(dealer);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                ListValue<Integer> listValue = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                view.getCenterPanel().topBar.year.getItems().clear();
                view.getCenterPanel().topBar.year.getItems().addAll(listValue.getList());
                if(!listValue.getList().isEmpty()) {
                    view.getCenterPanel().topBar.year.getSelectionModel().selectFirst();
                }
            }
        });
        service.start();
    }

    public void getLedgerMonthsByYearAndDealer() {
        LedgerDTO ledgerDTO = new LedgerDTO();
        ledgerDTO.setDealer(view.getDealerDTO());
        ledgerDTO.setYear(view.getCenterPanel().topBar.year.getSelectionModel().getSelectedItem());

        var service = new GetLedgerMonthsByYearAndDealerService(ledgerDTO);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                ListValue<Month> listValue = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                view.getCenterPanel().topBar.month.getItems().clear();
                Collections.sort(listValue.getList(), Comparator.comparing(Month::getValue));
                view.getCenterPanel().topBar.month.getItems().addAll(listValue.getList());
                if(!listValue.getList().isEmpty()) {
                    view.getCenterPanel().topBar.month.getSelectionModel().selectLast();
                }
            }
        });
        service.start();
    }

    public  void getLedgerByMonthAndYearAndDealer() {
        LedgerDTO ledgerDTO = new LedgerDTO();
        ledgerDTO.setDealer(view.getDealerDTO());
        ledgerDTO.setYear(view.getCenterPanel().topBar.year.getSelectionModel().getSelectedItem());
        ledgerDTO.setMonth(view.getCenterPanel().topBar.month.getSelectionModel().getSelectedItem());

        var service = new GetLedgerByMonthAndYearAndDealerService(ledgerDTO);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                LedgerDTO value = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);
                view.setLedgerDTO(value);
                view.getCenterPanel().topBar.setOpenBalance(value.getOpenBalance());
                view.getCenterPanel().topBar.setClosedBalance(value.getClosedBalance());
                getLedgerTransactions();
            }
        });
        service.start();
    }

    public void getLedgerTransactions() {

        LedgerDTO ledgerDTO = new LedgerDTO();
        ledgerDTO.setLedgerId(view.getLedgerDTO().getLedgerId());

        LedgerTransactionDTO transactionDTO = new LedgerTransactionDTO();
        transactionDTO.setLedger(ledgerDTO);

        int page = view.getCenterPanel().bottomBar.pageNo.getPageNo();
        int size = 50;

        LedgerTransactionFilter filter = new LedgerTransactionFilter(transactionDTO, page, size, new SortMap("ledgerTransactionId", "asc"));

        var service = new GetLedgerTransactionsByLedgerService(filter);
        view.setStatusBarStatus(StatusBarStatus.WORKING);
        service.setOnFailed(new WorkerStateEventStatusBarExceptionHandler(view, view.getStatusBar()));
        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent workerStateEvent) {
                Page<LedgerTransactionDTO> page = service.getValue();
                view.setStatusBarStatus(StatusBarStatus.READY);

                EventHandler<ActionEvent> disableDefaultAction = disableDefaultActionEvent();

                view.getCenterPanel().bottomBar.prev.setDisable(page.isFirst());
                view.getCenterPanel().bottomBar.next.setDisable(page.isLast());

                long totalItems = page.getTotalElements();

                //Setting items info label
                long pageNumber = page.getPageable().getPageNumber();
                long pageSize = page.getPageable().getPageSize();
                long numberOfElements = page.getNumberOfElements();
                long startItem = (pageNumber * pageSize) + 1;
                long endItem = (pageNumber * pageSize) + numberOfElements;
                if(endItem == 0) startItem = 0;
                String info = "Showing " + startItem + " - " + endItem + " of " + totalItems + (totalItems == 1 ? " transaction" : " transactions");
                view.getCenterPanel().bottomBar.info.setText(info);

                //Setting no of pages
                long pages = page.getTotalPages();

                ObservableList<Integer> items = FXCollections.observableArrayList();
                for(int i=0 ; i<pages; i++)
                {
                    items.add(i+1);
                }
                if(items.isEmpty())
                    items.add(1);
                view.getCenterPanel().bottomBar.pageNo.setPageNoItems(items);

                //Populate data to the content pane.
                List<LedgerTransactionCard> cards = page.getContent().stream().map(transactionDTO -> new LedgerTransactionCard(transactionDTO)).collect(Collectors.toList());
                view.getCenterPanel().contentPane.getContentBox().getChildren().clear();
                view.getCenterPanel().contentPane.getContentBox().getChildren().addAll(cards);
            }
        });
        service.start();
    }

    private EventHandler<ActionEvent> disableDefaultActionEvent() {
        return new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                event.consume();
            }
        };
    }
}
