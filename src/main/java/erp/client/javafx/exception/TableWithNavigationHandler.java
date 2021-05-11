package erp.client.javafx.exception;

import erp.client.javafx.container.status.StatusBarStatus;
import erp.client.javafx.container.tablewithnavigation.AbstractTableWithNavigationDialog;
import erp.client.javafx.http.Page;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public abstract class TableWithNavigationHandler<T, S> implements EventHandler<WorkerStateEvent> {

    private Service<Page<T>> service;
    private AbstractTableWithNavigationDialog<S> view;

    public TableWithNavigationHandler(Service<Page<T>> service, AbstractTableWithNavigationDialog<S> view) {
        this.service = service;
        this.view = view;
    }

    @Override
    public void handle(WorkerStateEvent workerStateEvent) {
        Page<T> page = service.getValue();
        view.getBottomBar().getPrevious().setDisable(page.isFirst());
        view.getBottomBar().getNext().setDisable(page.isLast());

        long totalItems = page.getTotalElements();

        //Setting items info label
        long pageNumber = page.getPageable().getPageNumber();
        long pageSize = page.getPageable().getPageSize();
        long numberOfElements = page.getNumberOfElements();
        long startItem = (pageNumber * pageSize) + 1;
        long endItem = (pageNumber * pageSize) + numberOfElements;
        if(endItem == 0) startItem = 0;
        String info = "Showing "+startItem+" - "+endItem+" of "+totalItems;
        view.getBottomBar().getInfo().setText(info);

        //Setting no of pages
        long pages = page.getTotalPages();

        ObservableList<Integer> items = FXCollections.observableArrayList();
        for(int i=0 ; i<pages; i++)
        {
            items.add(i+1);
        }
        if(items.isEmpty())
            items.add(1);
        view.getBottomBar().getPageNo().setPageNoItems(items);

        //Populate data to the table.
        view.getCenterPane().getTable().getItems().clear();
//        page.getContent().forEach(userObj -> view.getCenterPane().getTable().getItems().add(new S(userObj)));
//        view.getCenterPane().getTable().getItems().addAll(valueList);
        setData(page, view);
        view.setStatusBarStatus(StatusBarStatus.READY);
    }

    public abstract void setData(Page<T> page, AbstractTableWithNavigationDialog<S> view);
}
